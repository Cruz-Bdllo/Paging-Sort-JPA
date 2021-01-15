package com.codexy.app.repository;

import com.codexy.app.entities.Brand;
import com.codexy.app.entities.MadeIn;
import com.codexy.app.entities.Product;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Clase que funciona como repositorio ya que construye y realiza consultas personalizadas
 * de acuerdo a los criterios de búsqueda del cliente especificados mediante la URL.
 */
@Repository
public class ProductRepositoryCriteria {

    /* ~ PROPERTIES
    ======================================= */

    // Nombre de las propiedades definidas en la clase Producto la cual une tablas mediante INNER JOIN
    private final String BRAND = "brand";
    private final String MADE_IN = "madein";

    private EntityManager entityManager; // Persistence
    private CriteriaBuilder criteriaBuilder;


    /* ~ CONSTRUCTOR
    ======================================= */
    public ProductRepositoryCriteria(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }


    /* ~ METHODS
    ======================================= */

    /**
     * Método que realiza una consulta personalizada de acuerdo a los criterios de búsqueda definidos por el usuario,
     * Creando uniones (JOINS) si asi se requiere.
     * @param page Clase que contiene las configuraciones de como retornar los resultados (ordenamiento, dirección).
     * @param productSearchCriteria Clase que contiene los criterios de búsqueda que puede usar el cliente (brand,
     *                              madeIn, etc).
     * @return {@link Page} Que contiene los resultados de la búsqueda de manera paginada.
     */
    public Page<Product> getProductsWithCriteria(ProductPage page,
                                          ProductSearchCriteria productSearchCriteria) {
        // Que tipo de dato tendrá como resultado
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // Sobre que entidad se hara la consulta
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        // Indicamos que tablas se relacionan con la consulta mediante un INNER JOIN
        // Permitiendo realizar la unión y definirle un alias para acceder a sus campos
        Join<Product, Brand> joinBrand = productRoot.join(BRAND, JoinType.INNER);
        Join<Product, MadeIn> joinMadeIn = productRoot.join(MADE_IN, JoinType.INNER);

        // Definimos el predicado -> criterios de búsqueda que se agregaran a la consulta
        // Obtenemos los criterios (filtros -> clausulas)
        Predicate productPredicate = getPredicate(productRoot, productSearchCriteria, joinBrand, joinMadeIn);

        // Agregamos la clausula Where a nuestros criterios de búsqueda
        criteriaQuery.where(productPredicate);

        // Ordenamos nuestros resultados de acuerdo a ProductPage
        setOrder(page, criteriaQuery, productRoot);

        // Realizamos la consulta a la BD.
        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        // Adecuamos los resultados de acuerdo a las configuraciones de la Pagina
        query.setFirstResult(page.getPageNumber() * page.getPageSize());
        query.setMaxResults(page.getPageSize());

        // Creamos el Pageable
        Pageable productPageable = getPageable(page);

        // Determinar cuantos resultados se han encontrado una vez aplicados los criterios de búsqueda,
        // para agregarlos a la clase Page.
        long countProducts = getCountProducts(joinBrand, joinMadeIn, productPredicate);

        // Creamos un Objeto Page para retornarlo
        return new PageImpl<Product>(query.getResultList(), productPageable, countProducts);

    } // end method


    /**
     * Método que realiza una consulta para obtener el número de resultados una vez aplicados los criterios de búsqueda.
     * @param joinBrand Require la unión de la tabla {@link Brand}
     * @param joinMadeIn Require la unión de la tabla {@link MadeIn}
     * @param productPredicate Contiene los criterios de búsqueda.
     * @return numero de resultados.
     */
    private long getCountProducts(Join<Product, Brand> joinBrand,
                                  Join<Product, MadeIn> joinMadeIn,
                                  Predicate productPredicate) {
        CriteriaQuery<Long> queryCount = criteriaBuilder.createQuery(Long.class);
        Root<Product> countRoot = queryCount.from(Product.class);
        // Inners
        joinBrand = countRoot.join(BRAND, JoinType.INNER);
        joinMadeIn = countRoot.join(MADE_IN, JoinType.INNER);
        queryCount.select(criteriaBuilder.count(countRoot))
                .where(productPredicate);

        return entityManager.createQuery(queryCount).getSingleResult();
    }


    /**
     * Construye los criterios de búsqueda mediante conjunción.
     * @param productRoot Entidad sobre la cual se realizara la consulta.
     * @param productSearchCriteria Criterios que requiere el usuario.
     * @param joinBrand Unión con la tabla {@link Brand}
     * @param joinMadeIn Unión con la tabla {@link MadeIn}
     * @return Construcción única de todos los criterios de consulta.
     */
    private Predicate getPredicate(Root<Product> productRoot,
                                   ProductSearchCriteria productSearchCriteria,
                                   Join<Product, Brand> joinBrand, Join<Product, MadeIn> joinMadeIn) {

        List<Predicate> predicates = new ArrayList<>();

        /*
        Evaluamos cada criterio de búsqueda que el usuario haya solicitado ya sea:
        brand | madeIn | greaterThan | lessThan
        * */
        if(Objects.nonNull(productSearchCriteria.getBrand())) { // Crea la clausula -> Predicate
            predicates.add(
                    criteriaBuilder.like(joinBrand.get("name"), "%"+productSearchCriteria.getBrand()+"%")
            );
        }
        if(Objects.nonNull(productSearchCriteria.getMadeIn())) { // Crea la clausula -> Predicate
            predicates.add(
                    criteriaBuilder.like(joinMadeIn.get("name"), "%"+productSearchCriteria.getMadeIn()+"%")
            );
        }
        if(Objects.nonNull(productSearchCriteria.getGreaterThan())) { // Crea la clausula -> Predicate
            Predicate predicateGranter =
                    criteriaBuilder.greaterThan(productRoot.get("price"), productSearchCriteria.getGreaterThan());
            predicates.add(predicateGranter);
        }
        if(Objects.nonNull(productSearchCriteria.getLessThan())) { // Crea la clausula -> Predicate
            Predicate predicateLess =
                    criteriaBuilder.lessThan(productRoot.get("price"), productSearchCriteria.getLessThan());
            predicates.add(predicateLess);
        }
        // Retornamos un único Predicate
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    } // end method


    /**
     * Creamos y retornamos las configuraciones sobre los resultados indicados por el cliente ya sea
     * la cantidad de elementos por página, ordenamiento y dirección.
     * @param page Clase que contiene la configuración de la página.
     * @return Paginado con las configuraciones.
     */
    private Pageable getPageable(ProductPage page) {
        Sort sort = Sort.by(page.getDirectionBy(), page.getSortBy());
        return PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
    }

    /**
     * Ordenamos nuestros resultados de acuerdo al cliente.
     * @param page Clase que contiene la configuración de la página.
     * @param criteriaQuery Criterios de búsqueda.
     * @param productRoot Entidad sobre la que se hace la consulta.
     */
    private void setOrder(ProductPage page,
                          CriteriaQuery<Product> criteriaQuery,
                          Root<Product> productRoot) {
        if (Objects.equals(page.getDirectionBy(), Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(productRoot.get(page.getSortBy())));
        }else {
            criteriaQuery.orderBy(criteriaBuilder.desc(productRoot.get(page.getSortBy())));
        }
    } // end method


} // end repository

# Spring Boot
## Paginación, Ordenación y criterios de busqueda en registros
### Objetivo
Permitir al cliente obtener resultados de una petición de manera paginada de tal forma que al realizar una petición no regrese todos los registros de manera abrupta.
Como resultado los tendrá por "paginas" y podrá seleccionar cuantos elementos mostrar por cada una, a su vez podrá hacer busquedas aplicando un conjunto de criterios que retornara solo aquellos que lo cumplan.
### Caso
Teniendo una base de datos realizada en **MariaDB** contendrá las entidades siguientes:
- Productos.
- Marcas.
- Pais de elaboración.
La tabla **Productos** Almacenara información de celulares a los cuales se le relacionan la tabla **Marcas** y **Pais de elaboración** con el fin de normalizar un poco la misma.

![diagram-er][er]

### Posibles consultas
Sabemos que de como usuarios podriamos querer consultar la información de los celulares con ciertas reestricciones ya sean simples o un poco mas complejas, como podrian ser:
- Consultar los celulares que se han hecho en cierto pais.
- Consultar los celulares que se han hecho en cierto pais y que el nombre de la marca comience con X letra.
- Consultar los celulares que sean de X marca pero que su precio sea menor a Y cantidad.
- Consultar los celulares que sean de X marca, hecho en Y pais sean menor a Z cantidad y esten ordenados de manera ascendente de acuerdo a X campo.
- ETC.
Las consultas facilmente se podrian realizar con el uso de INNER JOIN atravez de un SGBD, pero como podríamos realizarlas usando Spring Data JPA, en este repositorio se tratara de hacer esta pequeña práctica.

### Resultados
- Consultar los celulares que se han hecho en cierto pais.
![query-1][img1]

- Consultar los celulares que se han hecho en cierto pais y que el nombre de la marca comience con X letra.
![query-2][img2]

- Consultar los celulares que sean de X marca pero que su precio sea menor a Y cantidad.
![query-3][img3]

- Consultar los celulares que sean de X marca, hecho en Y pais sean menor a Z cantidad y esten ordenados de manera ascendente de acuerdo a X campo.
![query-4][img4]


### Notas
El repositorio contiene un archivo llamado **create.sql** dentro de la carpeta __Scripts__ en ella tiene la definición de la Base de datos y las tablas necesarias para correr el repositorio asi como registros de prueba. 

[er]: https://raw.githubusercontent.com/Cruz-Bdllo/Paging-Sort-JPA/master/Diagrams/diagram.png "Diagrama de Productos"
[img1]: https://raw.githubusercontent.com/Cruz-Bdllo/Paging-Sort-JPA/master/images/query1.png "Solución a la consulta 1"
[img2]: https://raw.githubusercontent.com/Cruz-Bdllo/Paging-Sort-JPA/master/images/query2.png "Solución a la consulta 2"
[img3]: https://raw.githubusercontent.com/Cruz-Bdllo/Paging-Sort-JPA/master/images/query3.png "Solución a la consulta 3"
[img4]: https://raw.githubusercontent.com/Cruz-Bdllo/Paging-Sort-JPA/master/images/query4.png "Solución a la consulta 4"





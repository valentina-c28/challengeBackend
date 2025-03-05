Este proyecto consiste de una API que recibe peticiones HTTP referentes a una base de datos que contiene usuarios, con sus respectivos nombres, emails, id y fechas de ingreso. 
La API será la encargada de validar los datos de entrada y hacer la eliminación por ID e inserción de dichos usuarios en la base de datos, así como también devolver una lista de los 
usuarios almacenados. 
Para resolver este ejercicio decidi utilizar Spring Boot, ya que Java es un lenguaje con el que estoy más familiarizada. Para almacenar los usuarios cree una base de datos llamada 
"challenge". El sistema de gestión de base de datos que elegí es PostgreSQL y la API fue documentada con Postman.
Al ejecutar la API esta se conectará a la base de datos, creará la tabla "usuarios" y la inicializará con usuarios ficticios, dejandola lista para probarla. 
Las pruebas de las peticiones DELETE y POST se realizaron a través de Postman. 

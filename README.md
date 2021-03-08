# MeLi Mutant REST API
## Tabla de contenido
1. [Información general](#general-info)
2. [Tecnologías](#technologies)
3. [Instalación](#installation)
### Información general
***
REST API para la validación de cadenas de DNA 

#### Metodo POST /mutant

Se recibe como parámetro un array de Strings que representan cada fila de una tabla
de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las
cuales representa cada base nitrogenada del ADN:

({"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]})

A T G C G A--------------------**A** T G C **G** A

C A G T G C--------------------C **A** G T **G** C

T T A T T T---------------------T T **A** T **G** T

A G A C G G--------------------A G A **A** **G** G

G C G T C A--------------------**C C C C** T **A**

T C A C T G--------------------T C A C T G

No-Mutante--------------------Mutante 

Se sabe si un humano es mutante, si se encuentra más de una secuencia de cuatro letras
iguales, de forma oblicua, horizontal o vertical.

#### Metodo GET /mutant/stats

Devuelve un Json con las estadísticas de las verificaciones de ADN: 
{"count_mutant_dna":40, "count_human_dna":100, "ratio":0.4}

## Tecnologías
***
* [Java](https://www.java.com/): Version 1.8 
* [Spring Boot](https://spring.io/): Version 2.4.3
## Instalación
***
Para ejecutar la aplicacion se deben seguir los siguientes pasos: 
```
1. clonar la aplicación -> git clone https://github.com/falofo/meliTest.git
2. Ingresar a la carpeta meliTest -> cd meliTest
3. Crear una tabla en DynamoDb llamada "mutant" con una columna "id" de tipo String y una columba "mutant" de tipo numerico
4. Actualizar los parametros amazon.dynamodb.endpoint, amazon.aws.accesskey, amazon.aws.secretkey en el archivo application.properties con los valores de conexión a AWS
5. Compilar la aplicación -> mvn clean install
6. Ver Code coverage ingresar a -> ../meliTest/target/site/jacoco-ut/index.html
7. Ejecutar la aplicación -> java -jar target/MeliTest-0.0.1-SNAPSHOT.jar
8. Consumir el servicio Post http://localhost:5000/mutant body ejemplo: {"dna":["AACC","TTCC","GGAA","AATT"]}
9. Consumir el servicio Get http://localhost:5000/mutant/stats 
10. Ingresar a SwaggerUI http://localhost:5000/swagger-ui
```

# conversordemonedas ALURA Oracle Porgrama One G6
Un conversor de monedas creado en JAVA como parte de formacion personal
# Conversor de Divisas y Criptomonedas

Este es un proyecto de conversor de divisas y criptomonedas desarrollado en Java utilizando Spring Boot. Permite convertir entre diferentes monedas nacionales y criptomonedas.

Tecnologías Utilizadas

- Java: Lenguaje de programación backend.
- Spring Boot: Framework para la creación de aplicaciones web.
- Jackson: Biblioteca para el procesamiento de JSON.
- Gso: Biblioteca para la conversión entre objetos Java y JSON.

 Requisitos Previos

- JDK 11 o superior.
- Maven para la gestión de dependencias.

Instalación y Configuración

 Configurar el archivo `pom.xml`:
    Asegúrate de que el archivo `pom.xml` incluya las siguientes dependencias:
    <dependencies>
   //Spring Boot//
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        //Jackson//
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.13.3</version>
        </dependency>
        //Gson//
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.9</version>
        </dependency>
    </dependencies>

 Ejecución del Proyecto

Para ejecutar el proyecto, utiliza el siguiente comando:

Inicio de la Aplicación:
Al ejecutar la aplicación, se mostrará un menú en la consola con las opciones disponibles.

Conversión de Monedas Nacionales:

Selecciona la opción 1.
Ingresa el número correspondiente a la moneda de origen.
Ingresa el número correspondiente a la moneda de destino.
Ingresa la cantidad a convertir.
La aplicación mostrará el resultado de la conversión.

Conversión de Criptomonedas:

Selecciona la opción 2.
Ingresa el número correspondiente a la criptomoneda de origen.
Ingresa el número correspondiente a la moneda nacional de destino.
Ingresa la cantidad a convertir.
La aplicación mostrará el resultado de la conversión.

Estructura del Proyecto

src
main ->java ->apis -> ExchangeAPI.java ->GeckoAPI.java
main ->java ->apis ->coins -> Coin.java, ConversionData.java , CryptoConversionData.java
main ->java -> app -> Principal.java, ConversorDeMonedasAplicattion.java
resources -> application.properties

Créditos
Este proyecto fue desarrollado por Angel Eduardo Olvera Perez con fines de aprendizaje.
GRACIAS

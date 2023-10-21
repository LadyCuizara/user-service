# User-service

Esta es una aplicación que permite crear usuarios que a la vez generan su propio token JWT. Esta funcionalidad esta expuesta en un RESTFull API.

El diagrama E-R para este proyecto es el siguiente:

![](diagram.png)

# Como empezar

Este es un projecto Spring Boot (2.7.8) con Maven (3.9.0) para el manejo de dependencias. La base de datos es de tipo H2 y contiene un script para crear la estructure inicial que se encuentra con el nombre `schema.sql`.

## Prerequisitos

El sistema operativo debe contar con las siguientes herramientas/lenguajes:
* Java 17.0.7
* Maven 3.x.x
* Git 2.x.x

## Instalacion

El repositorio es publico y puede descargarse mediante el siguiente comando: 

```bash
git clone git@github.com:LadyCuizara/user-service.git
```

Para generar los artefactos necesario para el despliegue, necesitas ejecutar:

```bash
mvn clean package
```

Por último para levantar el artefacto generado, puedes ejecutar el siguiente comando:

```bash
java -jar target/user-service-0.0.1-SNAPSHOT.jar
```

Mediante este comando se inicializará la aplicación Spring Boot en el puerto 8080 (puede ser modificado en el archivo `application.properties`)

# Como usar

La aplicacion expone una API para poder visualizar todos los endpoints disponibles. Puedes acceder a ella mediante la siguiente URL:

```
http://localhost:8080/swagger-ui/index.html
```

Una vez ingresando puedes probar el endpoint que registra usuarios. Este endpoint tiene las siguientes características:

| Verbo | URL                             |
|-------|---------------------------------|
| POST  | http://localhost:8080/api/users |

El parámetro de entrada es un Body Request en formato JSON:

```json
{
  "email": "juan@gmail.com",
  "name": "Juan Perez",
  "password": "Password1!",
  "phones": [
    {
      "citycode": 2,
      "countrycode": 591,
      "number": 77777777
    }
  ]
}
```

Al mismo tiempo de guardar el usuario en la base de datos, también se genera un token JWT que es retornado en la respuesta, como se ve a continuación:

* HTTP código de estado = 201
```json
{
  "user": {
    "id": "6fa14028-881b-41eb-bce0-f2c6326b8911",
    "name": "Juan Perez",
    "email": "juan@gmail.com",
    "password": "$2a$10$rT39SpilBW9IvbWbaNOWr.pihfSreTtq3pi2pgIVzjeDlkwV6hWj6",
    "phones": [
      {
        "number": 77777777,
        "cityCode": 2,
        "countryCode": 591
      }
    ],
    "created": "2023-10-21T20:13:32.311+00:00",
    "modified": "2023-10-21T20:13:32.311+00:00"
  },
  "session": {
    "userId": "6fa14028-881b-41eb-bce0-f2c6326b8911",
    "lastLogin": "2023-10-21T20:13:32.567+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKdWFuIFBlcmV6IiwiaWF0IjoxNjk3OTE5MjEyLCJleHAiOjE2OTc5MjEwMTJ9.G-h5uCdWiKpyydrz84lNHSEDbJa1shFsNg58XHxu9zA",
    "active": true
  }
}
```

Para visualizar la base de datos, usted puede acceder a la siguiente URL:

| URL                                    | Usuario | Contraseña |
|----------------------------------------|---------|------------|
| ```http://localhost:8080/h2-console``` | sa      | sa         |

## Excepciones
La aplicacion verifica las siguientes excepciones:
* Verificacion del formato de Email - EmailFormatException: Valida que el formato del email sea el siguiente: ``(aaaaaaa@dominio.cl)``

* Verificacion del correo unico - UserAlreadyExistsException : Verifica que el correo del nuevo usuario no haya sido registrado anteriormente

* Validacion de campos nulos e invalidos: Se verifica que el nombre, email, password del usuario no sean nulos ni vacios.

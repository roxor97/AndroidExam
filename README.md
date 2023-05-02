# Proyecto Java Android

El objetivo de este proyecto era desarrollar una aplicación móvil para dispositivos Android utilizando Java y Android Studio. La aplicación debía utilizar bases de datos SQLite de forma local y consumir servicios web RESTful. Además, se buscaba que la interfaz gráfica fuera sencilla y agradable para el usuario.

## Tecnologías utilizadas
- Java
- Android Studio
- SQLite
- GitHub

## Estructura del proyecto
El proyecto consta de varias clases Java que se encargan de diferentes aspectos de la aplicación:

- `MainActivity.java`: Esta clase es la actividad principal de la aplicación y se encarga de manejar la interfaz de usuario. Permite al usuario iniciar sesión, registrarse y ver la lista de asteroides cercanos a la Tierra.
- `User.java`: Esta clase define el modelo de usuario y se utiliza para almacenar información sobre los usuarios registrados.
- `Asteroids.java`: Esta clase define el modelo de asteroide y se utiliza para almacenar información sobre los asteroides cercanos a la Tierra.
- `DbHelper.java`: Esta clase se encarga de manejar la base de datos SQLite utilizada por la aplicación. Permite al usuario crear, leer, actualizar y eliminar información sobre los asteroides guardados.
- `UserService.java`: Esta clase se encarga de la comunicación con el servidor de autenticación utilizado por la aplicación. Permite al usuario registrarse y autenticarse en la aplicación.
- `AsteroidApiService.java`: Esta clase se encarga de la comunicación con la API de asteroides utilizada por la aplicación. Permite al usuario obtener información sobre los asteroides cercanos a la Tierra.

## Funcionamiento de la aplicación
La aplicación consume los datos del API Asteroids - NeoWs desarrollada por la Administración Nacional de la Aeronáutica y del Espacio (NASA). En particular, consume los datos del endpoint Neo-Feed, que proporciona un listado de asteroides cercanos a la Tierra en una fecha específica. El parámetro `start_date` del API se establece en el día en el que se realizó la entrevista.

La aplicación móvil cuenta con una ventana de registro de usuarios y otra de inicio de sesión. Los datos de los usuarios se almacenan en una base de datos local. Además, los datos obtenidos del API se almacenan en la tabla `Asteroids` de la base de datos local y están relacionados con la tabla `Users`, de forma que el usuario que ha iniciado sesión en la aplicación tiene relación con uno o muchos asteroides cercanos a la Tierra.



# Ecosistema APP
# Development

Pasos para levantar la app de desarrollo

1. Instalar un editor de codigo como Ecplipse o IntelliJ.
2. Instalar java 17 y el plugin Lombok.
3. Instalar MariaDB.
4. Crear un archivo .env con los siguientes campos.
   
SPRING_APPLICATION_NAME=ecosistema
SPRING_DATASOURCE_URL=jdbc:mariadb://localhost:3306/"nombre de la base de datos"
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_JPA_HIBERNATE_DDL_AUTO=update

SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID=
SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_SECRET=
SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_SCOPE=profile, email

CLOUDINARY_CLOUD_NAME=
CLOUDINARY_API_KEY=
CLOUDINARY_API_SECRET=

SECRET_KEY=
API_KEY=

EMAIL_USERNAME=
EMAIL_PASSWORD=

6. Reemplazar los campos con nombre de la base de datos,usuario,password. Entrar a google y configurar oauth para obtener el clientId y el ClientSecret. Lo mismo con cloudinary.
7. En Email Username y Email Password, poner tu correo electronico para que la aplicacion mande mensajes automaticos con los datos mas relevantes en la semana.
8. En src/main/java/com/semillero/ecosistema/init modificar el archivo UsuarioDataLoader.java
9. Cambiar la siguiente linea Usuario usuario1 = new Usuario(null, "tu nombre", "tu apellido", "tu correo", false, RolDeUsuario.ADMIN );
10. Correr el Backend






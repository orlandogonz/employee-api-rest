# employee-api-rest

## Descricion



Esto es una API REST usando a Jersey como framework. La idea del projecto es publicar un servicio que luego pueda ser
ejecutado en una operación CRUD con Employee Resource. Este es un servicio producido y consumido en formato JSON.


### Más

El projecto está compuesto por los siguientes items

> 1.	src/main/java folder:
        * com.orlando.java.sql: Aquí está el script para crear la tabla de información de employee.
> 2.	src/main/java folder: java clases.
>       *	com.orlando.java.web.ConnectionHelper: Creea la conexión a la base de datos.
>       *	com.orlando.java.web.Employee: Employee POJO class.
>       *	com.orlandojava.web.EmployeeDAO: Employee DAO.
>       *	com.orlando.java.web.EmployeeResource: Employee servicio rest.
> 3.	src/main/resource: files setup.
>       *	jdbc.properties: Configuración base de datos.
>       *	log4j.properties: logger configuracion.


### Build artifact (Compile)

> 1.	Desde la terminal: mvn clean package
> 2.	Desde el IDE: Revisa la información de cada IDE para ejecutar projectos en MAVEN.
>       *	Intellij: [IntelliJ](https://www.jetbrains.com/help/idea/maven-support.html)
>       *	Intellij: [Netbeans](https://platform.netbeans.org/tutorials/nbm-maven-quickstart.html)
>       *	Intellij: [Eclipse](http://maven.apache.org/plugins/maven-eclipse-plugin/usage.html)

## Run artifact

Ve a tu browser favorito y escribe la siguiente url: **http://localhost:8080//employee-service-rest/api/employees**

## Tips

Use Postman client para testear la aplicación de una api rest.

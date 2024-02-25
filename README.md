# Technologies used
- Java 21
- Spring Boot v3.2.3
- Maven
- Hibernate
- JPA
- Lombok
- MySQL
- Docker
- Spring boot security
- JWT
- Swagger

# Using the app

1. The app is initialised with data from the `data.sql` file. If it needs to be re-run, these commands need to be commented:

```
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
spring.jpa.hibernate.ddl-auto=create
```

2. The database can be created with docker.
3. Use `http://localhost:7000/swagger-ui/index.html` for an outline of the api.
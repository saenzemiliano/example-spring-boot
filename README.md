# example-spring-boot

## Requirements

- Maven Project
- Securing a Web Application (LDAP Authentication and JDBC Authorization)
- Accessing Data with JPA
- Building a RESTful Web Service
- Serving Web Content with Spring MVC
- Spring JavaMailSender
- Configuration for Multiple Profiles (dev, prod)

## Build

### Dev Profile
```
mvn -P dev spring-boot:run -Dspring-boot.run.profiles=dev
```
### Prod Profile
```
mvn clean package -P prod
```

## Setting the default active profile in Spring-boot (NOT USED!!!)

```
application.properties
application-dev.properties
application-prod.properties
```

### Into your application.properties, and just this file, must have the follow line:

```
spring.profiles.active=@spring.profiles.active@
```
### Run
```
mvn spring-boot:run -Dspring.profiles.active=dev
```

```
mvn spring-boot:run -Dspring.profiles.active=dev
```


## License

This project is licensed under the GNU GPLv3 - see the [LICENSE.md](LICENSE.md) file for details

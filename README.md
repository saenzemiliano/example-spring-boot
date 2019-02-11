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
mvn  -P dev spring-boot:run -Dspring.profiles.active=dev
```
### Prod Profile
```
mvn clean package -P prod -Dspring.profiles.active=prod
```

## License

This project is licensed under the GNU GPLv3 - see the [LICENSE.md](LICENSE.md) file for details

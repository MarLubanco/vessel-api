# Vessel Api
API for persistence and management of vessels and equipment.


## Technologies

```$xslt
Java 11
Hibernate
Jpa
Spring Boot 2.3.3
Maven
Git
H2 Database
```

## Run

Run project:

```sh
mvn install
```
```shell
mvn spring-boot:run
```

Run tests:
 
```shell
mvn test
```


| |ENDPOINTS|
|---|---|
Add Vessel| /vessels - POST| |
Get Equipments By Vessel | /vessels/{code}/equipments - GET||
Add Equipment | /equipments - POST  | |
Update Equipment List Status | /equipments - PUT||


Test naming Pattern

```$xslt
{method name}_{what should you do}_{ok or failure}()
```



Developer – Marcelo Thomé
# smpp-server-mock
[![codecov](https://codecov.io/gh/MikeSafonov/smpp-server-mock/branch/master/graph/badge.svg)](https://codecov.io/gh/MikeSafonov/smpp-server-mock)
[![Travis-CI](https://travis-ci.com/MikeSafonov/smpp-server-mock.svg?branch=master)](https://travis-ci.com/MikeSafonov/smpp-server-mock)
[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg)](https://conventionalcommits.org)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=alert_status)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=security_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=bugs)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=code_smells)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)

[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=ncloc)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_smpp-server-mock&metric=sqale_index)](https://sonarcloud.io/dashboard?id=MikeSafonov_smpp-server-mock)

This project helps to write integration test for applications uses SMPP connection. 

`smpp-server-mock` comes with:

- Mock SMPP server - [`MockSmppServer`](module-core/src/main/java/com/github/mikesafonov/smpp/server/MockSmppServer.java)
- Junit 5 extension - [`MockSmppExtension`](module-junit/src/main/java/com/github/mikesafonov/smpp/server/MockSmppExtension.java)
- Spring Boot Starter to start mock servers before application startup - 
[`MockSmppBootstrapConfiguration`](module-spring-boot/src/main/java/com/github/mikesafonov/smpp/server/MockSmppBootstrapConfiguration.java)
- AssertJ assertions for core SMPP server objects

## Using JUnit 5 extension

Add `JUnit 5` test dependencies.

Add extension dependency `com.github.mikesafonov:smpp-server-mock-junit`:

Maven: 
```
        <dependency>
            <groupId>com.github.mikesafonov</groupId>
            <artifactId>smpp-server-mock-junit</artifactId>
            <version>${version}</version>
            <scope>test</scope>
        </dependency>
```
Gradle:

```
dependencies{
    testImplementation("com.github.mikesafonov:smpp-server-mock-junit:${version}")
}
```

Write test:

```java
@ExtendWith(MockSmppExtension.class)
public class MyTest {
    @SmppServer(systemId = "customSystemId", password = "anotherPassword")
    MockSmppServer mockSmppServer;
    
    @Test
    void shouldSendSms(){
        MySmsSender sender = ...
        sender.sendSms("message", "number");
        
        List<SubmitSm> messages = mockSmppServer.getSubmitSmMessages();
        SubmitSm message = messages.get(0);

        assertEquals("number", message.getDestAddress().getAddress());
    }       
}
```

## Using AssertJ assertions

TODO

## Using Spring Boot Starter

TODO


## Build

### Build from source

You can build application using following command:

    ./gradlew clean build -x signArchives
    
#### Requirements:

JDK >= 1.8

### Unit tests

You can run unit tests using following command:

    ./gradlew test
    
### Mutation tests

You can run mutation tests using following command:

    ./grdlew pitest

You will be able to find pitest report in `build/reports/pitest/` folder.

## Contributing

Feel free to contribute. 
New feature proposals and bug fixes should be submitted as GitHub pull requests. 
Fork the repository on GitHub, prepare your change on your forked copy, and submit a pull request.

**IMPORTANT!**
>Before contributing please read about [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0-beta.2/) / [Conventional Commits RU](https://www.conventionalcommits.org/ru/v1.0.0-beta.2/)

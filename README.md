# Online Voting BE
[![HitCount](https://hits.dwyl.com/killbizz/Online-Voting-BE.svg?style=flat-square)](http://hits.dwyl.com/killbizz/Online-Voting-BE)
[![MIT License][license-shield]][license-url]

Backend module repository of the student internship project made up for Sync Lab company using Java Spring framework.

This software consists of a SpringBoot application, which offers *REST web services*.

The JWT authentication and authorization flow (refresh token included) is configured using Spring Security.

This Java application is deployed in a [GCP Compute Engine](https://cloud.google.com/compute?hl=it) VM using Docker and is used by the [Online Voting](https://github.com/killbizz/Online-Voting-React-FE) web application.

# Main Dependencies

- Spring Framework
- SpringBoot Framework
- Spring Data JPA
- Spring Data REST
- Spring Security
- Auth0 Java JWT

# Requirements

- MySQL: `^5.7.31`
- Java: `^11`
- Maven: `^3.8.1`

# Installation

```shell
# clone the repository and access the directory.
$ git clone https://github.com/killbizz/Online-Voting-BE.git && cd Online-Voting-BE

# download dependencies
$ mvn install

# run the application
$ mvn spring-boot:run

# to build for production
$ mvn clean package
```

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[license-shield]: https://img.shields.io/github/license/killbizz/Online-Voting-BE.svg?style=for-the-badge
[license-url]: https://github.com/killbizz/Online-Voting-BE/blob/main/LICENSE

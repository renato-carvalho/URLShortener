# URL Shortener

## Description

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Requirements

It needs to be done using Spring Boot framework. Explore the framework as much as possible.
Add some Unit tests to show that the project works.
For a given URL X, if it has been previously processed by the shortener within the last 30 days, it should always respond with the same shortened URL.

## Technologies

- Java 8
- Spring Boot 2.1.2.RELEASE
- Hibernate 5.3.7.Final
- REST API
- NPM
- Maven

## How to compile and run the application with an example for each call

Install: git clone  https://github.com/venuiticodechallenge/URLShortener/RenatoCalado.git

Build: mvn package

Deploy: mvn spring-boot:run

## Examples

http://localhost:8080/save?originalUrl={url}

http://localhost:8080/getById?id={id}
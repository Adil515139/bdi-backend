# bdi-backend

Steps to run the code:

1.	git clone  https://github.com/Adil515139/bdi-backend.git
2.	Import maven project in any JAVA IDE (STS/Eclipse).
3.	Build the Spring Boot application
4.	Run as Spring boot app in STS.
5.	It will up on server - http://localhost:8089/
To test APIs :-
1. Swagger - http://localhost:8089/swagger-ui/index.html#/task-controller

Library/Tools/DB:
1.	Spring Boot - 3+
2.	JDK - 8 or later
3.	Spring Framework
4.	Spring Data JPA
5.	Spring Web
6.	Maven
7.	MySQL
8.	IDE - Eclipse or Spring Tool Suite (STS)

Database Schema – 
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





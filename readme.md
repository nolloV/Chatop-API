# Chatop

![ChaTop Logo](./src/main/resources/static/chatop-banner.png)

## Description

Chatop est une application de mise en relation entre les futurs locataires et les propriétaires pour de la location saisonnière sur la côte basque.
Elle permet de gérer des annonces, des utilisateurs et des messages.

## Fonctionnalités

- Authentification et autorisation des utilisateurs
- Gestion des locations
- Gestion des messages
- Documentation API avec Swagger

## Prérequis

Avant de commencer, assurez-vous que les logiciels suivants sont installés sur votre système :

- [Java 17 ou supérieur](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.6.3 ou supérieur](https://maven.apache.org/download.cgi)
- [MySQL 8 ou supérieur](https://dev.mysql.com/downloads/mysql/)

## Installation des logiciels requis

### Java 17 ou supérieur

1. Rendez-vous sur la page de téléchargement de [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. Téléchargez l'installateur correspondant à votre système d'exploitation.
3. Exécutez l'installateur et suivez les instructions à l'écran pour installer Java.
4. Vérifiez l'installation en ouvrant une invite de commande et en tapant :
   ```sh
   java -version
   ```
   Vous devriez voir la version de Java installée.

### Maven 3.6.3 ou supérieur

1. Rendez-vous sur la page de téléchargement de [Maven](https://maven.apache.org/download.cgi).
2. Téléchargez l'archive binaire (zip ou tar.gz) correspondant à votre système d'exploitation.
3. Extrayez l'archive téléchargée dans un répertoire de votre choix.
4. Ajoutez le chemin du répertoire `bin` de Maven à la variable d'environnement `PATH`.
5. Vérifiez l'installation en ouvrant une invite de commande et en tapant :
   ```sh
   mvn -version
   ```
   Vous devriez voir la version de Maven installée.

### MySQL 8 ou supérieur

1. Rendez-vous sur la page de téléchargement de [MySQL](https://dev.mysql.com/downloads/mysql/).
2. Téléchargez l'installateur correspondant à votre système d'exploitation.
3. Exécutez l'installateur et suivez les instructions à l'écran pour installer MySQL.
4. Pendant l'installation, configurez le mot de passe de l'utilisateur root.
5. Vérifiez l'installation en ouvrant une invite de commande et en tapant :
   ```sh
   mysql -u root -p
   ```
   Entrez le mot de passe root que vous avez configuré. Vous devriez accéder à l'interface de commande MySQL.

## Configurer la base de données

Créez une nouvelle base de données pour votre application et ajoutez toutes les tables à votre base de données :

```sh
-- Create the database
DROP DATABASE IF EXISTS `chatop_db`;

CREATE DATABASE `chatop_db`;

-- Switch to the new database
USE `chatop_db`;

-- Create the tables
CREATE TABLE `users` (
 `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
 `name` VARCHAR(255) NOT NULL,
 `email` VARCHAR(255) UNIQUE NOT NULL,
 `password` VARCHAR(255) NOT NULL,
 `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `rentals` (
 `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
 `owner_id` BIGINT NOT NULL,
 `name` VARCHAR(255) NOT NULL,
 `surface` INT NOT NULL,
 `price` DECIMAL(10, 2) NOT NULL,
 `picture` TEXT NOT NULL,
 `description` TEXT,
 `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

CREATE TABLE `messages` (
 `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
 `user_id` BIGINT NOT NULL,
 `rental_id` BIGINT NOT NULL,
 `message` TEXT,
 `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
 FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`) ON DELETE CASCADE
);
```

## Procédure d'installation

**Clonage du projet :**

1. Clonez depuis GitHub : `git clone https://github.com/nolloV/Chatop-API.git .`

2. Configurez l'application dans « application.properties ».

**Configurez le fichier `application.properties` :**
Une fois que vous avez cloné le référentiel, vous devrez ajouter le fichier « application.properties » dans le dossier « src/main/resources/ » contenant ces propriétés :

```sh
# Nom de l'application Spring Boot
spring.application.name=chatop

# Port sur lequel le serveur va écouter
server.port=8005

# Configuration de la source de données (base de données MySQL)
spring.datasource.url=jdbc:mysql://localhost:3306/chatop_db
spring.datasource.username=root
spring.datasource.password=**your_password**

## Propriétés Hibernate
# Stratégie de génération du schéma de la base de données (update, create, create-drop, validate)
spring.jpa.hibernate.ddl-auto=update
# Désactive l'option "open-in-view" pour éviter les problèmes de session Hibernate
spring.jpa.open-in-view=false

# Clé secrète utilisée pour signer les tokens JWT
security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
# Durée d'expiration des tokens JWT en millisecondes (1 heure)
security.jwt.expiration-time=3600000

# Configuration des chemins pour les ressources statiques
spring.mvc.static-path-pattern=/static/**
spring.web.resources.static-locations=classpath:/static/
```

3. Exécutez l'application à l'aide de votre IDE ou en exécutant « mvn spring-boot:run » dans le répertoire du projet.

4. Accédez à l'URL Swagger pour explorer et tester l'API.

5. Vous pouvez également utiliser Postman pour tester les appels API.

## Structure du projet

Le projet suit une architecture classique en couches (Contrôleur/Service/Java Persistence API Repository) pour garantir la modularité et la maintenabilité du code.

## Dépendances

Voici la liste des dépendances utilisées dans ce projet :

### Spring Boot Starters

- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-test`

### Base de données

- `com.h2database:h2`
- `com.mysql:mysql-connector-j`

### API Servlet

- `javax.servlet:javax.servlet-api:4.0.1`

### Logging

- `ch.qos.logback:logback-classic:1.5.7`

### Persistence

- `javax.persistence:javax.persistence-api:2.2`

### Validation

- `javax.validation:validation-api:2.0.1.Final`

### Security Testing

- `org.springframework.security:spring-security-test`

### Documentation

- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2`

### JSON Web Token (JWT)

- `io.jsonwebtoken:jjwt-api:0.11.5`
- `io.jsonwebtoken:jjwt-impl:0.11.5`
- `io.jsonwebtoken:jjwt-jackson:0.11.5`

### Monitoring

- `io.sentry:sentry-spring-boot-starter:6.0.0`

### Utility

- `org.projectlombok:lombok`

## Authentification et sécurité

L'authentification est gérée par Spring Security avec JWT. Tous les itinéraires nécessitent une authentification, à l'exception de ceux liés à la création de compte ou à la connexion. Les mots de passe sont codés et stockés en toute sécurité dans la base de données. Les informations d'identification de la base de données ne sont pas exposées dans le code.

## Documentation API

L'API est documentée à l'aide de Swagger. Vous pouvez accéder à la documentation de l'API en accédant à l'URL Swagger après avoir exécuté le serveur `http://localhost:8005/swagger-ui/index.html`.

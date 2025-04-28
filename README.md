# Projet : Plantes versus Zombies (Backend) - Elora TRASANCOS

## Description du projet

Ce projet consiste à développer une API RESTful backend permettant de gérer :

- Les plantes,

- Les zombies,

- Les maps.

Cette API permet de créer, lire, mettre à jour et supprimer les données pour le jeu Plantes versus Zombies via des opérations CRUD complètes.

Elle est connectée à une base de données MySQL (ou H2 en test) et respecte l’architecture en couches recommandée :

Controller ➔ Service ➔ DAO ➔ Base de données.

## Technologies utilisées

- Java 21

- Spring MVC

- Maven

- Tomcat

- JdbcTemplate

- JUnit 5

- Mockito

Base de données : MySQL (production) / H2 (tests)

Servlet API : Jakarta Servlet API

Configuration CORS ainsi que la configuration Spring (web.xml, spring-config.xml) ont été faites manuellement.

## Tests réalisés

Nous avons mis en place des tests fonctionnels sur toutes les couches du projet :

- Configuration (Connexion à la base)

- DAO (tests d’accès aux données)

- Services 

- Controllers (tests des endpoints API)

La base de tests utilise H2 Database en mémoire pour simuler l'environnement de production.

## Comportements API importants

## POST `/zombies`

Lorsque l’on souhaite créer un nouveau zombie :

Validation : l’API vérifie si l’id_map fourni existe bien dans la table des maps.

En cas d’erreur :

Si l'identifiant de map est introuvable, l'API retourne une erreur 404 avec un message clair :

La map avec l’identifiant [id_map] n’existe pas.

Cela garantit l’intégrité des données lors de l’ajout de nouveaux zombies.

## Instructions pour lancer le projet

Compiler le projet :
```bash
mvn clean package
```
Déployer le fichier .war généré (dossier target) dans le dossier webapps de votre Tomcat.

Accéder à l'API via :

http://localhost:8080/CoursEpfBack

## Fonctionnalités principales

| Fonction                 | URL                          | Méthode HTTP | Description                                   |
|---------------------------|-------------------------------|--------------|-----------------------------------------------|
| Lister les zombies        | `/zombies`                   | GET          | Récupérer tous les zombies                   |
| Créer un zombie           | `/zombies`                   | POST         | Ajouter un nouveau zombie (vérification id_map) |
| Mettre à jour un zombie   | `/zombies/{id}`              | PUT          | Modifier un zombie existant                  |
| Supprimer un zombie       | `/zombies/{id}`              | DELETE       | Supprimer un zombie                          |
| Lister les zombies par map| `/zombies/map/{id_map}`      | GET          | Récupérer les zombies d'une carte spécifique  |


Idem pour les plantes et les cartes (/plants, /maps).


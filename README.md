# Projet de Recherche de Recettes - Backend

Ce projet a pour objectif de fournir un backend pour une application de recherche de recettes, similaire à Marmiton, permettant aux utilisateurs de trouver des recettes en fonction de leurs préférences alimentaires et de leurs restrictions d'allergènes.

## Fonctionnalités

- **Recherche de recettes** : Les utilisateurs peuvent rechercher des recettes en fonction de divers critères (nom, ingrédients, etc.).
- **Gestion des allergènes et des régimes** : Les utilisateurs connectés peuvent spécifier leurs allergies et préférences alimentaires.
- **Création de recettes par les administrateurs** : Les utilisateurs ayant des droits d'administration peuvent ajouter de nouvelles recettes au système.

## Structure du Projet

Ce backend est développé en Spring Boot et utilise une base de données PostgreSQL pour stocker les données. Un frontend en Angular, qui utilise ce backend, est disponible à [ce lien](https://github.com/MAGICTNT/file_rouge).

### Exemple de Configuration `application.properties`

Voici un exemple de configuration pour le fichier `application.properties` :

```properties
spring.application.name=back_project

spring.datasource.url=jdbc:postgresql://localhost:5432/[nom_de_la_bdd]
spring.datasource.username=[pseudo.bdd]
spring.datasource.password=[password.bdd]
spring.jpa.hibernate.ddl-auto=update

springdoc.swagger-ui.path=/doc
```

### Base de Données

Le projet utilise PostgreSQL. Les scripts SQL pour créer les tables et insérer des jeux de données de test se trouvent dans le répertoire `resources/sql`.

## Contribution

Les personnes ayant contribué au développement de ce projet sont :

- **Haiou King** - [GitHub](https://github.com/mangaluxe)
- **Tibault Garcia** - [GitHub](https://github.com/Mysthaqua)
- **Guilaume** - [GitHub](https://github.com/sun7code)
- **Maxime** - [GitHub](https://github.com/MAGICTNT)

## Installation et Lancement

1. **Cloner le projet** : Clonez ce dépôt sur votre machine.
2. **Configurer la base de données** : Assurez-vous que PostgreSQL est en cours d'exécution et créez une base de données pour le projet.
3. **Configurer `application.properties`** : Mettez à jour le fichier `application.properties` avec les informations de connexion à votre base de données.
4. **Lancer l'application** : Exécutez l'application avec votre IDE ou en ligne de commande avec `mvn spring-boot:run`.

## Documentation de l'API

Une documentation Swagger de l'API est accessible à l'adresse `/doc` lorsque le serveur est démarré.

---

**Note :** Ce backend est conçu pour être utilisé en tandem avec le frontend Angular disponible [ici](https://github.com/MAGICTNT/file_rouge).

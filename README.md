# deal-management-system
Application Spring Boot pour la gestion des transactions de devises (deals) avec validation automatique des paires de devises à partir d’un fichier CSV et utilisation des DTOs pour le transfert de données.
Description détaillée :

Le projet Deal Management System est une application backend développée avec Spring Boot qui permet de gérer des transactions financières entre différentes devises. L’application intègre les fichiers CSV pour charger les paires valides et utilise des DTOs (Data Transfer Objects) pour transférer les données entre le client et le serveur de manière sécurisée et structurée.

Fonctionnalités principales :

Validation des paires de devises

Les transactions ne peuvent être sauvegardées que si la paire de devises est valide.

Les paires valides sont chargées depuis le fichier deals.csv situé dans src/main/resources/.

Gestion des transactions (Deals)

Création d’un deal unique ou en masse.

Chaque deal possède un identifiant unique (dealUniqueId), les devises concernées (fromCurrency, toCurrency), le montant (dealAmount) et la date de la transaction (dealTimestamp).

Utilisation des DTOs (Data Transfer Objects)

Les DTOs permettent de transférer uniquement les informations nécessaires entre le client et le backend.

Conversion automatique entre DTO et entité via le service DealService.

Gestion des erreurs et exceptions

Si une paire est invalide, une exception est levée et un message clair est renvoyé au client.

Les paires invalides sont stockées dans une collection interne pour consultation.

Endpoints REST

POST /api/adddeal : Ajouter un deal unique.

POST /api/adddeals : Ajouter plusieurs deals à la fois.

GET /api/dtodeals : Récupérer la liste de tous les deals sous forme de DTO.

Technologies utilisées

Backend : Java 17, Spring Boot 3, Spring Data JPA

Base de données : MySQL

Gestion des dépendances : Maven

Validation et exceptions : Spring ExceptionHandler

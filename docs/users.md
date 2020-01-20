# Users management API

## Exigences Fonctionnelles

- Chaque utilisateur a un email qui permet l'authentification.
- Un utilisateur peut changer son mot de passe mais pas celui d'une autre personne
- L'authentification se fait par un jeton JWT.
- Nous avons implémenté deux des 4 fonctionnalités proposées :
  - Seul un admin peut créer un compte
  - Seul un admin peut bloquer ou débloquer un autre utilisateur. Si l'utilisateur est bloqué, il ne peut pas s'identifier.
- Nous avons mis en place une pagination pour les requêtes `GET` sur l'ensemble des `users`.

## Exigences non fonctionnelles

- Il est possible de lancer un script qui lance un `docker-compose` lançant les containers de l'API.
- Des test `BDD` ont été faits et sont expliqués [ici](./test_BDD.md)
- Des tests de charges ont été faits et sont expliqués [ici](./test_jmeter.md)

## Résumé de l'API

Avec cette API, il est possible de lancer un service d'authentification permettant à un utilisateur de se connecter à son profil et d'en éditer certain champs.

Les différents [use cases](./use_case_api_one.md) sont expliqués ici.
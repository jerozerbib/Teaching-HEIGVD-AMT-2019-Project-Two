# Projet 2 AMT : SpringBoot

## Lancer le projet

Pour lancer le projet, il suffit de lancer le script situé à la racine. Le nom du script est `run.sh`.

## Contraintes

- Nous avons dû utiliser Spring Boot, Spring MVC and Spring Data.
- Nous ne devions pas utiliser Spring Data REST.
- Nous avons dû faire la spécification avec Swagger / Open API.
- Nous devions faire deux projets Spring Boot, chacun produisant un fichier `.jar` différent.
- Nous devons fournir une architecture `docker-compose` comprenant :
  - Un container avec le premier back-end
  - Un container avec le second back-end
  - Un container avec le RDBMS; Chaque back-end doit avoir une base de données séparée, sans tables communes
  - Une container avec Traefik, agissant comme un reverse proxy dynamique.

## Api users

La documentation pour cette partie du projet se trouve [ici](./users.md).

## Api chillout

La documentation pour cette partie du projet se trouve [ici](./chillout.md)
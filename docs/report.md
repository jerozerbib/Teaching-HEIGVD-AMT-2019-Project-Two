# Projet 2 AMT : SpringBoot

## Lancer le projet

Pour lancer le projet, il suffit de lancer le script situé à la racine. Le nom du script est `run.sh`.

Pour commencer le projet, il est inscrit dans la base de données un certain nombres d'utilisateurs (50) :
```sql
INSERT INTO user_entity VALUES ("adam.zouari@heig-vd.ch","Adam",1,0,"Zouari","kwb1S3oeT5/dI+6JtRVFqDdPKjI1hGPSlO1YnzYvykv/JyPzE5/FdnO9RDGwpQOxpfSB0+jQiXUQkM+iddnCtQ==");
INSERT INTO user_entity VALUES ("jeremy.zerbib@heig-vd.ch","Jeremy",1,0,"Zerbib","LGJ/DV2emLDbjKxewD9bconRW7TxalS28RcZD7Se+j2DuciWSUyJtuIYhoZvDI6S0nEsRGhhRiyfQXcXdkQhhQ==");
INSERT INTO user_entity VALUES ("adrien.allemand@heig-vd.ch","Adrien",0,0,"Allemand","rRF+bwRUTsyZvmZNE7Xd/N/V2h3PYc6CtOBF94vkRu9mWpA/jqFQFoRlGXH7P/bptpFcPQ/RMMHPBpS0B/DiVQ==");
INSERT INTO user_entity VALUES ("dejvid.muaremi@heig-vd.ch","Dejvid",0,1,"Muaremi","6tBUVvPe+gng91uQMkGmEG0qjvKJwyu79bPoBiKnzCHQoKhIyXEgNzccZ0zejvZoV+znVzBhaZAjZyUUJ8bz8g==");
```
Les mots de passes sont stockés en hashés et encodés en base64.
La correspondance est la suivante :

- adam.zouari@heig-vd.ch     : azouari
- jeremy.zerbib@heig-vd.ch   : jzerbib
- adrien.allemand@heig-vd.ch : aallemand
- dejvid.muaremi@heig-vd.ch  : dmuaremi


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

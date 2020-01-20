# API Chillout

## Exigences Fonctionnelles

- À l'instar du premier projet, il fallait capturer 3 entités à travers cette API. Nous pouvions utiliser les mêmes entités que pour notre premier projet mais rien d'obligatoire.
  - Les `CRUDS` devaient être mis en place sur 2 des 3 entités et nous devions spécifier le comportement global lorsqu'une de ces entités est supprimée.
  - L'API devait trouver une possibilité d'associer/dissocier une paire des 2 entités principales.
  - Nous avions une liberté quant aux payloads utilisés et nous justifierons nos choix dans le rapport plus tard.
- L'API doit implémenter une forme de pagination pour faciliter la lecture et la charge.

## Exigences non fonctionnelles

Elle sont les mêmes que pour l'API `users` énoncées [ici](./users.md#Exigences non fonctionnelles).

## Résumé de l'API

### Choix d'implémentation

#### Products

```yaml
Product:
    type: object
    properties:
      name:
        type: string
      unitPrice:
        type: number
        format: double
      description:
        type: string
  ProductInput:
    allOf:
      - $ref: '#/definitions/Product'
      - type: object
  ProductOutput:
    allOf:
      - $ref: '#/definitions/Product'
      - type: object
      - properties:
          id:
            type: integer
            format: int64
```

Nous avons choisi de faire en sorte qu'une définition de `Product` soit faite de manière simple afin de pouvoir faire des requêtes sans passer l'ID de ce dernier. En effet, nous avons fait en sorte que l'ID d'un produit soit auto-généré par le back-end, ce qui rend le renseignement de ce champ inutile.

En revanche, lorsque nous voulons afficher un `Product`, nous voulons aussi afficher son ID. C'est pourquoi l'objet  `ProductOutput` existe. 

L'objet `Product` n'est jamais appelé directement.

#### Clients

```yaml
Client:
    type: object
    properties:
      email:
        type: string
```

Nous avons choisi de ne renseigner que le champ email dans ce cas. En effet, l'authentification se faisant du côté `users`, nous n'avons pas besoin de savoir quelles sont les informations dans la première base de données. 

#### OrderItems

```yaml
OrderItem:
    type: object
    properties:
      id:
        type: integer
        format: int64
      product:
        $ref: '#/definitions/ProductOutput'
      quantity:
        type: integer
```

L'objet `OrderItem` sert de clé de voûte à une commande. En effet, il permet de choisir un `Product` avec son ID, et de définir une quantité de produit que le client veut.

#### Orders

```yaml
Order:
    type: object
    properties:
      id:
        type: integer
        format: int64
      client:
        $ref: '#/definitions/Client'
      orderItems:
        type: array
        items:
          $ref: '#/definitions/OrderItem'
```

Un `Order` permet de passer une commande. Nous définissons une liste de `OrderItem`s et un `Client` qui passe sa commande.

Chaque commande a un `ID`.

### Use case

Pour trouver des informations sur les différents cas d'utilisations, il faut se référer à [ça](./use_case_chill.md). 

### Tests

Les test BDD se trouvent [ici](./test_BDD.md). Pour les tests JMeter, il faut se référer à [ça](./test_jmeter.md). 
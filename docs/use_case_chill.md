# Use Cases

## Chillout

### Log in

The `users`' API must be up so that we can login and we can authenticate in the app.

If it is not up, the `Clients` cannot perform any actions but list all the `Product`s.

### `/products`

#### I am logged in

- If I try to send a `POST` request with the **wrong format**. 
  - I receive an error code response (`400`) and the payload is not passed through.
  - No new `Product` is created.
- If I try to send a `POST` request with the **right format**.
  - I receive a granted code response (`201`) and the payload is passed through.
  - A new `Product` is created and is added to the *DB* 
- If I try to send a `GET` request.
  - I receive a list of `Product`s with the response code `200` and the payload is passed through.

#### I am not logged in

- If I try to send a `GET` request.
  - I receive a list of `Product`s with the response code `200` and the payload is passed through.
- If I try to send a `POST` 
  - I receive a `401` error code because I am not logged in

### `/products/{id}`

**For this operation and the following, we will not consider the wrongly formatted payload and a non logged in user**

#### I am an `admin`

- If I try to send a `PATCH` request
  - I send the payload that edits the `Product`
  - I can edit any field I want.
  - The `Product` is edited in the *DB*
- If I try to send a `GET` request
  - I send the payload with a right `id`
  - I get a Product
- If I try to send a `DELETE` request
  - I send the payload with the right `id`
  - I delete the `Product` from the DB.

#### I am a `Client`

- If I try to send a `PATCH` request :
  - I am blocked right away.
  - I receive a `401` code.
- If I try to send a `GET` request
  - I send the payload with a right `id`
  - I get a Product
- If I try to send a `DELETE` request
  - I am blocked right away.
  - I receive a `401` code.

### `/clients`

#### I am the `User`

- I send a `POST` 
  - I can create my account as a `Client` if I do not exist prior to this request.
- I send a `GET`
  - I am `admin`
    - I see all the `Client`s from the application
  - I am not `admin`
    - Access denied

### `/clients/{email}`

- I send a `PATCH`
  - I can edit my profile
  - If I am `admin`
    - I can edit any profile
- I send a `DELETE`
  - I can delete my profile
  - If I am `admin`
    - I can delete any profile
- I send a `GET`
  - I can get my profile
  - If I am `admin`
    - I can get any profile

### `/orders`

- I send a `POST`
  - I can create an `Order` in any case
- I send a `GET`
  - I am not an `admin`
    - I can't see them
  - I am an `admin`
    - I can see them

### `/orders/{id}`

- I send a `DELETE`
  - If it is my `Order` and I am not an `admin`
    - I can delete it  
  - If it is not my `Order` and I am not an `admin`
    - I can't delete it
  - If it is not my `Order` and I am an `admin`
    - I can't delete it
  - If it is my `Order` and I am an `admin`
    - I can delete it
- I send a `GET`
  - If it is my `Order` and I am not an `admin`
    - I can't see it  
  - If it is not my `Order` and I am not an `admin`
    - I can't see it
  - If it is not my `Order` and I am an `admin`
    - I can see it
  - If it is my `Order` and I am an `admin`
    - I can see it
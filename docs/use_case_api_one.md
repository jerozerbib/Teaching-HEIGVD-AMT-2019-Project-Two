# Use Cases

## User Management

### Log in

I have to enter my credentials with clear password that has to hashed by the backend.

If I am blocked, I have to wait for an admin to unblock me.
- I receive a code saying my request was refused (`403`) 

If all is OK, I can perform the aforementionned operations, provided that I am allowed to do so.

In order to perform any of the following operations, you need to be logged in.

### Adding a new `User`

#### I am an `admin`

- If I try to send a `POST` request with the **wrong format**. 
    - I receive an error code response (`400`) and the payload is not passed through.
    - No new `User` is created.
- If I try to send a `POST` request with the **right format**.
    - I receive a granted code response (`201`) and the payload is passed through.
    - A new `User` is created and is added to the *DB* 

#### I am a `user`

I receive a `Permission refused` error code (`403`) as I am not allowed to perform those kind of actions

### Editing a `User`

**For this operation and the following, we will not consider the wrongly formatted payload**

#### I am an `admin`

- If I try to send a `PATCH` request
    - I send the payload that edits the `User`
    - I can block/unblock a `User`
    - I can edit a password from any user
    - The `User` is edited in the *DB*

#### I am a `user`

- I want to edit my password :
    - I can do it
- I want to edit if I blocked or not :
    - I can't do it

### Deleting a `User`

#### I am an `admin`

- I can delete my account 

- I can delete anyone else's account.

#### I am an `employee`

- I can delete my account

### Listing all the `User`s

#### I am an `admin`

- I can list and see some data on other accounts.

#### I am an `employee`

- I cannot see the names of the other users.



# Use Cases

## User Management

### Log in

I have to enter my credentials.

If I am blocked, I have to wait for an admin to unblock me.

If all is OK, I can perform the aforementionned operations, provided that I am allowed to do so.

In order to perform any of the following operations, you need to be logged in.

### Adding a new `User`

#### I am an `admin`

- If I try to send a `POST` request with the **wrong format**. 
    - I receive an error message and the payload is not passed through.
    - No new `User` is created.
- If I try to send a `POST` request with the **right format**.
    - I receive a granted message and the payload is passed through.
    - A new `User` is created and is added to the *DB* 

#### I am an `employee`

I receive a `Permission refused` message as I am not allowed to perform those kind of actions

### Editing a `User`

**For this operation and the following, we will not consider the wrongly formatted payload**
#### I am an `admin`

- If I try to send a `PUT` request
    - I send the payload that edits the `User`
    - The `User` is edited in the *DB*
- I can also block/unblock a `User`

#### I am an `employee`

- I want to edit my name : 
    - I can do it
- I want to edit my password : 
    - The server has to generate a link, send it to the email
    - I have to click on the link and then I can edit my password
    - The `JWT` token has to be edited also on the backend part
- I want to edit my email : 
    - The server has to receive a confirmation from the former adress.
    - Upon confirmation, the `User` can edit his email
    - The latter email has to be validated also.

### Deleting a `User`

#### I am an `admin`

- I can delete my account 

- I cannot delete anyone else's account.

#### I am an `employee`

- I can delete my account

### Listing all the `User`s

#### I am an `admin`

- I can list and see some data on other accounts.

#### I am an `employee`

- I cannot see the names of the other users.



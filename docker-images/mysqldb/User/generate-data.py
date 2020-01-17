from random import randint
import hashlib
import base64

m = hashlib.sha3_512()

fichier = open("data.sql", "a")

# generate couple of userX/passwordX login
for i in range(1,50):
    m.update(("password"+str(i)).encode())
    strVal = "INSERT INTO user_entity VALUES (\"user"
    strVal += str(i) + "\", 0 , 0 , \"" + base64.b64encode(m.digest()).decode() + "\");\n"
    fichier.write(strVal)

fichier.close()

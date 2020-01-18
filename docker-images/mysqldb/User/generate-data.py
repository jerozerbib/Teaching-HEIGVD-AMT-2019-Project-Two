from random import randint
import hashlib
import base64


fichier = open("data.sql", "a")

# Create admin accounts
m = hashlib.sha3_512()
m.update(("azouari").encode())
fichier.write("INSERT INTO user_entity VALUES (\"adam.zouari@heig-vd.ch\",\"Adam\",1,0,\"Zouari\",\"" + base64.b64encode(m.digest()).decode() + "\");\n")

m = hashlib.sha3_512()              
m.update(("jzerbib").encode())
fichier.write("INSERT INTO user_entity VALUES (\"jeremy.zerbib@heig-vd.ch\",\"Jeremy\",1,0,\"Zerbib\",\"" + base64.b64encode(m.digest()).decode() + "\");\n")

# Create normal user account 
m = hashlib.sha3_512()
m.update(("aallemand").encode())
fichier.write("INSERT INTO user_entity VALUES (\"adrien.allemand@heig-vd.ch\",\"Adrien\",0,0,\"Allemand\",\"" + base64.b64encode(m.digest()).decode() + "\");\n")

# Create blocked user account (very randomly :P)
m = hashlib.sha3_512()
m.update(("dmuaremi").encode())
fichier.write("INSERT INTO user_entity VALUES (\"dejvid.muaremi@heig-vd.ch\",\"Dejvid\",0,1,\"Muaremi\",\"" + base64.b64encode(m.digest()).decode() + "\");\n")

# generate userX/passwordX acounts
for i in range(1,50):
    m = hashlib.sha3_512()
    m.update(("password"+str(i)).encode())
    strVal = "INSERT INTO user_entity VALUES (\"user"
    strVal += str(i) + "\",\"firstname\",0,0,\"lastname\",\"" + base64.b64encode(m.digest()).decode() + "\");\n"
    fichier.write(strVal)

fichier.close()

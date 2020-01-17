from random import randint

fichier = open("data.sql", "a")

for i in range(1,50):
    strVal = "INSERT INTO user_entity VALUES (\"user"
    strVal += str(i) + "\", 0 , 0 , \" password" + str(i) + "\");\n"
    fichier.write(strVal)

fichier.close()

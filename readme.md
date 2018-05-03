README
====
Installation back-end
----
# 1.
* Pour un système UNIX, lancer:
```
./gradlew bootRun
```
* Pour un Système Windows, lancer:
```
gradlew.bat bootRun
```
# 2.
Si l'application se lance, arrêter le et lancer: <br><br>
* UNIX:
```
./gradlew build
```
* Windows:
```
gradlew.bat build
```
# 3.
Lancer la commande:
```
java -jar build/libs/<nom-du-jar>.jar
```
pour lancer le serveur en standalone
# 4.
Accéder aux URI de l'application via des requêtes HTTP <br>
* soit avec votre navigateur
* soit avec [Postman](https://www.getpostman.com/)

README fais-mes-courses back-end
[![Build Status](https://travis-ci.org/hexif2019/SMART-WebLivreur.svg?branch=master)](https://travis-ci.org/hexif2019/SMART-WebLivreur)
====

Livrables présents dans ce répertoire
----
Vous pouvez trouver le fichier fmc-Slides.pdf et fmc-Bilan.pdf : la présentation lors de la soutenance et le dossier bilan du projet.

Vidéo teaser : https://youtu.be/gYTNkpxJKCc

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

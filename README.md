# TP Java 3D S9 : Catch me if you can

## Contexte du projet
Le projet "Catch me if you can" est une application interactive en Java utilisant JavaFX pour afficher et interagir avec des données liées à l'aviation. Cette application combine des données statiques issues d'un fichier CSV (liste des aéroports) et des données dynamiques récupérées via une API (aviationstack.com).

L'application affiche un globe terrestre en 3D, permettant à l'utilisateur de cliquer sur des points pour identifier les aéroports les plus proches et visualiser les vols associés.

---

## Objectifs pédagogiques
- **Programmation objet en Java** :
  - Programmation orienté objet faisant appel au connaisances aprises les années précedentes.

- **Utilisation de bibliothèques specifiques** :
  - Utilisation de JavaFX pour la création d'interfaces graphiques interactives ainsi que de l'API HTTP Java pour récupérer des données dynamiques.

- **Gestion de threads** :
  - Éviter les blocages d'interface via des tâches en arrière-plan.

- **Manipulation de données** :
  - Chargement et traitement de fichiers CSV et extraction de données JSON via une API REST.

---

## Compétences et connaissances acquises
2. **Gestion de données** :
   - Utilisation de `HttpClient` pour effectuer des appels HTTP.
   - Analyse de données JSON et conversion en objets Java.
   - Extraction de données d'un fichier CSV pour les stocker dans une `Araylist`

3. **Conception d'interfaces graphiques** :
   - Création d'une scène 3D interactive avec JavaFX.
   - Gestion des événements utilisateur (clics, zooms).

5. **Gestion des threads** :
   - Mise en œuvre de tâches asynchrones pour les requêtes HTTP.
   - Synchronisation des mises à jour de l'interface.

---

## Principales difficultés rencontrées
1. **Problèmes liés aux Coordonnées GPS** :
  - Quelques erreur se sont glissés au début du projet lors de l'importation des donées GSP avec des invertions entre latitude et longitude.
  - Il y a eu quelques erreur de coordonées du aux convertion degrées / radians ainsi qu'aux convertion en coordonées X,Y pour l'affichage, plaçant les points aux mauvais endroits.

2. **Mauvais type de donné dans les requetes** :
  - Lors des première requetes à l'API d'Aviationtrack, je me suis aperçu que le numéro de vol ne correspondait pas à un Integer, créant ainsi des erreur lorsque le programme souhaitait récupérer cette donné. J'ai du ajouter quelques lignes pour convertir les données de `String` à `Int`.

---

## Conclusion
Ce projet a permis d'approfondir les compétences en Java tout en travaillant sur une application captivante et interactive. Les défis techniques ont renforcé la compréhension des concepts fondamentaux et nous ont amener à découvrir de nouvelles technologies permetant l'interactivité et l'affichage 3D aisni que la gestion de données (partie que j'ai personellement préféré).

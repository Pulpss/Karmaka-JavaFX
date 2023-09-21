# LO02 - Jeu Karmaka

Ce projet est la recréation partielle du jeu de cartes Karmaka. Le projet a été réalisé dans le cadre de l'UE LO02 en tant que livrable.

## Démarrer le jeu

Afin de démarrer le jeu il faut utiliser `gradle`. Selon les systèmes d'exploitation, la commande peut varier mais en général il faut suivre ces commandes :

```bash
./gradlew
./gradlew run
```
ou sur Windows
```bash
./gradlew.bat
./gradlew.bat run
```

## Créer l'exécutable

Pour pouvoir obtenir un fichier `.jar` qui peut être exécuté plus simplement, on peut utiliser la commande spécifique suivante :

```bash
./gradlew
./gradlew fatJar
```
ou sur Windows
```bash
./gradlew.bat
./gradlew.bat fatJar
```

On va ensuite pouvoir lancer l'exécutable grace à la commande suivante :

```bash
java -jar ./app/build/libs/app-all.jar
```

## Technologies utilisées

Le jeu repose pour l'instant sur le plugin JavaFX pour l'interface graphique. Et prends avantage de gradle pour faciliter le développement.
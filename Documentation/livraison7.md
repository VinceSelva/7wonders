# Fonctionnalités implémentées
* Ajout d'une fonction pour détailler le score
* Ajout d'un type de client "intelligent" (qui choisit de préférence une carte qu'il peut poser)
* Affichage des numéros de tours
* Levée d'exception dans Deck lorsque le paquet est vide

# Tâches réalisées
* Ajout de documentation dans Client, Server et Game
* Ajout de tests

# Bilan de l'itération
Durant cette itération nous nous sommes concentrés sur les retours du client, l'ajout de documentation ainsi que les tests.
Maintenant, lorsqu'on lance le jeu, 4 joueurs se connectent, leurs identifiants sont envoyés au serveur. Puis chacun des joueurs reçoit ses 7 cartes aléatoirement ainsi qu'une merveille.
Puis, la partie commence et quand ils jouent ils s'échangent leur paquet avec leur voisin. Les cartes sont toujours envoyées en JSON.
Le score est calculé et détaillé en fin de partie et les numéros de tours sont affichés.

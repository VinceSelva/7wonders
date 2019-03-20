# Fonctionnalités implémentées
* Ajout des coûts de constructions
* Ajout de l'age II
* Ajout du deck de l'age II
* Ajout des batiments commerciaux
* Ajout de test
* Calcul du score
* Ajout d'un 4ème joueur

# Tâches réalisées
* Développement de la classe deckAgeII
* Développement de la classe Cost
* Développement de la classe Card
* Développement de la classe Client
* Développement de la classe Server
* Développement de la classe Participant
* Développement de la classe Age
* Modification de la classe deckAgeI
* Modification des uses cases
* Ajout du plug-in exec maven dans le pom
* Tests unitaires Junit

# Bilan de l'itération
Actuellement, quand on lance le jeu, 4 joueurs se connectent, leurs identifiants sont envoyés au serveur. Puis chacun des joueurs reçoit 4 cartes aléatoirement ainsi qu'une merveille.
Puis, la partie commence et quand ils jouent ils s'échangent leur paquet avec leur voisin. Les cartes sont toujours envoyées en JSON.
Nous n'avons pas encore ajouté l'age II au déroulement de la partie.
Le score est calculé en fin de partie.

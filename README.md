# RayTracing Avec Java-RMI
**Introduction**

Ce projet a abordé l'aspect crucial de la programmation répartie : le calcul parallèle, plus spécifiquement, la parallélisation des données. L'objectif était de réaliser un traitement intensif de données, impossible à accomplir sur un ordinateur personnel. Pour cela, nous avons utilisé un ensemble de machines pour décomposer le calcul en plusieurs parties, chacune étant transmise à une machine différente pour être calculée. Les résultats ont ensuite été combinés pour obtenir le résultat final.

# Le Tracé de Rayon
Dans ce projet, nous nous sommes concentrés sur une tâche qui nécessite une grande quantité de cycles CPU : la synthèse d'images, plus précisément, le tracé de rayon (raytracing). Nous avons utilisé une implantation naïve en Java d'un algorithme de synthèse d'images. La scène calculée était décrite dans le fichier simple.txt.

En expérimentant avec différents paramètres du programme, nous avons observé le temps d'exécution en fonction de la taille de l'image calculée. Les résultats ont été illustrés sous forme de courbe, montrant une corrélation entre le temps de calcul et la taille de l'image.

# Accélération des Calculs
Le constat a été que le calcul d'une image de haute résolution demandait beaucoup de temps. Pour y remédier, nous avons décidé de paralléliser les calculs sur un ensemble de machines. L'architecture consistait en :

Un ensemble de nœuds de calcul, capable de calculer une partie d'une scène,
Un serveur de nœuds pour récupérer les coordonnées des nœuds de calcul,
Un programme pour découper le calcul, récupérer les coordonnées des nœuds disponibles, envoyer un calcul sur chacun et afficher le résultat.
En faisant cela, nous avons réussi à accélérer les calculs en distribuant les tâches sur plusieurs machines, prouvant ainsi l'efficacité de la parallélisation des données.

# Réalisation
Le processus de réalisation de cette application distribuée a comporté l'identification des processus fixes et éphémères, la compréhension des types de données échangées entre les processus et la mise en œuvre de la parallélisation pour effectuer les calculs simultanément sur différentes machines.

Dans l'ensemble, ce projet a démontré la puissance et l'efficacité du calcul parallèle dans la gestion des tâches intensives en termes de calcul, ouvrant la voie à une multitude d'applications dans le domaine de la programmation répartie.

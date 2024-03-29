import raytracer.*;
import serveur.AucunServiceException;
import serveur.InterfaceServiceRaytracing;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LancerCalcul {
    public static void main(String[] args) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        String ip;
        int port;
        if (args.length != 2) {
            System.out.println("Entrez l'IP");
            ip = sc.nextLine();
            System.out.println("Entrez le port");
            port = sc.nextInt();
        } else {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        // On ajoute le scanner qui permet d'entrer les données:
        String fichier_description = "simple.txt", nomService = "ServiceRaytracing";

        System.out.println("Veuillez entrer la hauteur de la scène :");
        int hauteur = sc.nextInt();
        System.out.println("Veuillez entrer la largeur de la scène :");
        int largeur = sc.nextInt();
        System.out.println("Veuillez entrer le nombre de découpage :");
        int nbDecoupage = sc.nextInt();
        sc.close();

        // on récupére l'annuaire du serveur central:
        Registry reg;
        InterfaceServiceRaytracing serviceRaytracing;
        try {
            reg = LocateRegistry.getRegistry(ip, port);
            serviceRaytracing = (InterfaceServiceRaytracing) reg.lookup(nomService);
            System.out.println("Connexion au serveur réussie");
        } catch (RemoteException e) {
            System.out.println("Erreur de connexion au serveur");
            e.printStackTrace();
            return;
        } catch (NotBoundException e) {
            System.out.println("Impossible de récupérer le service");
            e.printStackTrace();
            return;
        }

        // création d'une fenêtre
        Disp disp = new Disp("Raytracer", largeur, hauteur);

        // Initialisation d'une scène depuis le modèle
        Scene scene = new Scene(fichier_description, largeur, hauteur);

        int largeurDecoupage = largeur / nbDecoupage;
        int hauteurDecoupage = hauteur / nbDecoupage;

        List<Thread> threads = new ArrayList<>();
        Instant debut = Instant.now();
        // On découpe l'image en nbDecoupage x
        System.out.println("Découpage de l'image en " + nbDecoupage + "x" + nbDecoupage + " images");
        for (int i = 0; i < nbDecoupage; i++) {
            for (int j = 0; j < nbDecoupage; j++) {
                final int debutX = i * largeurDecoupage;
                final int debutY = j * hauteurDecoupage;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean calculTermine = false;
                        while (!calculTermine) {
                            try {
                                InterfaceServiceCalcul service;
                                try {
                                    service = serviceRaytracing.getMachineQuiCalcul();
                                } catch (AucunServiceException e) {
                                    System.out.println("Aucun service disponible");
                                    System.exit(400);
                                    return;
                                }
                                try {
                                    Image image = service.calculerBoutScene(scene, debutX, debutY, largeurDecoupage,
                                            hauteurDecoupage);
                                    disp.setImage(image, debutX, debutY);
                                    calculTermine = true;
                                } catch (RemoteException e) {
                                    System.out.println(
                                            "Erreur de connexion a un client pour le bloc " + debutX + ":" + debutY);
                                    try {
                                        serviceRaytracing.supprimerMachineQuiCalcul(service);
                                    } catch (RemoteException ex) {
                                        System.out.println("Impossible de supprimer la machine qui calcul");
                                        System.out.println("Serveur de calcul indisponible");
                                        ex.printStackTrace();
                                    }
                                }
                            } catch (RemoteException e) {

                            }
                        }
                    }
                });
                // thread.start();
                threads.add(thread);
            }
        }
        System.out.println("Lancement des threads (temps d'initialisation :ms)");
        for (Thread thread : threads) {
            thread.start();
        }

        // Attendre la fin de tous les threads
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // Gérer l'exception appropriée ici
                e.printStackTrace();
            }
        }
        Instant fin = Instant.now();
        Duration duree = Duration.between(debut, fin);
        System.out.println("Temps de calcul : " + duree.toMillis() + "ms");

    }
}

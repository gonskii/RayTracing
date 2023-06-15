import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;
import serveur.InterfaceServiceRaytracing;
import servicedecalcul.InterfaceServiceCalcul;

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
        String ip = args[0], fichier_description = "simple.txt", nomService = "ServiceRaytracing";
        int port = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer la hauteur de la scène :");
        int hauteur = sc.nextInt();
        System.out.println("Veuillez entrer la largeur de la scène :");
        int largeur = sc.nextInt();
        System.out.println("Veuillez entrer le nombre de découpage :");
        int nbDecoupage = sc.nextInt();

        sc.close();
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
                final InterfaceServiceCalcul service = serviceRaytracing.getMachineQuiCalcul();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean calculTermine = false;
                        while (!calculTermine) {
                            try {
                                InterfaceServiceCalcul service = serviceRaytracing.getMachineQuiCalcul();
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
                        }
                    }
                });
                thread.start();
                threads.add(thread);
            }
        }
        System.out.println("Lancement des threads (temps d'initialisation :ms)");
        /*
         * for (Thread thread : threads) {
         * thread.start();
         * }
         */

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

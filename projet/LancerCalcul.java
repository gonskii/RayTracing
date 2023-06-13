import raytracer.Disp;
import raytracer.Image;
import raytracer.Scene;
import serveur.InterfaceServiceRaytracing;
import servicedecalcul.InterfaceServiceCalcul;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LancerCalcul {
    public static void main(String[] args) throws RemoteException {
        String ip = args[0], fichier_description = "simple.txt", nomService = "ServiceRaytracing";
        int port = Integer.parseInt(args[1]);
        int largeur = 512, hauteur = 512, nbDecoupage = 5;

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

        // On découpe l'image en nbDecoupage x
        System.out.println("Découpage de l'image en " + nbDecoupage + "x" + nbDecoupage + " images");
        for (int i = 0; i < nbDecoupage; i++) {
            for (int j = 0; j < nbDecoupage; j++) {
                final int x = i, y = j;
                // On récupère le service de calcul
                InterfaceServiceCalcul service = serviceRaytracing.getMachineQuiCalcul();
                // On lance le calcul
                Image image = service.calculerBoutScene(scene, x * largeurDecoupage, y * hauteurDecoupage,
                        largeurDecoupage,
                        hauteurDecoupage);

                // On affiche le résultat
                disp.setImage(image, i * largeurDecoupage, j * hauteurDecoupage);
            }
        }
    }
}

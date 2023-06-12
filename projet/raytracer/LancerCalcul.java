import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


public class LancerCalcul {
    public static void main(String[] args) {
        String ip = "charlemagne.iutnc.univ-lorraine.fr", fichier_description = "simple.txt", nomService = "contacts";
        int port = 1935;
        int largeur = 512, hauteur = 512, nbDecoupage = 5;

        Registry reg;
        ServiceRatracing rd;
        try {
            reg = LocateRegistry.getRegistry(ip, port);
            rd = (ServiceRatracing) reg.lookup(nomService);
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
        ArrayList<ServiceCalcul> listMachine = rd.getListeMachineQuiCalcul();
        System.out.println("Nombre de machine : " + listMachine.size());

        int largeurDecoupage = largeur / nbDecoupage;
        int hauteurDecoupage = hauteur / nbDecoupage;

        // On découpe l'image en nbDecoupage x
        int serviceActuel = 0;
        for (int i = 0; i < nbDecoupage; i++) {
            for (int j = 0; j < nbDecoupage; j++) {
                final int x = i, y = j;
                // On récupère le service de calcul
                ServiceCalcul service = listMachine.get(serviceActuel);
                serviceActuel = (serviceActuel + 1) % listMachine.size();

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

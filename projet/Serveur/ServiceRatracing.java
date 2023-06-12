package projet.Serveur;

import java.lang.reflect.Array;
import java.util.ArrayList;

import projet.ServiceDeCalcul.ServiceCalcul;

public class ServiceRatracing {
    
    // Liste des clients
    private ArrayList<ServiceCalcul> machineQuiCalcul = new ArrayList<>();


    /**
     * Méthode permettant de supprimer un client de la liste des clients
     */
    public void supprimerMachineQuiCalcul(ServiceCalcul machineQuiCalcul) {
        this.machineQuiCalcul.remove(machineQuiCalcul);
    }


    /**
     * méthode enregistrant un client dans la liste des clients
     */
    public void enregistrerMachineQuiCalcul(ServiceCalcul machineQuiCalcul) {
        this.machineQuiCalcul.add(machineQuiCalcul);
    }

    /**
     * méthode getListeMachineQuiCalcul
     * @return la liste des machine qui calcul
     */
    public ArrayList<ServiceCalcul> getListeMachineQuiCalcul() {
        return this.machineQuiCalcul;
    }

}

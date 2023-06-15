package serveur;

import servicedecalcul.InterfaceServiceCalcul;

import java.util.ArrayList;

public class ServiceRaytracing implements InterfaceServiceRaytracing {

    // Liste des clients
    private ArrayList<InterfaceServiceCalcul> machineQuiCalcul = new ArrayList<>();
    private int prochaineMachine = 0;

    @Override
    public void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        System.out.println("Machine supprimee, nombre de machines : " + (this.machineQuiCalcul.size() + 1));
        this.machineQuiCalcul.remove(machineQuiCalcul);
    }

    @Override
    public void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        System.out.println("Nouvelle machine enregistree, nombre de machines : " + (this.machineQuiCalcul.size() + 1));
        this.machineQuiCalcul.add(machineQuiCalcul);
    }

    @Override
    public InterfaceServiceCalcul getMachineQuiCalcul() {
        System.out.println("Récupération de la machine " + prochaineMachine);
        prochaineMachine = (prochaineMachine + 1) % this.machineQuiCalcul.size();
        return this.machineQuiCalcul.get(prochaineMachine);
    }
}

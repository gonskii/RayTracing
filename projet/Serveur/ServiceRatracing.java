import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServiceRatracing implements InterfaceServiceRatracing {

    // Liste des clients
    private ArrayList<InterfaceServiceCalcul> machineQuiCalcul = new ArrayList<>();
    private int prochaineMachine = 0;

    /**
     * Méthode permettant de supprimer un client de la liste des clients
     * 
     * @param machineQuiCalcul
     */
    public void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        this.machineQuiCalcul.remove(machineQuiCalcul);
    }

    /**
     * méthode enregistrant un client dans la liste des clients
     * 
     * @param machineQuiCalcul
     */
    public void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        this.machineQuiCalcul.add(machineQuiCalcul);
    }

    /**
     * méthode getListeMachineQuiCalcul
     * 
     * @return une machine qui calcul
     */
    public InterfaceServiceCalcul getMachineQuiCalcul() {
        prochaineMachine = (prochaineMachine + 1) % this.machineQuiCalcul.size();
        return this.machineQuiCalcul.get(prochaineMachine);
    }

}

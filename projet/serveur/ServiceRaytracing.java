import java.util.ArrayList;

public class ServiceRaytracing implements InterfaceServiceRaytracing {

    // Liste des clients
    private ArrayList<InterfaceServiceCalcul> machineQuiCalcul = new ArrayList<>();
    private int prochaineMachine = 0;

    @Override
    public synchronized void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        System.out.println("Machine supprimee, nombre de machines : " + (this.machineQuiCalcul.size() + 1));
        this.machineQuiCalcul.remove(machineQuiCalcul);
    }

    @Override
    public void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) {
        System.out.println("Nouvelle machine enregistree, nombre de machines : " + (this.machineQuiCalcul.size() + 1));
        this.machineQuiCalcul.add(machineQuiCalcul);
    }

    @Override
    public InterfaceServiceCalcul getMachineQuiCalcul() throws AucunServiceException {
        if (this.machineQuiCalcul.size() == 0) {
            throw new AucunServiceException();
        }
        prochaineMachine = (prochaineMachine + 1) % this.machineQuiCalcul.size();
        System.out.println("Récupération de la machine " + (prochaineMachine + 1));
        return this.machineQuiCalcul.get(prochaineMachine);
    }
}

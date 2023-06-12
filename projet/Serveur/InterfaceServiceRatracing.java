import java.util.ArrayList;

public interface InterfaceServiceRatracing {
    
    public void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul);

    public void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul);

    public ArrayList<InterfaceServiceCalcul> getListeMachineQuiCalcul();

}

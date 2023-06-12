import java.rmi.Remote;
import java.util.ArrayList;

public interface InterfaceServiceRatracing extends Remote{
    
    public void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul);

    public void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul);

    public ArrayList<InterfaceServiceCalcul> getListeMachineQuiCalcul();

}

package projet.Serveur;

import java.util.ArrayList;

import projet.ServiceDeCalcul.ServiceCalcul;

public interface InterfaceServiceRatracing {
    
    public void supprimerMachineQuiCalcul(ServiceCalcul machineQuiCalcul);

    public void enregistrerMachineQuiCalcul(ServiceCalcul machineQuiCalcul);

    public ArrayList<ServiceCalcul> getListeMachineQuiCalcul();

}

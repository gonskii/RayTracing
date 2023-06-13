package serveur;

import servicedecalcul.InterfaceServiceCalcul;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServiceRaytracing extends Remote {
    void supprimerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) throws RemoteException;

    void enregistrerMachineQuiCalcul(InterfaceServiceCalcul machineQuiCalcul) throws RemoteException;

    InterfaceServiceCalcul getMachineQuiCalcul() throws RemoteException ;

}

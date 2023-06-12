package projet.Serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LanceService {
    public static void main(String[] args) throws RemoteException {

        try {
            ServiceRatracing service = new ServiceRatracing();
            InterfaceServiceRatracing rd = (InterfaceServiceRatracing) UnicastRemoteObject.exportObject(service, 0);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("ServiceRatracing", (Remote) rd);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}

import serveur.InterfaceServiceRaytracing;
import serveur.ServiceRaytracing;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LanceServiceCentrale {
    public static void main(String[] args) {

        try {
            int port = 1099;
            ServiceRaytracing service = new ServiceRaytracing();
            InterfaceServiceRaytracing rd = (InterfaceServiceRaytracing) UnicastRemoteObject.exportObject(service, 0);
            Registry reg = LocateRegistry.createRegistry(port);
            reg.rebind("ServiceRaytracing", (Remote) rd);
            System.out.print("Serveur lancé sur l'ip ");
            InetAddress ip = InetAddress.getLocalHost();
            System.out.print(ip.getHostAddress() + ":" + port + "\n");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}

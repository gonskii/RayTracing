import serveur.InterfaceServiceRaytracing;
import servicedecalcul.InterfaceServiceCalcul;
import servicedecalcul.ServiceCalcul;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LancerMachineCalcul {
    public static void main(String[] args) throws Exception {
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        Registry reg = LocateRegistry.getRegistry(ip, port);
        InterfaceServiceRaytracing serviceRaytracing = (InterfaceServiceRaytracing) reg.lookup("ServiceRaytracing");
        
        ServiceCalcul servicecalcul = new ServiceCalcul();
        InterfaceServiceCalcul serviceCalculRemote = (InterfaceServiceCalcul) UnicastRemoteObject.exportObject(servicecalcul, 0);
        serviceRaytracing.enregistrerMachineQuiCalcul(serviceCalculRemote);
        System.out.println("ServiceCalcul enregistré auprès du serveur");
    }
}

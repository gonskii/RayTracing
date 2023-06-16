import serveur.InterfaceServiceRaytracing;
import servicedecalcul.InterfaceServiceCalcul;
import servicedecalcul.ServiceCalcul;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class LancerMachineCalcul {
    public static void main(String[] args) throws Exception {
        //Utilisation des scanners:
        Scanner sc = new Scanner(System.in); 
        String ip = sc.nextLine();
        int port = sc.nextInt();
        sc.close();

        Registry reg = LocateRegistry.getRegistry(ip, port);
        InterfaceServiceRaytracing serviceRaytracing = (InterfaceServiceRaytracing) reg.lookup("ServiceRaytracing");
        
        ServiceCalcul servicecalcul = new ServiceCalcul();
        InterfaceServiceCalcul serviceCalculRemote = (InterfaceServiceCalcul) UnicastRemoteObject.exportObject(servicecalcul, 0);
        serviceRaytracing.enregistrerMachineQuiCalcul(serviceCalculRemote);
        System.out.println("ServiceCalcul enregistré auprès du serveur");
    }
}

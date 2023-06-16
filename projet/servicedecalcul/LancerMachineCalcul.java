import raytracer.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class LancerMachineCalcul {
    public static void main(String[] args) throws Exception {
        String ip;
        int port;
        if (args.length != 2) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez l'ip du serveur");
            ip = sc.nextLine();
            System.out.println("Entrez le port du serveur");
            port = sc.nextInt();
            sc.close();
        } else {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }

        Registry reg = LocateRegistry.getRegistry(ip, port);
        InterfaceServiceRaytracing serviceRaytracing = (InterfaceServiceRaytracing) reg.lookup("ServiceRaytracing");

        ServiceCalcul servicecalcul = new ServiceCalcul();
        InterfaceServiceCalcul serviceCalculRemote = (InterfaceServiceCalcul) UnicastRemoteObject
                .exportObject(servicecalcul, 0);
        serviceRaytracing.enregistrerMachineQuiCalcul(serviceCalculRemote);
        System.out.println("ServiceCalcul enregistré auprès du serveur");
    }
}

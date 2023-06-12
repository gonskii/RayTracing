package projet.ServiceDeCalcul;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import projet.Serveur.ServiceRatracing;
import projet.raytracer.Image;
import projet.raytracer.Scene;

public class ServiceCalcul implements InterfaceServiceCalcul {

    /**
     * Constructeur ServiceCalcul
     * qui s'enregistre auprès du service de nom
     * @param ip
     * @param port
     */
    public ServiceCalcul(String ip, int port) throws Exception{
        Registry reg = LocateRegistry.getRegistry(ip, port);
        ServiceRatracing serviceRatracing = (ServiceRatracing) reg.lookup("ServiceRatracing");
        serviceRatracing.enregistrerMachineQuiCalcul(this);
    }

    /**
     * méthode calculerBoutScene
     * @param Scene
     * @param x
     * @param y
     * @param largeur
     * @param hauteur
     */
    @Override
    public Image calculerBoutScene(Scene Scene, int x, int y, int largeur, int hauteur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculerBoutScene'");
    }

    
}

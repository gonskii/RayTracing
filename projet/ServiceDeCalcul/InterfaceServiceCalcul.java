package servicedecalcul;

import raytracer.Image;
import raytracer.Scene;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceServiceCalcul extends Remote {
    Image calculerBoutScene(Scene scene, int x, int y, int largeur, int hauteur) throws RemoteException;
}

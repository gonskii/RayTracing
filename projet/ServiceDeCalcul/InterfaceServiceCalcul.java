import java.rmi.Remote;

public interface InterfaceServiceCalcul extends Remote  {
    public Image calculerBoutScene(Scene Scene, int x, int y, int largeur, int hauteur);
}

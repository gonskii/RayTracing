package projet.ServiceDeCalcul;

import projet.raytracer.Image;
import projet.raytracer.Scene;

public interface InterfaceServiceCalcul {


    public Image calculerBoutScene(Scene Scene, int x, int y, int largeur, int hauteur);
}

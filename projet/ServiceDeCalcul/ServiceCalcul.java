package servicedecalcul;

import raytracer.Image;
import raytracer.Scene;

import java.io.Serializable;

public class ServiceCalcul implements InterfaceServiceCalcul, Serializable {

    /**
     * méthode calculerBoutScene
     *
     * @param scene   la scène à calculer
     * @param x       coordonnée x du coin supérieur gauche de la partie de la scène à calculer
     * @param y       coordonnée y du coin supérieur gauche de la partie de la scène à calculer
     * @param largeur largeur de la partie de la scène à calculer
     * @param hauteur hauteur de la partie de la scène à calculer
     */
    @Override
    public Image calculerBoutScene(Scene scene, int x, int y, int largeur, int hauteur) {
        System.out.println("Calcul de la partie de la scène : " + x + " " + y + " " + largeur + " " + hauteur);
        Image image = scene.compute(x, y, largeur, hauteur);
        System.out.println("Calcul terminé");
        return image;
    }
}

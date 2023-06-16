import raytracer.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

public class ServiceCalcul implements InterfaceServiceCalcul, Serializable {

    /**
     * méthode calculerBoutScene
     *
     * @param scene   la scène à calculer
     * @param x       coordonnée x du coin supérieur gauche de la partie de la scène
     *                à calculer
     * @param y       coordonnée y du coin supérieur gauche de la partie de la scène
     *                à calculer
     * @param largeur largeur de la partie de la scène à calculer
     * @param hauteur hauteur de la partie de la scène à calculer
     */
    @Override
    public Image calculerBoutScene(Scene scene, int x, int y, int largeur, int hauteur) {
        System.out.println("Calcul de la partie de la scene : " + x + " " + y + " " + largeur + " " + hauteur);
        Instant debut = Instant.now();
        Image image = scene.compute(x, y, largeur, hauteur);
        Instant fin = Instant.now();
        long duree = Duration.between(debut, fin).toMillis();

        System.out.println("Calcul termine en:" + duree + " ms");
        return image;
    }
}

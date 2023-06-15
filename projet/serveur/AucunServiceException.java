package serveur;

public class AucunServiceException extends Exception {
    public AucunServiceException() {
        super("Aucun service disponible");
    }
}

package volkodav.ampilogova.darya.projecte_github;

public class Denominacio {

    // ATRIBUTS
    private long idDenominacio;
    private String nomDenominacio;

    // CONSTRUCTOR
    public Denominacio(long idDenominacio, String nomDenominacio) {
        this.idDenominacio = idDenominacio;
        this.nomDenominacio = nomDenominacio;
    }

    // MÈTODES GETTER I SETTER
    public long getIdDenominacio() {
        return idDenominacio;
    }

    public void setIdDenominacio(long idDenominacio) {
        this.idDenominacio = idDenominacio;
    }

    public String getNomDenominacio() {
        return nomDenominacio;
    }

    public void setNomDenominacio(String nomDenominacio) {
        this.nomDenominacio = nomDenominacio;
    }
}

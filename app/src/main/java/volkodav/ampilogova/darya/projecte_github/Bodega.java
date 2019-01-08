package volkodav.ampilogova.darya.projecte_github;

public class Bodega {

    // ATRIBUTS DE BODEGA
    private long idBodega;
    private String nomBodega;

    // CONSTRUCTOR
    public Bodega(long idBodega, String nomBodega) {
        this.idBodega = idBodega;
        this.nomBodega = nomBodega;
    }

    // MÈTODES GETTER I SETTER
    public long getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(long idBodega) {
        this.idBodega = idBodega;
    }

    public String getNomBodega() {
        return nomBodega;
    }

    public void setNomBodega(String nomBodega) {
        this.nomBodega = nomBodega;
    }
}

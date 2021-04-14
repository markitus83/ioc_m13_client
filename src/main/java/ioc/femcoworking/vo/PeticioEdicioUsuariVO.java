
package ioc.femcoworking.vo;

/**
 * Classe que representa el ValueObject de PeticioEdicioUsuari
 * 
 * @author Marc Ginovart Vega
 */
public class PeticioEdicioUsuariVO {
    private String nom;
    private String cifEmpresa;
    private String direccio;
    private String poblacio;
    private String provincia;

    public PeticioEdicioUsuariVO(
        String nom, 
        String cifEmpresa, 
        String direccio, 
        String poblacio, 
        String provincia
    ) {
        this.nom = nom;
        this.cifEmpresa = cifEmpresa;
        this.direccio = direccio;
        this.poblacio = poblacio;
        this.provincia = provincia;
    }

    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCifEmpresa() {
        return cifEmpresa;
    }

    public void setCifEmpresa(String cifEmpresa) {
        this.cifEmpresa = cifEmpresa;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}

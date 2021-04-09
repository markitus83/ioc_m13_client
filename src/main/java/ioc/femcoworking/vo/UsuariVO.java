
package ioc.femcoworking.vo;

import java.util.Date;
import resources.Rol;

/**
 * Classe que representa el ValueObject d'Usuari
 * 
 * @author Marc Ginovart Vega
 */
public class UsuariVO {
    private String idUsuari;
    private String email;
    private String contrasenya;
    private Rol rol;
    private String nom;
    private String cifEmpresa;
    private String direccio;
    private String poblacio;
    private String provincia;
    private Date dataCreacio;
    private Date ultimAcces;
    private Boolean deshabilitat;

    public UsuariVO() {
    }

    public UsuariVO(
        String email, 
        String contrasenya, 
        Rol rol, 
        String nom, 
        String cifEmpresa, 
        String direccio, 
        String poblacio, 
        String provincia, 
        Boolean deshabilitat
    ) {
        this.nom = nom;
        this.email = email;
        this.contrasenya = contrasenya;
        this.rol = rol;
        this.cifEmpresa = cifEmpresa;
        this.direccio = direccio;
        this.poblacio = poblacio;
        this.provincia = provincia;
        this.deshabilitat = deshabilitat;
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getUltimAcces() {
        return ultimAcces;
    }

    public void setUltimAcces(Date ultimAcces) {
        this.ultimAcces = ultimAcces;
    }

    public Boolean getDeshabilitat() {
        return deshabilitat;
    }

    public void setDeshabilitat(Boolean deshabilitat) {
        this.deshabilitat = deshabilitat;
    }
}

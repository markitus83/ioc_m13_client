
package ioc.femcoworking.vo;

import resources.Oficina;

/**
 * Classe que representa el ValueObject de Oficina
 * 
 * @author Marc Ginovart Vega
 */
public class OficinaVO {
    private String idOficina;
    private String nom;
    private Oficina tipusOficina;
    private Integer capacitat;
    private Float preu;
    private String serveis;
    private Boolean habilitada;
    private String direccio;
    private String poblacio;
    private String provincia;

    public OficinaVO() {
    }

    public String getIdOficina() {
        return idOficina;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Oficina getTipusOficina() {
        return tipusOficina;
    }

    public void setTipusOficina(Oficina tipusOficina) {
        this.tipusOficina = tipusOficina;
    }

    public Integer getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(Integer capacitat) {
        this.capacitat = capacitat;
    }

    public Float getPreu() {
        return preu;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    public String getServeis() {
        return serveis;
    }

    public void setServeis(String serveis) {
        this.serveis = serveis;
    }

    public Boolean getHabilitada() {
        return habilitada;
    }

    public void setHabilitada(Boolean habilitada) {
        this.habilitada = habilitada;
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

package ioc.femcoworking.vo;

import java.util.Date;
import resources.Categoria;

/**
 * Classe que representa el ValueObject d'Oficina
 * 
 * @author Marc Ginovart Vega
 */
public class OficinaVO {
    private String idOficina;
    private String nom;
    private Categoria tipus;
    private Integer capacitat;
    private Double preu;
    private String serveis;
    private Boolean habilitada;
    private String provincia;
    private String poblacio;
    private String direccio;
    private Date dataCreacio;
    private Date dataUltimaEdicio;
    private Boolean eliminat;

    public OficinaVO() {
    }

    public OficinaVO(
        String nom,
        Categoria categoria,
        Integer capacitat,
        Double preu,
        String serveis,
        String provincia,
        String poblacio,
        String direccio,
        Boolean habilitada,
        Boolean eliminat
    ) {
        this.nom = nom;
        this.tipus = categoria;
        this.capacitat = capacitat;
        this.preu = preu;
        this.serveis = serveis;
        this.provincia = provincia;
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.habilitada = habilitada;
        this.eliminat = eliminat;
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

    public Categoria getTipus() {
        return tipus;
    }

    public void setTipus(Categoria tipusOficina) {
        this.tipus = tipusOficina;
    }

    public Integer getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(Integer capacitat) {
        this.capacitat = capacitat;
    }

    public Double getPreu() {
        return preu;
    }

    public void setPreu(Double preu) {
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

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }

    public Date getDataUltimaEdicio() {
        return dataUltimaEdicio;
    }

    public void setDataUltimaEdicio(Date dataUltimaEdicio) {
        this.dataUltimaEdicio = dataUltimaEdicio;
    }

    public Boolean isEliminat() {
        return eliminat;
    }

    public void setEliminat(boolean eliminat) {
        this.eliminat = eliminat;
    }
    
    public String toString() {
        return this.nom;
    }
}
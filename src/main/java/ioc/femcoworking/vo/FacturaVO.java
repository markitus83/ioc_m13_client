
package ioc.femcoworking.vo;

import java.util.Date;
import resources.Categoria;

/**
 * Classe que representa el ValueObject de Facturació
 * 
 * @author Marc Ginovart Vega
 */
public class FacturaVO {
    private String idFactura;
    private String numeroFactura;
    private ReservaVO idReserva;
    private UsuariVO idUsuari;
    private Double subTotal;
    private Double impostos;
    private Double total;
    
    private String numeroReserva;
    private String nomUsuariReserva;
    private String nomOficina;
    private Categoria tipusOficina;
    private Double preuOficina;
    private String serveisOficina;
    private Date data_inici_reserva;
    private Date data_fi_reserva;
    private Date dataCreacio;

    public FacturaVO() {
    }

    public FacturaVO(String idFactura, ReservaVO idReserva, UsuariVO idUsuari, Double subTotal, Double impostos, Double total, String numeroReserva, String nomUsuariReserva, String nomOficina, Categoria tipusOficina, Double preuOficina, String serveisOficina, Date data_inici_reserva, Date data_fi_reserva, Date dataCreacio) {
        this.idFactura = idFactura;
        this.idReserva = idReserva;
        this.idUsuari = idUsuari;
        this.subTotal = subTotal;
        this.impostos = impostos;
        this.total = total;
        this.numeroReserva = numeroReserva;
        this.nomUsuariReserva = nomUsuariReserva;
        this.nomOficina = nomOficina;
        this.tipusOficina = tipusOficina;
        this.preuOficina = preuOficina;
        this.serveisOficina = serveisOficina;
        this.data_inici_reserva = data_inici_reserva;
        this.data_fi_reserva = data_fi_reserva;
        this.dataCreacio = dataCreacio;
    }

    

    public String getIdFactura() {
        return idFactura;
    }

    public ReservaVO getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(ReservaVO idReserva) {
        this.idReserva = idReserva;
    }

    public UsuariVO getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(UsuariVO idUsuari) {
        this.idUsuari = idUsuari;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getImpostos() {
        return impostos;
    }

    public void setImpostos(Double impostos) {
        this.impostos = impostos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    //----------

    public String getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public String getNomUsuariReserva() {
        return nomUsuariReserva;
    }

    public void setNomUsuariReserva(String nomUsuariReserva) {
        this.nomUsuariReserva = nomUsuariReserva;
    }

    public String getNomOficina() {
        return nomOficina;
    }

    public void setNomOficina(String nomOficina) {
        this.nomOficina = nomOficina;
    }

    public Categoria getTipusOficina() {
        return tipusOficina;
    }

    public void setTipusOficina(Categoria tipusOficina) {
        this.tipusOficina = tipusOficina;
    }

    public Double getPreuOficina() {
        return preuOficina;
    }

    public void setPreuOficina(Double preuOficina) {
        this.preuOficina = preuOficina;
    }

    public String getServeisOficina() {
        return serveisOficina;
    }

    public void setServeisOficina(String serveisOficina) {
        this.serveisOficina = serveisOficina;
    }

    public Date getData_inici_reserva() {
        return data_inici_reserva;
    }

    public void setData_inici_reserva(Date data_inici_reserva) {
        this.data_inici_reserva = data_inici_reserva;
    }

    public Date getData_fi_reserva() {
        return data_fi_reserva;
    }

    public void setData_fi_reserva(Date data_fi_reserva) {
        this.data_fi_reserva = data_fi_reserva;
    }

    public Date getDataCreacio() {
        return dataCreacio;
    }

    public void setDataCreacio(Date dataCreacio) {
        this.dataCreacio = dataCreacio;
    }
    
    
}

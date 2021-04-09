
package ioc.femcoworking.vo;

import java.util.Date;

/**
 * Classe que representa el ValueObject de Reserva
 * 
 * @author Marc Ginovart Vega
 */
public class ReservaVO {
    private String idReserva;
    private String idSala;
    private String idUsuari;
    private Date dataInici;
    private Date dataFi;

    public ReservaVO() {
    }

    public ReservaVO(String idSala, String idUsuari, Date dataInici, Date dataFi) {
        this.idSala = idSala;
        this.idUsuari = idUsuari;
        this.dataInici = dataInici;
        this.dataFi = dataFi;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(String idUsuari) {
        this.idUsuari = idUsuari;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }
    
    
}

package ioc.femcoworking.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

/**
 * Classe que representa el ValueObject de Reserva
 * 
 * @author Marc Ginovart Vega
 */
public class ReservaVO {
    private String idReserva;
    private OficinaVO idOficina;
    private UsuariVO idUsuari;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataIniciReserva;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataFiReserva;

    public ReservaVO() {
    }

    public ReservaVO(OficinaVO idOficina, UsuariVO idUsuari, Date dataInici, Date dataFi) {
        this.idOficina = idOficina;
        this.idUsuari = idUsuari;
        this.dataIniciReserva = dataInici;
        this.dataFiReserva = dataFi;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public OficinaVO getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(OficinaVO idOficina) {
        this.idOficina = idOficina;
    }

    public UsuariVO getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(UsuariVO idUsuari) {
        this.idUsuari = idUsuari;
    }

    public Date getDataInici() {
        return dataIniciReserva;
    }

    public void setDataInici(Date dataInici) {
        this.dataIniciReserva = dataInici;
    }

    public Date getDataFi() {
        return dataFiReserva;
    }

    public void setDataFi(Date dataFi) {
        this.dataFiReserva = dataFi;
    }
}
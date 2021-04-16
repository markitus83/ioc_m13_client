
package ioc.femcoworking.vo;

/**
 * Classe que representa el ValueObject de PeticioAltaOficina
 * 
 * @author Marc Ginovart Vega
 */
public class PeticioAltaOficinaVO {
    private String codiAcces;
    private OficinaVO oficina;

    public String getCodiAcces() {
        return codiAcces;
    }

    public void setCodiAcces(String codiAcces) {
        this.codiAcces = codiAcces;
    }

    public OficinaVO getOficina() {
        return oficina;
    }

    public void setOficina(OficinaVO oficina) {
        this.oficina = oficina;
    }  
}

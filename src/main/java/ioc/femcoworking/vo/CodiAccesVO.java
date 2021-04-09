
package ioc.femcoworking.vo;

/**
 * Classe que representa el ValueObject de CodiAcces
 * 
 * @author Marc Ginovart Vega
 */
public class CodiAccesVO {
    private String codiAcces;

    public CodiAccesVO(String codiAcces) {
        this.codiAcces = codiAcces;
    }

    public String getCodiAcces() {
        return codiAcces;
    }

    public void setCodiAcces(String codiAcces) {
        this.codiAcces = codiAcces;
    }
}

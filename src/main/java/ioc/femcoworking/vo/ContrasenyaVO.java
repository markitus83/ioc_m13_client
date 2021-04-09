
package ioc.femcoworking.vo;

/**
 * Classe que representa el ValueObject de Contrasenya
 * 
 * @author Marc Ginovart Vega
 */
public class ContrasenyaVO {
    private String contrasenya;

    public ContrasenyaVO(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}

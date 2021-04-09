
package ioc.femcoworking.vo;

/**
 * Classe que representa el ValueObject de DadesAcces
 * 
 * @author Marc Ginovart Vega
 */
public class DadesAccesVO {
    private String email;
    private String contrasenya;

    public DadesAccesVO(String email, String contrasenya) {
        this.email = email;
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}

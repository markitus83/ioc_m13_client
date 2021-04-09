
package resources;

/**
 * Classe que representa els tipus de rol que pot tenir l'aplicació
 * 
 * @author Marc Ginovart Vega
 */
public enum Rol {
    ADMINISTRADOR("ADMINISTRADOR", 0),
    CLIENT("CLIENT", 1);
    
    private final String rolNom;
    private final int rolValor;
    
    Rol(String rolName, int rolValue) {
        this.rolNom = rolName;
        this.rolValor = rolValue;
    }
    
    public String getRolNom() {
        return rolNom;
    }
    
    public Integer getRolValor() {
        return rolValor;
    }
}

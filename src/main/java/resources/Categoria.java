
package resources;

/**
 * Classe que representa els tipus de sala que pot haver-hi
 * 
 * @author Marc Ginovart Vega
 */
public enum Categoria {
    OFICINA_PRIVADA("OFICINA_PRIVADA", 0),
    SUITE_OFICINES("SUITE_OFICINES", 1),
    ESCRIPTORI_DEDICAT("ESCRIPTORI_DEDICAT", 2);
    
    private final String salaNom;
    private final int salaValor;

    private Categoria(String salaNom, int salaValor) {
        this.salaNom = salaNom;
        this.salaValor = salaValor;
    }

    public static Categoria getOFICINA_PRIVADA() {
        return OFICINA_PRIVADA;
    }

    public static Categoria getSUITE_OFICINES() {
        return SUITE_OFICINES;
    }

    public static Categoria getESCRIPTORI_DEDICAT() {
        return ESCRIPTORI_DEDICAT;
    }

    public String getSalaNom() {
        return salaNom;
    }

    public int getSalaValor() {
        return salaValor;
    }
}

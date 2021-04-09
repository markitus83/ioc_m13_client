
package resources;

/**
 * Classe que representa els tipus de sala que pot haver-hi
 * 
 * @author Marc Ginovart Vega
 */
public enum Sala {
    INDIVIDUAL("INDIVIDUAL", 0),
    COMPARTIDA("COMPARTIDA", 1),
    REUNIONS("REUNIONS", 2),
    CONJUNTA("CONJUNTA", 3);
    
    private final String salaNom;
    private final int salaValor;

    private Sala(String salaNom, int salaValor) {
        this.salaNom = salaNom;
        this.salaValor = salaValor;
    }

    public static Sala getINDIVIDUAL() {
        return INDIVIDUAL;
    }

    public static Sala getCOMPARTIDA() {
        return COMPARTIDA;
    }

    public static Sala getREUNIONS() {
        return REUNIONS;
    }

    public static Sala getCONJUNTA() {
        return CONJUNTA;
    }

    public String getSalaNom() {
        return salaNom;
    }

    public int getSalaValor() {
        return salaValor;
    }
}

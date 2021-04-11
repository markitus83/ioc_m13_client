
package resources;

/**
 * Classe que representa els tipus de sala que pot haver-hi
 * 
 * @author Marc Ginovart Vega
 */
public enum Oficina {
    INDIVIDUAL("INDIVIDUAL", 0),
    COMPARTIDA("COMPARTIDA", 1),
    REUNIONS("REUNIONS", 2),
    CONJUNTA("CONJUNTA", 3);
    
    private final String salaNom;
    private final int salaValor;

    private Oficina(String salaNom, int salaValor) {
        this.salaNom = salaNom;
        this.salaValor = salaValor;
    }

    public static Oficina getINDIVIDUAL() {
        return INDIVIDUAL;
    }

    public static Oficina getCOMPARTIDA() {
        return COMPARTIDA;
    }

    public static Oficina getREUNIONS() {
        return REUNIONS;
    }

    public static Oficina getCONJUNTA() {
        return CONJUNTA;
    }

    public String getSalaNom() {
        return salaNom;
    }

    public int getSalaValor() {
        return salaValor;
    }
}

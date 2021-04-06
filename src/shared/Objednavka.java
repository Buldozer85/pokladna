package shared;

import java.io.Serializable;

import java.util.LinkedList;


public class Objednavka implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -4666760960562716166L;
    private int id;
    private Double cena;
    private String casObjednavky;
    private static LinkedList<Polozka> objednavka = new LinkedList<>();
    private LinkedList<Polozka> polozky = new LinkedList<>();
 
    public Double getCena() {
        return cena;
    }
    public LinkedList<Polozka> getPolozky() {
        return polozky;
    }
    public Objednavka setPolozky(LinkedList<Polozka> polozky) {
        this.polozky = polozky;
        return this;
    }
    public int getId() {
        return id;
    }
    public Objednavka setId(int id) {
        this.id = id;
        return this;
    }
    public String getCasObjednavky() {
        return casObjednavky;
    }
    public Objednavka setCasObjednavky(String casObjednavky) {
        this.casObjednavky = casObjednavky;
        return this;
    }
    public static LinkedList<Polozka> getObjednavky() {
        return objednavka;
    }
    public Objednavka setObjednavky(LinkedList<Polozka> objednavka) {
        Objednavka.objednavka = objednavka;
        return this;
    }
    public Objednavka setCena(Double cena) {
        this.cena = cena;
        return this;
    }
    
}

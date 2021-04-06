package shared;

import java.util.ArrayList;
import java.util.List;

public class Polozka extends Produkty {
    /**
     *
     */
    private static final long serialVersionUID = 4457999599152509060L;
    private int id;
    private String nazev;
    private Double cena;
    private String druh;
    private List<Pridavek> pridavky = new ArrayList<>();

    public Double getCena(){
        return cena;
    }
    public List<Pridavek> getPridavky() {
        return pridavky;
    }
    public Polozka setPridavky(List<Pridavek> pridavky) {
        this.pridavky = pridavky;
        return this;
    }
    public String getDruh() {
        return druh;
    }
    public Polozka setDruh(String druh) {
        this.druh = druh;
        return this;
    }
    public Polozka setCena(Double cena){
        this.cena = cena;
        return this;
    }

    public String getNazev(){
        return nazev;
    }
    public Polozka setNazev(String nazev){
         this.nazev = nazev;
        return this;
    }
    public int getId(){
        return id;
    }
    public Polozka setId(int id) {
        this.id = id;
        return this;
    }

    
    
}

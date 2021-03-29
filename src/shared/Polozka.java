package shared;

public class Polozka extends Produkty {
    private int id;
    private String nazev;
    private Double cena;

    public Double getCena(){
        return cena;
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

package shared;

public class Pridavek extends Produkty {
    /**
     *
     */
    private static final long serialVersionUID = -2477700579747907176L;
    private int id;
    private String nazev;
    private Double cena;
    private boolean isActive;
  

    public Double getCena(){
        return cena;
    }
 
    public boolean isActive() {
        return isActive;
    }

    public Pridavek setActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public Pridavek setCena(Double cena){
        this.cena = cena;
        return this;
    }

    public String getNazev(){
        return nazev;
    }
    public Pridavek setNazev(String nazev){
         this.nazev = nazev;
        return this;
    }
    public int getId(){
        return id;
    }
    public Pridavek setId(int id) {
        this.id = id;
        return this;
    }
}

package shared;

import java.io.Serializable;

public abstract class Produkty implements Serializable{

    private int id;

    public int getId() {
        return id;
    }
    public Produkty setId(int id) {
        this.id = id;
        return this;
    }

   
}
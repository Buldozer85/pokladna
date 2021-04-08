package shared;

import java.io.Serializable;

public abstract class Produkty implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3676984846975442355L;
    private int id;

    public int getId() {
        return id;
    }

    public Produkty setId(int id) {
        this.id = id;
        return this;
    }

}
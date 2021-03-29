package client;
import java.rmi.Naming;

import shared.Polozky;
import shared.Polozka;

public class App {
    public static void main(String[] args) throws Exception {
        
        try {
            Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
            Polozka p = new Polozka().setNazev("DvořiBurger").setCena(35.5);
            polozky.writePolozka(p);
            System.out.println("Hello, World!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        

       
        
    }
}

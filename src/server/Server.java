package server;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.Uloziste;

public class Server {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(12345);
            reg.rebind("polozky", new Polozky());
            reg.rebind("pridavky", new Pridavky());
            reg.rebind("objednavky", new Objednavky());
            reg.rebind("uloziste", new Uloziste());

            System.out.println("Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    
}

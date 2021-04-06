package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Objednavky extends Remote {
    Objednavka getObjednavka(int id) throws RemoteException;
    List<Objednavka> getObjednavky() throws RemoteException;
    boolean writeObjednavka(Objednavka Objednavka) throws RemoteException;
}

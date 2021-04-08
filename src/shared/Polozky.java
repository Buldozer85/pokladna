package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Polozky extends Remote {
   

    boolean upravPolozka(Polozka polozka) throws RemoteException;

    List<Polozka> getPolozky() throws RemoteException;

    List<Polozka> getPolozkyAdmin() throws RemoteException;

    boolean writePolozka(Polozka polozka) throws RemoteException;
}

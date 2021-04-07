package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IUloziste extends Remote {
    public  boolean zapisDoUloziste(Objednavka objednavka) throws RemoteException;
    public List<Objednavka> vratObjednavky() throws RemoteException;
     
    
}

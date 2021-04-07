package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Pridavky extends Remote {
    Pridavek getPridavek(int id) throws RemoteException;
    List<Pridavek> getPridavky() throws RemoteException;
    List<Pridavek> getPridavkyAdministrace() throws RemoteException;
    boolean writePridavek(Pridavek pridavek) throws RemoteException;
    boolean upravPridavek(Pridavek pridavek) throws RemoteException;
}

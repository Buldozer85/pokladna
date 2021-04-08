package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Pridavky extends Remote {
    

    List<Pridavek> getPridavky() throws RemoteException;

    List<Pridavek> getPridavkyAdministrace() throws RemoteException;

    boolean writePridavek(Pridavek pridavek) throws RemoteException;

    boolean upravPridavek(Pridavek pridavek) throws RemoteException;
}

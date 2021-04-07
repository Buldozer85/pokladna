package shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Uloziste  extends UnicastRemoteObject implements IUloziste, Serializable {
  
    /**
     *
     */
    private static final long serialVersionUID = -5463203417346866089L;
    private LinkedList<Objednavka> listObjednavek = new LinkedList<>();

    public Uloziste() throws RemoteException {

        Timer timer = new Timer();
        TimerTask hodina = new TimerTask(){
            @Override
            public void run () {
                AutomatickeMazani();
            }
        };
        timer.schedule(hodina,01,1000*60*60);
    }

    @Override
    public List<Objednavka> vratObjednavky() throws RemoteException {
      
        return this.listObjednavek;
    }

    @Override
    public boolean zapisDoUloziste(Objednavka objednavka) {
        if(this.listObjednavek.add(objednavka)) return true;
        else return false;
    }
    

    private void AutomatickeMazani(){
        if(!this.listObjednavek.isEmpty()){
        for (Objednavka objednavka : listObjednavek) {
            Instant casObj = Instant.parse(objednavka.getCasObjednavky());
            Instant now = Instant.now();
            
            if (Duration.between(casObj, now).compareTo(Duration.ofMinutes(10)) == 1) {
                this.listObjednavek.remove(objednavka);
            }
        }
        
    }
    }
    

}

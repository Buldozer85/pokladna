package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import server.DB.Database;
import shared.Polozka;

public class Polozky extends UnicastRemoteObject implements shared.Polozky {
   
   protected Polozky() throws RemoteException{
       super();
   }
   
    @Override
    public Polozka getPolozka(int id){
      return null;  
    }
    @Override
    public boolean writePolozka(Polozka polozka) throws RemoteException{
        try (Connection conn = Database.get().getConnection()) {
            conn.setAutoCommit(false);

            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO polozky (nazev, cena) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS
            )){
                stmt.setString(1, polozka.getNazev());
                stmt.setDouble(2, polozka.getCena());

                if(stmt.executeUpdate() != 1){
                    throw new Exception("Nepodaril se zapis tridy");
                }

                ResultSet rs =stmt.getGeneratedKeys();

                if(rs.next()){
                    polozka.setId(rs.getInt(1));
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<Polozka> getPolozky() throws RemoteException{
        return null;
    }

}

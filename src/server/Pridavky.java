package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import server.DB.Database;
import shared.Pridavek;

public class Pridavky extends UnicastRemoteObject implements shared.Pridavky {

    /**
     *
     */
    private static final long serialVersionUID = -113687425156397787L;

    protected Pridavky() throws RemoteException {
        super();
        
    }

    @Override
    public Pridavek getPridavek(int id) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Pridavek> getPridavky() throws RemoteException {
       List<Pridavek> pridavky = new ArrayList<>();
       try (Connection conn = Database.get().getConnection()) {
           Statement pridavkyStatement = conn.createStatement();
           ResultSet pridavkyRs = pridavkyStatement.executeQuery("SELECT pridavky.ID, pridavky.nazev, pridavky.cena FROM pridavky");
        while (pridavkyRs.next()) {
            Pridavek pridavek = new Pridavek().setId(pridavkyRs.getInt("ID")).setNazev(pridavkyRs.getString("nazev")).setCena(pridavkyRs.getDouble("cena"));
            pridavky.add(pridavek);
        }       
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
        return pridavky;
    }

    @Override
    public boolean writePridavek(Pridavek pridavek) throws RemoteException {
      try (Connection conn = Database.get().getConnection()) {
          conn.setAutoCommit(false);

          try (PreparedStatement stmt = conn.prepareStatement(
              "INSERT INTO pridavky (nazev, cena) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS
          )) {
              stmt.setString(1, pridavek.getNazev());
              stmt.setDouble(2, pridavek.getCena());

              if(stmt.executeUpdate() != 1){
                  throw new Exception("Nepodaril se zapis pridavku");
              }

              ResultSet rs = stmt.getGeneratedKeys();

              if (rs.next()) {
                  pridavek.setId(rs.getInt(1));
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
    
}

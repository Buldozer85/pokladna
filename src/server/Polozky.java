package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.DB.Database;
import shared.Polozka;

public class Polozky extends UnicastRemoteObject implements shared.Polozky {

    /**
     *
     */
    private static final long serialVersionUID = 8899018683648579653L;

    protected Polozky() throws RemoteException {
        super();
    }

   

    @Override
    public boolean writePolozka(Polozka polozka) throws RemoteException {
        try (Connection conn = Database.get().getConnection()) {
            conn.setAutoCommit(false);

            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO polozky (nazev, cena, druh) VALUES (?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, polozka.getNazev());
                stmt.setDouble(2, polozka.getCena());
                stmt.setObject(3, polozka.getDruh());

                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis polozky");
                }

                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
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
    public List<Polozka> getPolozky() throws RemoteException {
        List<Polozka> polozky = new ArrayList<>();
        try (Connection conn = Database.get().getConnection();
                Statement polozkyStmt = conn.createStatement();
                ResultSet polozkyRs = polozkyStmt.executeQuery(
                        "SELECT polozky.ID, polozky.nazev, polozky.cena, polozky.druh,polozky.isActive FROM polozky WHERE polozky.isActive = 1")) {
            ;

            while (polozkyRs.next()) {
                Polozka polozka = new Polozka().setId(polozkyRs.getInt("ID")).setNazev(polozkyRs.getString("nazev"))
                        .setCena(polozkyRs.getDouble("cena")).setDruh(polozkyRs.getString("druh"))
                        .setActive(polozkyRs.getBoolean("isActive"));
                polozky.add(polozka);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return polozky;
    }

    @Override
    public List<Polozka> getPolozkyAdmin() throws RemoteException {
        List<Polozka> polozky = new ArrayList<>();
        try (Connection conn = Database.get().getConnection();
                Statement polozkyStmt = conn.createStatement();
                ResultSet polozkyRs = polozkyStmt.executeQuery(
                        "SELECT polozky.ID, polozky.nazev, polozky.cena, polozky.druh, polozky.isActive FROM polozky")) {
            ;

            while (polozkyRs.next()) {
                Polozka polozka = new Polozka().setId(polozkyRs.getInt("ID")).setNazev(polozkyRs.getString("nazev"))
                        .setCena(polozkyRs.getDouble("cena")).setDruh(polozkyRs.getString("druh"))
                        .setActive(polozkyRs.getBoolean("isActive"));
                polozky.add(polozka);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return polozky;
    }

    @Override
    public boolean upravPolozka(Polozka polozka) throws RemoteException {
        try (Connection conn = Database.get().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE polozky SET polozky.isActive = ?, polozky.nazev = ?, polozky.cena = ?, polozky.druh = ? WHERE polozky.ID = ?")) {
                int pom;
                if (polozka.isActive())
                    pom = 1;
                else
                    pom = 0;
                stmt.setInt(1, pom);
                stmt.setString(2, polozka.getNazev());
                stmt.setDouble(3, polozka.getCena());
                stmt.setString(4, polozka.getDruh());
                stmt.setInt(5, polozka.getId());
               

                
               
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodařilo se upravit polozku");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                System.out.println(e.getMessage());
                throw e;
            }
            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }

    }

}

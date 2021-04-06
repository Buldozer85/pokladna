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
import shared.Objednavka;
import shared.Polozka;
import shared.Pridavek;

public class Objednavky extends UnicastRemoteObject implements shared.Objednavky {

    /**
     *
     */
    private static final long serialVersionUID = 7078414319144090834L;
    private int cisloPolozkyVobjednavce = 0;

    protected Objednavky() throws RemoteException {
        super();

    }

    @Override
    public Objednavka getObjednavka(int id) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Objednavka> getObjednavky() throws RemoteException {
        List<Objednavka> objednavky = new ArrayList<>();

        try (Connection conn = Database.get().getConnection();
                Statement objednavkyStmt = conn.createStatement();

                ResultSet objednavkyRs = objednavkyStmt.executeQuery(
                        "SELECT objednavky.ID, objednavky.celkovaCena, objednavky.casObjednavky FROM objednavky ORDER BY objednavky.ID");

                PreparedStatement polozkyStmt = conn.prepareStatement(
                        "SELECT DISTINCT polozky_v_objednavce.Polozka_ID, polozky.nazev, polozky.cena, polozky.druh, polozky_v_objednavce.cisloPolozkyVobjednavce FROM polozky_v_objednavce JOIN polozky ON polozky.ID = polozky_v_objednavce.Polozka_ID WHERE polozky_v_objednavce.Objednavka_ID  = ? ORDER BY polozky_v_objednavce.cisloPolozkyVobjednavce");

                PreparedStatement pridavkyStmt = conn.prepareStatement(
                        "SELECT polozky_v_objednavce.Pridavek_ID, polozky_v_objednavce.cisloPolozkyVobjednavce, pridavky.nazev, pridavky.cena FROM polozky_v_objednavce  JOIN pridavky ON pridavky.ID = polozky_v_objednavce.Pridavek_ID  JOIN polozky ON polozky.ID = polozky_v_objednavce.Polozka_ID WHERE polozky_v_objednavce.cisloPolozkyVobjednavce = ? AND polozky_v_objednavce.Objednavka_ID = ? AND polozky_v_objednavce.Polozka_ID = ? ")) {
            ;

            while (objednavkyRs.next()) {

                Objednavka objednavka = new Objednavka().setCasObjednavky(objednavkyRs.getString("casObjednavky"))
                        .setCena(objednavkyRs.getDouble("celkovaCena")).setId(objednavkyRs.getInt("ID"));

                polozkyStmt.setInt(1, objednavka.getId());

                try (ResultSet polozkaRs = polozkyStmt.executeQuery()) {

                    while (polozkaRs.next()) {

                        Polozka polozka = new Polozka().setId(polozkaRs.getInt("Polozka_ID"))
                                .setNazev(polozkaRs.getString("nazev")).setCena(polozkaRs.getDouble("cena"))
                                .setDruh(polozkaRs.getString("druh"));

                        objednavka.getPolozky().add(polozka);

                        int cisloPolozkyVobjednavce = polozkaRs.getInt("cisloPolozkyVobjednavce");

                        pridavkyStmt.setInt(1, cisloPolozkyVobjednavce);
                        pridavkyStmt.setInt(2, objednavka.getId());
                        pridavkyStmt.setInt(3, polozka.getId());

                        try (ResultSet pridavekRs = pridavkyStmt.executeQuery()) {

                            while (pridavekRs.next()) {

                                Pridavek pridavek = new Pridavek().setId(pridavekRs.getInt("Pridavek_ID"))
                                        .setNazev(pridavekRs.getString("nazev")).setCena(pridavekRs.getDouble("cena"));

                                polozka.getPridavky().add(pridavek);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            continue;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    continue;
                }
                objednavky.add(objednavka);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
        return objednavky;
    }

    @Override
    public boolean writeObjednavka(Objednavka objednavka) throws RemoteException {
        try (Connection conn = Database.get().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO objednavky (celkovaCena, casObjednavky) VALUES (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setDouble(1, objednavka.getCena());
                System.out.println(objednavka.getPolozky());

                stmt.setString(2, objednavka.getCasObjednavky());

                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis objednavky");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    objednavka.setId(rs.getInt(1));
                }
                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO polozkamapridavky(Polozky_ID, Pridavek_ID) VALUES(?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                for (Polozka polozka : objednavka.getPolozky()) {

                    if (polozka.getPridavky().isEmpty() == false) {
                        for (Pridavek pridavek : polozka.getPridavky()) {
                            System.out.println(pridavek.getNazev());
                            stmt.setInt(1, polozka.getId());
                            stmt.setInt(2, pridavek.getId());
                            stmt.executeUpdate();

                        }

                    } else {
                        stmt.setInt(1, polozka.getId());
                        stmt.setNull(2, 4);
                        stmt.executeUpdate();

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                conn.rollback();
                throw e;
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO polozky_v_objednavce (Objednavka_ID, Polozka_ID, Pridavek_ID, cisloPolozkyVobjednavce) VALUES(?,?,?,?) ",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                for (Polozka polozka : objednavka.getPolozky()) {
                    if (!polozka.getPridavky().isEmpty()) {
                        for (Pridavek pridavek : polozka.getPridavky()) {
                            stmt.setInt(1, objednavka.getId());
                            stmt.setInt(2, polozka.getId());
                            stmt.setInt(3, pridavek.getId());
                            stmt.setInt(4, cisloPolozkyVobjednavce);
                            stmt.executeUpdate();
                        }
                    } else {
                        stmt.setInt(1, objednavka.getId());
                        stmt.setInt(2, polozka.getId());
                        stmt.setNull(3, 4);
                        stmt.setInt(4, cisloPolozkyVobjednavce);
                        stmt.executeUpdate();
                    }

                    cisloPolozkyVobjednavce++;
                }
                stmt.executeBatch();

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

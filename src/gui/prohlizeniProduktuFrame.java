package gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.Polozka;
import shared.Polozky;
import shared.Pridavek;
import shared.Pridavky;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class prohlizeniProduktuFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1835011863546575303L;
    private Container obal = this.getContentPane();
    private JPanel obalPolozky, obalPridavky;
    private JButton b;
    private JLabel polozkyLabel, pridavkyLabel;

    public prohlizeniProduktuFrame() {
        initComponents();
    }

    private void initComponents() {
        obalPolozky = new JPanel();
        obalPridavky = new JPanel();

        BoxLayout boxL = new BoxLayout(obal, BoxLayout.Y_AXIS);
        obal.setLayout(boxL);

        polozkyLabel = new JLabel();
        polozkyLabel.setText("Položky");
        polozkyLabel.setHorizontalAlignment(JLabel.CENTER);
        obal.add(polozkyLabel);

        GridLayout gl = new GridLayout();

        gl.setHgap(5);
        gl.setVgap(5);

        obalPolozky.setLayout(gl);

        try {
            Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
            for (Polozka p : polozky.getPolozky()) {

                b = new JButton(p.getNazev());
                b.setSize(new Dimension(10, 10));

                obalPolozky.add(b);
            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        obal.add(obalPolozky);

        pridavkyLabel = new JLabel();
        pridavkyLabel.setText("Přídavky");
        pridavkyLabel.setHorizontalAlignment(JLabel.CENTER);
        obalPridavky.setLayout(gl);
        obal.add(pridavkyLabel);
        obal.add(obalPridavky);

        try {
            Pridavky pridavky = (Pridavky) Naming.lookup("rmi://localhost:12345/pridavky");
            for (Pridavek p : pridavky.getPridavky()) {

                b = new JButton(p.getNazev());

                obalPridavky.add(b);

            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }

}

package gui;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import shared.Objednavka;

import shared.Pridavek;
import shared.Pridavky;

public class pridavekFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 6263029152855512021L;
    private JButton b, ok;
    private java.awt.Container pane = this.getContentPane();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public pridavekFrame() {
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        this.setSize(new Dimension(width, height));
        initComponents();
    }

    public void initComponents() {
        ok = new JButton("OK");

        try {
            Pridavky pridavky = (Pridavky) Naming.lookup("rmi://localhost:12345/pridavky");
            for (Pridavek p : pridavky.getPridavky()) {

                b = new JButton(p.getNazev());
                b.addActionListener((l) -> {
                    Objednavka.getObjednavky().getLast().getPridavky().add(p);

                    hamburgeryFrame.addText("\t+" + p.getNazev() + " " + p.getCena() + " Kč");
                    hamburgeryFrame.prictiCenu(p.getCena());

                });

                pane.add(b);

            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        ok.addActionListener((e) -> {

            this.setVisible(false);

        });
        pane.add(ok);
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
    }
}

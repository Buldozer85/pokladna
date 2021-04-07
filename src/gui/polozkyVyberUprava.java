package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shared.Polozka;
import shared.Polozky;

import java.awt.Container;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class polozkyVyberUprava extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Container obal = this.getContentPane();
    private JButton b;
    private polozkyUpravaFrame upravaFrame;
    private JPanel obalCely;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public polozkyVyberUprava() {
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        this.setSize(new Dimension(width, height));
        initComponents();
    }

    private void initComponents() {
        obalCely = new JPanel();
        FlowLayout fl = new FlowLayout();
        
        

        GridLayout gl = new GridLayout();
        gl.setHgap(5);
        gl.setVgap(5);

        obalCely.setLayout(gl);
        obal.setLayout(fl);
        obal.add(obalCely);

        try {
            Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
            for (Polozka p : polozky.getPolozkyAdmin()) {

                b = new JButton(p.getNazev());

                b.addActionListener((l) -> {
                    System.out.println(p.getId() + "klik");

                    upravaFrame = new polozkyUpravaFrame(p);
                    upravaFrame.setVisible(true);
                });

                obalCely.add(b);
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

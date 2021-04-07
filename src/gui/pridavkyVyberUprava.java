package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shared.Pridavek;
import shared.Pridavky;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class pridavkyVyberUprava extends JFrame {

    private static final long serialVersionUID = 1L;
    private Container obal = this.getContentPane();
    private JButton b;
    private pridavkyUpravaFrame upravaFrame;
    private JPanel obalCely;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public pridavkyVyberUprava() {
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
            Pridavky pridavky = (Pridavky) Naming.lookup("rmi://localhost:12345/pridavky");
            for (Pridavek p : pridavky.getPridavkyAdministrace()) {

                b = new JButton(p.getNazev());

                b.addActionListener((l) -> {

                    upravaFrame = new pridavkyUpravaFrame(p);
                    upravaFrame.setVisible(true);
                });

                obalCely.add(b);
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

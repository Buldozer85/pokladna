package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import shared.Objednavka;
import shared.Objednavky;
import shared.Polozka;
import shared.Polozky;
import shared.Pridavek;

public class hamburgeryFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 3014994083657330792L;
    private pridavekFrame f;
    private JButton b, potvrdit, storno;
    private static JTextPane objednavkyPane, celkovaCenaPane;
    private java.awt.Container pane = this.getContentPane();
    private java.awt.Container obalBtn, obalText, obalPotvrdit;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private  Double cena = 0.0;
    private  Objednavka objednavka ;

    public hamburgeryFrame() {
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        this.setSize(new Dimension(width, height));
        initComponents();
    }

    public void initComponents() {
        objednavkyPane = new JTextPane();
        celkovaCenaPane = new JTextPane();
        obalBtn = new Container();
        obalText = new Container();
        obalPotvrdit = new Container();
        potvrdit = new JButton("Potvrdit");
        storno = new JButton("Storno");

        pane.setLayout(new FlowLayout(FlowLayout.LEFT));

        pane.add(obalBtn);
        pane.add(obalText);
        GridLayout gl = new GridLayout();
        gl.setHgap(5);
        gl.setVgap(5);
        obalBtn.setLayout(gl);

        BoxLayout box = new BoxLayout(obalText, BoxLayout.Y_AXIS);

        obalText.setLayout(box);

        obalText.add(objednavkyPane);
        obalText.add(celkovaCenaPane);

        objednavkyPane.setText("Objednávka" + "\n" + "_________________________");
        objednavkyPane.setEditable(false);

        objednavkyPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        obalPotvrdit.add(potvrdit);
        obalPotvrdit.add(storno);

        FlowLayout flowLayoutPotvrdit = new FlowLayout();
        flowLayoutPotvrdit.setHgap(5);
        flowLayoutPotvrdit.setVgap(5);

        obalPotvrdit.setLayout(flowLayoutPotvrdit);
        obalText.add(obalPotvrdit);

        try {
            Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
            for (Polozka p : polozky.getPolozky()) {

                b = new JButton(p.getNazev());

                b.addActionListener((l) -> {
                    f = new pridavekFrame();
                    f.setVisible(true);
                    if (!Objednavka.getObjednavky().add(p)) {
                        JOptionPane.showMessageDialog(this, "nepodařilo se přidat do objednávky");

                    }

                    addText("\n " + p.getNazev() + "\n\t" + p.getCena().toString() + " Kč");

                    for (Polozka polozka : Objednavka.getObjednavky()) {
                   
                        if(!polozka.getPridavky().isEmpty()){
                        for (Pridavek pridavek : polozka.getPridavky()) {
                         System.out.println(pridavek.getNazev());
                          cena += pridavek.getCena();
                        }
                    }
                    else{
        
                        cena += polozka.getCena();
                    }
                }
                celkovaCenaPane.setText("Cena\t" + cena.toString() + " Kč");
                });

                obalBtn.add(b);

            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        potvrdit.addActionListener((e)->{
           
            for (Polozka polozka : Objednavka.getObjednavky()) {
                System.out.println(polozka.getNazev());
                if(!polozka.getPridavky().isEmpty()){
                for (Pridavek pridavek : polozka.getPridavky()) {
                 System.out.println(pridavek.getNazev());
                  cena += pridavek.getCena();
                }
            }
            else{

                cena += polozka.getCena();
            }
            }
            try {
                Objednavky objednavky = (Objednavky) Naming.lookup("rmi://localhost:12345/objednavky");
                java.util.Date dt = new java.util.Date();

                java.text.SimpleDateFormat sdf = 
                     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                String currentTime = sdf.format(dt);
                LinkedList<Polozka> o = Objednavka.getObjednavky();
                objednavka = new Objednavka().setCasObjednavky(currentTime).setCena(cena).setPolozky(o);
               
              

           objednavky.writeObjednavka(objednavka);
            } catch (RemoteException | NotBoundException | MalformedURLException ex) {
        
                ex.printStackTrace();
            }
     

        });

    }

    public static void addText(String Text) {
        if (objednavkyPane != null)
            objednavkyPane.setText(objednavkyPane.getText() + "\n" + Text);

    }

}

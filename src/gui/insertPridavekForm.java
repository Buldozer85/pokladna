package gui;

import java.awt.Dimension;
import java.rmi.Naming;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shared.Pridavek;
import shared.Pridavky;

public class insertPridavekForm extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -7115827006528791185L;
    private JTextField nazevTextField, cenaTextField;
    private JButton potvrdVlozeniButton;
    

    public insertPridavekForm() {
        super();
        setSize(400, 800);
        initComponents();

    }

    private void initComponents() {
        nazevTextField = new JTextField();
        cenaTextField = new JTextField();
        potvrdVlozeniButton = new JButton("Vložit");
        
    
        java.awt.Container pane = this.getContentPane();

        potvrdVlozeniButton.addActionListener((e) -> {
            try {
                Pridavky pridavky = (Pridavky) Naming.lookup("rmi://localhost:12345/pridavky");
                Pridavek p = new Pridavek().setNazev(nazevTextField.getText())
                        .setCena(Double.parseDouble(cenaTextField.getText()));
                if (!pridavky.writePridavek(p)) {
                    JOptionPane.showMessageDialog(this, "Nepodařilo se zapsat přídavek");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Přídavek: " + nazevTextField.getText() + "Byl úspěšně zapsán");
                    this.setVisible(false);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        BoxLayout b = new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS);
        pane.setLayout(b);
        pane.add(nazevTextField);

        pane.add(Box.createRigidArea(new Dimension(80, 40)));
        cenaTextField.setSize(400, 200);
        nazevTextField.setAlignmentX(CENTER_ALIGNMENT);
        nazevTextField.setAlignmentY(CENTER_ALIGNMENT);
        pane.add(cenaTextField);
       
        pane.add(potvrdVlozeniButton);


    }
}

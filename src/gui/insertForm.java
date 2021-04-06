package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.rmi.Naming;

import shared.Polozky;
import shared.Polozka;

public class insertForm extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 127860281665570197L;
    private JTextField nazevTextField, cenaTextField;
    private JButton potvrdVlozeniButton;
    private JComboBox<String> druhBox;

    public insertForm() {
        super();
        setSize(400, 800);
        initComponents();

    }

    private void initComponents() {
        nazevTextField = new JTextField();
        cenaTextField = new JTextField();
        potvrdVlozeniButton = new JButton("Vložit");
        String [] druhy = {"Hamberger","Piti", "Wrap", "Hranolky"};
         druhBox = new JComboBox<>(druhy);
         druhBox.setBounds(50, 100,90,20);
        java.awt.Container pane = this.getContentPane();

        potvrdVlozeniButton.addActionListener((e) -> {
            try {
                Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
                Polozka p = new Polozka().setNazev(nazevTextField.getText())
                        .setCena(Double.parseDouble(cenaTextField.getText())).setDruh((String)druhBox.getItemAt(druhBox.getSelectedIndex()));
                if (!polozky.writePolozka(p)) {
                    JOptionPane.showMessageDialog(this, "Nepodařilo se zapsat položku");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Položka: " + nazevTextField.getText() + "Byla úspěšně zapsána");
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
        pane.add(druhBox);
        pane.add(potvrdVlozeniButton);


    }

}
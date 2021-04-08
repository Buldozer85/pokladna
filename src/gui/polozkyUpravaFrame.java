package gui;

import java.rmi.Naming;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import shared.Polozka;
import shared.Polozky;

public class polozkyUpravaFrame extends JFrame {
    private Polozka p1;
    private static final long serialVersionUID = 127860281665570197L;
    private JTextField nazevTextField, cenaTextField;
    private JButton potvrdVlozeniButton, zpetButton;
    private JComboBox<String> druhBox;
    private JCheckBox isActive;
    private JLabel isActiveLabel;
    private JPanel obalIsActive, obalCely;

    public polozkyUpravaFrame(Polozka p1) {
        this.p1 = p1;
        initComponents();

        this.setSize(new Dimension(300, 500));
    }

    private void initComponents() {
        nazevTextField = new JTextField();
        nazevTextField.setText(p1.getNazev());
        cenaTextField = new JTextField();
        cenaTextField.setText(p1.getCena().toString());
        potvrdVlozeniButton = new JButton("Vložit");
        isActive = new JCheckBox();
        isActiveLabel = new JLabel();
        obalIsActive = new JPanel();
        obalCely = new JPanel();
        zpetButton = new JButton("Zpět");
        isActive.setText("Aktivní: ");
        String[] druhy = { "Hamberger", "Piti", "Wrap", "Hranolky" };
        druhBox = new JComboBox<>(druhy);
        druhBox.setBounds(50, 100, 90, 20);
        druhBox.setSelectedItem(p1.getDruh());
        java.awt.Container pane = this.getContentPane();
        if (p1.isActive()) {
            isActive.setSelected(true);
        }

        potvrdVlozeniButton.addActionListener((e) -> {
            try {
                Polozky polozky = (Polozky) Naming.lookup("rmi://localhost:12345/polozky");
                Polozka p = new Polozka().setId(p1.getId()).setNazev(nazevTextField.getText())
                        .setCena(Double.parseDouble(cenaTextField.getText()))
                        .setDruh((String) druhBox.getItemAt(druhBox.getSelectedIndex()));
                if (isActive.isSelected()) {
                    p.setActive(true);
                    p1.setActive(true);
                } else {
                    p.setActive(false);
                    p1.setActive(false);

                }
                ;

                if (!polozky.upravPolozka(p)) {
                    JOptionPane.showMessageDialog(this, "Nepodařilo se Upravit položku");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Položka: " + nazevTextField.getText() + "Byla úspěšně upravena");
                    this.setVisible(false);
                    new polozkyVyberUprava().setVisible(true);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
           
            
        });

        potvrdVlozeniButton.setAlignmentX(CENTER_ALIGNMENT);
        potvrdVlozeniButton.setAlignmentY(CENTER_ALIGNMENT);

        zpetButton.setAlignmentX(CENTER_ALIGNMENT);
        zpetButton.setAlignmentY(CENTER_ALIGNMENT);

        zpetButton.addActionListener((l)->{
            this.setVisible(false);
            new polozkyVyberUprava().setVisible(true);
        });

        BoxLayout b = new BoxLayout(obalCely, BoxLayout.PAGE_AXIS);
        FlowLayout fl = new FlowLayout();
        pane.setLayout(fl);
        obalCely.setLayout(b);
        pane.add(obalCely);
        obalCely.add(nazevTextField);

        obalCely.add(Box.createRigidArea(new Dimension(40, 40)));
        cenaTextField.setSize(new Dimension(200, 100));
        nazevTextField.setAlignmentX(CENTER_ALIGNMENT);
        nazevTextField.setAlignmentY(CENTER_ALIGNMENT);
        obalCely.add(cenaTextField);
        obalCely.add(Box.createRigidArea(new Dimension(40, 40)));
        obalCely.add(druhBox);
        obalCely.add(Box.createRigidArea(new Dimension(40, 40)));

        obalIsActive.setLayout(fl);
        obalIsActive.add(isActiveLabel);
        obalIsActive.add(isActive);
        obalCely.add(obalIsActive);
        obalCely.add(Box.createRigidArea(new Dimension(80, 40)));
        obalCely.add(potvrdVlozeniButton);
        obalCely.add(Box.createRigidArea(new Dimension(20, 20)));
        obalCely.add(zpetButton);

    }

}

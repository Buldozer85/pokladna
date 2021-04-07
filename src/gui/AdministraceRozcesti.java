package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class AdministraceRozcesti extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 8354716607326597238L;
    private Container obal = this.getContentPane();
    private JButton polozkyBtn, pridavkyBtn;
    private polozkyVyberUprava uVyberUprava;
    private pridavkyVyberUprava pridavkyVyberUprava;

    public AdministraceRozcesti() {
        this.setSize(new Dimension(200, 200));
        initComponents();
    }

    private void initComponents() {
        polozkyBtn = new JButton("Položky administrace");
        pridavkyBtn = new JButton("Přídavky administrace");

        FlowLayout fl = new FlowLayout();
        fl.setHgap(10);
        fl.setVgap(10);

        obal.setLayout(fl);

        obal.add(polozkyBtn);
        obal.add(pridavkyBtn);

        polozkyBtn.addActionListener((e) -> {
            uVyberUprava = new polozkyVyberUprava();
            uVyberUprava.setVisible(true);

        });

        pridavkyBtn.addActionListener((e) -> {
            pridavkyVyberUprava = new pridavkyVyberUprava();
            pridavkyVyberUprava.setVisible(true);
        });

    }

}

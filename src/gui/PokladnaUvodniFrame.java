package gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class PokladnaUvodniFrame extends JFrame {
    /**
         *
         */
        private static final long serialVersionUID = 5723103369713080232L;
private javax.swing.JLabel dvoriNald;
    private javax.swing.JButton historieObj;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton novaObj;
    private hamburgeryFrame nabidka;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private ObjednavkyFrame objednavkyFrame;

   

 

    public PokladnaUvodniFrame() {
        int height = screenSize.height * 2 / 3;
        int width = screenSize.width * 2 / 3;
        this.setSize(new Dimension(width, height));
        initComponents();
    }

 

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        novaObj = new javax.swing.JButton();
        historieObj = new javax.swing.JButton();
        dvoriNald = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        novaObj.setFont(new java.awt.Font("Dialog", 1, 18));
        novaObj.setText("Nová objednávka");

        historieObj.setFont(new java.awt.Font("Dialog", 1, 18));
        historieObj.setText("Historie Objednávek");

        historieObj.addActionListener((e) -> {
       objednavkyFrame = new ObjednavkyFrame();
       objednavkyFrame.setVisible(true);
       this.setVisible(false);
        });

        dvoriNald.setFont(new java.awt.Font("Dialog", 1, 48));
        dvoriNald.setText("Vítejte V DvořiNaldu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addGap(175, 175, 175)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(novaObj, javax.swing.GroupLayout.PREFERRED_SIZE, 324,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(historieObj, javax.swing.GroupLayout.PREFERRED_SIZE, 325,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(117, Short.MAX_VALUE).addComponent(dvoriNald,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                        .addComponent(dvoriNald, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(novaObj, javax.swing.GroupLayout.PREFERRED_SIZE, 93,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30).addComponent(historieObj, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(15, 15, 15).addComponent(jPanel1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()));

        novaObj.addActionListener((e) -> {
          nabidka = new hamburgeryFrame();
          this.setVisible(false);
          nabidka.setVisible(true);
          
        });

        pack();
    }
}

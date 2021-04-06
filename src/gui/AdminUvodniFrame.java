package gui;

import java.awt.Dimension;
import java.awt.Toolkit;




public class AdminUvodniFrame extends javax.swing.JFrame {
        /**
         *
         */
        private static final long serialVersionUID = 5244163580302597039L;
        private javax.swing.JLabel dvoriNald;
        private javax.swing.JButton Administrace;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JButton prohlizeniProduktu;
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private AdministraceRozcesti administraceRozcesti;
        private prohlizeniProduktuFrame prohlizeniProduktuFrame;

        public AdminUvodniFrame() {
                int height = screenSize.height * 2 / 3;
                int width = screenSize.width * 2 / 3;
                this.setSize(new Dimension(width, height));
                initComponents();
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                prohlizeniProduktu = new javax.swing.JButton();
                Administrace = new javax.swing.JButton();
                dvoriNald = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                prohlizeniProduktu.setFont(new java.awt.Font("Dialog", 1, 18));
                prohlizeniProduktu.setText("Nabízené produkty");

                Administrace.setFont(new java.awt.Font("Dialog", 1, 18));
                Administrace.setText("Administrace");

                Administrace.addActionListener((e) -> {
                        administraceRozcesti = new AdministraceRozcesti();
                        administraceRozcesti.setVisible(true);
                        this.setVisible(false);

                });

                dvoriNald.setFont(new java.awt.Font("Dialog", 1, 48));
                dvoriNald.setText("Administrace");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup().addGap(175, 175, 175)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(prohlizeniProduktu,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                324,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(Administrace,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                325,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup().addContainerGap(117, Short.MAX_VALUE)
                                                .addComponent(dvoriNald, javax.swing.GroupLayout.PREFERRED_SIZE, 475,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(104, 104, 104)));
                jPanel1Layout.setVerticalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                                                .addComponent(dvoriNald, javax.swing.GroupLayout.DEFAULT_SIZE, 98,
                                                                Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(prohlizeniProduktu,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 93,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(Administrace, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(54, 54, 54)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addGap(15, 15, 15)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap()));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap()));

                prohlizeniProduktu.addActionListener((e) -> {
                        prohlizeniProduktuFrame = new prohlizeniProduktuFrame();
                        prohlizeniProduktuFrame.setVisible(true);
                });

                pack();
        }

}

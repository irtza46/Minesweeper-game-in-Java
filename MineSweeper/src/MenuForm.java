public class MenuForm extends javax.swing.JFrame {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }
    
    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        difficulty_cb = new javax.swing.JComboBox<>();
        start_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setResizable(false);
        setSize(new java.awt.Dimension(549, 338));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        difficulty_cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BEGINNER", "INTERMEDIATE", "ADVANCED" }));
        jPanel1.add(difficulty_cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 160, 30));

        start_btn.setText("Start Game");
        start_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start_btnActionPerformed(evt);
            }
        });
        jPanel1.add(start_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, -1, 34));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuBackground.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void start_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_start_btnActionPerformed
        PlayForm playForm;
        if(difficulty_cb.getSelectedIndex() == Difficulty.EASY.ordinal())
            playForm = new PlayForm(8, 10);
        else if(difficulty_cb.getSelectedIndex() == Difficulty.MEDIUM.ordinal())
            playForm = new PlayForm(16, 36);
        else
            playForm = new PlayForm(24, 91);
        playForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_start_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /**
         * Set the Nimbus look and feel.
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MenuForm().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> difficulty_cb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton start_btn;
    // End of variables declaration//GEN-END:variables
}
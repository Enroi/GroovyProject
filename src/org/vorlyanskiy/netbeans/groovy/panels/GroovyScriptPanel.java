package org.vorlyanskiy.netbeans.groovy.panels;

import javax.swing.JFileChooser;
import org.vorlyanskiy.netbeans.groovy.utils.VariousProjectUtils;

final class GroovyScriptPanel extends javax.swing.JPanel {

    private final GroovyScriptOptionsPanelController controller;

    GroovyScriptPanel(GroovyScriptOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPathToGroovy = new javax.swing.JLabel();
        jTextFieldPathToGroovy = new javax.swing.JTextField();
        jButtonSekect = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPathToGroovy, org.openide.util.NbBundle.getMessage(GroovyScriptPanel.class, "GroovyScriptPanel.jLabelPathToGroovy.text")); // NOI18N

        jTextFieldPathToGroovy.setText(org.openide.util.NbBundle.getMessage(GroovyScriptPanel.class, "GroovyScriptPanel.jTextFieldPathToGroovy.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSekect, org.openide.util.NbBundle.getMessage(GroovyScriptPanel.class, "GroovyScriptPanel.jButtonSekect.text")); // NOI18N
        jButtonSekect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSekectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelPathToGroovy, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPathToGroovy, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSekect, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPathToGroovy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPathToGroovy, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSekect))
                .addGap(0, 38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSekectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSekectActionPerformed
        selectGroovyPath();
    }//GEN-LAST:event_jButtonSekectActionPerformed
    
    private void selectGroovyPath() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            jTextFieldPathToGroovy.setText(path);
        }
    }
    void load() {
        jTextFieldPathToGroovy.setText(VariousProjectUtils.getGlobalGroovyPath());
    }

    void store() {
        VariousProjectUtils.putGlobalGroovyPath(jTextFieldPathToGroovy.getText());
    }

    boolean valid() {
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSekect;
    private javax.swing.JLabel jLabelPathToGroovy;
    private javax.swing.JTextField jTextFieldPathToGroovy;
    // End of variables declaration//GEN-END:variables
}

package org.vorlyanskiy.netbeans.groovy.panels;

import javax.swing.JFileChooser;
import org.vorlyanskiy.netbeans.groovy.datamodel.OptionsDataModel;

/**
 *
 * @author Vladimir Orlyanskiy <vorlyanskiy@fusionconnect.com>
 */
public class OptionsPanel extends javax.swing.JPanel {
    

    private final OptionsDataModel dataModel;
    /**
     * Creates new form OptionsPanel
     */
    public OptionsPanel(OptionsDataModel dataModel) {
        this.dataModel = dataModel;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldPathToGroovy = new javax.swing.JTextField();
        jButtonGroovyPathSelection = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(OptionsPanel.class, "OptionsPanel.jLabel1.text")); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jTextFieldPathToGroovy.setText(dataModel.getPathToGroovy());
        jTextFieldPathToGroovy.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPathToGroovyFocusLost(evt);
            }
        });
        jTextFieldPathToGroovy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPathToGroovyActionPerformed(evt);
            }
        });
        jTextFieldPathToGroovy.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldPathToGroovyPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonGroovyPathSelection, org.openide.util.NbBundle.getMessage(OptionsPanel.class, "OptionsPanel.jButtonGroovyPathSelection.text")); // NOI18N
        jButtonGroovyPathSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGroovyPathSelectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldPathToGroovy, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGroovyPathSelection)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPathToGroovy)
                        .addComponent(jButtonGroovyPathSelection)))
                .addGap(0, 34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPathToGroovyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPathToGroovyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPathToGroovyActionPerformed

    private void jTextFieldPathToGroovyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPathToGroovyFocusLost
        dataModel.setPathToGroovy(jTextFieldPathToGroovy.getText());
    }//GEN-LAST:event_jTextFieldPathToGroovyFocusLost

    private void jTextFieldPathToGroovyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldPathToGroovyPropertyChange
        dataModel.setPathToGroovy(jTextFieldPathToGroovy.getText());
    }//GEN-LAST:event_jTextFieldPathToGroovyPropertyChange

    private void jButtonGroovyPathSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGroovyPathSelectionActionPerformed
        selectGroovyPath();
    }//GEN-LAST:event_jButtonGroovyPathSelectionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGroovyPathSelection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldPathToGroovy;
    // End of variables declaration//GEN-END:variables

    private void selectGroovyPath() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            dataModel.setPathToGroovy(path);
            jTextFieldPathToGroovy.setText(path);
        }
    }
}

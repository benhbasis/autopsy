/*
 * Central Repository
 *
 * Copyright 2015-2017 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.centralrepository.optionspanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDb;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDbException;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamOrganization;

public final class ManageOrganizationsDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private EamDb dbManager;
    private EamOrganization newOrg;
    private final DefaultListModel<EamOrganization> rulesListModel = new DefaultListModel<>();
    @Messages({"ManageOrganizationsDialog.title.text=Manage Organizations"})
    /**
     * Creates new form ManageOrganizationsPanel
     */
    public ManageOrganizationsDialog() {
        super((JFrame) WindowManager.getDefault().getMainWindow(),
                Bundle.ManageOrganizationsDialog_title_text(),
                true); // NON-NLS
        initComponents();
        try {
            this.dbManager = EamDb.getInstance();
            organizationList.setCellRenderer(new DefaultListCellRenderer() {
                private static final long serialVersionUID = 1L;

                @Override
                public Component getListCellRendererComponent(javax.swing.JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    setText(((EamOrganization) value).getName());
                    return c;
                }
            });
            organizationList.setModel(rulesListModel);
            organizationList.addListSelectionListener(new OrganizationListSelectionListener());
            populateList();
            newOrg = null;
        } catch (EamDbException ex) {
            Exceptions.printStackTrace(ex);
        }
        display();
    }

    private void display() {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenDimension.width - getSize().width) / 2, (screenDimension.height - getSize().height) / 2);
        setVisible(true);
    }

    private void populateListAndSelect(EamOrganization selected) throws EamDbException {
        rulesListModel.clear();
        if (selected != null) {
        }
        List<EamOrganization> orgs = dbManager.getOrganizations();
        if (orgs.size() > 0) {
            for (EamOrganization org : orgs) {
                rulesListModel.addElement(org);
                if (selected != null && org.getOrgID() == selected.getOrgID()) {
                    selected = org;
                }
            }
            if (orgs.contains(selected)) {
                organizationList.setSelectedValue(selected, true);
            } else {
                organizationList.setSelectedIndex(0);
            }
            organizationList.validate();
            organizationList.repaint();
        }
    }

    private void populateList() throws EamDbException {
        EamOrganization selected = organizationList.getSelectedValue();
        populateListAndSelect(selected);
    }

    @Messages({"ManageOrganizationsDialog.pocNameLabel.text=Point of Contact Name:",
        "ManageOrganizationsDialog.deleteButton.text=Delete",
        "ManageOrganizationsDialog.newButton.text=New",
        "ManageOrganizationsDialog.closeButton.text=Close",
        "ManageOrganizationsDialog.orgNameLabel.text=Organization Name:",
        "ManageOrganizationsDialog.pocEmailLabel.text=Point of Contact Email:",
        "ManageOrganizationsDialog.editButton.text=Edit",
        "ManageOrganizationsDialog.pocPhoneLabel.text=Point of Contact Phone:",
        "ManageOrganizationsDialog.orgDescriptionTextArea.text=Organizations are used to provide additional contact information for the content they are associated with.",
        "ManageOrganizationsDialog.orgListLabel.text=Organizations",
        "ManageOrganizationsDialog.orgDetailsLabel.text=Organization Details"})
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageOrganizationsScrollPane = new javax.swing.JScrollPane();
        manageOrganizationsPanel = new javax.swing.JPanel();
        orgListScrollPane = new javax.swing.JScrollPane();
        organizationList = new javax.swing.JList<>();
        orgDescriptionScrollPane = new javax.swing.JScrollPane();
        orgDescriptionTextArea = new javax.swing.JTextArea();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        orgListLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pocNameLabel = new javax.swing.JLabel();
        pocPhoneLabel = new javax.swing.JLabel();
        pocEmailLabel = new javax.swing.JLabel();
        orgNameLabel = new javax.swing.JLabel();
        orgNameTextField = new javax.swing.JTextField();
        pocNameTextField = new javax.swing.JTextField();
        pocPhoneTextField = new javax.swing.JTextField();
        pocEmailTextField = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        orgDetailsLabel = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(545, 450));

        manageOrganizationsScrollPane.setMinimumSize(null);
        manageOrganizationsScrollPane.setPreferredSize(new java.awt.Dimension(535, 415));

        manageOrganizationsPanel.setPreferredSize(new java.awt.Dimension(527, 407));

        organizationList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        orgListScrollPane.setViewportView(organizationList);

        orgDescriptionTextArea.setEditable(false);
        orgDescriptionTextArea.setBackground(new java.awt.Color(240, 240, 240));
        orgDescriptionTextArea.setColumns(20);
        orgDescriptionTextArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        orgDescriptionTextArea.setLineWrap(true);
        orgDescriptionTextArea.setRows(3);
        orgDescriptionTextArea.setText(org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.orgDescriptionTextArea.text")); // NOI18N
        orgDescriptionTextArea.setWrapStyleWord(true);
        orgDescriptionScrollPane.setViewportView(orgDescriptionTextArea);

        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.newButton.text")); // NOI18N
        newButton.setMargin(new java.awt.Insets(2, 6, 2, 6));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(deleteButton, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.deleteButton.text")); // NOI18N
        deleteButton.setMargin(new java.awt.Insets(2, 6, 2, 6));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(closeButton, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.closeButton.text")); // NOI18N
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(orgListLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.orgListLabel.text")); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        org.openide.awt.Mnemonics.setLocalizedText(pocNameLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.pocNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(pocPhoneLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.pocPhoneLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(pocEmailLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.pocEmailLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(orgNameLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.orgNameLabel.text")); // NOI18N

        orgNameTextField.setEditable(false);

        pocNameTextField.setEditable(false);

        pocPhoneTextField.setEditable(false);

        pocEmailTextField.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(editButton, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.editButton.text")); // NOI18N
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(orgDetailsLabel, org.openide.util.NbBundle.getMessage(ManageOrganizationsDialog.class, "ManageOrganizationsDialog.orgDetailsLabel.text")); // NOI18N

        javax.swing.GroupLayout manageOrganizationsPanelLayout = new javax.swing.GroupLayout(manageOrganizationsPanel);
        manageOrganizationsPanel.setLayout(manageOrganizationsPanelLayout);
        manageOrganizationsPanelLayout.setHorizontalGroup(
            manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orgDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orgListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                        .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addComponent(orgListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton))
                            .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pocNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(orgNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pocPhoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pocEmailLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pocNameTextField)
                                    .addComponent(pocPhoneTextField)
                                    .addComponent(pocEmailTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(orgNameTextField))))
                        .addContainerGap())
                    .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(orgDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        manageOrganizationsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteButton, editButton, newButton});

        manageOrganizationsPanelLayout.setVerticalGroup(
            manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                        .addComponent(orgDetailsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(orgNameLabel)
                            .addComponent(orgNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pocNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pocNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pocPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pocPhoneLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pocEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pocEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(closeButton))
                    .addComponent(jSeparator1)
                    .addGroup(manageOrganizationsPanelLayout.createSequentialGroup()
                        .addComponent(orgDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orgListLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orgListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(manageOrganizationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newButton)
                            .addComponent(deleteButton)
                            .addComponent(editButton))))
                .addContainerGap())
        );

        manageOrganizationsScrollPane.setViewportView(manageOrganizationsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(manageOrganizationsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(manageOrganizationsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //WJS-TODO implement delete 
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        AddNewOrganizationDialog dialogO = new AddNewOrganizationDialog();
        if (dialogO.isChanged()) {
            try {
                newOrg = dialogO.getNewOrg();
                populateListAndSelect(newOrg);
            } catch (EamDbException ex) {

            }
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        EamOrganization orgToEdit = organizationList.getSelectedValue();
        AddNewOrganizationDialog dialogO = new AddNewOrganizationDialog(orgToEdit);
        if (dialogO.isChanged()) {
            try {
                newOrg = dialogO.getNewOrg();
                populateListAndSelect(newOrg);
            } catch (EamDbException ex) {

            }
        }
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel manageOrganizationsPanel;
    private javax.swing.JScrollPane manageOrganizationsScrollPane;
    private javax.swing.JButton newButton;
    private javax.swing.JScrollPane orgDescriptionScrollPane;
    private javax.swing.JTextArea orgDescriptionTextArea;
    private javax.swing.JLabel orgDetailsLabel;
    private javax.swing.JLabel orgListLabel;
    private javax.swing.JScrollPane orgListScrollPane;
    private javax.swing.JLabel orgNameLabel;
    private javax.swing.JTextField orgNameTextField;
    private javax.swing.JList<EamOrganization> organizationList;
    private javax.swing.JLabel pocEmailLabel;
    private javax.swing.JTextField pocEmailTextField;
    private javax.swing.JLabel pocNameLabel;
    private javax.swing.JTextField pocNameTextField;
    private javax.swing.JLabel pocPhoneLabel;
    private javax.swing.JTextField pocPhoneTextField;
    // End of variables declaration//GEN-END:variables
    public boolean isChanged() {
        return newOrg != null;
    }

    public EamOrganization getNewOrg() {
        return newOrg;
    }

    /**
     * A list events listener for the interesting files sets list component.
     */
    private final class OrganizationListSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }
            EamOrganization selected;
            if (newOrg != null) {
                selected = newOrg;
            } else {
                selected = organizationList.getSelectedValue();
            }
            if (selected != null) {
                orgNameTextField.setText(selected.getName());
                pocNameTextField.setText(selected.getPocName());
                pocPhoneTextField.setText(selected.getPocPhone());
                pocEmailTextField.setText(selected.getPocEmail());
            } else {
                orgNameTextField.setText("");
                pocNameTextField.setText("");
                pocPhoneTextField.setText("");
                pocEmailTextField.setText("");
            }
        }

    }
}

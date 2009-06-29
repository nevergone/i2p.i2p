/*
 * GeneralConfiguration.java
 *
 * Created on 10 april 2009, 19:04
 */

package net.i2p.desktopgui.gui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.i2p.desktopgui.router.configuration.SpeedHelper;
import javax.swing.JComboBox;
import javax.swing.ButtonModel;
import javax.swing.JTextField;
import net.i2p.desktopgui.router.RouterHelper;
import net.i2p.desktopgui.router.configuration.SpeedHandler;
import net.i2p.desktopgui.router.configuration.UpdateHelper;
import net.i2p.router.web.NewsFetcher;
import net.i2p.desktopgui.router.configuration.UpdateHandler;
import java.util.Date;
import javax.swing.SwingWorker;
import net.i2p.i2ptunnel.web.IndexBean;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 *
 * @author  mathias
 */
public class GeneralConfiguration extends javax.swing.JFrame {

    /** Creates new form GeneralConfiguration */
    public GeneralConfiguration() {
        initComponents();
        extraInitComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.requestFocus();
        this.setVisible(true);
    }
    
    private void extraInitComponents() {
        initSpeedTab();
        initUpdateTab();
        initTunnelTab();
    }

    private void initSpeedTab() {
        try {
            String inbound = SpeedHelper.getInboundBandwidth();
            String outbound = SpeedHelper.getOutboundBandwidth();

            initSpeeds("" + Integer.parseInt(inbound)*8, "" + Integer.parseInt(outbound)*8);
            initUsage("" + Integer.parseInt(inbound), "" + Integer.parseInt(outbound));
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception noticed, probably running desktopgui in a debugger instead of along with I2P!?");
            initSpeeds("100", "100");
            initUsage("12", "12");
        }
    }

    private void initUpdateTab() {
        //Set update policy
        String updatePolicy = UpdateHelper.getUpdatePolicy();
        if(updatePolicy.equals(UpdateHelper.NOTIFY_UPDATE_POLICY)) {
            updateButtonGroup.setSelected(updateInform.getModel(), true);
        }
        else if(updatePolicy.equals(UpdateHelper.DOWNLOAD_UPDATE_POLICY)) {
            updateButtonGroup.setSelected(updateDownload.getModel(), true);
        }
        else if(updatePolicy.equals(UpdateHelper.INSTALL_UPDATE_POLICY)) {
            updateButtonGroup.setSelected(updateDownloadRestart.getModel(), true);
        }
        else {
            System.out.println("desktopgui: no updates for you!");
        }

        //Check if an update is available
        //TODO: move this method out of the routerconsole so desktopgui doesn't depend on routerconsole!!!
        if(NewsFetcher.getInstance(RouterHelper.getContext()).updateAvailable()) {
            updateNow.setVisible(true);
        }
        else {
            updateNow.setVisible(false);
        }
    }

    private void initTunnelTab() {
        while(((DefaultTableModel) clientTable.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) clientTable.getModel()).removeRow(0);
        }
        while(((DefaultTableModel) serverTable.getModel()).getRowCount() > 0) {
            ((DefaultTableModel) serverTable.getModel()).removeRow(0);
        }
        IndexBean bean = new IndexBean();
        for(int i=0; i<bean.getTunnelCount(); i++) {
            if(bean.isClient(i)) {
                Object[] row = {bean.getTunnelName(i), bean.getTunnelType(i),
                                bean.getClientInterface(i) + ":" + bean.getClientPort(i),
                                getTunnelStatus(bean.getTunnelStatus(i))};
                ((DefaultTableModel) clientTable.getModel()).addRow(row);
            }
            else {
                Object[] row = {bean.getTunnelName(i),
                                bean.getServerTarget(i),
                                getTunnelStatus(bean.getTunnelStatus(i))};
                ((DefaultTableModel) serverTable.getModel()).addRow(row);
            }
        }
    }

    public String getTunnelStatus(int status) {
        if(status == IndexBean.NOT_RUNNING) {
            return "Not running";
        }
        else if(status == IndexBean.RUNNING) {
            return "Running";
        }
        else if(status == IndexBean.STANDBY) {
            return "Standby";
        }
        else if(status == IndexBean.STARTING) {
            return "Starting";
        }
        else {
            return "Error: status not found";
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        updateButtonGroup = new javax.swing.ButtonGroup();
        applyPanel = new javax.swing.JPanel();
        cancel = new javax.swing.JToggleButton();
        ok = new javax.swing.JToggleButton();
        settingsPanel = new javax.swing.JTabbedPane();
        speedPanel = new javax.swing.JPanel();
        uploadSpeedLabel = new javax.swing.JLabel();
        downloadSpeedLabel = new javax.swing.JLabel();
        uploadspeed = new javax.swing.JTextField();
        downloadspeed = new javax.swing.JTextField();
        uploadkbps = new javax.swing.JComboBox();
        downloadkbps = new javax.swing.JComboBox();
        uploadUsageLabel = new javax.swing.JLabel();
        downloadUsageLabel = new javax.swing.JLabel();
        uploadgb = new javax.swing.JTextField();
        downloadgb = new javax.swing.JTextField();
        gbUploadLabel = new javax.swing.JLabel();
        gbDownloadLabel = new javax.swing.JLabel();
        uploadDownloadExplanation = new javax.swing.JLabel();
        updatesPanel = new javax.swing.JPanel();
        updateMethod = new javax.swing.JLabel();
        updateInform = new javax.swing.JRadioButton();
        updateDownload = new javax.swing.JRadioButton();
        updateDownloadRestart = new javax.swing.JRadioButton();
        checkUpdates = new javax.swing.JToggleButton();
        updateNow = new javax.swing.JToggleButton();
        advancedUpdateConfig = new javax.swing.JToggleButton();
        tunnelPanel = new javax.swing.JPanel();
        clientFrame = new javax.swing.JScrollPane();
        clientTable = new javax.swing.JTable();
        serverFrame = new javax.swing.JScrollPane();
        serverTable = new javax.swing.JTable();
        tunnelsExplanation = new javax.swing.JLabel();
        clientTunnelLabel = new javax.swing.JLabel();
        serverTunnelLabel = new javax.swing.JLabel();
        networkPanel = new javax.swing.JPanel();
        advancedPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(net.i2p.desktopgui.desktopgui.Main.class).getContext().getResourceMap(GeneralConfiguration.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        applyPanel.setName("applyPanel"); // NOI18N

        cancel.setText(resourceMap.getString("cancel.text")); // NOI18N
        cancel.setName("cancel"); // NOI18N
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        ok.setText(resourceMap.getString("ok.text")); // NOI18N
        ok.setName("ok"); // NOI18N
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout applyPanelLayout = new javax.swing.GroupLayout(applyPanel);
        applyPanel.setLayout(applyPanelLayout);
        applyPanelLayout.setHorizontalGroup(
            applyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, applyPanelLayout.createSequentialGroup()
                .addContainerGap(475, Short.MAX_VALUE)
                .addComponent(ok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancel)
                .addContainerGap())
        );
        applyPanelLayout.setVerticalGroup(
            applyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(applyPanelLayout.createSequentialGroup()
                .addGroup(applyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(ok))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        settingsPanel.setName("settingsPanel"); // NOI18N

        speedPanel.setName("speedPanel"); // NOI18N
        speedPanel.setLayout(null);

        uploadSpeedLabel.setText(resourceMap.getString("uploadSpeedLabel.text")); // NOI18N
        uploadSpeedLabel.setName("uploadSpeedLabel"); // NOI18N
        speedPanel.add(uploadSpeedLabel);
        uploadSpeedLabel.setBounds(20, 20, 140, 30);

        downloadSpeedLabel.setText(resourceMap.getString("downloadSpeedLabel.text")); // NOI18N
        downloadSpeedLabel.setName("downloadSpeedLabel"); // NOI18N
        speedPanel.add(downloadSpeedLabel);
        downloadSpeedLabel.setBounds(20, 60, 140, 30);

        uploadspeed.setText(resourceMap.getString("uploadspeed.text")); // NOI18N
        uploadspeed.setName("uploadspeed"); // NOI18N
        uploadspeed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                speedKeyReleased(evt);
            }
        });
        speedPanel.add(uploadspeed);
        uploadspeed.setBounds(160, 20, 77, 27);

        downloadspeed.setText(resourceMap.getString("downloadspeed.text")); // NOI18N
        downloadspeed.setName("downloadspeed"); // NOI18N
        downloadspeed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                speedKeyReleased(evt);
            }
        });
        speedPanel.add(downloadspeed);
        downloadspeed.setBounds(160, 60, 77, 27);

        uploadkbps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kbps", "kBps" }));
        uploadkbps.setName("uploadkbps"); // NOI18N
        uploadkbps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadkbpsActionPerformed(evt);
            }
        });
        speedPanel.add(uploadkbps);
        uploadkbps.setBounds(240, 20, 68, 27);

        downloadkbps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "kbps", "kBps" }));
        downloadkbps.setName("downloadkbps"); // NOI18N
        downloadkbps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadkbpsActionPerformed(evt);
            }
        });
        speedPanel.add(downloadkbps);
        downloadkbps.setBounds(240, 60, 68, 27);

        uploadUsageLabel.setText(resourceMap.getString("uploadUsageLabel.text")); // NOI18N
        uploadUsageLabel.setName("uploadUsageLabel"); // NOI18N
        speedPanel.add(uploadUsageLabel);
        uploadUsageLabel.setBounds(330, 20, 97, 30);

        downloadUsageLabel.setText(resourceMap.getString("downloadUsageLabel.text")); // NOI18N
        downloadUsageLabel.setName("downloadUsageLabel"); // NOI18N
        speedPanel.add(downloadUsageLabel);
        downloadUsageLabel.setBounds(330, 60, 97, 30);

        uploadgb.setText(resourceMap.getString("uploadgb.text")); // NOI18N
        uploadgb.setName("uploadgb"); // NOI18N
        uploadgb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                monthKeyReleased(evt);
            }
        });
        speedPanel.add(uploadgb);
        uploadgb.setBounds(440, 20, 60, 27);

        downloadgb.setText(resourceMap.getString("downloadgb.text")); // NOI18N
        downloadgb.setName("downloadgb"); // NOI18N
        downloadgb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                monthKeyReleased(evt);
            }
        });
        speedPanel.add(downloadgb);
        downloadgb.setBounds(440, 60, 60, 27);

        gbUploadLabel.setText(resourceMap.getString("gbUploadLabel.text")); // NOI18N
        gbUploadLabel.setName("gbUploadLabel"); // NOI18N
        speedPanel.add(gbUploadLabel);
        gbUploadLabel.setBounds(510, 20, 19, 30);

        gbDownloadLabel.setText(resourceMap.getString("gbDownloadLabel.text")); // NOI18N
        gbDownloadLabel.setName("gbDownloadLabel"); // NOI18N
        speedPanel.add(gbDownloadLabel);
        gbDownloadLabel.setBounds(510, 60, 19, 30);

        uploadDownloadExplanation.setText(resourceMap.getString("uploadDownloadExplanation.text")); // NOI18N
        uploadDownloadExplanation.setName("uploadDownloadExplanation"); // NOI18N
        speedPanel.add(uploadDownloadExplanation);
        uploadDownloadExplanation.setBounds(20, 100, 520, 70);

        settingsPanel.addTab(resourceMap.getString("speedPanel.TabConstraints.tabTitle"), speedPanel); // NOI18N

        updatesPanel.setName("updatesPanel"); // NOI18N

        updateMethod.setText(resourceMap.getString("updateMethod.text")); // NOI18N
        updateMethod.setName("updateMethod"); // NOI18N

        updateButtonGroup.add(updateInform);
        updateInform.setText(resourceMap.getString("updateInform.text")); // NOI18N
        updateInform.setName("updateInform"); // NOI18N

        updateButtonGroup.add(updateDownload);
        updateDownload.setText(resourceMap.getString("updateDownload.text")); // NOI18N
        updateDownload.setName("updateDownload"); // NOI18N

        updateButtonGroup.add(updateDownloadRestart);
        updateDownloadRestart.setText(resourceMap.getString("updateDownloadRestart.text")); // NOI18N
        updateDownloadRestart.setName("updateDownloadRestart"); // NOI18N

        checkUpdates.setText(resourceMap.getString("checkUpdates.text")); // NOI18N
        checkUpdates.setName("checkUpdates"); // NOI18N
        checkUpdates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkUpdatesActionPerformed(evt);
            }
        });

        updateNow.setText(resourceMap.getString("updateNow.text")); // NOI18N
        updateNow.setName("updateNow"); // NOI18N
        updateNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateNowActionPerformed(evt);
            }
        });

        advancedUpdateConfig.setText(resourceMap.getString("advancedUpdateConfig.text")); // NOI18N
        advancedUpdateConfig.setName("advancedUpdateConfig"); // NOI18N
        advancedUpdateConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advancedUpdateConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatesPanelLayout = new javax.swing.GroupLayout(updatesPanel);
        updatesPanel.setLayout(updatesPanelLayout);
        updatesPanelLayout.setHorizontalGroup(
            updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatesPanelLayout.createSequentialGroup()
                .addGroup(updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatesPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateMethod)
                            .addGroup(updatesPanelLayout.createSequentialGroup()
                                .addComponent(checkUpdates)
                                .addGap(18, 18, 18)
                                .addComponent(updateNow))))
                    .addGroup(updatesPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateInform, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(updateDownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateDownloadRestart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatesPanelLayout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(advancedUpdateConfig)
                .addContainerGap())
        );
        updatesPanelLayout.setVerticalGroup(
            updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateInform)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateDownload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateDownloadRestart)
                .addGap(18, 18, 18)
                .addGroup(updatesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkUpdates)
                    .addComponent(updateNow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(advancedUpdateConfig)
                .addContainerGap())
        );

        settingsPanel.addTab(resourceMap.getString("updatesPanel.TabConstraints.tabTitle"), updatesPanel); // NOI18N

        tunnelPanel.setName("tunnelPanel"); // NOI18N

        clientFrame.setName("clientFrame"); // NOI18N

        clientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type", "Address", "Status"
            }
        ));
        clientTable.setName("clientTable"); // NOI18N
        clientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientTableMouseClicked(evt);
            }
        });
        clientFrame.setViewportView(clientTable);
        clientTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("clientTable.columnModel.title0")); // NOI18N
        clientTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("clientTable.columnModel.title1")); // NOI18N
        clientTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("clientTable.columnModel.title2")); // NOI18N
        clientTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("clientTable.columnModel.title3")); // NOI18N

        serverFrame.setName("serverFrame"); // NOI18N

        serverTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Address", "Status"
            }
        ));
        serverTable.setName("serverTable"); // NOI18N
        serverTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serverTableMouseClicked(evt);
            }
        });
        serverFrame.setViewportView(serverTable);
        serverTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("serverTable.columnModel.title0")); // NOI18N
        serverTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("serverTable.columnModel.title1")); // NOI18N
        serverTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("serverTable.columnModel.title2")); // NOI18N

        tunnelsExplanation.setText(resourceMap.getString("tunnelsExplanation.text")); // NOI18N
        tunnelsExplanation.setName("tunnelsExplanation"); // NOI18N

        clientTunnelLabel.setText(resourceMap.getString("clientTunnelLabel.text")); // NOI18N
        clientTunnelLabel.setName("clientTunnelLabel"); // NOI18N

        serverTunnelLabel.setText(resourceMap.getString("serverTunnelLabel.text")); // NOI18N
        serverTunnelLabel.setName("serverTunnelLabel"); // NOI18N

        javax.swing.GroupLayout tunnelPanelLayout = new javax.swing.GroupLayout(tunnelPanel);
        tunnelPanel.setLayout(tunnelPanelLayout);
        tunnelPanelLayout.setHorizontalGroup(
            tunnelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tunnelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tunnelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tunnelsExplanation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addComponent(serverFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addComponent(clientTunnelLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clientFrame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addComponent(serverTunnelLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        tunnelPanelLayout.setVerticalGroup(
            tunnelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tunnelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clientTunnelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverTunnelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverFrame, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tunnelsExplanation, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        settingsPanel.addTab(resourceMap.getString("tunnelPanel.TabConstraints.tabTitle"), tunnelPanel); // NOI18N

        networkPanel.setName("networkPanel"); // NOI18N

        javax.swing.GroupLayout networkPanelLayout = new javax.swing.GroupLayout(networkPanel);
        networkPanel.setLayout(networkPanelLayout);
        networkPanelLayout.setHorizontalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        networkPanelLayout.setVerticalGroup(
            networkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        settingsPanel.addTab(resourceMap.getString("networkPanel.TabConstraints.tabTitle"), networkPanel); // NOI18N

        advancedPanel.setName("advancedPanel"); // NOI18N

        javax.swing.GroupLayout advancedPanelLayout = new javax.swing.GroupLayout(advancedPanel);
        advancedPanel.setLayout(advancedPanelLayout);
        advancedPanelLayout.setHorizontalGroup(
            advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        advancedPanelLayout.setVerticalGroup(
            advancedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        settingsPanel.addTab(resourceMap.getString("advancedPanel.TabConstraints.tabTitle"), advancedPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(applyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(settingsPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void speedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_speedKeyReleased
    try {
        String upload = "";
        if(uploadkbps.getSelectedIndex() == KILOBIT)
            upload = "" + Integer.parseInt(uploadspeed.getText())/8;
        else
            upload = uploadspeed.getText();
        String download = "";
        if(downloadkbps.getSelectedIndex() == KILOBIT)
            download = "" + Integer.parseInt(downloadspeed.getText())/8;
        else
            download = downloadspeed.getText();
        initUsage(upload, download);
    }
    catch(NumberFormatException e) {
        e.printStackTrace();
        return;
    }
}//GEN-LAST:event_speedKeyReleased

private void uploadkbpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadkbpsActionPerformed
    kbpsSwitchPerformed(uploadkbps, uploadspeed);
}//GEN-LAST:event_uploadkbpsActionPerformed

private void downloadkbpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadkbpsActionPerformed
    kbpsSwitchPerformed(downloadkbps, downloadspeed);
}//GEN-LAST:event_downloadkbpsActionPerformed

private void monthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthKeyReleased
    try {
        int uploadMonthValue = Integer.parseInt(uploadgb.getText());
        int downloadMonthValue = Integer.parseInt(downloadgb.getText());

        String upload = "";
        String burstUpload = "";
        String download = "";
        String burstDownload = "";

        if(uploadkbps.getSelectedIndex() == KILOBIT)
            upload = "" + SpeedHelper.calculateSpeed(uploadMonthValue)*8; //kbit
        else
            upload = "" + SpeedHelper.calculateSpeed(uploadMonthValue); //kbyte

        if(downloadkbps.getSelectedIndex() == KILOBIT)
            download = "" + SpeedHelper.calculateSpeed(downloadMonthValue)*8; //kbit
        else
            download = "" + SpeedHelper.calculateSpeed(downloadMonthValue); //kbyte

        initSpeeds(upload, download);
    }
    catch(NumberFormatException e) {
        e.printStackTrace();
        return;
    }
}//GEN-LAST:event_monthKeyReleased

private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
    this.dispose();
}//GEN-LAST:event_cancelMouseClicked

private void okMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okMouseClicked
    saveSpeeds();
    saveUpdatePolicy();
    this.dispose();
}//GEN-LAST:event_okMouseClicked

private void checkUpdatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkUpdatesActionPerformed
    long current = new Date().getTime();
    if(current < newsLastFetched + 5*60*1000) {
        return;
    }
    checkUpdates.setText("Checking for updates");
    checkUpdates.setEnabled(false);
    newsLastFetched = current;
    SwingWorker sw = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                NewsFetcher.getInstance(RouterHelper.getContext()).fetchNews();
                return null;
            }

            @Override
            protected void done() {
                checkUpdates.setText("Check for updates now");
                checkUpdates.setEnabled(true);
                if(NewsFetcher.getInstance(RouterHelper.getContext()).updateAvailable()) {
                    updateNow.setVisible(true);
                }
            }

    };
}//GEN-LAST:event_checkUpdatesActionPerformed

private void updateNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateNowActionPerformed
    SwingWorker sw = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                new net.i2p.router.web.UpdateHandler().update();
                return null;
            }
        
    };
    updateNow.setEnabled(false);
    updateNow.setText("Updating...");
    checkUpdates.setEnabled(false);

}//GEN-LAST:event_updateNowActionPerformed

private void advancedUpdateConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advancedUpdateConfigActionPerformed
    try {
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:7657/configupdate.jsp"));
    } catch (URISyntaxException ex) {
        Logger.getLogger(GeneralConfiguration.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IOException ex) {
            Logger.getLogger(GeneralConfiguration.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_advancedUpdateConfigActionPerformed

private void clientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientTableMouseClicked
    int row = clientTable.getSelectedRow();
    if(row == -1) { //No selected row
        return;
    }
    else {
        IndexBean bean = new IndexBean();
        /*
         * TODO: This is not entirely good: if one adds/removes a tunnel without desktopgui, this number will be wrong
         */
        int clientNumber = 0;
        int i = 0;
        for(clientNumber=0; clientNumber<bean.getTunnelCount(); clientNumber++) {
            if(bean.isClient(clientNumber)) {
                if(i == row) {
                    break;
                }
                i++;
            }
        }
        new ClientTunnelWindow(clientNumber, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    initTunnelTab();
                }
            
        });
    }
}//GEN-LAST:event_clientTableMouseClicked

private void serverTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serverTableMouseClicked
    int row = serverTable.getSelectedRow();
    if(row == -1) { //No selected row
        return;
    }
    else {
        IndexBean bean = new IndexBean();
        /*
         * TODO: This is not entirely good: if one adds/removes a tunnel without desktopgui, this number will be wrong
         */
        int serverNumber = 0;
        int i = 0;
        for(serverNumber=0; serverNumber<bean.getTunnelCount(); serverNumber++) {
            if(!bean.isClient(serverNumber)) {
                if(i == row) {
                    break;
                }
                i++;
            }
        }
        new ServerTunnelWindow(serverNumber, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    initTunnelTab();
                }

        });
    }
}//GEN-LAST:event_serverTableMouseClicked

    protected void initUsage(String upload, String download) {
        uploadgb.setText("" + SpeedHelper.calculateMonthlyUsage(Integer.parseInt(upload)));
        downloadgb.setText("" + SpeedHelper.calculateMonthlyUsage(Integer.parseInt(download)));
    }

    protected void initSpeeds(String upload, String download) {
        uploadspeed.setText(upload);
        downloadspeed.setText(download);
    }

    private void kbpsSwitchPerformed(JComboBox kbps, JTextField speed) {
        int index = kbps.getSelectedIndex();
        int previous = Integer.parseInt(speed.getText());
        if(index == KILOBIT) {
            speed.setText("" + previous*8);
        }
        else {
            speed.setText("" + previous/8);
        }
    }

    protected void saveSpeeds() {
        int maxDownload = Integer.parseInt(downloadspeed.getText());
        int maxUpload = Integer.parseInt(uploadspeed.getText());
        if(uploadkbps.getSelectedIndex() == KILOBIT) {
            SpeedHandler.setOutboundBandwidth(maxUpload/8);
            SpeedHandler.setOutboundBurstBandwidth(maxUpload/8);
        }
        else {
            SpeedHandler.setOutboundBandwidth(maxUpload);
            SpeedHandler.setOutboundBurstBandwidth(maxUpload);
        }
        if(downloadkbps.getSelectedIndex() == KILOBIT) {
            SpeedHandler.setInboundBandwidth(maxDownload/8);
            SpeedHandler.setInboundBurstBandwidth(maxDownload/8);
        }
        else {
            SpeedHandler.setInboundBandwidth(maxDownload);
            SpeedHandler.setInboundBurstBandwidth(maxDownload);
        }
    }

    protected void saveUpdatePolicy() {
        ButtonModel policyButton = updateButtonGroup.getSelection();
        if(policyButton.equals(updateInform.getModel())) {
            UpdateHandler.setUpdatePolicy(UpdateHelper.NOTIFY_UPDATE_POLICY);
        }
        else if(policyButton.equals(updateDownload.getModel())) {
            UpdateHandler.setUpdatePolicy(UpdateHelper.DOWNLOAD_UPDATE_POLICY);
        }
        else if(policyButton.equals(updateDownloadRestart.getModel())) {
            UpdateHandler.setUpdatePolicy(UpdateHelper.INSTALL_UPDATE_POLICY);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel advancedPanel;
    private javax.swing.JToggleButton advancedUpdateConfig;
    private javax.swing.JPanel applyPanel;
    private javax.swing.JToggleButton cancel;
    private javax.swing.JToggleButton checkUpdates;
    private javax.swing.JScrollPane clientFrame;
    private javax.swing.JTable clientTable;
    private javax.swing.JLabel clientTunnelLabel;
    private javax.swing.JLabel downloadSpeedLabel;
    private javax.swing.JLabel downloadUsageLabel;
    private javax.swing.JTextField downloadgb;
    private javax.swing.JComboBox downloadkbps;
    private javax.swing.JTextField downloadspeed;
    private javax.swing.JLabel gbDownloadLabel;
    private javax.swing.JLabel gbUploadLabel;
    private javax.swing.JPanel networkPanel;
    private javax.swing.JToggleButton ok;
    private javax.swing.JScrollPane serverFrame;
    private javax.swing.JTable serverTable;
    private javax.swing.JLabel serverTunnelLabel;
    private javax.swing.JTabbedPane settingsPanel;
    private javax.swing.JPanel speedPanel;
    private javax.swing.JPanel tunnelPanel;
    private javax.swing.JLabel tunnelsExplanation;
    private javax.swing.ButtonGroup updateButtonGroup;
    private javax.swing.JRadioButton updateDownload;
    private javax.swing.JRadioButton updateDownloadRestart;
    private javax.swing.JRadioButton updateInform;
    private javax.swing.JLabel updateMethod;
    private javax.swing.JToggleButton updateNow;
    private javax.swing.JPanel updatesPanel;
    private javax.swing.JLabel uploadDownloadExplanation;
    private javax.swing.JLabel uploadSpeedLabel;
    private javax.swing.JLabel uploadUsageLabel;
    private javax.swing.JTextField uploadgb;
    private javax.swing.JComboBox uploadkbps;
    private javax.swing.JTextField uploadspeed;
    // End of variables declaration//GEN-END:variables

    public static final int KILOBIT = 0;
    public static final int KILOBYTE = 1;

    private long newsLastFetched = 0;
}

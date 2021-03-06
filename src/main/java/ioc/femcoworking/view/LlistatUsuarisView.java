
package ioc.femcoworking.view;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioc.femcoworking.dto.DTOUsuari;
import ioc.femcoworking.vo.UsuariVO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;
import java.util.*;
import javax.swing.table.TableModel;
import resources.SimpleDialog;

/**
 * GUI per a mostrar el llistat d'usuaris del sistema
 * 
 * @author Marc Ginovart Vega
 */
public class LlistatUsuarisView extends javax.swing.JFrame {
    private static String codiAcces;
    private static final Integer EDITAR_USUARI = 0;
    private static final Integer DESHABILITAR_USUARI = 1;
    
    public LlistatUsuarisView(String codi) {
        codiAcces = codi;  
        initComponents();
        carregarLlistatUsuaris(codiAcces);
    }
    
    public LlistatUsuarisView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        taulaUsuaris = new javax.swing.JTable();

        setTitle("FEM_Coworking");

        jLabel1.setText("Llistat Usuaris");

        taulaUsuaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        taulaUsuaris.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taulaUsuarisMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taulaUsuaris);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void taulaUsuarisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taulaUsuarisMouseClicked
        Integer index = taulaUsuaris.getSelectedRow();
        TableModel model = taulaUsuaris.getModel();
                
        String idUsuari = model.getValueAt(index, 0).toString();
        String nom = model.getValueAt(index, 1).toString();
        String email = model.getValueAt(index, 2).toString();
        String rol = model.getValueAt(index, 3).toString();
        String cif = model.getValueAt(index, 4).toString();
        String direccio = model.getValueAt(index, 5).toString();
        String poblacio = model.getValueAt(index, 6).toString();
        String provincia = model.getValueAt(index, 7).toString();
        String deshabilitat = model.getValueAt(index, 8).toString();
        
        InformacioUsuariView informacioUsuariView = new InformacioUsuariView(codiAcces, idUsuari);
        
        informacioUsuariView.inputNom.setText(nom);
        informacioUsuariView.inputEmail.setText(email);
        informacioUsuariView.inputRol.setText(rol);
        informacioUsuariView.inputCifEmpresa.setText(cif);
        informacioUsuariView.inputDireccio.setText(direccio);
        informacioUsuariView.inputPoblacio.setText(poblacio);
        informacioUsuariView.inputProvincia.setText(provincia);
        informacioUsuariView.chkDeshabilitat.setSelected(Boolean.parseBoolean(deshabilitat)); 
        
        /*
        Integer accioUsuari= new SimpleDialog().optionMessage("Editar o deshabilitar usuari?");        
        if (accioUsuari == EDITAR_USUARI) {
            habilitarCamps(informacioUsuariView);
        } else if (accioUsuari == DESHABILITAR_USUARI) {
            deshabilitarCamps(informacioUsuariView);
        }
        */
        informacioUsuariView.btnGuardarEditar.setVisible(false);
        
        informacioUsuariView.setVisible(true);
        
    }//GEN-LAST:event_taulaUsuarisMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LlistatUsuarisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LlistatUsuarisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LlistatUsuarisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LlistatUsuarisView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LlistatUsuarisView().setVisible(true);
            }
        });
    }
    
    private void carregarLlistatUsuaris(String codiAcces) {        
        DTOUsuari usuari = new DTOUsuari();
        
        try {
            JSONObject response = usuari.llistatUsuaris(codiAcces);
            
            if (200 != response.getInt("code")) {
                System.out.println(response.getString("message"));
                JSONObject jsonResponse = new JSONObject(response.getString("message"));
                new SimpleDialog().errorMessage(jsonResponse.get("message").toString());
            } else {
                List<UsuariVO> llistatUsuaris = new ObjectMapper().readValue(
                    response.getString("message"), 
                    new TypeReference<List<UsuariVO>>() {}
                );               
                
                DefaultTableModel model = new DefaultTableModel();
        
                model.setColumnIdentifiers(new Object[]{
                    "Id",
                    "Nom",
                    "Email",
                    "Rol",
                    "CIF Empresa",
                    "Direcci??",
                    "Poblaci??",
                    "Provincia",
                    "Deshabilitat",
                    "Creat el",
                    "Darrer acc??s"
                });
                
                for (UsuariVO usuariInfo: llistatUsuaris) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String modelData = objectMapper.writeValueAsString(usuariInfo);
                    System.out.println("oficina >> "+modelData);
                    model.addRow(new Object[]{
                        usuariInfo.getIdUsuari(),
                        usuariInfo.getNom(),
                        usuariInfo.getEmail(),
                        usuariInfo.getRol(),
                        usuariInfo.getCifEmpresa(),
                        usuariInfo.getDireccio(),
                        usuariInfo.getPoblacio(),
                        usuariInfo.getProvincia(),
                        usuariInfo.getDeshabilitat(),
                        usuariInfo.getDataCreacio(),
                        usuariInfo.getUltimAcces(),
                    });
                }
                
                taulaUsuaris.setModel(model);
                
                taulaUsuaris.getColumnModel().removeColumn(taulaUsuaris.getColumnModel().getColumn(0));
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LlistatUsuarisView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deshabilitarCamps(InformacioUsuariView informacioUsuariView) {
        informacioUsuariView.inputNom.setEnabled(false);
        informacioUsuariView.inputCifEmpresa.setEnabled(false);
        informacioUsuariView.inputDireccio.setEnabled(false);
        informacioUsuariView.inputPoblacio.setEnabled(false);
        informacioUsuariView.inputProvincia.setEnabled(false);
        
        informacioUsuariView.btnGuardarEditar.setVisible(false);
        informacioUsuariView.btnGuardarDeshabilitar.setVisible(true);
    }
    
    private void habilitarCamps(InformacioUsuariView informacioUsuariView) {
        informacioUsuariView.inputNom.setEnabled(true);
        informacioUsuariView.inputCifEmpresa.setEnabled(true);
        informacioUsuariView.inputDireccio.setEnabled(true);
        informacioUsuariView.inputPoblacio.setEnabled(true);
        informacioUsuariView.inputProvincia.setEnabled(true);
        
        informacioUsuariView.btnGuardarEditar.setVisible(true);
        informacioUsuariView.btnGuardarDeshabilitar.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable taulaUsuaris;
    // End of variables declaration//GEN-END:variables
}

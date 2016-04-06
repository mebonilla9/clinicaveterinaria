/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.intecap.clinicaveterinaria.vista.paneles;

import co.edu.intecap.clinicaveterinaria.control.ConsultaDelegado;
import co.edu.intecap.clinicaveterinaria.control.HistoriaDelegado;
import co.edu.intecap.clinicaveterinaria.control.MascotaDelegado;
import co.edu.intecap.clinicaveterinaria.control.MedicoDelegado;
import co.edu.intecap.clinicaveterinaria.modelo.vo.ConsultaVo;
import co.edu.intecap.clinicaveterinaria.modelo.vo.HistoriaVo;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MascotaVo;
import co.edu.intecap.clinicaveterinaria.modelo.vo.MedicoVo;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Lord_Nightmare
 */
public class ConsultaPanel extends javax.swing.JPanel {

    private static final String DATE_PATTERN
            = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

    private final List<MedicoVo> listaMedicos;
    private final List<MascotaVo> listaMascotas;
    private DefaultTableModel modelo;

    /**
     * Creates new form ConsultaPanel
     */
    public ConsultaPanel() {
        initComponents();
        listaMedicos = new MedicoDelegado(this).consultarMedicos();
        listaMascotas = new MascotaDelegado(this).consultarMascotas();
        configurarCombos();
        configurarMascaraFecha();
        configurarTabla();
        llenarTabla(new ConsultaDelegado(this).consultarConsultas(), modelo);
    }

    private void configurarCombos() {
        cboMedico.addItem("Seleccione un medico");
        cboMascota.addItem("Seleccione una mascota");
        for (MedicoVo medico : listaMedicos) {
            cboMedico.addItem(medico.getNombre());
        }
        for (MascotaVo mascota : listaMascotas) {
            cboMascota.addItem(mascota.getNombre());
        }
    }

    private void configurarMascaraFecha() {
        try {
            MaskFormatter mascaraFecha = new MaskFormatter("####-##-##");
            mascaraFecha.install(txtFecha);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private int getCboMedicosItem() {
        return cboMedico.getSelectedIndex() - 1;
    }

    private int getCboMascotasItem() {
        return cboMascota.getSelectedIndex() - 1;
    }

    private void registrarConsulta() {
        try {
            String fechaConsulta = txtFecha.getText();
            if (fechaConsulta.matches(DATE_PATTERN)) {
                ConsultaVo consultaVo = new ConsultaVo();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(fechaConsulta);
                consultaVo.setFecha(Date.valueOf(fechaConsulta));
                consultaVo.setMotivo(txtMotivo.getText());
                consultaVo.setEstado(txtEstado.getText());
                consultaVo.setDescripcion(txtDescripcion.getText());
                MedicoVo medicoVo = listaMedicos.get(this.getCboMedicosItem());
                consultaVo.setIdMedico(medicoVo.getIdMedico());
                MascotaVo mascotaVo = listaMascotas.get(this.getCboMascotasItem());
                HistoriaVo historiaVo = new HistoriaDelegado(this).consultarHistoriaMascota(mascotaVo.getIdMascota());
                consultaVo.setIdHistoria(historiaVo.getIdHistoria());
                new ConsultaDelegado(this).registrarConsulta(consultaVo);
                JOptionPane.showMessageDialog(this, "La consulta ha sido registrada", "Registro de consulta", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                refrescarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "La fecha digitada no es valida", "Error de fecha", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private void limpiarCampos(){
        txtFecha.setText("");
        txtMotivo.setText("");
        txtEstado.setText("");
        txtDescripcion.setText("");
        cboMascota.setSelectedIndex(0);
        cboMedico.setSelectedIndex(0);
    } 
    
    private void configurarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id consulta");
        modelo.addColumn("Fecha");
        modelo.addColumn("Motivo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Estado");
        modelo.addColumn("Id Medico");
        modelo.addColumn("Id Historia");
        tblConsulta.setModel(modelo);
        tblConsulta.getSelectionModel().addListSelectionListener(tableListener);
    }
    
    private void llenarTabla(List<ConsultaVo> listaConsulta, DefaultTableModel modelo) {
        for (ConsultaVo consultaVo : listaConsulta) {
            Object[] fila = new Object[7];
            fila[0] = consultaVo.getIdConsulta();
            fila[1] = consultaVo.getFecha();
            fila[2] = consultaVo.getMotivo();
            fila[3] = consultaVo.getDescripcion();
            fila[4] = consultaVo.getEstado();
            fila[5] = consultaVo.getIdMedico();
            fila[6] = consultaVo.getIdHistoria();
            modelo.addRow(fila);
        }
        tblConsulta.updateUI();
    }
    
    private void refrescarTabla(){
        modelo.setRowCount(0);
        List<ConsultaVo> listaConsultas = new ConsultaDelegado(this).consultarConsultas();
        this.llenarTabla(listaConsultas, this.modelo);
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
        jLabel2 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboMedico = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cboMascota = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConsulta = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        txtFecha = new javax.swing.JFormattedTextField();

        jLabel1.setText("Fecha:");

        jLabel2.setText("Motivo:");

        jLabel3.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setText("Estado:");

        jLabel5.setText("Medico:");

        jLabel6.setText("Mascota");

        tblConsulta.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblConsulta);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addComponent(cboMascota, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(170, 170, 170)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtEstado)
                                                .addComponent(cboMedico, 0, 152, Short.MAX_VALUE))))))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(btnGuardar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        registrarConsulta();
    }//GEN-LAST:event_btnGuardarActionPerformed

    ListSelectionListener tableListener = new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (tblConsulta.getSelectedRow() > -1) {
                System.out.println(tblConsulta.getValueAt(tblConsulta.getSelectedRow(), 0).toString());
            }
        }
    };

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cboMascota;
    private javax.swing.JComboBox<String> cboMedico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblConsulta;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtMotivo;
    // End of variables declaration//GEN-END:variables
}

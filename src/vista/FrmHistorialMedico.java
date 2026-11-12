package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmHistorialMedico extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmHistorialMedico(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);setTitle("Historial médico");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtPaciente = new javax.swing.JTextField();
        txtSangre = new javax.swing.JTextField();
        txtAlergias = new javax.swing.JTextField();
        txtCronicas = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollHistorialMedico = new javax.swing.JScrollPane();
        tblHistorialMedico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("HISTORIAL MÉDICO");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF04, RF27, RF28 | Consulta y edición de antecedentes clínicos");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del paciente"));
        panelDatos.add(txtPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtSangre.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupo sanguíneo"));
        panelDatos.add(txtSangre, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtAlergias.setBorder(javax.swing.BorderFactory.createTitledBorder("Alergias"));
        panelDatos.add(txtAlergias, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        txtCronicas.setBorder(javax.swing.BorderFactory.createTitledBorder("Enfermedades crónicas"));
        panelDatos.add(txtCronicas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        txtObservaciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Observaciones"));
        panelDatos.add(txtObservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnConsultar.setText("Buscar DNI");
        panelAcciones.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 286, 42));

        btnGuardar.setText("Guardar historial");
        panelAcciones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 23, 286, 42));

        btnActualizar.setText("Actualizar listado");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(608, 23, 286, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHistorialMedico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Paciente", "Grupo", "Alergias", "Crónicas", "Última actualización"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollHistorialMedico.setViewportView(tblHistorialMedico);

        panelTabla.add(scrollHistorialMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        btnConsultar.addActionListener(e->consultar());btnGuardar.addActionListener(e->guardar());btnActualizar.addActionListener(e->cargar());
        tblHistorialMedico.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblHistorialMedico.getSelectedRow()>=0){
                txtPaciente.setText(String.valueOf(tblHistorialMedico.getValueAt(tblHistorialMedico.getSelectedRow(),0)));
                consultar();
            }
        });
    }
    private void consultar(){try{
        String dni=Validaciones.dni(txtPaciente.getText());Paciente p=datos.buscarPacienteDni(dni);
        if(p==null)throw new IllegalArgumentException("Paciente no encontrado.");
        for(HistorialMedico h:datos.historiales())if(h.getIdPaciente()==p.getId()){
            txtSangre.setText(h.getSangre());txtAlergias.setText(h.getAlergias());
            txtCronicas.setText(h.getCronicas());txtObservaciones.setText(h.getObservaciones());return;
        }
        txtSangre.setText("");txtAlergias.setText("");txtCronicas.setText("");txtObservaciones.setText("");
        VistaUtil.mensaje(this,"Paciente sin historial. Puedes crear uno.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void guardar(){try{
        Paciente p=datos.buscarPacienteDni(Validaciones.dni(txtPaciente.getText()));
        if(p==null)throw new IllegalArgumentException("El DNI no está registrado.");
        datos.guardarHistorial(new HistorialMedico(p.getId(),txtSangre.getText().trim(),txtAlergias.getText().trim(),
            txtCronicas.getText().trim(),txtObservaciones.getText().trim()));
        cargar();VistaUtil.mensaje(this,"Historial actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        ArrayList<Object[]> filas=new ArrayList<>();
        for(HistorialMedico h:datos.historiales()){
            for(Paciente p:datos.pacientes())if(p.getId()==h.getIdPaciente()){
                filas.add(new Object[]{p.getDni(),p.getNombreCompleto(),h.getSangre(),h.getAlergias(),h.getCronicas(),h.getActualizado()});break;
            }
        }VistaUtil.filas(tblHistorialMedico,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollHistorialMedico;
    private javax.swing.JTable tblHistorialMedico;
    private javax.swing.JTextField txtAlergias;
    private javax.swing.JTextField txtCronicas;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JTextField txtSangre;
    // End of variables declaration//GEN-END:variables
}

package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
public class FrmHorarios extends javax.swing.JFrame {
    private final RepositorioClinica datos;
    private final Usuario sesion;
    public FrmHorarios(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);
        setTitle("Horarios Médicos Y Consultorios");setSize(1000,710);setLocationRelativeTo(null);
        construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        cbxMedico = new javax.swing.JComboBox();
        txtFecha = new javax.swing.JTextField();
        txtInicio = new javax.swing.JTextField();
        txtFin = new javax.swing.JTextField();
        txtConsultorio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        scrollHorarios = new javax.swing.JScrollPane();
        tblHorarios = new javax.swing.JTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lblTitulo.setText("HORARIOS MÉDICOS Y CONSULTORIOS");
        panelContenido.add(lblTitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,8,890,26));
        lblSubtitulo.setText("RF10 | Registrar disponibilidad por fecha, médico y consultorio");
        panelContenido.add(lblSubtitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,34,890,23));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cbxMedico.setBorder(javax.swing.BorderFactory.createTitledBorder("Médico"));
        panelDatos.add(cbxMedico,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,24,268,54));
        txtFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha AAAA-MM-DD"));
        panelDatos.add(txtFecha,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,24,268,54));
        txtInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora de inicio HH:MM"));
        panelDatos.add(txtInicio,new org.netbeans.lib.awtextra.AbsoluteConstraints(618,24,268,54));
        txtFin.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora de fin HH:MM"));
        panelDatos.add(txtFin,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,86,268,54));
        txtConsultorio.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultorio"));
        panelDatos.add(txtConsultorio,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,86,268,54));

        panelContenido.add(panelDatos,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,64,915,168));
        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnGuardar.setText("Registrar horario");
        panelAcciones.add(btnGuardar,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,23,284,42));
        btnEliminar.setText("Eliminar seleccionado");
        panelAcciones.add(btnEliminar,new org.netbeans.lib.awtextra.AbsoluteConstraints(312,23,284,42));
        btnActualizar.setText("Mostrar horarios");
        panelAcciones.add(btnActualizar,new org.netbeans.lib.awtextra.AbsoluteConstraints(605,23,284,42));

        panelContenido.add(panelAcciones,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,242,915,78));
        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tblHorarios.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{},new String[]{"ID","Médico","Fecha","Inicio","Fin","Consultorio"}){
            boolean[] canEdit=new boolean[]{false,false,false,false,false,false};
            public boolean isCellEditable(int rowIndex,int columnIndex){return canEdit[columnIndex];}
        });
        scrollHorarios.setViewportView(tblHorarios);
        panelTabla.add(scrollHorarios,new org.netbeans.lib.awtextra.AbsoluteConstraints(14,25,885,242));

        panelContenido.add(panelTabla,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,328,915,284));
        getContentPane().add(panelContenido,new org.netbeans.lib.awtextra.AbsoluteConstraints(8,8,950,620));
        pack();setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void construir(){
        txtFecha.setText(LocalDate.now().plusDays(1).toString());txtInicio.setText("08:00");txtFin.setText("12:00");txtConsultorio.setText("Consultorio 1");
        btnGuardar.addActionListener(e->guardar());btnEliminar.addActionListener(e->eliminar());btnActualizar.addActionListener(e->cargar());
        boolean administrador=sesion.getRol().equals("Administrador");
        btnGuardar.setEnabled(administrador);btnEliminar.setEnabled(administrador);
    }
    private void guardar(){try{
        Medico medico=(Medico)cbxMedico.getSelectedItem();if(medico==null)throw new IllegalArgumentException("Selecciona médico.");
        datos.registrarHorario(medico.getId(),Validaciones.fecha(txtFecha.getText()),Validaciones.hora(txtInicio.getText()),Validaciones.hora(txtFin.getText()),txtConsultorio.getText());
        cargar();VistaUtil.mensaje(this,"Horario registrado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{
        int i=tblHorarios.getSelectedRow();if(i<0)throw new IllegalArgumentException("Selecciona un horario.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar el horario seleccionado?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarHorario((int)tblHorarios.getValueAt(i,0));cargar();VistaUtil.mensaje(this,"Horario eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxMedico.removeAllItems();for(Medico m:datos.medicos())cbxMedico.addItem(m);
        ArrayList<Object[]> filas=new ArrayList<>();for(HorarioMedico h:datos.horarios()){
            String nombre="";for(Medico m:datos.medicos())if(m.getId()==h.getIdMedico())nombre=m.getNombreCompleto();
            filas.add(new Object[]{h.getId(),nombre,h.getFecha(),h.getInicio(),h.getFin(),h.getConsultorio()});
        }VistaUtil.filas(tblHorarios,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JComboBox cbxMedico;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtInicio;
    private javax.swing.JTextField txtFin;
    private javax.swing.JTextField txtConsultorio;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JScrollPane scrollHorarios;
    private javax.swing.JTable tblHorarios;
    // End of variables declaration//GEN-END:variables
}

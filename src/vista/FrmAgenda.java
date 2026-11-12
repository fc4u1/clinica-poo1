package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmAgenda extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    
    public FrmAgenda(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);setTitle("Agenda médica");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtFecha = new javax.swing.JTextField();
        txtDniPaciente = new javax.swing.JTextField();
        txtDniMedico = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollAgenda = new javax.swing.JScrollPane();
        tblAgenda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("AGENDA MÉDICA");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF23, RF24 | Filtrar citas por fecha y DNI del paciente o médico");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha (AAAA-MM-DD)"));
        panelDatos.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtDniPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del paciente (opcional)"));
        panelDatos.add(txtDniPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtDniMedico.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del médico (opcional)"));
        panelDatos.add(txtDniMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setText("Filtrar agenda");
        panelAcciones.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 434, 42));

        btnTodos.setText("Mostrar todas");
        panelAcciones.add(btnTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 434, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI paciente", "Paciente", "DNI médico", "Médico", "Fecha", "Hora", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollAgenda.setViewportView(tblAgenda);

        panelTabla.add(scrollAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){txtFecha.setText(LocalDate.now().plusDays(1).toString());
        btnBuscar.addActionListener(e->cargar());btnTodos.addActionListener(e->{
            txtFecha.setText("");txtDniPaciente.setText("");txtDniMedico.setText("");cargar();});
    }
    private void cargar(){try{
        String fecha=txtFecha.getText().trim(),dniP=txtDniPaciente.getText().trim(),dniM=txtDniMedico.getText().trim();
        if(!fecha.isEmpty())Validaciones.fecha(fecha);
        if(!dniP.isEmpty())Validaciones.dni(dniP);
        if(!dniM.isEmpty())Validaciones.dni(dniM);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Cita c:datos.citas()){
            if(c.getEstado().equals("Eliminada"))continue;
            if(!fecha.isEmpty()&&!c.getFecha().toString().equals(fecha))continue;
            Paciente p=null;Medico m=null;
            for(Paciente q:datos.pacientes())if(q.getId()==c.getIdPaciente())p=q;
            for(Medico q:datos.medicos())if(q.getId()==c.getIdMedico())m=q;
            if(p==null||m==null)continue;
            if(!dniP.isEmpty()&&!p.getDni().equals(dniP))continue;
            if(!dniM.isEmpty()&&!m.getDni().equals(dniM))continue;
            filas.add(new Object[]{c.getId(),p.getDni(),p.getNombreCompleto(),m.getDni(),m.getNombreCompleto(),c.getFecha(),c.getHora(),c.getEstado()});
        }VistaUtil.filas(tblAgenda,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnTodos;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollAgenda;
    private javax.swing.JTable tblAgenda;
    private javax.swing.JTextField txtDniMedico;
    private javax.swing.JTextField txtDniPaciente;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}

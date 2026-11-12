package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
public class FrmConsultas extends javax.swing.JFrame {
    private final RepositorioClinica datos;
    private final Usuario sesion;
    public FrmConsultas(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);
        setTitle("Consultas Médicas");setSize(1000,710);setLocationRelativeTo(null);
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
        cbxCita = new javax.swing.JComboBox();
        txtSintomas = new javax.swing.JTextField();
        txtDiagnostico = new javax.swing.JTextField();
        txtTratamiento = new javax.swing.JTextField();
        btnAtender = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        scrollConsultas = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lblTitulo.setText("CONSULTAS MÉDICAS");
        panelContenido.add(lblTitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,8,890,26));
        lblSubtitulo.setText("RF03 | Registrar consulta desde una cita atendida");
        panelContenido.add(lblSubtitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,34,890,23));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cbxCita.setBorder(javax.swing.BorderFactory.createTitledBorder("Cita asociada"));
        panelDatos.add(cbxCita,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,24,268,54));
        txtSintomas.setBorder(javax.swing.BorderFactory.createTitledBorder("Síntomas"));
        panelDatos.add(txtSintomas,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,24,268,54));
        txtDiagnostico.setBorder(javax.swing.BorderFactory.createTitledBorder("Diagnóstico"));
        panelDatos.add(txtDiagnostico,new org.netbeans.lib.awtextra.AbsoluteConstraints(618,24,268,54));
        txtTratamiento.setBorder(javax.swing.BorderFactory.createTitledBorder("Tratamiento"));
        panelDatos.add(txtTratamiento,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,86,268,54));

        panelContenido.add(panelDatos,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,64,915,168));
        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnAtender.setText("Marcar cita atendida");
        panelAcciones.add(btnAtender,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,23,284,42));
        btnGuardar.setText("Guardar consulta");
        panelAcciones.add(btnGuardar,new org.netbeans.lib.awtextra.AbsoluteConstraints(312,23,284,42));
        btnActualizar.setText("Mostrar consultas");
        panelAcciones.add(btnActualizar,new org.netbeans.lib.awtextra.AbsoluteConstraints(605,23,284,42));

        panelContenido.add(panelAcciones,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,242,915,78));
        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{},new String[]{"ID","Cita","Paciente","Médico","Diagnóstico","Fecha"}){
            boolean[] canEdit=new boolean[]{false,false,false,false,false,false};
            public boolean isCellEditable(int rowIndex,int columnIndex){return canEdit[columnIndex];}
        });
        scrollConsultas.setViewportView(tblConsultas);
        panelTabla.add(scrollConsultas,new org.netbeans.lib.awtextra.AbsoluteConstraints(14,25,885,242));

        panelContenido.add(panelTabla,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,328,915,284));
        getContentPane().add(panelContenido,new org.netbeans.lib.awtextra.AbsoluteConstraints(8,8,950,620));
        pack();setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void construir(){
        cbxCita.setRenderer(new javax.swing.DefaultListCellRenderer(){
            @Override public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> lista,Object valor,int indice,boolean seleccionado,boolean foco){
                super.getListCellRendererComponent(lista,valor,indice,seleccionado,foco);
                if(valor instanceof Cita){Cita cita=(Cita)valor;setText("Cita #"+cita.getId()+" - "+cita.getFecha()+" "+cita.getHora()+" ("+cita.getEstado()+")");}
                return this;
            }
        });
        btnAtender.addActionListener(e->atender());btnGuardar.addActionListener(e->guardar());btnActualizar.addActionListener(e->cargar());
        tblConsultas.getSelectionModel().addListSelectionListener(e->{
            if(e.getValueIsAdjusting()||tblConsultas.getSelectedRow()<0)return;
            int id=(int)tblConsultas.getValueAt(tblConsultas.getSelectedRow(),0);
            try{for(Consulta c:datos.consultas())if(c.getId()==id){
                txtSintomas.setText(c.getSintomas());txtDiagnostico.setText(c.getDiagnostico());txtTratamiento.setText(c.getTratamiento());
                for(int i=0;i<cbxCita.getItemCount();i++){Cita ci=(Cita)cbxCita.getItemAt(i);if(ci.getId()==c.getIdCita()){cbxCita.setSelectedIndex(i);break;}}
            }}catch(Exception ex){VistaUtil.error(this,ex);}
        });
    }
    private void atender(){try{
        Cita cita=(Cita)cbxCita.getSelectedItem();if(cita==null)throw new IllegalArgumentException("Selecciona una cita.");
        datos.atenderCita(cita.getId());cargar();VistaUtil.mensaje(this,"Cita marcada como atendida. Ahora puedes registrar la consulta.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void guardar(){try{
        Cita cita=(Cita)cbxCita.getSelectedItem();if(cita==null)throw new IllegalArgumentException("Selecciona una cita.");
        datos.guardarConsulta(cita.getId(),txtSintomas.getText(),txtDiagnostico.getText(),txtTratamiento.getText());
        cargar();VistaUtil.mensaje(this,"Consulta registrada o actualizada.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        int seleccion=-1;if(cbxCita.getSelectedItem()!=null)seleccion=((Cita)cbxCita.getSelectedItem()).getId();
        cbxCita.removeAllItems();for(Cita c:datos.citas())if(!c.getEstado().equals("Eliminada")&&!c.getEstado().equals("Cancelada"))cbxCita.addItem(c);
        for(int i=0;i<cbxCita.getItemCount();i++)if(((Cita)cbxCita.getItemAt(i)).getId()==seleccion){cbxCita.setSelectedIndex(i);break;}
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Consulta c:datos.consultas()){
            String paciente="",medico="";for(Cita cita:datos.citas())if(cita.getId()==c.getIdCita()){
                for(Paciente p:datos.pacientes())if(p.getId()==cita.getIdPaciente())paciente=p.getNombreCompleto();
                for(Medico m:datos.medicos())if(m.getId()==cita.getIdMedico())medico=m.getNombreCompleto();
            }
            filas.add(new Object[]{c.getId(),c.getIdCita(),paciente,medico,c.getDiagnostico(),c.getFecha()});
        }VistaUtil.filas(tblConsultas,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JComboBox cbxCita;
    private javax.swing.JTextField txtSintomas;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtTratamiento;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JScrollPane scrollConsultas;
    private javax.swing.JTable tblConsultas;
    // End of variables declaration//GEN-END:variables
}

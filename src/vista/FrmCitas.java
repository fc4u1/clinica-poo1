package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmCitas extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;


    public FrmCitas(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);setTitle("Citas médicas");
        setSize(1000,710);setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtDniPaciente = new javax.swing.JTextField();
        txtDniMedico = new javax.swing.JTextField();
        lblPaciente = new javax.swing.JLabel();
        lblMedico = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtHora = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnBuscarDni = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnReprogramar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRecargar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollCitas = new javax.swing.JScrollPane();
        tblCitas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("GESTIÓN DE CITAS MÉDICAS");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF02, RF20, RF21, RF22 | Registrar por DNI, modificar, cancelar y eliminar");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDniPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del paciente (8 dígitos)"));
        panelDatos.add(txtDniPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 420, 46));

        txtDniMedico.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del médico (8 dígitos)"));
        panelDatos.add(txtDniMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 20, 420, 46));

        lblPaciente.setText("Paciente: Escribe DNI y pulsa Buscar DNI");
        panelDatos.add(lblPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 66, 420, 26));

        lblMedico.setText("Médico: Escribe DNI y pulsa Buscar DNI");
        panelDatos.add(lblMedico, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 66, 420, 26));

        txtFecha.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha (AAAA-MM-DD)"));
        panelDatos.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 101, 420, 46));

        txtHora.setBorder(javax.swing.BorderFactory.createTitledBorder("Hora (HH:MM)"));
        panelDatos.add(txtHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 101, 420, 46));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscarDni.setText("Buscar DNI");
        panelAcciones.add(btnBuscarDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 118, 42));

        btnRegistrar.setText("Registrar cita");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 23, 118, 42));

        btnEditar.setText("Modificar cita");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 23, 118, 42));

        btnReprogramar.setText("Reprogramar");
        panelAcciones.add(btnReprogramar, new org.netbeans.lib.awtextra.AbsoluteConstraints(398, 23, 118, 42));

        btnCancelar.setText("Cancelar cita");
        panelAcciones.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 23, 118, 42));

        btnEliminar.setText("Eliminar cita");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 23, 118, 42));

        btnRecargar.setText("Refrescar");
        panelAcciones.add(btnRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 23, 118, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI paciente", "Paciente", "DNI médico", "Médico", "Fecha", "Hora", "Estado", "Empleado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollCitas.setViewportView(tblCitas);

        panelTabla.add(scrollCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Cita> cache=new ArrayList<>();
    private void construir(){
        txtFecha.setText(LocalDate.now().plusDays(1).toString());txtHora.setText("09:00");
        btnBuscarDni.addActionListener(e->buscarDnis(true));
        txtDniPaciente.addActionListener(e->buscarDnis(true));txtDniMedico.addActionListener(e->buscarDnis(true));
        btnRegistrar.addActionListener(e->guardar(false));
        btnEditar.addActionListener(e->guardar(true));
        btnReprogramar.addActionListener(e->guardar(true));
        btnCancelar.addActionListener(e->cancelar());btnEliminar.addActionListener(e->eliminar());
        btnRecargar.addActionListener(e->cargar());
        tblCitas.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblCitas.getSelectedRow()>=0){
                int id=(int)tblCitas.getValueAt(tblCitas.getSelectedRow(),0);
                for(Cita c:cache)if(c.getId()==id){
                    for(Paciente p:listaPacientes())if(p.getId()==c.getIdPaciente())txtDniPaciente.setText(p.getDni());
                    for(Medico m:listaMedicos())if(m.getId()==c.getIdMedico())txtDniMedico.setText(m.getDni());
                    txtFecha.setText(c.getFecha().toString());txtHora.setText(c.getHora().toString());
                    buscarDnis(false);break;
                }
            }
        });
    }
    private ArrayList<Paciente> listaPacientes(){try{return datos.pacientes();}catch(Exception ex){return new ArrayList<>();}}
    private ArrayList<Medico> listaMedicos(){try{return datos.medicos();}catch(Exception ex){return new ArrayList<>();}}
    private boolean buscarDnis(boolean avisar){try{
        String dniPaciente=Validaciones.dni(txtDniPaciente.getText());
        String dniMedico=Validaciones.dni(txtDniMedico.getText());
        Paciente p=datos.buscarPacienteDni(dniPaciente);
        Medico m=datos.buscarMedicoDni(dniMedico);
        lblPaciente.setText(p==null?"Paciente no encontrado":"Paciente: "+p.getNombreCompleto()+" | DNI "+p.getDni());
        lblMedico.setText(m==null?"Médico no encontrado":"Médico: "+m.getNombreCompleto()+" | "+m.getEspecialidad());
        if(p==null||m==null){if(avisar)VistaUtil.mensaje(this,"Primero registre al paciente y médico o corrija los DNI.");return false;}
        return true;
    }catch(Exception ex){lblPaciente.setText("Paciente: verifique DNI");lblMedico.setText("Médico: verifique DNI");if(avisar)VistaUtil.error(this,ex);return false;}}
    private void guardar(boolean editar){try{
        if(!buscarDnis(true))return;
        Paciente p=datos.buscarPacienteDni(txtDniPaciente.getText().trim());
        Medico m=datos.buscarMedicoDni(txtDniMedico.getText().trim());
        int id=0,empleado=sesion.getIdEmpleado();
        if(editar){int fila=tblCitas.getSelectedRow();if(fila<0)throw new IllegalArgumentException("Selecciona una cita para modificar.");
            id=(int)tblCitas.getValueAt(fila,0);
            for(Cita anterior:cache)if(anterior.getId()==id)empleado=anterior.getIdEmpleado();
        }
        Cita cita=new Cita(id,p.getId(),m.getId(),empleado,Validaciones.fecha(txtFecha.getText()),
            Validaciones.hora(txtHora.getText()),"Programada");
        datos.guardarCita(cita);cargar();VistaUtil.mensaje(this,editar?"Cita modificada.":"Cita registrada.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private int idSeleccionado(){int f=tblCitas.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona una cita de la tabla.");return (int)tblCitas.getValueAt(f,0);}
    private void cancelar(){try{int id=idSeleccionado();
        if(JOptionPane.showConfirmDialog(this,"¿Cancelar cita "+id+"?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.cancelarCita(id);cargar();VistaUtil.mensaje(this,"La cita permanece en el historial con estado Cancelada.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int id=idSeleccionado();
        if(JOptionPane.showConfirmDialog(this,"¿Retirar la cita "+id+" de la agenda? Se conservará con estado Eliminada.","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarCita(id);cargar();VistaUtil.mensaje(this,"Cita retirada de la agenda. Se conserva para auditoría.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cache=datos.citas();ArrayList<Object[]> filas=new ArrayList<>();
        ArrayList<Paciente> pacientes=datos.pacientes();ArrayList<Medico> medicos=datos.medicos();
        for(Cita c:cache){if(c.getEstado().equals("Eliminada"))continue;
            String p="",dp="",m="",dm="";
            for(Paciente x:pacientes)if(x.getId()==c.getIdPaciente()){p=x.getNombreCompleto();dp=x.getDni();}
            for(Medico x:medicos)if(x.getId()==c.getIdMedico()){m=x.getNombreCompleto();dm=x.getDni();}
            filas.add(new Object[]{c.getId(),dp,p,dm,m,c.getFecha(),c.getHora(),c.getEstado(),c.getIdEmpleado()});
        }
        VistaUtil.filas(tblCitas,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarDni;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReprogramar;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollCitas;
    private javax.swing.JTable tblCitas;
    private javax.swing.JTextField txtDniMedico;
    private javax.swing.JTextField txtDniPaciente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    // End of variables declaration//GEN-END:variables
}

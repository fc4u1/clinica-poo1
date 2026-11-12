package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmPacientes extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;


    public FrmPacientes(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);setTitle("Pacientes");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtDni = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollPacientes = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("GESTIÓN DE PACIENTES");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF01, RF18, RF19 | Registrar, modificar y eliminar pacientes");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDni.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI (8 dígitos)"));
        panelDatos.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtNombres.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombres"));
        panelDatos.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtApellidos.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellidos"));
        panelDatos.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        txtNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder("Nacimiento (AAAA-MM-DD)"));
        panelDatos.add(txtNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 268, 54));

        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono"));
        panelDatos.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 91, 268, 54));

        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar DNI / nombre"));
        panelDatos.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 91, 268, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setText("Registrar paciente");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 168, 42));

        btnActualizar.setText("Modificar paciente");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 23, 168, 42));

        btnEliminar.setText("Eliminar paciente");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 23, 168, 42));

        btnBuscar.setText("Buscar");
        panelAcciones.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 23, 168, 42));

        btnMostrar.setText("Mostrar todos");
        panelAcciones.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 23, 168, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "Nombres", "Apellidos", "Nacimiento", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPacientes.setViewportView(tblPacientes);

        panelTabla.add(scrollPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Paciente> cache=new ArrayList<>();
    private void construir(){
        btnRegistrar.addActionListener(e->guardar(false));btnActualizar.addActionListener(e->guardar(true));
        btnEliminar.addActionListener(e->eliminar());btnBuscar.addActionListener(e->filtrar());
        btnMostrar.addActionListener(e->{txtBuscar.setText("");cargar();});
        tblPacientes.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblPacientes.getSelectedRow()>=0){
                int id=(int)tblPacientes.getValueAt(tblPacientes.getSelectedRow(),0);
                for(Paciente p:cache)if(p.getId()==id){
                    txtDni.setText(p.getDni());txtNombres.setText(p.getNombres());txtApellidos.setText(p.getApellidos());
                    txtNacimiento.setText(p.getFechaNacimiento().toString());txtTelefono.setText(p.getTelefono());break;
                }
            }
        });
    }
    private void guardar(boolean editar){try{
        int id=0;
        if(editar){int f=tblPacientes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un paciente.");id=(int)tblPacientes.getValueAt(f,0);}
        Paciente p=new Paciente(id,Validaciones.dni(txtDni.getText()),
            Validaciones.obligatorio(txtNombres.getText(),"Nombres"),
            Validaciones.obligatorio(txtApellidos.getText(),"Apellidos"),
            Validaciones.fecha(txtNacimiento.getText()),txtTelefono.getText().trim());
        datos.guardarPaciente(p);cargar();VistaUtil.mensaje(this,"Paciente registrado o actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int f=tblPacientes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un paciente.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar paciente? Solo es posible si no tiene citas.","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarPaciente((int)tblPacientes.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Paciente eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{cache=datos.pacientes();mostrar(cache);}catch(Exception ex){VistaUtil.error(this,ex);}}
    private void filtrar(){String q=txtBuscar.getText().trim().toLowerCase();ArrayList<Paciente> filtrados=new ArrayList<>();
        for(Paciente p:cache)if(p.getDni().contains(q)||p.getNombreCompleto().toLowerCase().contains(q))filtrados.add(p);
        mostrar(filtrados);
    }
    private void mostrar(ArrayList<Paciente> lista){ArrayList<Object[]> filas=new ArrayList<>();
        for(Paciente p:lista)filas.add(new Object[]{p.getId(),p.getDni(),p.getNombres(),p.getApellidos(),p.getFechaNacimiento(),p.getTelefono()});
        VistaUtil.filas(tblPacientes,filas);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollPacientes;
    private javax.swing.JTable tblPacientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

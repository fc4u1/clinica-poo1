package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmMedicos extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmMedicos(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);setTitle("Médicos");setSize(1000,710);
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
        txtEspecialidad = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollMedicos = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("DIRECTORIO DE MÉDICOS");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF25, RF26 | Registro, búsqueda y actualización de médicos");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDni.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI (8 dígitos)"));
        panelDatos.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtNombres.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombres"));
        panelDatos.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtApellidos.setBorder(javax.swing.BorderFactory.createTitledBorder("Apellidos"));
        panelDatos.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        txtEspecialidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Especialidad"));
        panelDatos.add(txtEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por DNI / nombre"));
        panelDatos.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setText("Registrar médico");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 168, 42));

        btnEditar.setText("Modificar médico");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 23, 168, 42));

        btnEliminar.setText("Eliminar médico");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 23, 168, 42));

        btnBuscar.setText("Buscar");
        panelAcciones.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 23, 168, 42));

        btnActualizar.setText("Mostrar todos");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 23, 168, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "DNI", "Nombres", "Apellidos", "Especialidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollMedicos.setViewportView(tblMedicos);

        panelTabla.add(scrollMedicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Medico> cache=new ArrayList<>();
    private void construir(){
        btnRegistrar.addActionListener(e->guardar(false));
        btnEditar.addActionListener(e->guardar(true));
        btnEliminar.addActionListener(e->eliminar());
        btnBuscar.addActionListener(e->buscar());
        btnActualizar.addActionListener(e->{txtBuscar.setText("");cargar();});
        tblMedicos.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting() && tblMedicos.getSelectedRow()>=0){
                int id=(int)tblMedicos.getValueAt(tblMedicos.getSelectedRow(),0);
                for(Medico m:cache)if(m.getId()==id){
                    txtDni.setText(m.getDni());txtNombres.setText(m.getNombres());
                    txtApellidos.setText(m.getApellidos());txtEspecialidad.setText(m.getEspecialidad());break;
                }
            }
        });
    }
    private void guardar(boolean editar){try{
        int id=0;
        if(editar){int f=tblMedicos.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un médico de la tabla.");id=(int)tblMedicos.getValueAt(f,0);}
        Medico medico=new Medico(id,Validaciones.dni(txtDni.getText()),
            Validaciones.obligatorio(txtNombres.getText(),"Nombres"),
            Validaciones.obligatorio(txtApellidos.getText(),"Apellidos"),
            Validaciones.obligatorio(txtEspecialidad.getText(),"Especialidad"));
        datos.guardarMedico(medico);cargar();VistaUtil.mensaje(this,"Médico registrado o actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{
        int f=tblMedicos.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un médico.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar médico seleccionado?","Confirmación",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarMedico((int)tblMedicos.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Médico eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{cache=datos.medicos();mostrar(cache);}catch(Exception ex){VistaUtil.error(this,ex);}}
    private void buscar(){String s=txtBuscar.getText().trim().toLowerCase();ArrayList<Medico> fil=new ArrayList<>();
        for(Medico m:cache)if(m.getDni().contains(s)||m.getNombreCompleto().toLowerCase().contains(s)||m.getEspecialidad().toLowerCase().contains(s))fil.add(m);
        mostrar(fil);
    }
    private void mostrar(ArrayList<Medico> lista){ArrayList<Object[]> filas=new ArrayList<>();
        for(Medico m:lista)filas.add(new Object[]{m.getId(),m.getDni(),m.getNombres(),m.getApellidos(),m.getEspecialidad()});
        VistaUtil.filas(tblMedicos,filas);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollMedicos;
    private javax.swing.JTable tblMedicos;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEspecialidad;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}

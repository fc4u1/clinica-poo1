package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmUsuarios extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmUsuarios(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ADMIN, lblTitulo, lblSubtitulo);setTitle("Usuarios");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();
        cbxRol = new javax.swing.JComboBox();
        cbxEmpleado = new javax.swing.JComboBox();
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("CUENTAS DE USUARIO");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF14, RF16, RF17 | Crear y administrar usuarios y cuentas");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre de usuario"));
        panelDatos.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtClave.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña (vacío = conservar al editar)"));
        panelDatos.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        cbxRol.setBorder(javax.swing.BorderFactory.createTitledBorder("Rol"));
        panelDatos.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        cbxEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado"));
        panelDatos.add(cbxEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setText("Crear usuario");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 168, 42));

        btnEditar.setText("Editar usuario");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 23, 168, 42));

        btnActivar.setText("Activar usuario");
        panelAcciones.add(btnActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 23, 168, 42));

        btnDesactivar.setText("Desactivar usuario");
        panelAcciones.add(btnDesactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 23, 168, 42));

        btnActualizar.setText("Actualizar lista");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 23, 168, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Rol", "Empleado", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollUsuarios.setViewportView(tblUsuarios);

        panelTabla.add(scrollUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        btnRegistrar.addActionListener(e->guardar(false));btnEditar.addActionListener(e->guardar(true));
        btnActivar.addActionListener(e->activar(true));btnDesactivar.addActionListener(e->activar(false));
        btnActualizar.addActionListener(e->cargar());
        tblUsuarios.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblUsuarios.getSelectedRow()>=0){
                int id=(int)tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(),0);
                try{for(Usuario u:datos.usuarios())if(u.getId()==id){
                    txtUsuario.setText(u.getUsuario());txtClave.setText("");
                    for(int i=0;i<cbxRol.getItemCount();i++)if(((Rol)cbxRol.getItemAt(i)).getId()==u.getIdRol())cbxRol.setSelectedIndex(i);
                    for(int i=0;i<cbxEmpleado.getItemCount();i++)if(((Empleado)cbxEmpleado.getItemAt(i)).getId()==u.getIdEmpleado())cbxEmpleado.setSelectedIndex(i);
                    break;
                }}catch(Exception ex){VistaUtil.error(this,ex);}
            }
        });
    }
    private void guardar(boolean editar){try{
        String nombre=Validaciones.obligatorio(txtUsuario.getText(),"Usuario");
        String clave=new String(txtClave.getPassword());
        if(editar){int f=tblUsuarios.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona usuario.");
            datos.editarUsuario((int)tblUsuarios.getValueAt(f,0),nombre,clave);
        }else{
            Rol r=(Rol)cbxRol.getSelectedItem();Empleado e=(Empleado)cbxEmpleado.getSelectedItem();
            if(r==null||e==null)throw new IllegalArgumentException("Selecciona rol y empleado.");
            datos.crearUsuario(nombre,clave,r.getId(),e.getId());
        }
        txtClave.setText("");cargar();VistaUtil.mensaje(this,"Usuario registrado o actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void activar(boolean estado){try{int f=tblUsuarios.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona usuario.");
        int id=(int)tblUsuarios.getValueAt(f,0);if(id==sesion.getId()&&!estado)throw new IllegalArgumentException("No puedes desactivar tu propia sesión.");
        datos.activarUsuario(id,estado);cargar();VistaUtil.mensaje(this,estado?"Usuario activado.":"Usuario desactivado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxRol.removeAllItems();for(Rol r:datos.roles())cbxRol.addItem(r);
        cbxEmpleado.removeAllItems();for(Empleado e:datos.empleados())cbxEmpleado.addItem(e);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Usuario u:datos.usuarios())filas.add(new Object[]{u.getId(),u.getUsuario(),u.getRol(),u.getIdEmpleado(),u.isActivo()?"Sí":"No"});
        VistaUtil.filas(tblUsuarios,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cbxEmpleado;
    private javax.swing.JComboBox cbxRol;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

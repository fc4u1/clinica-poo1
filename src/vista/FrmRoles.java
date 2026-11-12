package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmRoles extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmRoles(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ADMIN, lblTitulo, lblSubtitulo);setTitle("Roles y permisos");
        setSize(1000,710);setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        cbxUsuario = new javax.swing.JComboBox();
        cbxRol = new javax.swing.JComboBox();
        panelAcciones = new javax.swing.JPanel();
        btnAsignar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollRoles = new javax.swing.JScrollPane();
        tblRoles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("ROLES Y PERMISOS");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF17 | Asignación de roles a usuarios");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));
        panelDatos.add(cbxUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 420, 54));

        cbxRol.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo rol"));
        panelDatos.add(cbxRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 24, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAsignar.setText("Asignar rol");
        panelAcciones.add(btnAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 434, 42));

        btnActualizar.setText("Actualizar roles");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 434, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRoles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Rol", "Usuarios asociados", "Acceso principal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollRoles.setViewportView(tblRoles);

        panelTabla.add(scrollRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        btnAsignar.addActionListener(e->asignar());btnActualizar.addActionListener(e->cargar());
    }
    private void asignar(){try{Usuario u=(Usuario)cbxUsuario.getSelectedItem();Rol r=(Rol)cbxRol.getSelectedItem();
        if(u==null||r==null)throw new IllegalArgumentException("Selecciona usuario y rol.");
        datos.cambiarRolUsuario(u.getId(),r.getId());cargar();VistaUtil.mensaje(this,"Rol actualizado. El usuario debe volver a ingresar para aplicar permisos.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxUsuario.removeAllItems();for(Usuario u:datos.usuarios())cbxUsuario.addItem(u);
        cbxRol.removeAllItems();for(Rol r:datos.roles())cbxRol.addItem(r);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Rol r:datos.roles()){
            int cantidad=0;for(Usuario u:datos.usuarios())if(u.getIdRol()==r.getId())cantidad++;
            String permiso=r.getId()==1?"Todos":r.getId()==2?"Pacientes, citas, médicos, agenda":r.getId()==3?"Agenda e historial":"Inventario, farmacia";
            filas.add(new Object[]{r.getId(),r.getNombre(),cantidad,permiso});
        }VistaUtil.filas(tblRoles,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JComboBox cbxRol;
    private javax.swing.JComboBox cbxUsuario;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollRoles;
    private javax.swing.JTable tblRoles;
    // End of variables declaration//GEN-END:variables
}

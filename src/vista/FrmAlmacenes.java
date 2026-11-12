package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmAlmacenes extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmAlmacenes(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Almacenes");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        cbxEmpleado = new javax.swing.JComboBox();
        panelAcciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAsignar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollAlmacenes = new javax.swing.JScrollPane();
        tblAlmacenes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("ALMACENES DE FARMACIA");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF12, RF37 | Registrar almacenes y asignar empleados");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre del almacén"));
        panelDatos.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 420, 54));

        txtCapacidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Capacidad (unidades)"));
        panelDatos.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 24, 420, 54));

        cbxEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado a asignar"));
        panelDatos.add(cbxEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Registrar almacén");
        panelAcciones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 139, 42));

        btnEditar.setText("Modificar almacén");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 23, 139, 42));

        btnEliminar.setText("Eliminar almacén");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 23, 139, 42));

        btnAsignar.setText("Asignar empleado");
        panelAcciones.add(btnAsignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 23, 139, 42));

        btnQuitar.setText("Quitar asignación");
        panelAcciones.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(608, 23, 139, 42));

        btnActualizar.setText("Actualizar");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(755, 23, 139, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAlmacenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Almacén", "Capacidad", "Empleados asignados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollAlmacenes.setViewportView(tblAlmacenes);

        panelTabla.add(scrollAlmacenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Almacen> cache=new ArrayList<>();
    private void construir(){
        btnGuardar.addActionListener(e->guardar(false));btnEditar.addActionListener(e->guardar(true));
        btnEliminar.addActionListener(e->eliminar());btnActualizar.addActionListener(e->cargar());
        btnAsignar.addActionListener(e->asignar());btnQuitar.addActionListener(e->quitarAsignacion());
        tblAlmacenes.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblAlmacenes.getSelectedRow()>=0){
                int id=(int)tblAlmacenes.getValueAt(tblAlmacenes.getSelectedRow(),0);
                for(Almacen a:cache)if(a.getId()==id){txtNombre.setText(a.getNombre());txtCapacidad.setText(""+a.getCapacidad());break;}
            }
        });
    }
    private void guardar(boolean editar){try{
        int id=0;if(editar){int f=tblAlmacenes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona almacén.");id=(int)tblAlmacenes.getValueAt(f,0);}
        datos.guardarAlmacen(new Almacen(id,txtNombre.getText().trim(),Validaciones.entero(txtCapacidad.getText(),"Capacidad")));
        cargar();VistaUtil.mensaje(this,"Almacén registrado o actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int f=tblAlmacenes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona almacén.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar almacén?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarAlmacen((int)tblAlmacenes.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Almacén eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void asignar(){try{
        int f=tblAlmacenes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un almacén.");
        Empleado e=(Empleado)cbxEmpleado.getSelectedItem();if(e==null)throw new IllegalArgumentException("Selecciona un empleado.");
        datos.asignarEmpleado(e.getId(),(int)tblAlmacenes.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Empleado asignado al almacén.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void quitarAsignacion(){try{
        int f=tblAlmacenes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un almacén.");
        Empleado e=(Empleado)cbxEmpleado.getSelectedItem();if(e==null)throw new IllegalArgumentException("Selecciona empleado.");
        datos.quitarAsignacion(e.getId(),(int)tblAlmacenes.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Asignación retirada.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxEmpleado.removeAllItems();for(Empleado e:datos.empleados())cbxEmpleado.addItem(e);
        cache=datos.almacenes();ArrayList<Object[]> filas=new ArrayList<>();
        for(Almacen a:cache){
            String nombres="";for(Empleado e:datos.empleadosAsignados(a.getId()))nombres+= (nombres.isEmpty()?"":", ")+e.getNombreCompleto();
            filas.add(new Object[]{a.getId(),a.getNombre(),a.getCapacidad(),nombres});
        }VistaUtil.filas(tblAlmacenes,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JComboBox cbxEmpleado;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollAlmacenes;
    private javax.swing.JTable tblAlmacenes;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

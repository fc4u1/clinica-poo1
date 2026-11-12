package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmProveedores extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    public FrmProveedores(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Proveedores");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtRuc = new javax.swing.JTextField();
        txtEmpresa = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollProveedores = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("GESTIÓN DE PROVEEDORES");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF08, RF38 | Registrar y administrar proveedores");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtRuc.setBorder(javax.swing.BorderFactory.createTitledBorder("RUC (11 dígitos)"));
        panelDatos.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtEmpresa.setBorder(javax.swing.BorderFactory.createTitledBorder("Razón social"));
        panelDatos.add(txtEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono"));
        panelDatos.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Registrar proveedor");
        panelAcciones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 213, 42));

        btnEditar.setText("Modificar proveedor");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 23, 213, 42));

        btnEliminar.setText("Eliminar proveedor");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 213, 42));

        btnActualizar.setText("Mostrar todos");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 23, 213, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUC", "Razón social", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollProveedores.setViewportView(tblProveedores);

        panelTabla.add(scrollProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Proveedor> cache=new ArrayList<>();
    private void construir(){
        btnGuardar.addActionListener(e->guardar(false));btnEditar.addActionListener(e->guardar(true));
        btnEliminar.addActionListener(e->eliminar());btnActualizar.addActionListener(e->cargar());
        tblProveedores.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblProveedores.getSelectedRow()>=0){
                int id=(int)tblProveedores.getValueAt(tblProveedores.getSelectedRow(),0);
                for(Proveedor p:cache)if(p.getId()==id){txtRuc.setText(p.getRuc());txtEmpresa.setText(p.getEmpresa());txtTelefono.setText(p.getTelefono());break;}
            }
        });
    }
    private void guardar(boolean editar){try{
        int id=0;if(editar){int f=tblProveedores.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona proveedor.");id=(int)tblProveedores.getValueAt(f,0);}
        datos.guardarProveedor(new Proveedor(id,txtRuc.getText().trim(),txtEmpresa.getText().trim(),txtTelefono.getText().trim()));
        cargar();VistaUtil.mensaje(this,"Proveedor guardado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int f=tblProveedores.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona proveedor.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar proveedor?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarProveedor((int)tblProveedores.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Proveedor eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{cache=datos.proveedores();ArrayList<Object[]> filas=new ArrayList<>();
        for(Proveedor p:cache)filas.add(new Object[]{p.getId(),p.getRuc(),p.getEmpresa(),p.getTelefono()});
        VistaUtil.filas(tblProveedores,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollProveedores;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

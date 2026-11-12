package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmLotes extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    
    
    public FrmLotes(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Lotes");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        cbxMedicamento = new javax.swing.JComboBox();
        cbxAlmacen = new javax.swing.JComboBox();
        cbxProveedor = new javax.swing.JComboBox();
        txtVencimiento = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRecargar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollLotes = new javax.swing.JScrollPane();
        tblLotes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("LOTES DE FARMACIA");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF06, RF08, RF36 | Lotes vinculados con proveedores");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxMedicamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Medicamento"));
        panelDatos.add(cbxMedicamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        cbxAlmacen.setBorder(javax.swing.BorderFactory.createTitledBorder("Almacén"));
        panelDatos.add(cbxAlmacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        cbxProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor del lote"));
        panelDatos.add(cbxProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        txtVencimiento.setBorder(javax.swing.BorderFactory.createTitledBorder("Vencimiento (AAAA-MM-DD)"));
        panelDatos.add(txtVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        txtCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad"));
        panelDatos.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Registrar lote");
        panelAcciones.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 213, 42));

        btnEditar.setText("Modificar lote");
        panelAcciones.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 23, 213, 42));

        btnEliminar.setText("Eliminar lote");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 213, 42));

        btnRecargar.setText("Actualizar lista");
        panelAcciones.add(btnRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 23, 213, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Medicamento", "Almacén", "Proveedor", "Fecha ingreso", "Vencimiento", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollLotes.setViewportView(tblLotes);

        panelTabla.add(scrollLotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        btnGuardar.addActionListener(e->guardar());btnEditar.addActionListener(e->editar());
        btnEliminar.addActionListener(e->eliminar());btnRecargar.addActionListener(e->cargar());
        tblLotes.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblLotes.getSelectedRow()>=0){
                int id=(int)tblLotes.getValueAt(tblLotes.getSelectedRow(),0);
                try{for(Lote l:datos.lotes())if(l.getId()==id){
                    txtCantidad.setText(""+l.getCantidad());txtVencimiento.setText(l.getFechaVencimiento().toString());
                    for(int i=0;i<cbxMedicamento.getItemCount();i++)if(((Medicamento)cbxMedicamento.getItemAt(i)).getId()==l.getIdMedicamento())cbxMedicamento.setSelectedIndex(i);
                    for(int i=0;i<cbxAlmacen.getItemCount();i++)if(((Almacen)cbxAlmacen.getItemAt(i)).getId()==l.getIdAlmacen())cbxAlmacen.setSelectedIndex(i);
                    for(int i=0;i<cbxProveedor.getItemCount();i++)if(((Proveedor)cbxProveedor.getItemAt(i)).getId()==l.getIdProveedor())cbxProveedor.setSelectedIndex(i);
                    break;
                }}catch(Exception ex){VistaUtil.error(this,ex);}
            }
        });
    }
    private void guardar(){try{
        Medicamento m=(Medicamento)cbxMedicamento.getSelectedItem();Almacen a=(Almacen)cbxAlmacen.getSelectedItem();
        Proveedor p=(Proveedor)cbxProveedor.getSelectedItem();
        if(m==null||a==null||p==null)throw new IllegalArgumentException("Selecciona medicamento, almacén y proveedor.");
        datos.agregarLote(m.getId(),a.getId(),p.getId(),Validaciones.fecha(txtVencimiento.getText()),Validaciones.entero(txtCantidad.getText(),"Cantidad"));
        cargar();VistaUtil.mensaje(this,"Lote registrado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void editar(){try{int f=tblLotes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un lote.");
        datos.actualizarLote((int)tblLotes.getValueAt(f,0),Validaciones.entero(txtCantidad.getText(),"Cantidad"),Validaciones.fecha(txtVencimiento.getText()));
        cargar();VistaUtil.mensaje(this,"Lote actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int f=tblLotes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona lote.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar este lote?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarLote((int)tblLotes.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Lote eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxMedicamento.removeAllItems();for(Medicamento m:datos.medicamentos())cbxMedicamento.addItem(m);
        cbxAlmacen.removeAllItems();for(Almacen a:datos.almacenes())cbxAlmacen.addItem(a);
        cbxProveedor.removeAllItems();for(Proveedor p:datos.proveedores())cbxProveedor.addItem(p);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Lote l:datos.lotes()){
            String nombre="",almacen="";
            for(Medicamento m:datos.medicamentos())if(m.getId()==l.getIdMedicamento())nombre=m.getNombreComercial();
            for(Almacen a:datos.almacenes())if(a.getId()==l.getIdAlmacen())almacen=a.getNombre();
            String proveedor="";for(Proveedor p:datos.proveedores())if(p.getId()==l.getIdProveedor())proveedor=p.getEmpresa();
            filas.add(new Object[]{l.getId(),nombre,almacen,proveedor,l.getFechaIngreso(),l.getFechaVencimiento(),l.getCantidad()});
        }VistaUtil.filas(tblLotes,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JComboBox cbxAlmacen;
    private javax.swing.JComboBox cbxMedicamento;
    private javax.swing.JComboBox cbxProveedor;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollLotes;
    private javax.swing.JTable tblLotes;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtVencimiento;
    // End of variables declaration//GEN-END:variables
}

package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmMovimientos extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    
    
    public FrmMovimientos(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Movimientos de inventario");
        setSize(1000,710);setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        cbxLote = new javax.swing.JComboBox();
        cbxTipo = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollPendientes = new javax.swing.JScrollPane();
        tblPendientes = new javax.swing.JTable();
        scrollMovimientos = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("MOVIMIENTOS DE INVENTARIO");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF07, RF13 | Entradas y salidas de varios lotes");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxLote.setBorder(javax.swing.BorderFactory.createTitledBorder("Lote"));
        panelDatos.add(cbxLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        cbxTipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de movimiento"));
        panelDatos.add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad"));
        panelDatos.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregar.setText("Añadir lote");
        panelAcciones.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 213, 42));

        btnQuitar.setText("Quitar detalle");
        panelAcciones.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 23, 213, 42));

        btnRegistrar.setText("Registrar movimiento");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 213, 42));

        btnActualizar.setText("Actualizar historial");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(683, 23, 213, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lote", "Medicamento", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPendientes.setViewportView(tblPendientes);

        panelTabla.add(scrollPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 102));

        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Grupo", "Lote", "Tipo", "Cantidad", "Empleado", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollMovimientos.setViewportView(tblMovimientos);

        panelTabla.add(scrollMovimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 137, 885, 120));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private final ArrayList<int[]> pendientes=new ArrayList<>();
    private void construir(){
        cbxTipo.addItem("ENTRADA");cbxTipo.addItem("SALIDA");
        btnAgregar.addActionListener(e->agregar());btnQuitar.addActionListener(e->quitar());
        btnRegistrar.addActionListener(e->guardar());btnActualizar.addActionListener(e->cargar());
    }
    private void agregar(){try{
        Lote l=(Lote)cbxLote.getSelectedItem();if(l==null)throw new IllegalArgumentException("Seleccione un lote.");
        int cantidad=Validaciones.entero(txtCantidad.getText(),"Cantidad");if(cantidad<=0)throw new IllegalArgumentException("Cantidad debe ser positiva.");
        for(int[] p:pendientes)if(p[0]==l.getId())throw new IllegalArgumentException("El lote ya está agregado, retíralo si deseas cambiar la cantidad.");
        pendientes.add(new int[]{l.getId(),cantidad});mostrarPendientes();
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void quitar(){try{int f=tblPendientes.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona un detalle pendiente.");
        pendientes.remove(f);mostrarPendientes();
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void guardar(){try{
        if(pendientes.isEmpty())throw new IllegalArgumentException("Agrega al menos un lote.");
        datos.registrarMovimientosLotes(new ArrayList<>(pendientes),sesion.getIdEmpleado(),(String)cbxTipo.getSelectedItem());
        pendientes.clear();mostrarPendientes();cargar();VistaUtil.mensaje(this,"Movimiento registrado correctamente. Se actualizó el stock de todos los lotes.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void mostrarPendientes(){ArrayList<Object[]> filas=new ArrayList<>();
        for(int[] p:pendientes){String med="";
            try{for(Lote l:datos.lotes())if(l.getId()==p[0])for(Medicamento m:datos.medicamentos())if(m.getId()==l.getIdMedicamento())med=m.getNombreComercial();}
            catch(Exception ex){VistaUtil.error(this,ex);}
            filas.add(new Object[]{p[0],med,p[1]});
        }VistaUtil.filas(tblPendientes,filas);
    }
    private void cargar(){try{
        cbxLote.removeAllItems();for(Lote l:datos.lotes())cbxLote.addItem(l);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Movimiento m:datos.movimientos())filas.add(new Object[]{m.getId(),datos.grupoMovimiento(m.getId()),m.getIdLote(),m.getTipo(),m.getCantidad(),m.getIdEmpleado(),m.getFecha()});
        VistaUtil.filas(tblMovimientos,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox cbxLote;
    private javax.swing.JComboBox cbxTipo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollMovimientos;
    private javax.swing.JScrollPane scrollPendientes;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTable tblPendientes;
    private javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables
}

package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmStock extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    
    public FrmStock(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Stock de farmacia");
        setSize(1000,710);setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        cbxFiltro = new javax.swing.JComboBox();
        panelAcciones = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollStock = new javax.swing.JScrollPane();
        tblStock = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("EXISTENCIAS DE FARMACIA");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF32, RF33, RF34 | Consultar stock y vencimientos");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar medicamento"));
        panelDatos.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 420, 54));

        cbxFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Mostrar existencias"));
        panelDatos.add(cbxFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 24, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setText("Actualizar stock");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 434, 42));

        btnFiltrar.setText("Aplicar filtro");
        panelAcciones.add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 434, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicamento", "Disponible", "Mínimo", "Estado stock", "Próximo vencimiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollStock.setViewportView(tblStock);

        panelTabla.add(scrollStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        cbxFiltro.addItem("Todos");cbxFiltro.addItem("Stock bajo");cbxFiltro.addItem("Próximos a vencer (30 días)");
        btnActualizar.addActionListener(e->cargar());btnFiltrar.addActionListener(e->cargar());
    }
    private void cargar(){try{
        String busca=txtBuscar.getText().trim().toLowerCase();String filtro=(String)cbxFiltro.getSelectedItem();
        ArrayList<Object[]> filas=new ArrayList<>();int disponibles=0;
        for(Medicamento m:datos.medicamentos()){
            if(!m.getNombreComercial().toLowerCase().contains(busca)&&!m.getNombreGenerico().toLowerCase().contains(busca))continue;
            int cantidad=0;LocalDate proximo=null;
            for(Lote l:datos.lotes())if(l.getIdMedicamento()==m.getId()){
                if(!l.getFechaVencimiento().isBefore(LocalDate.now()))cantidad+=l.getCantidad();
                if(l.getCantidad()>0&&!l.getFechaVencimiento().isBefore(LocalDate.now())&&(proximo==null||l.getFechaVencimiento().isBefore(proximo)))proximo=l.getFechaVencimiento();
            }
            boolean bajo=cantidad<m.getStockMinimo();
            boolean vencer=proximo!=null&&!proximo.isAfter(LocalDate.now().plusDays(30));
            if("Stock bajo".equals(filtro)&&!bajo)continue;
            if("Próximos a vencer (30 días)".equals(filtro)&&!vencer)continue;
            filas.add(new Object[]{m.getNombreComercial(),cantidad,m.getStockMinimo(),bajo?"BAJO":"OK",proximo==null?"Sin lotes vigentes":proximo});
            disponibles+=cantidad;
        }
        VistaUtil.filas(tblStock,filas);lblSubtitulo.setText("RF32 · RF33 · RF34 | Stock visible disponible: "+disponibles+" unidades");
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JComboBox cbxFiltro;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollStock;
    private javax.swing.JTable tblStock;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}

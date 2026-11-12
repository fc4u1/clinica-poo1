package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmMedicamentos extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;


    public FrmMedicamentos(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);setTitle("Medicamentos");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtComercial = new javax.swing.JTextField();
        txtGenerico = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtMinimo = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        panelAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnTodo = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollMedicamentos = new javax.swing.JScrollPane();
        tblMedicamentos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("MEDICAMENTOS DE FARMACIA");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF30, RF31, RF32 | Registro, búsqueda y control de medicamentos");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtComercial.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre comercial"));
        panelDatos.add(txtComercial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtGenerico.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre genérico"));
        panelDatos.add(txtGenerico, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        txtTipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo"));
        panelDatos.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        txtMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder("Stock mínimo"));
        panelDatos.add(txtMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 91, 420, 54));

        txtBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar medicamento"));
        panelDatos.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 91, 420, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegistrar.setText("Registrar medicamento");
        panelAcciones.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 168, 42));

        btnActualizar.setText("Modificar medicamento");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 23, 168, 42));

        btnEliminar.setText("Eliminar medicamento");
        panelAcciones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 23, 168, 42));

        btnBuscar.setText("Buscar");
        panelAcciones.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 23, 168, 42));

        btnTodo.setText("Mostrar todos");
        panelAcciones.add(btnTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 23, 168, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Comercial", "Genérico", "Tipo", "Mínimo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollMedicamentos.setViewportView(tblMedicamentos);

        panelTabla.add(scrollMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<Medicamento> cache=new ArrayList<>();
    private void construir(){
        btnRegistrar.addActionListener(e->guardar(false));btnActualizar.addActionListener(e->guardar(true));
        btnEliminar.addActionListener(e->eliminar());btnBuscar.addActionListener(e->filtrar());btnTodo.addActionListener(e->{txtBuscar.setText("");cargar();});
        tblMedicamentos.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()&&tblMedicamentos.getSelectedRow()>=0){
                int id=(int)tblMedicamentos.getValueAt(tblMedicamentos.getSelectedRow(),0);
                for(Medicamento m:cache)if(m.getId()==id){
                    txtComercial.setText(m.getNombreComercial());txtGenerico.setText(m.getNombreGenerico());
                    txtTipo.setText(m.getTipo());txtMinimo.setText(""+m.getStockMinimo());break;
                }
            }
        });
    }
    private void guardar(boolean editar){try{
        int id=0;if(editar){int f=tblMedicamentos.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona medicamento.");id=(int)tblMedicamentos.getValueAt(f,0);}
        Medicamento m=new Medicamento(id,Validaciones.obligatorio(txtComercial.getText(),"Comercial"),
            Validaciones.obligatorio(txtGenerico.getText(),"Genérico"),
            Validaciones.obligatorio(txtTipo.getText(),"Tipo"),Validaciones.entero(txtMinimo.getText(),"Stock mínimo"));
        datos.guardarMedicamento(m);cargar();VistaUtil.mensaje(this,"Medicamento registrado o actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void eliminar(){try{int f=tblMedicamentos.getSelectedRow();if(f<0)throw new IllegalArgumentException("Selecciona medicamento.");
        if(JOptionPane.showConfirmDialog(this,"¿Eliminar medicamento?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.eliminarMedicamento((int)tblMedicamentos.getValueAt(f,0));cargar();VistaUtil.mensaje(this,"Medicamento eliminado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{cache=datos.medicamentos();mostrar(cache);}catch(Exception ex){VistaUtil.error(this,ex);}}
    private void filtrar(){String q=txtBuscar.getText().toLowerCase().trim();ArrayList<Medicamento> filtrados=new ArrayList<>();
        for(Medicamento m:cache)if(m.getNombreComercial().toLowerCase().contains(q)||m.getNombreGenerico().toLowerCase().contains(q))filtrados.add(m);
        mostrar(filtrados);
    }
    private void mostrar(ArrayList<Medicamento> lista){ArrayList<Object[]> filas=new ArrayList<>();
        for(Medicamento m:lista)filas.add(new Object[]{m.getId(),m.getNombreComercial(),m.getNombreGenerico(),m.getTipo(),m.getStockMinimo()});
        VistaUtil.filas(tblMedicamentos,filas);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnTodo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollMedicamentos;
    private javax.swing.JTable tblMedicamentos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtComercial;
    private javax.swing.JTextField txtGenerico;
    private javax.swing.JTextField txtMinimo;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}

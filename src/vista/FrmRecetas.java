package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
public class FrmRecetas extends javax.swing.JFrame {
    private final RepositorioClinica datos;
    private final Usuario sesion;
    public FrmRecetas(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.FARMACIA, lblTitulo, lblSubtitulo);
        setTitle("Recetas Médicas Y Despacho");setSize(1000,710);setLocationRelativeTo(null);
        construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        cbxConsulta = new javax.swing.JComboBox();
        cbxMedicamento = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnDespachar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        scrollPendientes = new javax.swing.JScrollPane();
        tblPendientes = new javax.swing.JTable();
        scrollRecetas = new javax.swing.JScrollPane();
        tblRecetas = new javax.swing.JTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lblTitulo.setText("RECETAS MÉDICAS Y DESPACHO");
        panelContenido.add(lblTitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,8,890,26));
        lblSubtitulo.setText("RF05, RF29 | Prescribir y despachar medicamentos asociados a consultas");
        panelContenido.add(lblSubtitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,34,890,23));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        cbxConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta registrada"));
        panelDatos.add(cbxConsulta,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,24,268,54));
        cbxMedicamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Medicamento a prescribir"));
        panelDatos.add(cbxMedicamento,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,24,268,54));
        txtCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder("Cantidad"));
        panelDatos.add(txtCantidad,new org.netbeans.lib.awtextra.AbsoluteConstraints(618,24,268,54));

        panelContenido.add(panelDatos,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,64,915,168));
        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnAgregar.setText("Añadir medicamento");
        panelAcciones.add(btnAgregar,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,23,167,42));
        btnQuitar.setText("Quitar de lista");
        panelAcciones.add(btnQuitar,new org.netbeans.lib.awtextra.AbsoluteConstraints(195,23,167,42));
        btnGuardar.setText("Generar receta");
        panelAcciones.add(btnGuardar,new org.netbeans.lib.awtextra.AbsoluteConstraints(371,23,167,42));
        btnDespachar.setText("Despachar");
        panelAcciones.add(btnDespachar,new org.netbeans.lib.awtextra.AbsoluteConstraints(546,23,167,42));
        btnActualizar.setText("Actualizar");
        panelAcciones.add(btnActualizar,new org.netbeans.lib.awtextra.AbsoluteConstraints(722,23,167,42));

        panelContenido.add(panelAcciones,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,242,915,78));
        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tblPendientes.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{},new String[]{"Medicamento","Cantidad"}){
            boolean[] canEdit=new boolean[]{false,false};
            public boolean isCellEditable(int rowIndex,int columnIndex){return canEdit[columnIndex];}
        });
        scrollPendientes.setViewportView(tblPendientes);
        panelTabla.add(scrollPendientes,new org.netbeans.lib.awtextra.AbsoluteConstraints(14,23,885,115));
        tblRecetas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{},new String[]{"ID","Consulta","Paciente","Detalle de prescripción","Estado","Fecha"}){
            boolean[] canEdit=new boolean[]{false,false,false,false,false,false};
            public boolean isCellEditable(int rowIndex,int columnIndex){return canEdit[columnIndex];}
        });
        scrollRecetas.setViewportView(tblRecetas);
        panelTabla.add(scrollRecetas,new org.netbeans.lib.awtextra.AbsoluteConstraints(14,146,885,115));

        panelContenido.add(panelTabla,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,328,915,284));
        getContentPane().add(panelContenido,new org.netbeans.lib.awtextra.AbsoluteConstraints(8,8,950,620));
        pack();setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private final ArrayList<DetalleReceta> pendientes=new ArrayList<>();
    private void construir(){
        btnAgregar.addActionListener(e->agregar());btnQuitar.addActionListener(e->quitar());
        btnGuardar.addActionListener(e->guardar());btnDespachar.addActionListener(e->despachar());btnActualizar.addActionListener(e->cargar());
        boolean medico=sesion.getRol().equals("Médico")||sesion.getRol().equals("Administrador");
        boolean farmacia=sesion.getRol().equals("Farmacia")||sesion.getRol().equals("Administrador");
        btnGuardar.setEnabled(medico);btnAgregar.setEnabled(medico);btnQuitar.setEnabled(medico);btnDespachar.setEnabled(farmacia);
        mostrarPendientes();
    }
    private void agregar(){try{
        Medicamento med=(Medicamento)cbxMedicamento.getSelectedItem();
        if(med==null)throw new IllegalArgumentException("Seleccione medicamento.");
        int cantidad=Integer.parseInt(txtCantidad.getText().trim());if(cantidad<=0)throw new IllegalArgumentException("Cantidad debe ser positiva.");
        for(DetalleReceta d:pendientes)if(d.getIdMedicamento()==med.getId())throw new IllegalArgumentException("El medicamento ya está agregado.");
        pendientes.add(new DetalleReceta(med.getId(),cantidad));mostrarPendientes();
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void quitar(){int i=tblPendientes.getSelectedRow();if(i<0){VistaUtil.mensaje(this,"Selecciona un medicamento de la lista temporal.");return;}pendientes.remove(i);mostrarPendientes();}
    private void mostrarPendientes(){try{ArrayList<Object[]> filas=new ArrayList<>();
        for(DetalleReceta d:pendientes)for(Medicamento m:datos.medicamentos())if(m.getId()==d.getIdMedicamento())filas.add(new Object[]{m.getNombreComercial(),d.getCantidad()});
        VistaUtil.filas(tblPendientes,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void guardar(){try{
        Consulta c=(Consulta)cbxConsulta.getSelectedItem();if(c==null)throw new IllegalArgumentException("Registra una consulta primero.");
        datos.crearReceta(c.getId(),new ArrayList<>(pendientes));pendientes.clear();mostrarPendientes();cargar();
        VistaUtil.mensaje(this,"Receta generada. Farmacia podrá dispensarla con lotes vigentes.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void despachar(){try{
        int fila=tblRecetas.getSelectedRow();if(fila<0)throw new IllegalArgumentException("Selecciona una receta de la tabla.");
        int id=(int)tblRecetas.getValueAt(fila,0);
        if(JOptionPane.showConfirmDialog(this,"¿Despachar receta #"+id+" y descontar sus medicamentos del stock?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        datos.despacharReceta(id,sesion.getIdEmpleado());cargar();VistaUtil.mensaje(this,"Receta dispensada. El inventario fue actualizado.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxConsulta.removeAllItems();for(Consulta c:datos.consultas())cbxConsulta.addItem(c);
        cbxMedicamento.removeAllItems();for(Medicamento m:datos.medicamentos())cbxMedicamento.addItem(m);
        ArrayList<Object[]> filas=new ArrayList<>();
        for(Receta r:datos.recetas()){
            String paciente="";for(Consulta c:datos.consultas())if(c.getId()==r.getIdConsulta())for(Cita ci:datos.citas())if(ci.getId()==c.getIdCita())for(Paciente p:datos.pacientes())if(p.getId()==ci.getIdPaciente())paciente=p.getNombreCompleto();
            StringBuilder detalle=new StringBuilder();for(DetalleReceta d:r.getDetalles())for(Medicamento m:datos.medicamentos())if(m.getId()==d.getIdMedicamento()){
                if(detalle.length()>0)detalle.append("; ");detalle.append(m.getNombreComercial()).append(" x").append(d.getCantidad());
            }
            filas.add(new Object[]{r.getId(),r.getIdConsulta(),paciente,detalle.toString(),r.getEstado(),r.getFecha()});
        }VistaUtil.filas(tblRecetas,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JComboBox cbxConsulta;
    private javax.swing.JComboBox cbxMedicamento;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnDespachar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JScrollPane scrollPendientes;
    private javax.swing.JTable tblPendientes;
    private javax.swing.JScrollPane scrollRecetas;
    private javax.swing.JTable tblRecetas;
    // End of variables declaration//GEN-END:variables
}

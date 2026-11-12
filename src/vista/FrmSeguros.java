package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
public class FrmSeguros extends javax.swing.JFrame {
    private final RepositorioClinica datos;
    private final Usuario sesion;
    public FrmSeguros(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ATENCION, lblTitulo, lblSubtitulo);
        setTitle("Seguros Y Pólizas De Pacientes");setSize(1000,710);setLocationRelativeTo(null);
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
        txtDni = new javax.swing.JTextField();
        cbxSeguro = new javax.swing.JComboBox();
        txtAseguradora = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        btnAseguradora = new javax.swing.JButton();
        btnPoliza = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        scrollPolizas = new javax.swing.JScrollPane();
        tblPolizas = new javax.swing.JTable();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        lblTitulo.setText("SEGUROS Y PÓLIZAS DE PACIENTES");
        panelContenido.add(lblTitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,8,890,26));
        lblSubtitulo.setText("RF09 | Registrar aseguradoras y vincular pólizas con pacientes");
        panelContenido.add(lblSubtitulo,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,34,890,23));
        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        txtDni.setBorder(javax.swing.BorderFactory.createTitledBorder("DNI del paciente"));
        panelDatos.add(txtDni,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,24,268,54));
        cbxSeguro.setBorder(javax.swing.BorderFactory.createTitledBorder("Aseguradora / tipo"));
        panelDatos.add(cbxSeguro,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,24,268,54));
        txtAseguradora.setBorder(javax.swing.BorderFactory.createTitledBorder("Nombre de aseguradora"));
        panelDatos.add(txtAseguradora,new org.netbeans.lib.awtextra.AbsoluteConstraints(618,24,268,54));
        txtTipo.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de seguro"));
        panelDatos.add(txtTipo,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,86,268,54));
        txtTelefono.setBorder(javax.swing.BorderFactory.createTitledBorder("Teléfono de aseguradora"));
        panelDatos.add(txtTelefono,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,86,268,54));
        txtNumero.setBorder(javax.swing.BorderFactory.createTitledBorder("Número de póliza"));
        panelDatos.add(txtNumero,new org.netbeans.lib.awtextra.AbsoluteConstraints(618,86,268,54));
        txtDesde.setBorder(javax.swing.BorderFactory.createTitledBorder("Vigencia desde AAAA-MM-DD"));
        panelDatos.add(txtDesde,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,148,268,54));
        txtHasta.setBorder(javax.swing.BorderFactory.createTitledBorder("Vigencia hasta AAAA-MM-DD"));
        panelDatos.add(txtHasta,new org.netbeans.lib.awtextra.AbsoluteConstraints(319,148,268,54));

        panelContenido.add(panelDatos,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,64,915,224));
        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnAseguradora.setText("Crear aseguradora");
        panelAcciones.add(btnAseguradora,new org.netbeans.lib.awtextra.AbsoluteConstraints(20,23,284,42));
        btnPoliza.setText("Asociar póliza");
        panelAcciones.add(btnPoliza,new org.netbeans.lib.awtextra.AbsoluteConstraints(312,23,284,42));
        btnActualizar.setText("Mostrar pólizas");
        panelAcciones.add(btnActualizar,new org.netbeans.lib.awtextra.AbsoluteConstraints(605,23,284,42));

        panelContenido.add(panelAcciones,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,298,915,78));
        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("REGISTROS"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tblPolizas.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{},new String[]{"ID","DNI","Paciente","Aseguradora","N.º póliza","Vigencia","Estado"}){
            boolean[] canEdit=new boolean[]{false,false,false,false,false,false,false};
            public boolean isCellEditable(int rowIndex,int columnIndex){return canEdit[columnIndex];}
        });
        scrollPolizas.setViewportView(tblPolizas);
        panelTabla.add(scrollPolizas,new org.netbeans.lib.awtextra.AbsoluteConstraints(14,25,885,186));

        panelContenido.add(panelTabla,new org.netbeans.lib.awtextra.AbsoluteConstraints(16,384,915,228));
        getContentPane().add(panelContenido,new org.netbeans.lib.awtextra.AbsoluteConstraints(8,8,950,620));
        pack();setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void construir(){
        txtDesde.setText(LocalDate.now().toString());txtHasta.setText(LocalDate.now().plusYears(1).toString());
        btnAseguradora.addActionListener(e->crearSeguro());btnPoliza.addActionListener(e->crearPoliza());btnActualizar.addActionListener(e->cargar());
    }
    private void crearSeguro(){try{
        datos.registrarSeguro(txtAseguradora.getText(),txtTipo.getText(),txtTelefono.getText());
        cargar();VistaUtil.mensaje(this,"Aseguradora registrada. Ya puede seleccionarla en el formulario de póliza.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void crearPoliza(){try{
        Paciente paciente=datos.buscarPacienteDni(Validaciones.dni(txtDni.getText()));
        if(paciente==null)throw new IllegalArgumentException("Primero registra al paciente.");
        Seguro seguro=(Seguro)cbxSeguro.getSelectedItem();if(seguro==null)throw new IllegalArgumentException("Primero registra la aseguradora.");
        datos.registrarPoliza(paciente.getId(),seguro.getId(),txtNumero.getText(),Validaciones.fecha(txtDesde.getText()),Validaciones.fecha(txtHasta.getText()));
        cargar();VistaUtil.mensaje(this,"Póliza vinculada al paciente.");
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void cargar(){try{
        cbxSeguro.removeAllItems();for(Seguro s:datos.seguros())cbxSeguro.addItem(s);
        ArrayList<Object[]> filas=new ArrayList<>();for(PolizaPaciente pol:datos.polizas()){
            String dni="",paciente="",aseguradora="";
            for(Paciente p:datos.pacientes())if(p.getId()==pol.getIdPaciente()){dni=p.getDni();paciente=p.getNombreCompleto();}
            for(Seguro s:datos.seguros())if(s.getId()==pol.getIdSeguro())aseguradora=s.toString();
            filas.add(new Object[]{pol.getId(),dni,paciente,aseguradora,pol.getNumero(),pol.getInicio()+" a "+pol.getFin(),pol.vigente(LocalDate.now())?"Vigente":"No vigente"});
        }VistaUtil.filas(tblPolizas,filas);
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelContenido;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JTextField txtDni;
    private javax.swing.JComboBox cbxSeguro;
    private javax.swing.JTextField txtAseguradora;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JButton btnAseguradora;
    private javax.swing.JButton btnPoliza;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JScrollPane scrollPolizas;
    private javax.swing.JTable tblPolizas;
    // End of variables declaration//GEN-END:variables
}

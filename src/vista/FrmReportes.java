package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmReportes extends javax.swing.JFrame {
    private final RepositorioClinica datos;private final Usuario sesion;
    
    public FrmReportes(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.ADMIN, lblTitulo, lblSubtitulo);setTitle("Resumen del sistema");setSize(1000,710);
        setLocationRelativeTo(null);construir();cargar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        cbxReporte = new javax.swing.JComboBox();
        panelAcciones = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        panelTabla = new javax.swing.JPanel();
        scrollArea = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("RESUMEN DE ACTIVIDAD");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 870, 26));

        lblSubtitulo.setText("RF39, RF40 | Reporte por fechas y exportación de texto");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 34, 890, 23));

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder("INFORMACIÓN / BÚSQUEDA"));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDesde.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha inicial (AAAA-MM-DD)"));
        panelDatos.add(txtDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 24, 268, 54));

        txtHasta.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha final (AAAA-MM-DD)"));
        panelDatos.add(txtHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 24, 268, 54));

        cbxReporte.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de reporte"));
        panelDatos.add(cbxReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 24, 268, 54));

        panelContenido.add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 64, 915, 168));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCIONES"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setText("Generar reporte");
        panelAcciones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 23, 434, 42));

        btnExportar.setText("Exportar TXT");
        panelAcciones.add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 23, 434, 42));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 242, 915, 78));

        panelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("RESUMEN DEL SISTEMA"));
        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        area.setEditable(false);
        scrollArea.setViewportView(area);

        panelTabla.add(scrollArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 25, 885, 232));

        panelContenido.add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 328, 915, 280));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void construir(){
        txtDesde.setText(LocalDate.now().minusDays(7).toString());txtHasta.setText(LocalDate.now().plusDays(30).toString());
        cbxReporte.addItem("Citas médicas");cbxReporte.addItem("Inventario");
        btnActualizar.addActionListener(e->cargar());btnExportar.addActionListener(e->exportar());
    }
    private void cargar(){try{
        LocalDate desde=Validaciones.fecha(txtDesde.getText()),hasta=Validaciones.fecha(txtHasta.getText());
        if(desde.isAfter(hasta))throw new IllegalArgumentException("La fecha inicial debe ser anterior a la final.");
        StringBuilder texto=new StringBuilder("CLÍNICA JESÚS DEL NORTE\nReporte: "+cbxReporte.getSelectedItem()+"\nRango: "+desde+" a "+hasta+"\n\n");
        if(cbxReporte.getSelectedIndex()==0){
            texto.append("ID | PACIENTE | MÉDICO | FECHA | HORA | ESTADO\n");
            for(Cita c:datos.citas())if(!c.getFecha().isBefore(desde)&&!c.getFecha().isAfter(hasta)){
                String p="",m="";
                for(Paciente x:datos.pacientes())if(x.getId()==c.getIdPaciente())p=x.getNombreCompleto();
                for(Medico x:datos.medicos())if(x.getId()==c.getIdMedico())m=x.getNombreCompleto();
                texto.append(c.getId()).append(" | ").append(p).append(" | ").append(m).append(" | ").append(c.getFecha()).append(" | ").append(c.getHora()).append(" | ").append(c.getEstado()).append("\n");
            }
        }else{
            texto.append("MEDICAMENTO | LOTE | STOCK | VENCIMIENTO\n");
            for(Lote l:datos.lotes()){
                String nombre="";for(Medicamento m:datos.medicamentos())if(m.getId()==l.getIdMedicamento())nombre=m.getNombreComercial();
                texto.append(nombre).append(" | ").append(l.getId()).append(" | ").append(l.getCantidad()).append(" | ").append(l.getFechaVencimiento()).append("\n");
            }
            texto.append("\nMOVIMIENTOS EN RANGO\n");
            for(Movimiento mov:datos.movimientos())if(!mov.getFecha().toLocalDate().isBefore(desde)&&!mov.getFecha().toLocalDate().isAfter(hasta)){
                texto.append(mov.getId()).append(" | Lote ").append(mov.getIdLote()).append(" | ").append(mov.getTipo()).append(" | ").append(mov.getCantidad()).append(" | ").append(mov.getFecha()).append("\n");
            }
        }
        area.setText(texto.toString());area.setCaretPosition(0);
    }catch(Exception ex){VistaUtil.error(this,ex);}}
    private void exportar(){try{
        if(area.getText().isBlank())cargar();
        JFileChooser dlg=new JFileChooser();dlg.setSelectedFile(new java.io.File("reporte_clinica.txt"));
        if(dlg.showSaveDialog(this)!=JFileChooser.APPROVE_OPTION)return;
        java.io.File archivo=dlg.getSelectedFile();
        if(archivo.exists()&&JOptionPane.showConfirmDialog(this,"¿Sobrescribir archivo?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        java.nio.file.Files.writeString(archivo.toPath(),area.getText(),java.nio.charset.StandardCharsets.UTF_8);
        VistaUtil.mensaje(this,"Reporte exportado a "+archivo.getAbsolutePath());
    }catch(Exception ex){VistaUtil.error(this,ex);}}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox cbxReporte;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollArea;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    // End of variables declaration//GEN-END:variables
}

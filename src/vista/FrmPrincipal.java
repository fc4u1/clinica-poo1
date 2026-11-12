package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmPrincipal extends javax.swing.JFrame {
    private final RepositorioClinica datos;
    private final Usuario sesion;
    public FrmPrincipal(RepositorioClinica datos,Usuario sesion){
        this.datos=datos;this.sesion=sesion;initComponents();Tema.aplicar(this, Tema.PRINCIPAL, lblTitulo, lblSubtitulo);
        setTitle("Clínica Jesús del Norte - Menú principal");setSize(1000,710);
        setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);construir();Tema.organizarMenu(panelModulos);Tema.etiquetaRol(lblRolSesion,sesion.getRol());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelModulos = new javax.swing.JPanel();
        btnUsuarios = new javax.swing.JButton();
        btnRoles = new javax.swing.JButton();
        btnPacientes = new javax.swing.JButton();
        btnMedicos = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        btnMedicamentos = new javax.swing.JButton();
        btnLotes = new javax.swing.JButton();
        btnMovimientos = new javax.swing.JButton();
        btnStock = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        btnAlmacenes = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        btnConsultas = new javax.swing.JButton();
        btnRecetas = new javax.swing.JButton();
        btnSeguros = new javax.swing.JButton();
        btnHorarios = new javax.swing.JButton();

        panelSesion = new javax.swing.JPanel();
        lblUsuarioSesion = new javax.swing.JLabel();
        lblRolSesion = new javax.swing.JLabel();
        lblModo = new javax.swing.JLabel();
        lblPersistencia = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("CLÍNICA JESÚS DEL NORTE");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 8, 900, 34));

        lblSubtitulo.setText("Panel principal · Seleccione un módulo según sus permisos");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 43, 900, 23));

        panelModulos.setBorder(javax.swing.BorderFactory.createTitledBorder("MÓDULOS DEL SISTEMA"));
        panelModulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnUsuarios.setText("Usuarios");
        panelModulos.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 195, 42));

        btnRoles.setText("Roles");
        panelModulos.add(btnRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 35, 195, 42));

        btnPacientes.setText("Pacientes");
        panelModulos.add(btnPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 35, 195, 42));

        btnMedicos.setText("Médicos");
        panelModulos.add(btnMedicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 111, 195, 42));

        btnCitas.setText("Citas");
        panelModulos.add(btnCitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 111, 195, 42));

        btnAgenda.setText("Agenda");
        panelModulos.add(btnAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 111, 195, 42));

        btnMedicamentos.setText("Medicamentos");
        panelModulos.add(btnMedicamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 187, 195, 42));

        btnLotes.setText("Lotes");
        panelModulos.add(btnLotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 187, 195, 42));

        btnMovimientos.setText("Movimientos");
        panelModulos.add(btnMovimientos, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 187, 195, 42));

        btnStock.setText("Stock");
        panelModulos.add(btnStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 263, 195, 42));

        btnProveedores.setText("Proveedores");
        panelModulos.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 263, 195, 42));

        btnAlmacenes.setText("Almacenes");
        panelModulos.add(btnAlmacenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 263, 195, 42));

        btnHistorial.setText("Historial médico");
        panelModulos.add(btnHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 339, 195, 42));

        btnReportes.setText("Reportes");
        panelModulos.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 339, 195, 42));

        btnConsultas.setText("Consultas");
        panelModulos.add(btnConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 339, 195, 42));

        btnRecetas.setText("Recetas");
        panelModulos.add(btnRecetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 415, 195, 42));

        btnSeguros.setText("Seguros");
        panelModulos.add(btnSeguros, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 415, 195, 42));

        btnHorarios.setText("Horarios");
        panelModulos.add(btnHorarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(452, 415, 195, 42));

        panelContenido.add(panelModulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 80, 686, 525));

        panelSesion.setBorder(javax.swing.BorderFactory.createTitledBorder("SESIÓN ACTUAL"));
        panelSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuarioSesion.setText("Usuario: (sesión)");
        panelSesion.add(lblUsuarioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 42, 195, 28));

        lblRolSesion.setText("Rol: (sesión)");
        panelSesion.add(lblRolSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 80, 195, 46));

        lblModo.setText("Versión de clase");
        panelSesion.add(lblModo, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 160, 194, 28));

        lblPersistencia.setText("Datos en memoria");
        panelSesion.add(lblPersistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 199, 194, 28));

        btnSalir.setText("CERRAR SESIÓN");
        panelSesion.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 438, 186, 42));

        panelContenido.add(panelSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 80, 219, 525));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 950, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void construir(){
        lblUsuarioSesion.setText("Usuario: "+sesion.getUsuario());
        lblRolSesion.setText("Rol: "+sesion.getRol());
        String[] nombres={"Usuarios","Roles","Pacientes","Médicos","Citas","Agenda",
            "Medicamentos","Lotes","Movimientos","Stock","Proveedores","Almacenes","Historial médico","Reportes","Consultas","Recetas","Seguros","Horarios"};
        javax.swing.JButton[] botones={btnUsuarios,btnRoles,btnPacientes,btnMedicos,btnCitas,btnAgenda,
            btnMedicamentos,btnLotes,btnMovimientos,btnStock,btnProveedores,btnAlmacenes,btnHistorial,btnReportes,btnConsultas,btnRecetas,btnSeguros,btnHorarios};
        for(int i=0;i<botones.length;i++){
            final String nombre=nombres[i];
            botones[i].setEnabled(puede(nombre));
            botones[i].addActionListener(e->abrir(nombre));
        }
        btnSalir.addActionListener(e->{new FrmLogin(datos).setVisible(true);dispose();});
    }
    private boolean puede(String nombre){
        String rol=sesion.getRol();if(rol.equals("Administrador"))return true;
        if(rol.equals("Recepcionista"))return nombre.equals("Pacientes")||nombre.equals("Citas")||nombre.equals("Médicos")||nombre.equals("Agenda")||nombre.equals("Seguros")||nombre.equals("Horarios");
        if(rol.equals("Farmacia"))return nombre.equals("Medicamentos")||nombre.equals("Lotes")||nombre.equals("Movimientos")||nombre.equals("Stock")||nombre.equals("Proveedores")||nombre.equals("Almacenes")||nombre.equals("Recetas");
        if(rol.equals("Médico"))return nombre.equals("Historial médico")||nombre.equals("Agenda")||nombre.equals("Consultas")||nombre.equals("Recetas")||nombre.equals("Horarios");
        return false;
    }
    private void abrir(String nombre){
        if(!puede(nombre)){VistaUtil.mensaje(this,"No tienes permisos.");return;}
        javax.swing.JFrame v=null;
        switch(nombre){
            case "Usuarios":v=new FrmUsuarios(datos,sesion);break;
            case "Roles":v=new FrmRoles(datos,sesion);break;
            case "Pacientes":v=new FrmPacientes(datos,sesion);break;
            case "Médicos":v=new FrmMedicos(datos,sesion);break;
            case "Citas":v=new FrmCitas(datos,sesion);break;
            case "Agenda":v=new FrmAgenda(datos,sesion);break;
            case "Medicamentos":v=new FrmMedicamentos(datos,sesion);break;
            case "Lotes":v=new FrmLotes(datos,sesion);break;
            case "Movimientos":v=new FrmMovimientos(datos,sesion);break;
            case "Stock":v=new FrmStock(datos,sesion);break;
            case "Proveedores":v=new FrmProveedores(datos,sesion);break;
            case "Almacenes":v=new FrmAlmacenes(datos,sesion);break;
            case "Historial médico":v=new FrmHistorialMedico(datos,sesion);break;
            case "Reportes":v=new FrmReportes(datos,sesion);break;
            case "Consultas":v=new FrmConsultas(datos,sesion);break;
            case "Recetas":v=new FrmRecetas(datos,sesion);break;
            case "Seguros":v=new FrmSeguros(datos,sesion);break;
            case "Horarios":v=new FrmHorarios(datos,sesion);break;

        }
        if(v!=null)v.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnAlmacenes;
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnLotes;
    private javax.swing.JButton btnMedicamentos;
    private javax.swing.JButton btnMedicos;
    private javax.swing.JButton btnMovimientos;
    private javax.swing.JButton btnPacientes;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnReportes;
    private javax.swing.JButton btnRoles;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnStock;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblPersistencia;
    private javax.swing.JLabel lblRolSesion;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuarioSesion;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelModulos;
    private javax.swing.JPanel panelSesion;
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnRecetas;
    private javax.swing.JButton btnSeguros;
    private javax.swing.JButton btnHorarios;
    // End of variables declaration//GEN-END:variables
}

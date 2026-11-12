package vista;
import dao.RepositorioClinica;
import modelo.*;
import util.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class FrmLogin extends javax.swing.JFrame {
    private final RepositorioClinica datos;



    public FrmLogin(RepositorioClinica datos){
        this.datos=datos;initComponents();Tema.aplicar(this, Tema.PRINCIPAL, lblTitulo, lblSubtitulo);setTitle("Clínica Jesús del Norte - Acceso");
        setSize(600,435);setLocationRelativeTo(null);construir();lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenido = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        panelAcceso = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();
        panelAcciones = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        lblAyuda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContenido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("CLÍNICA JESÚS DEL NORTE");
        panelContenido.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 480, 35));

        lblSubtitulo.setText("Sistema de gestión");
        panelContenido.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 44, 480, 24));

        panelAcceso.setBorder(javax.swing.BorderFactory.createTitledBorder("INICIAR SESIÓN"));
        panelAcceso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsuario.setText("admin01");
        txtUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuario"));
        panelAcceso.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 28, 444, 54));

        txtClave.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña"));
        txtClave.setText("demo123");
        panelAcceso.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 93, 444, 54));

        panelContenido.add(panelAcceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 84, 485, 170));

        panelAcciones.setBorder(javax.swing.BorderFactory.createTitledBorder("ACCESO"));
        panelAcciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIngresar.setText("INGRESAR");
        panelAcciones.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 17, 205, 42));

        lblAyuda.setText("Clave de prueba: demo123");
        panelAcciones.add(lblAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 23, 235, 25));

        panelContenido.add(panelAcciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 266, 485, 69));

        getContentPane().add(panelContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 8, 530, 348));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void construir(){
        // Los controles son creados en initComponents() por el diseñador NetBeans.
        btnIngresar.addActionListener(e->ingresar());
        txtClave.addActionListener(e->ingresar());
    }
    private void ingresar(){
        try{
            String clave=new String(txtClave.getPassword());
            Usuario usuario=datos.autenticar(txtUsuario.getText(),clave);
            if(usuario==null){VistaUtil.mensaje(this,"Credenciales incorrectas o usuario inactivo.");return;}
            new FrmPrincipal(datos,usuario).setVisible(true);dispose();
        }catch(Exception ex){VistaUtil.error(this,ex);}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAcceso;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

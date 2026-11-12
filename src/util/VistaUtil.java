package util;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
public final class VistaUtil {
    private VistaUtil(){}
    public static JPanel inicio(String titulo,String descripcion){
        JPanel p=new JPanel(new BorderLayout(8,8));p.setBackground(new Color(244,247,250));
        p.setBorder(BorderFactory.createEmptyBorder(15,18,15,18));
        JPanel t=new JPanel(new GridLayout(2,1));t.setOpaque(false);
        JLabel l=new JLabel(titulo);l.setFont(new Font("SansSerif",Font.BOLD,23));l.setForeground(new Color(30,55,77));
        JLabel d=new JLabel(descripcion);d.setForeground(new Color(92,109,123));t.add(l);t.add(d);
        p.add(t,BorderLayout.NORTH);return p;
    }
    public static JPanel campos(){
        JPanel p=new JPanel(new GridLayout(0,4,10,7));p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder("Datos"));return p;
    }
    public static JTextField campo(JPanel p,String label,String inicial){
        p.add(new JLabel(label));JTextField t=new JTextField(inicial);p.add(t);return t;
    }
    public static JPasswordField clave(JPanel p,String label){
        p.add(new JLabel(label));JPasswordField t=new JPasswordField();p.add(t);return t;
    }
    public static <T> JComboBox<T> combo(JPanel p,String label){
        p.add(new JLabel(label));JComboBox<T> b=new JComboBox<>();p.add(b);return b;
    }
    public static JPanel acciones(){JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT,10,6));p.setOpaque(false);return p;}
    public static JButton boton(JPanel p,String etiqueta){
        JButton b=new JButton(etiqueta);b.setFocusPainted(false);p.add(b);return b;
    }
    public static JTable tabla(String... cabeceras){
        JTable t=new JTable(new DefaultTableModel(cabeceras,0){
            @Override public boolean isCellEditable(int row,int col){return false;}
        });t.setRowHeight(25);t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.getTableHeader().setReorderingAllowed(false);return t;
    }
    public static void filas(JTable tabla,ArrayList<Object[]> filas){
        DefaultTableModel m=(DefaultTableModel)tabla.getModel();m.setRowCount(0);
        for(Object[] f:filas)m.addRow(f);
    }
    public static void mensaje(Component padre,String mensaje){JOptionPane.showMessageDialog(padre,mensaje,"Aviso",JOptionPane.INFORMATION_MESSAGE);}
    public static void error(Component padre,Exception ex){
        String m=ex.getMessage();if(m==null||m.isEmpty())m=ex.getClass().getSimpleName();
        JOptionPane.showMessageDialog(padre,m,"Revisar operación",JOptionPane.ERROR_MESSAGE);
    }
    public static void listaPanel(JPanel destino,String titulo,String descripcion,JTable t){
        JPanel p=inicio(titulo,descripcion);p.add(new JScrollPane(t),BorderLayout.CENTER);
        destino.removeAll();destino.setLayout(new BorderLayout());destino.add(p,BorderLayout.CENTER);
    }
    public static JPanel formularioConTabla(JPanel destino,String titulo,String descripcion,JPanel campos,JPanel botones,JTable tabla){
        JPanel p=inicio(titulo,descripcion);JPanel arriba=new JPanel(new BorderLayout(6,6));arriba.setOpaque(false);
        arriba.add(campos,BorderLayout.CENTER);arriba.add(botones,BorderLayout.SOUTH);
        JPanel cabecera=new JPanel(new BorderLayout(4,4));cabecera.setOpaque(false);
        JPanel encabezado=new JPanel(new GridLayout(2,1));encabezado.setOpaque(false);
        JLabel l=new JLabel(titulo);l.setFont(new Font("SansSerif",Font.BOLD,23));l.setForeground(new Color(30,55,77));
        encabezado.add(l);encabezado.add(new JLabel(descripcion));
        cabecera.add(encabezado,BorderLayout.NORTH);cabecera.add(arriba,BorderLayout.CENTER);
        p.add(cabecera,BorderLayout.NORTH);p.add(new JScrollPane(tabla),BorderLayout.CENTER);
        destino.removeAll();destino.setLayout(new BorderLayout());destino.add(p,BorderLayout.CENTER);
        return p;
    }
}

package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

public final class Tema {
    public static final int PRINCIPAL = 0;
    public static final int ADMIN = 1;
    public static final int ATENCION = 2;
    public static final int FARMACIA = 3;

    public static final Color FONDO = new Color(0xF4F7FA);
    public static final Color BLANCO = Color.WHITE;
    public static final Color TEXTO = new Color(0x1E374D);
    public static final Color SECUNDARIO = new Color(0x5C6D7B);
    public static final Color BORDE = new Color(0xC9D3DC);
    public static final Color BORDE_PANEL = new Color(0xD5DEE6);
    public static final Color LINEA_TABLA = new Color(0xE3E9EE);
    public static final Color ERROR = new Color(0xC62828);
    public static final Color VERDE_ESTADO = new Color(0x2E7D32);
    private static final Color INACTIVO = new Color(0xE9EDF1);
    private static final Color TEXTO_INACTIVO = new Color(0x9AA7B2);

    private static final Color[] PRINCIPALES = {
        new Color(0x1E4E79), new Color(0x5E35B1),
        new Color(0x00838F), new Color(0x2E7D32)
    };
    private static final Color[] OSCUROS = {
        new Color(0x153A5C), new Color(0x4527A0),
        new Color(0x006064), new Color(0x1B5E20)
    };
    private static final Color[] CLAROS = {
        new Color(0xE3EEF7), new Color(0xEDE7F6),
        new Color(0xE0F4F6), new Color(0xE8F5E9)
    };

    private static final String FUENTE = "Segoe UI";
    private static final String CLAVE_GRUPO = "clinica.tema.grupo";
    private static final String CLAVE_TIPO = "clinica.tema.tipo";
    private static final String CLAVE_FOCO = "clinica.tema.foco";
    private static final int PRIMARIO = 1;
    private static final int SECUNDARIO_BOTON = 2;
    private static final int PELIGRO = 3;

    private Tema() { }

    public static Color acento(int grupo) {
        return PRINCIPALES[limitar(grupo)];
    }

    public static Color suave(int grupo) {
        return CLAROS[limitar(grupo)];
    }

    private static int limitar(int grupo) {
        return grupo < PRINCIPAL || grupo > FARMACIA ? PRINCIPAL : grupo;
    }

    public static void aplicar(JFrame ventana, int grupo, JLabel titulo, JLabel subtitulo) {
        titulo.setName("lblTitulo");
        subtitulo.setName("lblSubtitulo");
        ventana.getContentPane().setBackground(FONDO);
        recorrer(ventana.getContentPane(), limitar(grupo));
        ventana.repaint();
    }

    private static void recorrer(Component componente, int grupo) {
        if (componente instanceof JPanel) {
            prepararPanel((JPanel) componente, grupo);
        } else if (componente instanceof JButton) {
            JButton boton = (JButton) componente;
            estiloBoton(boton, grupo, tipoBoton(boton.getText()));
        } else if (componente instanceof JTable) {
            prepararTabla((JTable) componente, grupo);
        } else if (componente instanceof JScrollPane) {
            JScrollPane scroll = (JScrollPane) componente;
            scroll.setBorder(BorderFactory.createLineBorder(BORDE_PANEL));
            scroll.getViewport().setBackground(BLANCO);
            scroll.setBackground(BLANCO);
        } else if (componente instanceof JComboBox<?>) {
            prepararCombo((JComboBox<?>) componente, grupo);
        } else if (componente instanceof JTextComponent) {
            prepararTexto((JTextComponent) componente, grupo);
        } else if (componente instanceof JLabel) {
            prepararEtiqueta((JLabel) componente, grupo);
        }
        if (componente instanceof Container) {
            for (Component hijo : ((Container) componente).getComponents()) {
                recorrer(hijo, grupo);
            }
        }
    }

    private static Font fuente(int tamano, boolean negrita) {
        return new Font(FUENTE, negrita ? Font.BOLD : Font.PLAIN, tamano);
    }

    private static void prepararPanel(JPanel panel, int grupo) {
        if ("panelContenido".equals(panel.getName())) {
            panel.setBackground(FONDO);
        } else if (panel.getBorder() instanceof TitledBorder) {
            TitledBorder viejo = (TitledBorder) panel.getBorder();
            panel.setBackground(BLANCO);
            panel.setBorder(bordeTitulado(viejo.getTitle(), grupo, true, false));
        } else {
            if (panel.isOpaque()) panel.setBackground(FONDO);
        }
    }

    private static void prepararEtiqueta(JLabel label, int grupo) {
        String nombre = label.getName();
        if ("lblTitulo".equals(nombre)) {
            label.setFont(fuente(22, true));
            label.setForeground(acento(grupo));
        } else if ("lblSubtitulo".equals(nombre)) {
            label.setFont(fuente(13, false));
            label.setForeground(SECUNDARIO);
        } else if ("lblAyuda".equals(nombre) || label.getText() != null
                    && label.getText().startsWith("Clave de prueba")) {
            label.setFont(fuente(11, false).deriveFont(Font.ITALIC));
            label.setForeground(SECUNDARIO);
        } else {
            label.setFont(fuente(12, false));
            label.setForeground(TEXTO);
        }
    }

    private static void prepararTexto(JTextComponent campo, int grupo) {
        campo.setFont(fuente(13, false));
        campo.setBackground(BLANCO);
        campo.setForeground(TEXTO);
        campo.setCaretColor(acento(grupo));
        if (campo.getBorder() instanceof TitledBorder) {
            String titulo = ((TitledBorder) campo.getBorder()).getTitle();
            campo.setBorder(bordeTitulado(titulo, grupo, false, false));
            if (!Boolean.TRUE.equals(campo.getClientProperty(CLAVE_FOCO))) {
                campo.putClientProperty(CLAVE_FOCO, Boolean.TRUE);
                campo.addFocusListener(new FocusAdapter() {
                    @Override public void focusGained(FocusEvent e) {
                        campo.setBorder(bordeTitulado(titulo, grupo, false, true));
                        campo.repaint();
                    }
                    @Override public void focusLost(FocusEvent e) {
                        campo.setBorder(bordeTitulado(titulo, grupo, false, false));
                        campo.repaint();
                    }
                });
            }
        }
    }

    private static void prepararCombo(JComboBox<?> combo, int grupo) {
        combo.setFont(fuente(12, false));
        combo.setBackground(BLANCO);
        combo.setForeground(TEXTO);
        if (combo.getBorder() instanceof TitledBorder) {
            String titulo = ((TitledBorder) combo.getBorder()).getTitle();
            combo.setBorder(bordeTitulado(titulo, grupo, false, false));
        }
    }

    private static Border bordeTitulado(String titulo, int grupo, boolean panel, boolean foco) {
        Color color = foco ? acento(grupo) : panel ? BORDE_PANEL : BORDE;
        TitledBorder b = BorderFactory.createTitledBorder(
            new BordeRedondeado(color, panel ? 13 : 9, foco ? 2 : 1),
            titulo, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
            fuente(panel ? 12 : 11, panel), panel ? acento(grupo) : SECUNDARIO);
        return b;
    }

    private static int tipoBoton(String texto) {
        String t = texto == null ? "" : texto.trim().toLowerCase(Locale.ROOT);
        if (t.startsWith("eliminar") || t.startsWith("cancelar") ||
            t.startsWith("desactivar") || t.startsWith("cerrar sesión") ||
            t.startsWith("quitar") || t.startsWith("borrar") || t.startsWith("anular")) {
            return PELIGRO;
        }
        if (t.startsWith("registrar") || t.startsWith("guardar") ||
            t.startsWith("crear") || t.startsWith("generar") ||
            t.startsWith("despachar") || t.startsWith("ingresar") ||
            t.startsWith("añadir") || t.startsWith("agregar") ||
            t.startsWith("marcar") || t.startsWith("asignar") ||
            t.startsWith("emitir")) return PRIMARIO;
        return SECUNDARIO_BOTON;
    }

    public static void botonModulo(JButton boton, int grupo) {
        estiloBoton(boton, grupo, PRIMARIO);
        boton.setToolTipText(boton.isEnabled() ? "Abrir modulo" : "Sin permiso para tu rol");
    }

    public static void botonPeligro(JButton boton, int grupo) {
        estiloBoton(boton, grupo, PELIGRO);
    }

    private static void estiloBoton(JButton boton, int grupo, int tipo) {
        boton.putClientProperty(CLAVE_GRUPO, limitar(grupo));
        boton.putClientProperty(CLAVE_TIPO, tipo);
        boton.setUI(new BotonRedondeadoUI());
        boton.setFont(fuente(12, true));
        boton.setFocusPainted(false);
        boton.setRolloverEnabled(true);
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
        boton.setForeground(tipo == PRIMARIO ? BLANCO : tipo == PELIGRO ? ERROR : acento(grupo));
    }

    private static class BotonRedondeadoUI extends BasicButtonUI {
        @Override public void paint(Graphics graphics, JComponent componente) {
            JButton b = (JButton) componente;
            int grupo = (Integer) b.getClientProperty(CLAVE_GRUPO);
            int tipo = (Integer) b.getClientProperty(CLAVE_TIPO);
            boolean hover = b.getModel().isRollover();
            boolean pulsado = b.getModel().isPressed();
            Color fondo;
            Color borde;
            Color texto;
            if (!b.isEnabled()) {
                fondo = INACTIVO; borde = BORDE_PANEL; texto = TEXTO_INACTIVO;
            } else if (tipo == PRIMARIO) {
                fondo = hover || pulsado ? OSCUROS[grupo] : PRINCIPALES[grupo];
                borde = fondo; texto = BLANCO;
            } else if (tipo == PELIGRO) {
                fondo = hover || pulsado ? ERROR : BLANCO;
                borde = ERROR; texto = hover || pulsado ? BLANCO : ERROR;
            } else {
                fondo = hover || pulsado ? CLAROS[grupo] : BLANCO;
                borde = PRINCIPALES[grupo]; texto = PRINCIPALES[grupo];
            }
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = b.getWidth() - 1, h = b.getHeight() - 1;
            g.setColor(fondo);
            g.fillRoundRect(1, 1, w - 1, h - 1, 12, 12);
            g.setColor(borde);
            g.drawRoundRect(1, 1, w - 1, h - 1, 12, 12);
            if (b.isFocusOwner()) {
                g.setColor(OSCUROS[grupo]);
                g.drawRoundRect(3, 3, w - 5, h - 5, 10, 10);
            }
            g.dispose();
            b.setForeground(texto);
            super.paint(graphics, componente);
        }
    }

    private static void prepararTabla(JTable tabla, int grupo) {
        tabla.setFont(fuente(12, false));
        tabla.setRowHeight(28);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);
        tabla.setGridColor(LINEA_TABLA);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setBackground(BLANCO);
        tabla.setForeground(TEXTO);
        tabla.setSelectionBackground(CLAROS[grupo]);
        tabla.setSelectionForeground(TEXTO);
        tabla.setDefaultRenderer(Object.class, new CeldaTematica(grupo));
        JTableHeader cabecera = tabla.getTableHeader();
        if (cabecera != null) {
            cabecera.setReorderingAllowed(false);
            cabecera.setPreferredSize(new java.awt.Dimension(cabecera.getPreferredSize().width, 32));
            cabecera.setDefaultRenderer(new CabeceraTematica(grupo));
            cabecera.setBackground(PRINCIPALES[grupo]);
            cabecera.setForeground(BLANCO);
            cabecera.setFont(fuente(12, true));
        }
    }

    private static class CabeceraTematica extends DefaultTableCellRenderer {
        private final int grupo;
        CabeceraTematica(int grupo) { this.grupo = grupo; }
        @Override public Component getTableCellRendererComponent(JTable tabla, Object valor,
                    boolean seleccionado, boolean foco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, false, false, fila, columna);
            setBackground(PRINCIPALES[grupo]);
            setForeground(BLANCO);
            setFont(fuente(12, true));
            setHorizontalAlignment(SwingConstants.LEFT);
            setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 8));
            return this;
        }
    }

    private static class CeldaTematica extends DefaultTableCellRenderer {
        private final int grupo;
        CeldaTematica(int grupo) { this.grupo = grupo; }
        @Override public Component getTableCellRendererComponent(JTable tabla, Object valor,
                    boolean seleccionado, boolean foco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionado, foco, fila, columna);
            setFont(fuente(12, false));
            setBorder(BorderFactory.createEmptyBorder(0, 9, 0, 7));
            Color colorFondo = fila % 2 == 0 ? BLANCO : new Color(0xF7FAFC);
            Color colorTexto = TEXTO;
            boolean esStock = tieneColumna(tabla, "Estado stock") && tieneColumna(tabla, "Disponible");
            boolean esCita = tieneColumna(tabla, "Estado") && (
                tieneColumna(tabla, "DNI paciente") || tieneColumna(tabla, "Empleado"));
            String estado = obtenerColumna(tabla, fila, "Estado");
            if (esStock) {
                String stock = obtenerColumna(tabla, fila, "Estado stock");
                if ("BAJO".equalsIgnoreCase(stock)) {
                    colorFondo = new Color(0xFFF4D6);
                    colorTexto = new Color(0x8A5A00);
                }
                try {
                    int disponible = Integer.parseInt(obtenerColumna(tabla, fila, "Disponible"));
                    if (disponible <= 0) {
                        colorFondo = new Color(0xFDECEA);
                        colorTexto = new Color(0xB71C1C);
                    } else {
                        String vencimiento = obtenerColumna(tabla, fila, "Próximo vencimiento");
                        LocalDate fecha = LocalDate.parse(vencimiento);
                        long dias = ChronoUnit.DAYS.between(LocalDate.now(), fecha);
                        if (dias < 0) {
                            colorFondo = new Color(0xFDECEA);
                            colorTexto = new Color(0xB71C1C);
                        } else if (dias <= 30) {
                            colorFondo = new Color(0xFFF4D6);
                            colorTexto = new Color(0x8A5A00);
                        }
                    }
                } catch (RuntimeException ignorada) {
                }
            } else if (esCita) {
                String tituloColumna = tabla.getColumnName(columna).toLowerCase(Locale.ROOT);
                if (tituloColumna.equals("estado")) {
                    if ("Atendida".equalsIgnoreCase(estado)) colorTexto = VERDE_ESTADO;
                    else if ("Programada".equalsIgnoreCase(estado)) colorTexto = PRINCIPALES[PRINCIPAL];
                    else if ("Cancelada".equalsIgnoreCase(estado) || "Eliminada".equalsIgnoreCase(estado)) {
                        colorTexto = TEXTO_INACTIVO;
                    }
                }
            }
            if (seleccionado) {
                setBackground(CLAROS[grupo]);
                setForeground(colorTexto.equals(TEXTO_INACTIVO) ? SECUNDARIO : colorTexto);
            } else {
                setBackground(colorFondo);
                setForeground(colorTexto);
            }
            return this;
        }
        private boolean tieneColumna(JTable tabla, String titulo) {
            for (int col = 0; col < tabla.getColumnCount(); col++) {
                if (titulo.equalsIgnoreCase(tabla.getColumnName(col))) return true;
            }
            return false;
        }
        private String obtenerColumna(JTable tabla, int fila, String titulo) {
            for (int col = 0; col < tabla.getColumnCount(); col++) {
                if (titulo.equalsIgnoreCase(tabla.getColumnName(col))) {
                    Object valor = tabla.getValueAt(fila, col);
                    return valor == null ? "" : valor.toString();
                }
            }
            return "";
        }
    }

    private static class BordeRedondeado extends AbstractBorder {
        private final Color color;
        private final int radio;
        private final int grosor;
        BordeRedondeado(Color color, int radio, int grosor) {
            this.color = color;
            this.radio = radio;
            this.grosor = grosor;
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(4, 8, 4, 8); }
        @Override public Insets getBorderInsets(Component c, Insets insets) {
            insets.top = 4; insets.left = 8; insets.bottom = 4; insets.right = 8;
            return insets;
        }
        @Override public boolean isBorderOpaque() { return false; }
        @Override public void paintBorder(Component c, Graphics graphics, int x, int y, int w, int h) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.setStroke(new java.awt.BasicStroke(grosor));
            g.drawRoundRect(x + 1, y + 1, w - 3, h - 3, radio, radio);
            g.dispose();
        }
    }

    public static void organizarMenu(JPanel panel) {
        String[][] secciones = {
            {"Usuarios", "Roles", "Reportes"},
            {"Pacientes", "Médicos", "Citas", "Agenda", "Horarios", "Seguros", "Consultas", "Historial médico"},
            {"Medicamentos", "Lotes", "Movimientos", "Stock", "Proveedores", "Almacenes", "Recetas"}
        };
        String[] titulos = {"ADMINISTRACIÓN", "ATENCIÓN MÉDICA", "FARMACIA E INVENTARIO"};
        int[] grupos = {ADMIN, ATENCION, FARMACIA};
        int[] titulosY = {23, 123, 300};
        int[] botonesY = {47, 147, 324};
        for (int seccion = 0; seccion < secciones.length; seccion++) {
            JLabel encabezado = new JLabel(titulos[seccion]);
            encabezado.setFont(fuente(12, true));
            encabezado.setForeground(PRINCIPALES[grupos[seccion]]);
            encabezado.setBounds(20, titulosY[seccion], 640, 18);
            panel.add(encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, titulosY[seccion], 640, 18));
            for (int i = 0; i < secciones[seccion].length; i++) {
                JButton actual = buscarBoton(panel, secciones[seccion][i]);
                if (actual == null) continue;
                int columna = i % 3, fila = i / 3;
                panel.remove(actual);
                panel.add(actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    20 + columna * 216, botonesY[seccion] + fila * 41, 195, 36));
                botonModulo(actual, grupos[seccion]);
                if (!actual.isEnabled()) actual.setToolTipText("Sin permiso para tu rol");
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private static JButton buscarBoton(JPanel panel, String titulo) {
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton && titulo.equals(((JButton) c).getText())) {
                return (JButton) c;
            }
        }
        return null;
    }

    public static void etiquetaRol(JLabel etiqueta, String rol) {
        int grupo = "Administrador".equals(rol) ? ADMIN :
            "Farmacia".equals(rol) ? FARMACIA :
            "Médico".equals(rol) || "Recepcionista".equals(rol) ? ATENCION : PRINCIPAL;
        etiqueta.setFont(fuente(12, true));
        etiqueta.setOpaque(true);
        etiqueta.setForeground(PRINCIPALES[grupo]);
        etiqueta.setBackground(CLAROS[grupo]);
        etiqueta.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 6));
    }
}

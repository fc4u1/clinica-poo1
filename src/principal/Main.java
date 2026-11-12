package principal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import dao.DatosMemoria;
import vista.FrmLogin;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
            catch(Exception ignorada) { }
            UIManager.put("OptionPane.yesButtonText", "Sí");
            UIManager.put("OptionPane.noButtonText", "No");
            UIManager.put("OptionPane.cancelButtonText", "Cancelar");
            UIManager.put("OptionPane.okButtonText", "Aceptar");
            new FrmLogin(new DatosMemoria()).setVisible(true);
        });
    }
}

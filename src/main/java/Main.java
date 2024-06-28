
import javax.swing.JPanel;
import views.MainFrame;
import views.auths.Login;

/**
 *
 * @author arfanxn
 */
public class Main {

    public static void main(String[] args) {
        try {
            // TODO: fix erron on load env
            // load and configure the env 
            // configs.ENV.getInstance().loadAndConfigure();

            JPanel initialComponent = new Login();
            MainFrame frame = MainFrame.getInstance();
            frame.initialize(initialComponent);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

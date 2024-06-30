
import javax.swing.JPanel;
import utilities.Context;
import views.MainFrame;
import views.auths.Login;
import views.goals.Index;
import containers.Services;
import exceptions.ResponseException;

/**
 *
 * @author arfanxn
 */
public class Main {

    public static void main(String[] args) {
        try {
            // load and configure the env
            configs.ENV.getInstance().load().configure();

            Context ctx = Context.instantiate();

            Main.show(ctx);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void show(Context ctx) {
        JPanel initialComponent;

        try {
            ctx = Services.initUser().showSelf(ctx);
            ctx.remove("message");
            initialComponent = new Index(ctx);
        } catch (ResponseException e) {
            if (e.getStatusCode() == 401) { // if unauthorized 
                initialComponent = new Login(ctx);
            } else {
                e.printStackTrace();
                System.exit(1);
                return;
            }
        }

        MainFrame frame = MainFrame.getInstance();
        frame.initialize(initialComponent);
    }

}

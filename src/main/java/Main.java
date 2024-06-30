
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
            ctx = Services.initAuth().check(ctx);
        } catch (ResponseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // if authorized then the initialComponent would be dashboard page
        var isAuth = ctx.<Boolean>get("is_auth", Boolean.class);
        initialComponent = isAuth ? new Index(ctx) : new Login(ctx);

        MainFrame frame = MainFrame.getInstance();
        frame.initialize(initialComponent);
    }

}

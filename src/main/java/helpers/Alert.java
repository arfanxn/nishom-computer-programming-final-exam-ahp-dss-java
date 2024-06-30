/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

import java.awt.Component;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import utilities.Context;

/**
 *
 * @author arfanxn
 */
public class Alert {

    public static void message(Component parentComponent, String message, Runnable afterward) {
        CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS).execute(() -> {
            JOptionPane.showMessageDialog(
                    parentComponent,
                    message
            );
            if (afterward != null) {
                afterward.run();
            }
        });
    }

    /**
     *
     * @param parentComponent
     * @param message
     * @deprecated
     */
    @Deprecated
    public static void message(Component parentComponent, String message) {
        Alert.message(parentComponent, message, null);
    }

    public static void message(Component parentComponent, Context ctx, Runnable afterward) {
        if (ctx.has("message")) {
            var message = ctx.<String>pull("message");
            Alert.message(parentComponent, message, afterward);
        }
    }

    public static void confirmation(Component parentComponent, String message, Runnable afterwardYes, Runnable afterwardNo) {
        CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS).execute(() -> {
            int option = JOptionPane.showConfirmDialog(
                    parentComponent,
                    message,
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (afterwardYes != null && option == JOptionPane.YES_OPTION) {
                afterwardYes.run();
            } else if (afterwardNo != null && option == JOptionPane.NO_OPTION) {
                afterwardNo.run();
            }
        });
    }

    public static void confirmation(Component parentComponent, Context ctx, Runnable afterwardYes, Runnable afterwardNo) {
        if (ctx.has("message")) {
            var message = ctx.<String>pull("message");
            Alert.confirmation(parentComponent, message, afterwardYes, afterwardNo);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package containers;

import controllers.AuthController;
import services.AuthService;

/**
 *
 * @author arfanxn
 */
public class Controllers {
    
    public static AuthController initAuth() {
        AuthService service = Services.initAuth();
        AuthController controller = new AuthController(service);
        return controller;
    }
    
}

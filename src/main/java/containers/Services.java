/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package containers;

import services.UserService;
import services.AuthService;
import services.GoalService;

/**
 *
 * @author arfanxn
 */
public class Services {
    
    public static UserService initUser() {
        return new UserService();
    }
    
    public static AuthService initAuth() {
        return new AuthService();
    }
    
    public static GoalService initGoal() {
        return new GoalService();
    }
    
}

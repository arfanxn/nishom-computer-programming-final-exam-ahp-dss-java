/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import services.AuthService;

/**
 *
 * @author arfanxn
 */
public class AuthController {
    
    private AuthService service;
    
    public AuthController (AuthService service) {
        this.service = service;
    }
    
}

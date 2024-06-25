/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.unipi.WeatherAnalyzerServer.utility;

import java.io.Serializable;

/**
 *
 * @author Filippo
 */
public class LoginResponse implements Serializable{
    public String body;
    public String token;

    public LoginResponse(String body, String token) {
        this.body = body;
        this.token = token;
    }
}

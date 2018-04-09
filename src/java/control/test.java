/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Edgar
 */
public class test {
    public static void main(String[] args) {
        if(Gmail.sendMail("edgar_lizcano@hotmail.com", "Prueba", "Saludos mano")){
            System.out.println("Enviado mail correctamente");
        }else{
            System.out.println("Error enviando");
        }
    }
}

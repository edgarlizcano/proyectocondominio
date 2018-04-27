/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class test {
    public static void main(String[] args) {
        try {
            String inicio = "2018-04-15";
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(inicio);
            String fecha = new SimpleDateFormat("dd-MM-yyyy").format(d);
            
            
            System.out.println(inicio);
            System.out.println(d);
            System.out.println(fecha);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

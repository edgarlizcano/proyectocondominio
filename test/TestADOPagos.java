
import db.ADOPagos;
import java.util.ArrayList;
import modelo.Pagos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edgar
 */
public class TestADOPagos {
    public static void main(String[] args) {
        //ArrayList<Pagos> lista = ADOPagos.obtenerPagoByFecha("2018/04/01", "2018/04/30");
        ArrayList<Pagos> lista = ADOPagos.obtenerPagosEnEspera();
        //ArrayList<Pagos> lista = ADOPagos.obtenerPagosConfirmados();
//        Pagos a = ADOPagos.obtenerPagoById(16);
//        lista.add(a);
        for (Pagos p: lista){
            System.out.println("ID Pago: " +p.getIdPagos());
            System.out.println("Fecha Pago: " +p.getFecha());
            System.out.println("Cedula: " +p.getCedulaDepositante());
            System.out.println("Nombre y apellido: " +p.getNombreApellido());
        }
    }
    
}

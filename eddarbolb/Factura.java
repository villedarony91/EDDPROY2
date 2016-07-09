/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddarbolb;
import Utilities.Log;
import avl.*;
import listascolas.*;

/**
 *
 * @author otto_
 */
public class Factura {
    String fecha;
    double total;
    NodoAvl usuario;
    Lista detalle;
    
    Factura(String fecha,double total,NodoAvl usuario, Lista detalle){
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
        this.detalle = detalle;
        Log.logger.info("*************" + usuario.username);
    }
    
}

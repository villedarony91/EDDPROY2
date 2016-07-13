/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaOrdenada;

/**
 *
 * @author otto_
 */
public class NodoListaDoble {
    
    public NodoListaDoble sig, ant;
    public String nombre;
    public int cantidad;
    
    NodoListaDoble(int cantidad, String nombre){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.sig = null;
        this.ant = null;    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listascolas;

/**
 *
 * @author rlopez
 */
public class Nodo {
    String user;
    String address;
    int envio;
    int fact;
    int cant;
    public Producto prod;
    public Nodo next;
    
    Nodo(String user){
        this.user = user;
    }
    
}

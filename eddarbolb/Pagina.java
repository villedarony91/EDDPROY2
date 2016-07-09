/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddarbolb;

/**
 *
 * @author otto_
 */

public class Pagina {
    int m = 5;
    int[] claves;
    Pagina[] ramas;
    Factura[] facturas;
    int cuenta;
    int idpagina;
    
    Pagina(){
        this.cuenta = 0;
        this.claves = new int[m];
        this.ramas = new Pagina[m];
        this.facturas = new Factura[m];
    }
    
    public boolean nodoLleno(Pagina actual){
        return (actual.cuenta >= (m-1));
    }
    
    public boolean nodoSemiVacio(Pagina actual){
        return (actual.cuenta < (m/2));
    }
    
    public void imprimir(Pagina actual){

        System.out.println("| Nodo: ");
        for(int i = 1; i <= actual.cuenta; i++){
            System.out.print(actual.claves[i]);
        }
        System.out.print(" |");
        
    }
    
    
    
}
    

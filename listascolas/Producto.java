/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listascolas;


/**
 *Clase tipo producto para ser utilizada en listas y en la tabla Hash
 * @author rlopez
 */
public class Producto {
    public int code;
    public String name;
    public String brand;
    public double price;
    public String image;
    
    public Producto(int code, String name, String brand,
            double price, String image){
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }
    
    public Producto(){
        
    }
    
    
}

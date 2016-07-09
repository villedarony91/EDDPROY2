/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import avl.*;
import java.util.regex.Pattern;
import listascolas.*;
import eddarbolb.*;

/**
 *
 * @author rlopez
 */
public class MessageReceiver {
    public ArbolAvl avl = new ArbolAvl();
    public Hash hash = new Hash();
    public ArbolB arbolB = new ArbolB();

    public void receiveMessage(String message){
        String tmp = message.replaceAll("\\s+", "");
        tmp = tmp.replaceAll(";", "");
        tmp = tmp.replace(")", "");
        String[] str = tmp.split(Pattern.quote("("));
        distribute(str);
    }
    
    void distribute(String[] message){        
        if(message.length == 2 ){
            String[] str = message[1].split(",");
            switch(message[0]){
                case "Usuario":
                    insertToAvlUser(str);
                    Log.logger.info("recibido usuario " + str[0] +" "+ str[1]);
                    break;
                case "Direccion":
                    insertAdress(str);
                    Log.logger.info("recibido direccion " +str[0]);
                    break;
                case "PorComprar":
                    insertToWhish(str);
                    Log.logger.info("recibido por comprar");
                    break;
                case "Carrito":
                    insertToCarrito(str);
                    Log.logger.info("recibido carrito");
                    break;
                case "Producto":
                    insertToHash(str);
                    Log.logger.info("recibido producto");
                    break;
                case "Venta":
                    insertToB(str);
                    Log.logger.info("recibido venta");
                    break;
                case "Detalle":
                    
                    Log.logger.info("recibido detalle");
                    break;
            }
        }else{
            Log.logger.warn("error de parseo ");
        }
    }
    
    void insertToB(String[] msg){
        if(msg.length == 4){
            try{
                int factura = Integer.parseInt(msg[0]);
                double total = Double.parseDouble(msg[2]);
                arbolB.Insertar(factura, msg[1], total, avl.searchNode(msg[3]));
            }catch(Exception ex){
                Log.logger.warn("Venta en formato incorrecto");
            }  
        }else{
            Log.logger.warn("Venta en formato incorrecto");
        }
    }
    
    void insertToCarrito(String [] msj){
        if(msj.length == 3){
            try{
            int code = Integer.parseInt(msj[2]);
            int cant = Integer.parseInt(msj[1]);
            Producto prod = hash.search(code);
            if(prod != null){
             avl.insertToCarrito(msj[0], cant, prod);
            }
            }catch(Exception ex){
                Log.logger.warn("Carrito en formato incorrecto");
            }
        }else{
            Log.logger.warn("Carrito formato incorrecto");
        }
    }
    
    void insertToHash(String[] nodeProd){
        if(nodeProd.length == 5){
            hash.insertProduct(Integer.parseInt(nodeProd[0]),
                    nodeProd[1], nodeProd[2],
                    Double.parseDouble(nodeProd[3]), nodeProd[4]);
        }
    }
    
    void insertToAvlUser(String[] nodeAvl){
        if(nodeAvl.length == 2){
            avl.insert(nodeAvl[0], nodeAvl[1]);
        }else{
            Log.logger.error("Usuario en formato incorrecto");
        }
        
    }
    
    void insertAdress(String[] msj){
        if(msj.length == 4){
            int env;
            int fact;
            try{
                env = Integer.parseInt(msj[2]);
                fact = Integer.parseInt(msj[3]);
                avl.insertAdress(msj[0], msj[1], env, fact);      
            }catch(Exception ex){
                Log.logger.error("Error en formato de numero");
            }                     
        }else{
            Log.logger.warn("Direccion en formato incorrecto");
        }
        
    }
    
    void insertToWhish(String[] msj){
        if(msj.length == 3){
            try{
            int code = Integer.parseInt(msj[2]);
            int cant = Integer.parseInt(msj[1]);
            Producto prod = hash.search(code);
            if(prod != null){
             avl.insertToWish(msj[0], cant, prod);
            }
            }catch(Exception ex){
                Log.logger.warn("Por comprar en formato incorrecto");
            }
        }else{
            Log.logger.warn("Por comprar formato incorrecto");
        }
    }
        
    
}

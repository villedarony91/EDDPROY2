/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools  Templates
 * and open the template in the editor.
 */
package com;
import Utilities.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import listascolas.Producto;


/**
 *Clase con los métodos del webService
 * @author rlopez
 */
@WebService(serviceName = "Foo")
@Stateless()
public class Foo {
    MessageReceiver mr = new MessageReceiver();

    /**
     * Recibe los mensajes de las operaciones
     * @param message mensaje que es recibido
     * @return message el mensaje que fue recibido
     */
    @WebMethod(operationName = "receiveMessage")
    public String receiveMessage(@WebParam(name = "message") String message) {
        mr.receiveMessage(message);
        return "received " + message;
    }

    /**
     * grafica arbo avl
     * @return cadena para graficar arbol avl
     */
    @WebMethod(operationName = "graphAvlTree")
    public String graphAvlTree() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph avl{");
        sb.append(mr.avl.apGraph());
        sb.append("}");
        return sb.toString();
    }

    /**
     * metodo que grafica la lista de direcciones
     * @param username nombre de usuario del que se graficara la lista
     * @return cadena para graficar la lista
     */
    @WebMethod(operationName = "graphAdList")
    public String graphAdList(@WebParam(name = "username") String username) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g{");
        sb.append(mr.avl.graphAdress(username));
        sb.append("}");
        return  sb.toString();
    }

    /**
     * Busca si el producto existe
     * @param Code codigo del producto
     * @return informacion del producto, si existe
     */
    @WebMethod(operationName = "searchProduct")
    public String searchProduct(@WebParam(name = "Code") int Code) {
        Producto tmp = mr.hash.search(Code);
        StringBuilder sb = new StringBuilder();
        if(tmp != null){
            sb.append(tmp.code);
            sb.append(tmp.name);
            sb.append(tmp.price);
        }
        return sb.toString();
    }
    

    /**
     * Envia grafica de la lista de deseos
     * @param user nombre del usuario del que se desea graficar
     * @return grafica en formato graphViz
     */
    @WebMethod(operationName = "sendWhishGraph")
    public String sendWhishGraph(@WebParam(name = "user") String user) {
    StringBuilder sb = new StringBuilder();
        sb.append("digraph g {\n");
        sb.append(mr.avl.graphWhish(user));
        sb.append("}");
        return sb.toString();
    }

    /**
     * envia grafica del carrito
     * @param user nombre del usuario del que se enviara la grafica
     * @return cadena para graficar el carrito
     */
    @WebMethod(operationName = "sendCarritoGraph")
    public String sendCarritoGraph(@WebParam(name = "user") String user) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g {\n");
        sb.append(mr.avl.graphCarrito(user));
        sb.append("}");
        return sb.toString();
    }

    /**
     * valida usuario
     * @param user nombre del usuario
     * @param pass contrasena
     * @return  1 si coincidee 0 si no coincide
     */
    @WebMethod(operationName = "valUser")
    public int valUser(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        int ret = (mr.avl.validateUser(user, pass) ? 1 : 0 );
        return ret;
    }

    /**
     * envia codigo de producto para se consumido por el cliente
     * @return cadena que contiene los codigos de los productos
     */
    @WebMethod(operationName = "sendProdCode")
    public String sendProdCode() {
        return mr.hash.sendProdCode();
    }

    /**
     * Envia los nombres de los producto para el catalogo de productos
     * @return cadena que contiene todos los nombres de los productos
     */
    @WebMethod(operationName = "SendProdName")
    public String SendProdName() {
        return mr.hash.sendProdName();
    }

    /**
     * envia las marcas de los prodctos
     * @return 
     */
    @WebMethod(operationName = "sendProdBrand")
    public String sendProdBrand() {
        return mr.hash.sendProdBrand();
    }

    /**
     * envía los precios de los productos para el catalogo
     * @return cadena con todos los precios de los productos
     */
    @WebMethod(operationName = "sendProdPrice")
    public String sendProdPrice() {
        return mr.hash.sendProdPrice();
    }

    /**
     * envia las rutas de las imagenes
     * @return cadena con las rutas de las imagenenes para el catalogo de
     * productos
     */
    @WebMethod(operationName = "sendProdImage")
    public String sendProdImage() {
        return mr.hash.sendProdImage();
    }

    /**
     * envia la lista de deseos para el catalogo
     * @param username nombre del usuario del que se requiere la lista
     * @return cadena con la lista de deseos
     */
    @WebMethod(operationName = "sendWhish")
    public String sendWhish(@WebParam(name = "username") String username) {
        return mr.avl.sendWhishInfo(username);
    }

   /**
    * envia la información del carrito
    * @param user nombre del usuario del que se solicita el carrito
    * @return cadena con la información del carrito
    */
    @WebMethod(operationName = "sendCartInfo")
    public String sendCartInfo(@WebParam(name = "user") String user) {
        return mr.avl.sendCartInfo(user);
    }

    /**
     *remover un articulo de la lista de deseos
     * @param user nombre del usuario
     * @param code codigo del producto a remover
     * @return si fue eliminado o si no fue posible eliminar el producto
     */
    @WebMethod(operationName = "removeFromWhish")
    public String removeFromWhish(@WebParam(name = "user") String user, @WebParam(name = "code") String code) {
        String ret;
        try{
            ret = "Eliminado de whislist " + user + code;
            Log.logger.info(ret);
            int cod = Integer.parseInt(code);
            mr.avl.removeFromWhish(user, cod);
        }catch(Exception ex){
            Log.logger.warn("Codigo en formato incorrecto"+code);
            ret = "no fue posible eliminar "+ user + code;
        }
        return ret;
    }

    /**
     * remueve del carrito
     * @param user nombre de usuario del que se removerá
     * @param code codigo del producto a remover
     * @return si fue o no posible eliminar
     */
    @WebMethod(operationName = "removeFromCart")
    public String removeFromCart(@WebParam(name = "user") String user, @WebParam(name = "code") String code) {
        String ret;
        try{
            ret = "Eliminado del carrito " + user + code;
            Log.logger.info(ret);
            int cod = Integer.parseInt(code);
            mr.avl.removeFromCart(user, cod);
        }catch(Exception ex){
            Log.logger.warn("Codigo en formato incorrecto"+code);
            ret = "no fue posible eliminar "+ user + code;
        }
        return ret;
    }

    /**
     * envia grafica del arbol B
     * @return cadena con la grafica del arbo en formato graphViz
     */
    @WebMethod(operationName = "sendArbolB")
    public String sendArbolB() {
        return mr.arbolB.graficarArolbB(mr.arbolB);
    }

    /**
     * recibe la venta
     * @param user
     * @return si el mensaje fue recibido
     */
    @WebMethod(operationName = "sale")
    public String sale(@WebParam(name = "user") String user) {
        mr.sale(user);
        return "recibido venta";
    }

    /**
     * envía gráfica de la tabla hash
     * @return 
     */
    @WebMethod(operationName = "sendHashGraph")
    public String sendHashGraph() {
        return mr.hash.graphHash();
    }

    /**
     * 
     * @param fFecha
     * @param fValor
     * @param fusuario
     * @param fechaIni
     * @param fechafin
     * @param valor
     * @param usuario
     * @return 
     */
    @WebMethod(operationName = "report1")
    public String report1(@WebParam(name = "fFecha") String fFecha, @WebParam(name = "fValor") String fValor, 
            @WebParam(name = "fusuario") String fusuario, @WebParam(name = "fechaIni") String fechaIni, 
            @WebParam(name = "fechafin") String fechafin, @WebParam(name = "valor") String valor, 
            @WebParam(name = "usuario") String usuario) {
        try{
            int flagFecha = Integer.parseInt(fFecha);
            int flagValor = Integer.parseInt(fValor);
            int flagUsuario = Integer.parseInt(fusuario);
            double val = Double.parseDouble(valor);
            return mr.arbolB.reporte1(flagFecha, flagValor,flagUsuario, fechaIni, 
                    fechafin, val, mr.avl.searchNode(usuario) );
        }catch(Exception ex){
            Log.logger.error("error en envio de parametros reporte 1");
        }
        return "";
    }

    /**
     * 
     * @param fechaIni
     * @param fechaFin
     * @return 
     */
    @WebMethod(operationName = "report2")
    public String report2(@WebParam(name = "fechaIni") String fechaIni,
            @WebParam(name = "fechaFin") String fechaFin) {
        return mr.arbolB.reporte2(fechaIni, fechaFin);
    }

    /**
     * 
     * @param fechaIni
     * @param fechaFin
     * @return 
     */
    @WebMethod(operationName = "report3")
    public String report3(@WebParam(name = "fechaIni") String fechaIni, 
            @WebParam(name = "fechaFin") String fechaFin) {
        return mr.arbolB.reporte3(fechaIni, fechaFin);
    }
    
}
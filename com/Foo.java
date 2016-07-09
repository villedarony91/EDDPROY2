/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;
import Utilities.*;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import listascolas.Producto;


/**
 *
 * @author rlopez
 */
@WebService(serviceName = "Foo")
@Stateless()
public class Foo {
    MessageReceiver mr = new MessageReceiver();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public int operation(@WebParam(name = "i") int i, @WebParam(name = "j") int j) {
        //TODO write your implementation code here:
        return i + j ;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "receiveMessage")
    public String receiveMessage(@WebParam(name = "message") String message) {
        mr.receiveMessage(message);
        return "received " + message;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "graphAvlTree")
    public String graphAvlTree() {
        return mr.avl.apGraph();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "graphAdList")
    public String graphAdList(@WebParam(name = "username") String username) {
        return mr.avl.graphAdress(username);
    }

    /**
     * Web service operation
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
     * Web service operation
     */
    @WebMethod(operationName = "sendWhishGraph")
    public String sendWhishGraph(@WebParam(name = "user") String user) {e:
        return mr.avl.graphWhish(user);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendCarritoGraph")
    public String sendCarritoGraph(@WebParam(name = "user") String user) {
        return mr.avl.graphCarrito(user);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "valUser")
    public int valUser(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        int ret = (mr.avl.validateUser(user, pass) ? 1 : 0 );
        return ret;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendProdCode")
    public String sendProdCode() {
        return mr.hash.sendProdCode();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "SendProdName")
    public String SendProdName() {
        return mr.hash.sendProdName();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendProdBrand")
    public String sendProdBrand() {
        return mr.hash.sendProdBrand();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendProdPrice")
    public String sendProdPrice() {
        return mr.hash.sendProdPrice();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendProdImage")
    public String sendProdImage() {
        return mr.hash.sendProdImage();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendWhish")
    public String sendWhish(@WebParam(name = "username") String username) {
        return mr.avl.sendWhishInfo(username);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sendCartInfo")
    public String sendCartInfo(@WebParam(name = "user") String user) {
        return mr.avl.sendCartInfo(user);
    }

    /**
     * Web service operation
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
     * Web service operation
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
     * Web service operation
     */
    @WebMethod(operationName = "sendArbolB")
    public String sendArbolB() {
        return mr.arbolB.graficarArolbB(mr.arbolB);
    }
 
}
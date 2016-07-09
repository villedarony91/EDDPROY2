package avl;

import Utilities.Log;
import listascolas.*;

public class ArbolAvl {

    private NodoAvl root;

    public ArbolAvl() {
        root = null;
    }

    public void insert(String user, String pass) {
        root = insert(user, pass, root);
    }

    public String apGraph() {
        return root.getGraphContent();
    }

    private NodoAvl rotDI(NodoAvl n1) {
        n1.der = rotII(n1.der);
        return rotDD(n1);
    }

    private NodoAvl insert(String user, String pass, NodoAvl raiz) {
        if (raiz == null) {
            raiz = new NodoAvl(user, pass);
            Log.logger.info("Usuario insertado en avl " + user);
        } else if (user.compareTo(raiz.username) < 0) {
            raiz.izq = insert(user, pass, raiz.izq);
            if (altura(raiz.der) - altura(raiz.izq) == -2) {
                if (user.compareTo(raiz.izq.username) < 0) {
                    raiz = rotII(raiz);
                    Log.logger.info("Usuario insertado en avl " + user);
                } else {
                    raiz = ID(raiz);
                }
            }
        } else if (user.compareTo(raiz.username) > 0) {
            raiz.der = insert(user, pass, raiz.der);
            if (altura(raiz.der) - altura(raiz.izq) == 2) {
                if (user.compareTo(raiz.der.username) > 0) {
                    raiz = rotDD(raiz);
                    Log.logger.info("Usuario insertado en avl " + user);
                } else {
                    raiz = rotDI(raiz);
                }
            }
        } else {
            Log.logger.warn("Usuario repetido " + user);
        }
        raiz.altura = mayor(altura(raiz.izq), altura(raiz.der)) + 1;
        return raiz;
    }

    public void insertAdress(String user, String adress, int env, int fact) {
        NodoAvl tmp = root;
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.direcciones != null)
            tmp.direcciones.insertList(user, adress, env, fact);
            else{
                Lista ls = new Lista();
                ls.insertList(user, adress, env, fact);
                tmp.direcciones = ls;
            }
            Log.logger.info(adress + " Agregada a usuario :" + user);
        } else {
            Log.logger.warn("Usuario no encontrado " + user);
        }

    }
    
        public void insertToWish(String user, int cant, Producto prod) {
        NodoAvl tmp = root;
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.whish!= null)
            tmp.whish.insertTail(user, cant, prod);
            else{
                Lista ls = new Lista();
                ls.insertTail(user, cant, prod);
                tmp.whish = ls;
            }
            Log.logger.info(" Agregado a usuario :" + user);
        } else {
            Log.logger.warn("Usuario no encontrado " + user);
        }

    }
        
        public void removeFromWhish(String user, int code){
            NodoAvl tmp = searchNode(user);
            tmp.whish.deleteNode(code);
        }
        
        public void removeFromCart(String user, int code){
            NodoAvl tmp = searchNode(user);
            tmp.carrito.deleteNode(code);
        }
        
        public void insertToCarrito(String user, int cant, Producto prod) {
            NodoAvl tmp = root;
            while (tmp != null && !tmp.username.equals(user)) {
                if (user.compareTo(tmp.username) < 0) {
                    tmp = tmp.izq;
                } else {
                    tmp = tmp.der;
                }
            }
            if (tmp != null && user.equals(tmp.username)) {
                if(tmp.carrito != null)
                    tmp.carrito.insertTail(user, cant, prod);
                else{
                    Lista ls = new Lista();
                    ls.insertTail(user, cant, prod);
                    tmp.carrito = ls;
                }
                Log.logger.info(" Agregado a usuario :" + user);
            } else {
                Log.logger.warn("Usuario no encontrado " + user);
            }
            
        }
    
    public String graphAdress(String user){
        NodoAvl tmp = root;
        String ret = "Usuario no encontrado";
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.direcciones != null)
                ret = tmp.direcciones.graphList();
        }
        return ret;
    }
    
    public String graphWhish(String user){
        NodoAvl tmp = root;
        String ret = "Usuario no encontrado";
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.whish != null)
                ret = tmp.whish.graphQueue(user);
        }
        return ret;
    }
    
    public String graphCarrito(String user){
        NodoAvl tmp = root;
        String ret = "Usuario no encontrado";
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.whish != null)
                ret = tmp.carrito.graphQueue(user);
        }
        return ret;
    }
    
    public boolean validateUser(String user, String pass){
                NodoAvl tmp = root;
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        return (tmp != null && user.equals(tmp.username)
                && pass.equals(tmp.password));
        
    }
    
    public String sendWhishInfo(String user){
        NodoAvl tmp = searchNode(user);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(tmp!= null){
            if(tmp.whish.root != null){
                Lista wTmp= tmp.whish;
                sb.append(wTmp.getProdCode()).append(";");
                sb.append(wTmp.getProdNames()).append(";");
                sb.append(wTmp.getProdPrice()).append(";");
                sb.append(wTmp.getProdCant()).append(";");
                sb.append(wTmp.getProdImage());
            }
        }
        return sb.toString();
    }
    
    public String sendCartInfo(String user){
        NodoAvl tmp = searchNode(user);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(tmp!= null){
            if(tmp.carrito.root != null){
                Lista wTmp= tmp.carrito;
                sb.append(wTmp.getProdCode()).append(";");
                sb.append(wTmp.getProdNames()).append(";");
                sb.append(wTmp.getProdPrice()).append(";");
                sb.append(wTmp.getProdCant()).append(";");
                sb.append(wTmp.getProdImage());
            }
        }
        return sb.toString();
    }
    
    public NodoAvl searchNode(String user){
        NodoAvl tmp = root;
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        return tmp;
    }
    

    private NodoAvl rotII(NodoAvl n1) {
        NodoAvl n2 = n1.izq;
        n1.izq = n2.der;
        n2.der = n1;
        n1.altura = mayor(altura(n1.izq), altura(n1.der)) + 1;
        n2.altura = mayor(altura(n2.izq), n1.altura) + 1;
        return n2;
    }

    private int altura(NodoAvl nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.altura;
        }
    }

    private int mayor(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        }
        return n2;
    }

    private NodoAvl ID(NodoAvl n1) {
        n1.izq = rotDD(n1.izq);
        return rotII(n1);
    }

    private NodoAvl rotDD(NodoAvl n1) {
        NodoAvl n2 = n1.der;
        n1.der = n2.izq;
        n2.izq = n1;
        n1.altura = mayor(altura(n1.izq), altura(n1.der)) + 1;
        n2.altura = mayor(altura(n2.der), n1.altura) + 1;
        return n2;
    }

}

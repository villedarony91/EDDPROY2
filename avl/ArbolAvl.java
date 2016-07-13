package avl;

import Utilities.Log;
import listascolas.*;

/**
 * Clase arbol AVL
 * @author rlopez
 */
public class ArbolAvl {

    private NodoAvl root;
    private int iter;

    public ArbolAvl() {
        root = null;
        iter = 0;
    }

    /**
     * Inserta un nuevo nodo en el arbol AVL
     * @param user nombre del ususario a insertar
     * @param pass contrasena
     */
    public void insert(String user, String pass) {
        root = insert(user, pass, root);
    }

    /**
     * nombra y recopila los datos recopila los datos de la grafica
     * @return grafica en formato GraphViz
     */
    public String apGraph() {
        if(root != null){
            root.nameAvl();
            return doBigGraph();
        }else{
        return "";
        }
    }

    /**
     * Rotacion derecha izquierda
     * @param n1 nodo del problema
     * @return nueva raiz de sub arbol balanceado
     */
    private NodoAvl rotDI(NodoAvl n1) {
        n1.der = rotII(n1.der);
        return rotDD(n1);
    }

    /**
     * Inserta un nuevo nodo en el arbol AVL
     * @param user nombre de usuario
     * @param pass password
     * @param raiz raiz del arbo
     * @return raiz del sub Arbol
     */
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
    
    /**
     * Inserta una direccion a determinado nodo del arbol avl 
     * @param user nombre de usuario
     * @param adress direccion 
     * @param env es de envio
     * @param fact es de facturacion
     */
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
    /**
     * inserta en la lista de deseos
     * @param user nombre de usuario
     * @param cant cantidad
     * @param prod producto
     */
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
    
    /**
     * Remueve de la lista de deseos
     * @param user nombre de usuario
     * @param code codigo del producto
     */
    public void removeFromWhish(String user, int code){
        NodoAvl tmp = searchNode(user);
        tmp.whish.deleteNode(code);
    }
    
    /**
     * remueve del carrito de compras
     * @param user nombre de usuario
     * @param code codigo de producto
     */
    public void removeFromCart(String user, int code){
        NodoAvl tmp = searchNode(user);
        tmp.carrito.deleteNode(code);
    }
    /**
     * inserta en el carrito de compras
     * @param user nombre de usuario
     * @param cant cantidad
     * @param prod producto
     */
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
    
    /**
     * envia grafica de las direcciones
     * @param user nombre de usuario
     * @return cadena con la grafica de las direcciones en formato GraphViz
     */
    public String graphAdress(String user){
        NodoAvl tmp = root;
        String ret = "";
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
    
    /**
     * Grafica avl con detalles de carrito, lista de deseos y direcciones
     * @return grafica en formato GraphViz
     */
    public String doBigGraph(){
        NodoAvl tmp = root;
        StringBuilder sb = new StringBuilder();
        sb.append("subgraph {");
        sb.append("node [shape = record];\n");
        sb.append(getLabelNodeAvl());
        sb.append("}");
        Log.logger.info(sb.toString());
        return sb.toString();
    }
    
    /**
     * Obtiene los nodos para la grafica del nodo avl
     * @return los nodos en formato graphViz
     */
    private String getLabelNodeAvl(){
        return concatLabel(this.root);
    }
    
    /**
     * Concatena las etiquetas para la grafica
     * @param tmp nodo desde el que se comenzara la busqueda
     * @return grafica en formato graphViz
     */
    private String concatLabel(NodoAvl tmp){
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.nom).append("[label = \"").append("<izq>|")
                .append(tmp.username).append("\\n").append(tmp.password)
                .append("|<der>")
                .append("\"];");
        if(tmp.carrito != null){sb.append(getCarrito(tmp));}
        if(tmp.whish != null){ sb.append(getWish(tmp));}
        if(tmp.direcciones != null){ sb.append(getAdress(tmp));}
        
        if(tmp.izq != null){
            sb.append(tmp.nom).append(":izq->").append(tmp.izq.nom).append(";\n");
            sb.append(concatLabel(tmp.izq));
        }
        if(tmp.der != null){
            sb.append(tmp.nom).append(":der->").append(tmp.der.nom).append(";\n");
            sb.append(concatLabel(tmp.der));
        }
        return sb.toString();
    }
    
    /**
     * grafica del carrito para grafica con detalle
     * @param tmp nodo del que se graficara el carrito
     * @return grafica en formato dot
     */
    private String getCarrito(NodoAvl tmp){
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.carrito.graphBigQueue(tmp.nom+"C","Carrito"));
        sb.append(tmp.nom).append("->").append(tmp.nom).append("C0")
                .append(";\n");
        return sb.toString();
    }
    
    /**
     * grafica de la lista de deseos
     * @param tmp nodo del que se graficara la lista de deseos
     * @return grafica en formato GraphViz
     */
    private String getWish(NodoAvl tmp){
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.whish.graphBigQueue(tmp.nom+"W","Whislist"));
        sb.append(tmp.nom).append("->").append(tmp.nom).append("W0")
                .append(";\n");
        return sb.toString();
    }
    
    /**
     * grafica de las direcciones
     * @param tmp nodo del que se graficaran las direcciones
     * @return grafica en formato GraphViz
     */
    private String getAdress(NodoAvl tmp){
        StringBuilder sb = new StringBuilder();
        sb.append(tmp.direcciones.graphBigList(tmp.nom+"Add"));
        sb.append(tmp.nom).append("->").append(tmp.nom).append("Add0")
                .append(";\n");
        return sb.toString();
    }
    
    /**
     * Grafica de la lista de los deseos individual
     * @param user usuario
     * @return grafica en formato graphViz
     */
    public String graphWhish(String user){
        NodoAvl tmp = root;
        String ret = "";
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
    
    /**
     * Grafica del carrito para mostrar individual
     * @param user nombre del usuario del que se obtendrá el carrito
     * @return grafica en formato graphViz
     */
    public String graphCarrito(String user){
        NodoAvl tmp = root;
        String ret = "";
        while (tmp != null && !tmp.username.equals(user)) {
            if (user.compareTo(tmp.username) < 0) {
                tmp = tmp.izq;
            } else {
                tmp = tmp.der;
            }
        }
        if (tmp != null && user.equals(tmp.username)) {
            if(tmp.carrito != null)
                ret = tmp.carrito.graphQueue(user);
        }
        return ret;
    }
    
    /**
     * Grafica carrito 
     * @param user
     * @return grafica del carrito en formato graphViz
     */
    public String idGraphQueue(String user){
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
            if(tmp.carrito != null)
                ret = tmp.carrito.graphQueue(user);
        }
        return ret;
    }
    
    /**
     * Valida si el usuario y contraseña son correctos
     * @param user nombre de usuario
     * @param pass contraseña 
     * @return verdadero si coinciden
     */
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
    
    /**
     * Envia informacion de la lista de deseos para se mostrada en el catalogo
     * @param user nombre de usuario
     * @return cadena para ser enviada al cliente
     */
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
    
    /**
     * Envía la información del carrito para ser consumida del lado del cliente
     * @param user nombre de usuario al que pertence el carrito
     * @return información del carrito a ser consumida por el cliente
     */
    public String sendCartInfo(String user){
        NodoAvl tmp = searchNode(user);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(tmp!= null){
            if(tmp.carrito != null && tmp.carrito.root != null){
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
    
    /**
     * Busca un nodo por medio del nombre de usuario
     * @param user nombre de usuario
     * @return nodo si fue encontrado, null, en caso contrario
     */
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
    
    /**
     * Rotacion izquierda izquierda
     * @param n1 nodo del problema
     * @return nueva raíz arreglada
     */
    private NodoAvl rotII(NodoAvl n1) {
        NodoAvl n2 = n1.izq;
        n1.izq = n2.der;
        n2.der = n1;
        n1.altura = mayor(altura(n1.izq), altura(n1.der)) + 1;
        n2.altura = mayor(altura(n2.izq), n1.altura) + 1;
        return n2;
    }
    
    /**
     * Obtiene la altura
     * @param nodo nodo a obtener su altura
     * @return altura
     */
    private int altura(NodoAvl nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.altura;
        }
    }
    
    /**
     *Obtiene el mayor entre dos números
     * @param n1 número uno 
     * @param n2 número dos
     * @return el numero mayor
     */
    private int mayor(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        }
        return n2;
    }
    
    /**
     * rotación izquierda derecha
     * @param n1 nodo del problema
     * @return nueva raíz corregida
     */
    private NodoAvl ID(NodoAvl n1) {
        n1.izq = rotDD(n1.izq);
        return rotII(n1);
    }
    
    /**
     * rotación derecha derecha
     * @param n1 nodo del problema
     * @return nueva raíz para insertar
     */
    private NodoAvl rotDD(NodoAvl n1) {
        NodoAvl n2 = n1.der;
        n1.der = n2.izq;
        n2.izq = n1;
        n1.altura = mayor(altura(n1.izq), altura(n1.der)) + 1;
        n2.altura = mayor(altura(n2.der), n1.altura) + 1;
        return n2;
    }
    
}

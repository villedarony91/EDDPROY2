
package avl;

import Utilities.*;
import listascolas.*;

/**
 * Clase de nodos para el arbol avl
 * @author rlopez
 */
public class NodoAvl {

    public String username;
    String password;
    Lista direcciones;
    public Lista whish;
    public Lista carrito;
    NodoAvl izq;
    NodoAvl der;  
    int altura;    
    public String nom;
    private static int correlativo=1;
    private final int id;    
    
    public NodoAvl(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.izq = null;
        this.der = null;
        this.id=correlativo++;        
    }
    /**
     * Insertar un nodo en el arbol
     * @param user nombre de usuario
     * @param pass contraseña
     */
    void insertar(String user, String pass) {
        if (user.compareTo(username) < 0) 
            if (izq == null) 
                izq = new NodoAvl(user,pass);
            else 
                izq.insertar(user,pass);
        else if (user.compareTo(username) > 0)
            
            if (der == null) 
                der = new NodoAvl(user,pass);            
            else
                der.insertar(user,pass);
        else
        Log.logger.error("Colision de valores duplicados en Arbol AVL "+user);
    }


    /**
     * Agrega un identificador unico a cada nodo, para ser utilizado en la 
     * grafica de graphViz
     */
    public void nameAvl(){
        this.nom = "avl"+id;
        Log.logger.info(this.nom+ " ** " +this.username);
        if(this.izq != null){
            this.izq.nameAvl();
        }
        if(this.der != null){
            this.der.nameAvl();
        }
    }
          
}

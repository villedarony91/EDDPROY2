
package avl;

import Utilities.*;
import listascolas.*;


public class NodoAvl {

    public String username;
    String password;
    Lista direcciones;
    public Lista whish;
    public Lista carrito;
    NodoAvl izq;
    NodoAvl der;  
    int altura;    
    private static int correlativo=1;
    private final int id;    
    
    public NodoAvl(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.izq = null;
        this.der = null;
        this.id=correlativo++;        
    }

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
            System.err.println("No se permiten los valores duplicados: \"" 
                    +  String.valueOf(user)+"\".");
    }
    public void graph(){
        Writer w = new Writer();
        w.write("treeGraph.dot", getGraphContent());
        w.compileDot("treeGraph");
    }

    public String getGraphContent() {
        return "digraph grafica{\n" +
               "rankdir=TB;\n" + 
               "node [shape = record];\n"+
                getNodes()+
                "}\n";
    }
    
    private String getNodes() {
        String name;
            name="nodo"+id+" [ label =\"<left>|"+username+"|<right>\"];\n";
            if(izq!=null){
            name=name + izq.getNodes() +
               "nodo"+id+":left->nodo"+izq.id+";\n";
        }
        if(der!=null){
            name=name + der.getNodes() +
               "nodo"+id+":right->nodo"+der.id+";\n";                    
        }
        return name;
    }        
}

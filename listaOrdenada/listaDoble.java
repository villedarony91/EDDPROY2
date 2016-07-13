/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaOrdenada;

/**
 *
 * @author otto_
 */
public class listaDoble {
    
    public NodoListaDoble primerNodo;
    public NodoListaDoble ultimoNodo;
    String idLista;
    int banderaCantidad;
    
    public listaDoble(String idLista){
        this.primerNodo = this.ultimoNodo = null;
        this.idLista = idLista;
    }
    
    public listaDoble(){
        this("listaDoble");
    }   
    
    public boolean estaVacia(){
        
        if(primerNodo == null){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    private void insertarALaCabeza(int cantidad, String nombre){
        
        if(estaVacia()){
            primerNodo = ultimoNodo = new NodoListaDoble(cantidad,nombre);
        }
        else{
            NodoListaDoble nuevo = new NodoListaDoble(cantidad,nombre);
            nuevo.sig = primerNodo;
            nuevo.ant = null;
            primerNodo.ant = nuevo;
            primerNodo = nuevo;
        }
        
    }
    
    private void insertarAlFinal(int cantidad, String nombre){
        
        if(estaVacia()){
            primerNodo = ultimoNodo = new NodoListaDoble(cantidad,nombre);
        }
        else{
            NodoListaDoble nuevo = new NodoListaDoble(cantidad,nombre);
            ultimoNodo.sig = nuevo;
            nuevo.ant = ultimoNodo;
            ultimoNodo = nuevo;
        }
    }
    
    public void insertar(String nombre, int cantidad){
        
        NodoListaDoble nuevo;
        NodoListaDoble auxiliar = primerNodo;
        NodoListaDoble anterior = auxiliar;
        
        if (!estaVacia()){
            
            if(buscar(nombre)!=null){
                NodoListaDoble actual = buscar(nombre);
                int actualCantidad = actual.cantidad + cantidad;
                eliminar(nombre);
                insertar(nombre,actualCantidad);
            }
            else{
                while (auxiliar != null && cantidad < auxiliar.cantidad  ){
                    anterior = auxiliar;
                    auxiliar = auxiliar.sig;
                }

                if (auxiliar == null){
                    this.insertarAlFinal(cantidad,nombre);
                } 
                else{
                    if (cantidad > auxiliar.cantidad || cantidad == auxiliar.cantidad){
                        if (auxiliar == primerNodo){
                            this.insertarALaCabeza(cantidad,nombre);
                        }
                        else{
                            nuevo = new NodoListaDoble(cantidad,nombre);
                            anterior.sig = nuevo;
                            nuevo.sig = auxiliar;
                            nuevo.ant = anterior;
                            auxiliar.ant = nuevo;
                        }
                    }
                }
            }

            
        }
        else{
            this.insertarALaCabeza(cantidad,nombre);
        }
        
        
    }
    
    void eliminarALaCabeza(){
       
       NodoListaDoble auxiliar = primerNodo;
        
       if(!estaVacia()){
           
           if (primerNodo == ultimoNodo){
               primerNodo = ultimoNodo = null;
           }
           else{
               primerNodo.sig.ant = null;
               primerNodo = primerNodo.sig;
               auxiliar.sig = null;
               auxiliar.ant = null;
               auxiliar = null;
           }
           
       }
              
   }
   
    void eliminarAlFinal(){
       
       NodoListaDoble auxiliar = ultimoNodo;
        
       if(!estaVacia()){
           
           if (primerNodo == ultimoNodo){
               primerNodo = ultimoNodo = null;
           }
           else{
               ultimoNodo.ant.sig = null;
               ultimoNodo = ultimoNodo.ant;
               auxiliar.ant = null;
               auxiliar.sig = null;
               auxiliar = null;
           }
           
           
       }
       
              
   }
    
    public void eliminar(String nombre){
        
        NodoListaDoble auxiliar = primerNodo;
        
        if(!estaVacia()){
            if(nombre.toLowerCase().compareTo(auxiliar.nombre.toLowerCase()) == 0){
                eliminarALaCabeza();
            }
            else{
                while(auxiliar != null && nombre.toLowerCase().compareTo(auxiliar.nombre.toLowerCase()) != 0 ){
                    auxiliar = auxiliar.sig;
                }
                
                if (auxiliar == null){
                    System.out.println("El usuario que desea eliminar no existe");
                }
                else{
                    
                    if (auxiliar == ultimoNodo){
                        eliminarAlFinal();
                    }
                    else{
                        
                        auxiliar.ant.sig = auxiliar.sig;
                        auxiliar.sig.ant = auxiliar.ant;
                        auxiliar.sig = null;
                        auxiliar.ant = null;
                        auxiliar = null;
                        
                    }
                    
                }
            }
        }
        else{
            System.out.println("El usuario que desea eliminar no existe");
        }
        
    }
    
    /*
    public void actulizar(String nombre, String nuevoNombre, String nuevoContraseña){
        
        NodoListaDoble auxiliar = primerNodo;
        listaSimple listaSimple;
        
        
        if(!estaVacia()){
            
                     
            while( auxiliar != null && nombre.toLowerCase().compareTo(auxiliar.nombre.toLowerCase()) != 0 ){
                auxiliar = auxiliar.sig;
            }
            
            if(auxiliar == null){
                System.out.println("El usuario que desea actualizar no existe");
            }
            else{
                if(nombre.toLowerCase().compareTo(auxiliar.nombre.toLowerCase()) == 0){
                    listaSimple = auxiliar.listaSimple;
                    eliminar(nombre);
                    insertar(nuevoNombre,nuevoContraseña,listaSimple);
                }
            }
            
        }
        else{
            System.out.println("El usuario que desea actualizar no existe");
        }
        
    }
    */
    
    
    public void imprimir(){
        
        if (!estaVacia()){
            
            NodoListaDoble auxiliar = primerNodo;
            
            while (auxiliar != null ){
                System.out.println("Nombre: " + auxiliar.nombre + " Cantidad: "+ auxiliar.cantidad);
                auxiliar = auxiliar.sig;
            }
            
        }
        
    }
    
    public NodoListaDoble buscar(String nombre){
        
        NodoListaDoble respuesta = null;
        
        if (!estaVacia()){
            
            NodoListaDoble auxiliar = primerNodo;
            
            while (auxiliar != null ){
                
                
                if (nombre.equals(auxiliar.nombre)){
                    respuesta =  auxiliar;
                }
                
                auxiliar = auxiliar.sig;
            }
                        
            return respuesta;
            
        }
        else{
            return respuesta;
        }
        
    }
    
    public String imprimirGraphviz(){
        
        String cuerpo = "";
        
        if (!estaVacia()){
            
            NodoListaDoble auxiliar = primerNodo;
            
            cuerpo = cuerpo + "subgraph cluster_01{\n";
    
            while (auxiliar != null ){
                cuerpo = cuerpo + "node" + idLista + "" + auxiliar.nombre + " [label=\" Nombre: " + auxiliar.nombre  + "\n cantidad: "+ auxiliar.cantidad+" \"];\n"; 
                
                if(auxiliar != primerNodo){
                    cuerpo = cuerpo + "node" + idLista + "" + auxiliar.nombre  + "->" + "node" + idLista + "" + auxiliar.ant.nombre + ";\n";
                }
                if (auxiliar.sig != null){
                    cuerpo = cuerpo + "node" + idLista + "" + auxiliar.nombre + "->" + "node" + idLista + "" + auxiliar.sig.nombre + ";\n";
                }
                
                
                
                auxiliar = auxiliar.sig;
            }
            
           cuerpo = cuerpo + "}\n";
            
        }
        
        return cuerpo;
        
    }
}
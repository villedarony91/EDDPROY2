/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddarbolb;
import Utilities.Log;
import java.io.*;
import avl.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import listascolas.*;
import listaOrdenada.*;

/**
 *
 * @author otto_
 */
public class ArbolB {
    public Pagina raiz;
    int idPagina;
    
    private Pagina arriba;
    /*
    private int k;
    private int indice;
    private int sube;
    private int claveMedia;
    private Pagina nuevo;
    */
    
    
    public ArbolB(){
        raiz = null;        
    }
    
    public boolean buscarEnPagina(Pagina actual, int clave){
        boolean existe =false;
        int i;
        
        if(clave < actual.claves[0]){
            
            if (actual.ramas[0] != null){
                return (buscarEnPagina(actual.ramas[0],clave));
            }
            return false;            
        }
        else{
            
            i = actual.cuenta - 1;
            
            //Si la clave a buscar es menor que las claves de la pagina y hay elementos hace el while
            while((clave <= actual.claves[i]) && i > 0){
                
                //si la clave a buscar es igual a la clave de la pagina actula, devuelve que la clave existe
                if(clave == actual.claves[i]){
                    existe = true;
                }
                i--;
            }
            
            if(clave == actual.claves[i]){
                    existe = true;
            }
            
            if(clave > actual.claves[i]){
                if(actual.ramas[i+1] != null){
                    return (buscarEnPagina(actual.ramas[i+1],clave));
                }
                return false;
            }
        }
        return existe;                
    }

    public boolean existeClave(int clave){
        Pagina actual;
        actual = raiz;
        boolean existe;
        
        if(actual == null){
            
            return false;
        }
        else{
            
            existe = buscarEnPagina(actual,clave);
            return existe;
        }
        
    }
    
    public Pagina recorrerArbol(Pagina actual, int clave){
        int i;
        
        if(clave < actual.claves[0]){
            
            if (actual.ramas[0] != null){
                arriba = actual;
                return (recorrerArbol(actual.ramas[0],clave));
            }
            return actual;            
        }
        else{
            
            i = actual.cuenta - 1;
            
            //Si la clave a buscar es menor que las claves de la pagina y hay elementos hace el while
            while((clave <= actual.claves[i]) && i > 0){
                
                //si la clave a buscar es igual a la clave de la pagina actual, devuelve que la clave existe
                if(clave == actual.claves[i]){
                    arriba = actual;
                    return actual;
                }
                i--;
            }
            
            if(clave == actual.claves[i]){
                arriba = actual;
                return actual;
            }
            
            if(clave > actual.claves[i]){
                
                if(actual.ramas[i+1] != null){
                    arriba = actual;
                    return (recorrerArbol(actual.ramas[i+1],clave));
                }
                arriba = actual;
                return actual;
            }
        }
        arriba = actual;
        return actual; 
    }
       
    public void insertarEnPaginaHoja(Pagina actual, int clave, Factura factura){
        int[] auxClaves = new int[actual.m];
            Factura[] auxFacturas = new Factura[actual.m];
        int indiceActual;
        
        if(clave < actual.claves[0]){
            
            auxClaves[0] = clave;
                auxFacturas[0] = factura;
            for(int i=0; i < (actual.cuenta);i++){
                auxClaves[i+1] = actual.claves[i];
                    auxFacturas[i+1] = actual.facturas[i];
            }
            actual.claves = auxClaves;
                actual.facturas = auxFacturas;
        }
        else{
            
            indiceActual = actual.cuenta - 1;
            while ( clave < actual.claves[indiceActual] && indiceActual > 0){
                indiceActual--;
            }
            
            if(clave > actual.claves[indiceActual]){
                
                for(int i=0; i <= indiceActual;i++){
                    auxClaves[i] = actual.claves[i];
                        auxFacturas[i] = actual.facturas[i]; 
                }
                auxClaves[indiceActual+1] = clave;
                    auxFacturas[indiceActual+1] = factura;               
                
                if(actual.cuenta > 1 && indiceActual != (actual.cuenta - 1)){
                    
                    for(int i=indiceActual +1; i < actual.cuenta;i++){
                        auxClaves[i+1] = actual.claves[i];  
                            auxFacturas[i+1] = actual.facturas[i]; 
                    }
                   
                }

            }
            actual.claves = auxClaves;
                actual.facturas = auxFacturas;
        }
        
        
    }
    
    public int insertarEnPagina(Pagina actual, int clave, Factura factura){
        int indice = 0;
        int[] auxClaves = new int[actual.m];
        Factura[] auxFacturas = new Factura[actual.m];
        int indiceActual;
        
        if(clave < actual.claves[0]){
            
            auxClaves[0] = clave;
                auxFacturas[0] = factura;
            indice =  0;
            for(int i=0; i < (actual.cuenta);i++){
                auxClaves[i+1] = actual.claves[i];
                    auxFacturas[i+1] = actual.facturas[i];
            }
            actual.claves = auxClaves;
                actual.facturas = auxFacturas;
        }
        else{
            
            indiceActual = actual.cuenta - 1;
            while ( clave < actual.claves[indiceActual] && indiceActual > 0){
                indiceActual--;
            }
            
            if(clave > actual.claves[indiceActual]){
                
                for(int i=0; i <= indiceActual;i++){
                    auxClaves[i] = actual.claves[i];
                        auxFacturas[i] = actual.facturas[i];
                }
                auxClaves[indiceActual+1] = clave;
                    auxFacturas[indiceActual+1] = factura;
                indice =  indiceActual+1;
                               
                
                if(actual.cuenta > 1 && indiceActual != (actual.cuenta - 1)){
                    
                    for(int i=indiceActual +1; i < actual.cuenta;i++){
                        auxClaves[i+1] = actual.claves[i];  
                            auxFacturas[i+1] = actual.facturas[i];
                    }
                   
                }

            }
            actual.claves = auxClaves;
            actual.facturas = auxFacturas;
            
        }
        return indice;
    }
    
    public void paginaAnterior(Pagina actual, Pagina auxiliar){
               
        if(auxiliar == actual){
            
            arriba = auxiliar;
        }
        else{
            
            for(int i = 0; i <= auxiliar.cuenta;i++){
                
                if(auxiliar.ramas[i] == actual){
                    
                    arriba = auxiliar;
                }
                else{
                      if(auxiliar.ramas[i] != null){

                        paginaAnterior(actual,auxiliar.ramas[i]); 
                    }  
                }
                
            }
            
        }  
    }
    
    
    public void SubirClave(int claveMedia,Factura facturaMedia, Pagina pagIzq, Pagina pagDer){
        
        paginaAnterior(pagIzq,raiz);
        
        Pagina auxiliar =  arriba;
        
        Pagina[] auxRamas = new Pagina[5];
        
        int indice;
    
    if (auxiliar != null){ 
        
        if(pagIzq == auxiliar){
            Pagina nuevaRaiz = new Pagina();
            nuevaRaiz.claves[0] = claveMedia;
                nuevaRaiz.facturas[0] = facturaMedia;
            nuevaRaiz.ramas[0] = pagIzq;
            nuevaRaiz.ramas[1] = pagDer;
            nuevaRaiz.cuenta = 1;
            raiz = nuevaRaiz;
            
        }
        else{
            
            if(auxiliar.nodoLleno(auxiliar)){
              
              indice = insertarEnPagina(auxiliar,claveMedia,facturaMedia);
              
              divirPaginaIntermedia(auxiliar,indice,pagIzq,pagDer);
              
                            
            }
            else{
                
                indice = insertarEnPagina(auxiliar,claveMedia,facturaMedia);
                
                auxiliar.cuenta++;
                if(indice == 0){
                    auxRamas[0] = pagIzq;
                    auxRamas[1] = pagDer;
                    for(int i = 1; i <= auxiliar.cuenta-1;i++){
                        
                        auxRamas[i+1] = auxiliar.ramas[i];
                    }
                    auxiliar.ramas = auxRamas;
                }
                if(indice == (auxiliar.cuenta - 1)){
                    
                    for(int i = 0; i < indice ;i++){
                        auxRamas[i] = auxiliar.ramas[i];
                    }
                    auxRamas[indice] = pagIzq;
                    auxRamas[indice+1] = pagDer;
                    auxiliar.ramas = auxRamas;
                }
                if(indice > 0 && indice < (auxiliar.cuenta - 1)){
                    
                    for(int i = 0; i < indice;i++){
                        auxRamas[i] = auxiliar.ramas[i];
                    }
                    auxRamas[indice] = pagIzq;
                    auxRamas[indice +1] = pagDer;
                    for(int i = indice + 1; i <= auxiliar.cuenta-1;i++){
                        auxRamas[i+1] = auxiliar.ramas[i];
                    }
                    auxiliar.ramas = auxRamas;
                }
                
            }
            
        }
    }
    }
    
    public void divirPaginaIntermedia(Pagina actual,int indice, Pagina Izq,Pagina Der){
        int claveMedia;
        Factura facturaMedia;
        Pagina[] auxRamas = new Pagina[actual.m];
        Pagina pagDer = new Pagina();
        this.idPagina++;
        pagDer.idpagina = this.idPagina;
        
        if(indice == 0){
            
            auxRamas[0] = Izq;
            auxRamas[1] = Der;
            auxRamas[2] = actual.ramas[1];
            auxRamas[3] = null;
            auxRamas[4] = null;
            
            
            pagDer.ramas[0] = actual.ramas[2];
            pagDer.ramas[1] = actual.ramas[3];
            pagDer.ramas[2] = actual.ramas[4];

            actual.ramas = auxRamas;
            
            
        }
        
        
        
        if(indice == (actual.cuenta)){
            
            auxRamas[0] = actual.ramas[0];
            auxRamas[1] = actual.ramas[1];
            auxRamas[2] = actual.ramas[2];
            auxRamas[3] = null;
            auxRamas[4] = null;
            
            pagDer.ramas[0] = actual.ramas[3];
            pagDer.ramas[1] = Izq;
            pagDer.ramas[2] = Der;

            
            actual.ramas = auxRamas;
            
            
        }
        if(indice > 0 && indice < (actual.cuenta)){
            
            if(indice < 2){
                auxRamas[0] = actual.ramas[0];
                auxRamas[1] = Izq;
                auxRamas[2] = Der;
                auxRamas[3] = null;
                auxRamas[4] = null;

                pagDer.ramas[0] = actual.ramas[2];
                pagDer.ramas[1] = actual.ramas[3];
                pagDer.ramas[2] = actual.ramas[4];

                actual.ramas = auxRamas;
            }
            if(indice > 2){
                
                auxRamas[0] = actual.ramas[0];
                auxRamas[1] = actual.ramas[1];
                auxRamas[2] = actual.ramas[2];
                auxRamas[3] = null;
                auxRamas[4] = null;

                pagDer.ramas[0] = Izq;
                pagDer.ramas[1] = Der;
                pagDer.ramas[2] = actual.ramas[4];

                actual.ramas = auxRamas;

            }
            if(indice == 2){
                
                auxRamas[0] = actual.ramas[0];
                auxRamas[1] = actual.ramas[1];
                auxRamas[2] = Izq;
                auxRamas[3] = null;
                auxRamas[4] = null;

                pagDer.ramas[0] = Der;
                pagDer.ramas[1] = actual.ramas[3];
                pagDer.ramas[2] = actual.ramas[4];

                actual.ramas = auxRamas;
            }
            
        }
        
        for(int i = 2+1; i < 5; i++){
            pagDer.claves[i-3] = actual.claves[i];
                pagDer.facturas[i-3] = actual.facturas[i];
            actual.claves[i] = 0;
                actual.facturas[i] = null;
        }
        actual.cuenta = 3;
        pagDer.cuenta = 2;
        
        claveMedia = actual.claves[actual.cuenta-1];
            facturaMedia = actual.facturas[actual.cuenta-1];
        actual.claves[actual.cuenta-1] = 0;
            actual.facturas[actual.cuenta-1] = null;
        actual.cuenta = actual.cuenta - 1; 
        
                
        
        
        
        SubirClave(claveMedia,facturaMedia,actual,pagDer);
        
    }
    
    
    public void divirPagina(Pagina actual){
        int claveMedia;
        Factura facturaMedia;
        Pagina pagDer = new Pagina();
        this.idPagina++;
        pagDer.idpagina = this.idPagina;
        
        for(int i = 2+1; i < 5; i++){
            
            pagDer.claves[i-3] = actual.claves[i];
                pagDer.facturas[i-3] = actual.facturas[i];
            actual.claves[i] = 0;
                actual.facturas[i] = null;
            pagDer.ramas[i-3] = actual.ramas[i];
            actual.ramas[i] = null;
            
        }
        actual.cuenta = 3;
        pagDer.cuenta = 2;
        
        claveMedia = actual.claves[actual.cuenta-1];
            facturaMedia = actual.facturas[actual.cuenta-1];
        actual.claves[actual.cuenta-1] = 0;
            actual.facturas[actual.cuenta-1] = null;
        actual.cuenta = actual.cuenta - 1; 
        
        
        
        
        SubirClave(claveMedia,facturaMedia,actual,pagDer);
        
    }
    /**
     * insertar
     * @param numFactura
     * @param fecha
     * @param total
     * @param usuario
     * @param detalle 
     */
    public void Insertar(int numFactura,String fecha,double total,NodoAvl usuario, Lista detalle){
        
        Pagina nuevo,actual;
        
        
        Nodo auxiliar = detalle.root;
        Lista carrito = new Lista();
        while (auxiliar != null){
            carrito.insertTail(usuario.username, auxiliar.cant, auxiliar.prod);           
            auxiliar = auxiliar.next;
        } 
        
        Factura factura = new Factura(fecha,total,usuario,carrito);
        
        if(raiz == null){
            nuevo = new Pagina();
            this.idPagina++;
            nuevo.idpagina = this.idPagina;
            nuevo.cuenta = 1;
            nuevo.claves[0] = numFactura;
            nuevo.facturas[0] = factura;
            nuevo.ramas[0] = nuevo.ramas[1] = nuevo.ramas[2] = nuevo.ramas[3] = nuevo.ramas[4] = null;
            raiz = nuevo;
        }
        else{
            
            
            if(existeClave(numFactura)){
                System.out.println("Numero de factura duplicado: "+numFactura);
            }
            else{
                
                actual = recorrerArbol(raiz,numFactura);
                if(actual.nodoLleno(actual)){

                    insertarEnPaginaHoja(actual,numFactura,factura);
                   
                    divirPagina(actual);
                    
                }
                else{
                    
                    insertarEnPaginaHoja(actual,numFactura,factura);
                    
                    actual.cuenta = actual.cuenta +1;
                }
                
                
            }
        }
        
        
    }
    
       public void Insertar(int numFactura,String fecha,double total,NodoAvl usuario){

        if(usuario == null){
            Log.logger.error("Usuario no encontrado");
            return;
        }
        
        Pagina nuevo,actual;
        
        Factura factura = new Factura(fecha,total,usuario,null);
        
        if(raiz == null){
            nuevo = new Pagina();
            this.idPagina++;
            nuevo.idpagina = this.idPagina;
            nuevo.cuenta = 1;
            nuevo.claves[0] = numFactura;
            nuevo.facturas[0] = factura;
            nuevo.ramas[0] = nuevo.ramas[1] = nuevo.ramas[2] = nuevo.ramas[3] = nuevo.ramas[4] = null;
            raiz = nuevo;
        }
        else{
            if(existeClave(numFactura)){
                Log.logger.warn("Numero de factura duplicado: "+numFactura);
                System.out.println("Numero de factura duplicado: "+numFactura);
            }
            else{
                
                actual = recorrerArbol(raiz,numFactura);
                if(actual.nodoLleno(actual)){

                    insertarEnPaginaHoja(actual,numFactura,factura);
                   
                    divirPagina(actual);
                    
                }
                else{
                    
                    insertarEnPaginaHoja(actual,numFactura,factura);
                    
                    actual.cuenta = actual.cuenta +1;
                }
                
                
            }
        }
        
        
    }
    
    
    

// GRAFICAR ARBOL B
    
    public String imprimirDetalleGraphviz(Lista lista,String cabecera){
        String cuerpo = "";
        
        if (lista.root != null){
            
            Nodo auxiliar = lista.root;
            
            cuerpo = cuerpo + "\n subgraph cluster_0"+ cabecera +"{\n";
            
    
            while (auxiliar != null ){
                cuerpo = cuerpo + "nodo" + cabecera + "" + auxiliar.nodeId + " [label=\" Producto: " + auxiliar.prod.name + " \\nPrecio: " + auxiliar.prod.price + "\\n Cantidad: " + auxiliar.cant + "\"];\n"; 
                
                if (auxiliar.next != null){
                    cuerpo = cuerpo + "nodo" + cabecera + "" + auxiliar.nodeId + "->" + "nodo" + cabecera + "" + auxiliar.next.nodeId + ";\n";
                }
                
                auxiliar = auxiliar.next;
            }
            
           cuerpo = cuerpo + "}\n";
            
        }
        
        return cuerpo;
    }
            
    public String imprimirGraphviz(Pagina actual,String cuerpo){
        
        int j=0;
        String cadena = cuerpo;
        String listas = "";
        String punteros ="";
        
        if(actual != null){
            cadena = cadena + "\n ";
            cadena = cadena + "nodoArbolB"+ actual.claves[0] +" [shape = record, label= \" <f0> ";
            for (int i = 0; i<4; i++){
                j++;
                if(actual.claves[i] > 0){
                    if(i != 4){
                      cadena = cadena + "| <f"+(i+j)+"> Factura: " + actual.claves[i] + " \\n Fecha: "+ actual.facturas[i].fecha + " \\n Total: "+ actual.facturas[i].total + " \\n Usuario: "+ actual.facturas[i].usuario.username+"|<f"+((i+j)+1)+">";   
                      if(actual.facturas[i].detalle != null){
                          listas = listas + "\"nodoArbolB"+actual.claves[0]+"\":f"+(i+j)+" -> " + "nodoArbolB" + actual.claves[i] + actual.facturas[i].detalle.root.nodeId+";";
                          listas = listas + imprimirDetalleGraphviz(actual.facturas[i].detalle,"ArbolB" + actual.claves[i] );
                      }
                                
                    }
                    else{
                        cadena = cadena + " | <f"+(i+j)+"> Factura: " + actual.claves[i] + " \\n Fecha: "+ actual.facturas[i].fecha + " \\nTotal: "+ actual.facturas[i].total + " \\nw Usuario: "+ actual.facturas[i].usuario.username+"";   
                        if(actual.facturas[i].detalle != null){
                          listas = listas + "\"nodoArbolB"+actual.claves[0]+"\":f"+(i+j)+" -> " + "nodoArbolB" + actual.claves[i] + actual.facturas[i].detalle.root.nodeId+";";
                          listas = listas + imprimirDetalleGraphviz(actual.facturas[i].detalle,"ArbolB" + actual.claves[i] );
                      }
                    } 
                }  
            }
            cadena = cadena +"\"]; \n";
            cadena = cadena +"\n";
            
            for (int i = 0; i<5; i++){
                if(actual.ramas[i]!=null){
                    punteros = punteros + "\"nodoArbolB"+actual.claves[0]+"\":f"+i*2+" -> \"nodoArbolB"+ actual.ramas[i].claves[0] +"\":f2; \n";
                    punteros = punteros + imprimirGraphviz(actual.ramas[i],cadena);
                }
            }
            
            cadena = cadena + punteros + listas;
            
        }
        return cadena;
    }

    public String graficarArolbB(ArbolB arbol){
        
        String dot;
        
        dot = "digraph arbolB{\n";
        //dot = dot + "splines=ortho;";
        dot = dot +"splines=polyline";
        dot = dot + arbol.imprimirGraphviz(arbol.raiz,"");
        dot = dot + "} \n";
        
        return dot;
    }    
    // ------ NUMERO FACTURA ----
    
    public int numeroDeFactura(){
        
        int numeroFactura;
        
        Random random = new Random();
        numeroFactura = (int) (random.nextDouble() * 50000000+ 1);
            
        while (numeroFactura == 0 || existeClave(numeroFactura) == true){
            numeroFactura = (int) (random.nextDouble() * 50000000+ 1);
        }
        
        return numeroFactura;
        
        
    }
    
    // ------ CARGA MASIVA DETALLE -----
    //Metodos para insertar detalle factura
    public Pagina buscarPagina(int numFactura, Pagina actual){
        
        
        int i;
        
        if(numFactura < actual.claves[0]){
            
            if (actual.ramas[0] != null){
                
                return (recorrerArbol(actual.ramas[0],numFactura));
            }           
        }
        else{
            
            i = actual.cuenta - 1;
            
            //Si la clave a buscar es menor que las claves de la pagina y hay elementos hace el while
            while((numFactura <= actual.claves[i]) && i > 0){
                
                //si la clave a buscar es igual a la clave de la pagina actual, devuelve que la clave existe
                if(numFactura == actual.claves[i]){
                    
                    return actual;
                }
                i--;
            }
            
            if(numFactura == actual.claves[i]){
                
                return actual;
            }
            
            if(numFactura > actual.claves[i]){
                
                if(actual.ramas[i+1] != null){
                    
                    return (recorrerArbol(actual.ramas[i+1],numFactura));
                }

            }
        }
        
        return null; 
        
    }
    
    
    
 public void insertarDetalle(int numFactura, int cantidad, double precio, Producto producto){

        Pagina pagina = buscarPagina(numFactura,this.raiz); //Busca la pagina en la que se encuentra el numero de Factura
        
        
        
        if(pagina != null){
            for (int i = 0; i < pagina.cuenta;i++){
                if(pagina.claves[i] == numFactura){
                    if(pagina.facturas[i].detalle !=null && producto != null){
                        pagina.facturas[i].detalle.insertDetalle(cantidad,precio,producto);
                        pagina.facturas[i].total = pagina.facturas[i].total + (precio * cantidad);
                    }
                    else{
                        if(producto == null){
                            Log.logger.warn("Producto no existe");
                        }
                        else{
                            Lista lista = new Lista();
                            lista.insertDetalle(cantidad,precio,producto);
                            pagina.facturas[i].detalle = lista;
                            pagina.facturas[i].total = pagina.facturas[i].total + (precio * cantidad);
                        }
                    }
                }
            }
        }
        else{
            Log.logger.error("No existe factura, insersion de detalle fallida");
        }
        
    }
    
    // ------- REPORTES ----------
    
        
    private String facturasPorFecha(String fechaIni, String fechaFin, String cuerpo, Pagina actual) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            cuerpo = cuerpo + facturasPorFecha(fechaIni,fechaFin,cuerpo,actual.ramas[0]);
            for (int j= 0; j < (actual.cuenta); j++){
                
                fechaActual = formato.parse(actual.facturas[j].fecha);
                
                if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){
                    
                    cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                }
                if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){
                    
                    cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                }
                
                cuerpo = cuerpo + facturasPorFecha(fechaIni,fechaFin,cuerpo,actual.ramas[j+1]);   
            }
        }
        return cuerpo;
    }
        
    private String facturasPorValor(double valor, Pagina actual, String cuerpo){
         if(actual != null){
            cuerpo = cuerpo + facturasPorValor(valor,actual.ramas[0],cuerpo);
            for (int j= 0; j < (actual.cuenta); j++){
                
                                
                if(valor == actual.facturas[j].total){
                    
                    cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                }
                
                cuerpo = cuerpo + facturasPorValor(valor,actual.ramas[j+1],cuerpo);
            }
        }
        return cuerpo;
    }
        
    private String facturasPorUsuario(NodoAvl usuario, Pagina actual, String cuerpo){
         if(actual != null){
            cuerpo = cuerpo + facturasPorUsuario(usuario,actual.ramas[0],cuerpo);
            for (int j= 0; j < (actual.cuenta); j++){
                
                
                if(usuario == actual.facturas[j].usuario){
                    
                    cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                }
                
                cuerpo = cuerpo + facturasPorUsuario(usuario,actual.ramas[j+1],cuerpo);
            }
        }
        return cuerpo;
    }
    
    private String facturasPorValorYUsuarios(double valor,NodoAvl usuario, Pagina actual, String cuerpo){
        
        if(actual != null){
            cuerpo = cuerpo + facturasPorValorYUsuarios(valor, usuario,actual.ramas[0],cuerpo);
            for (int j= 0; j < (actual.cuenta); j++){
                
                
                if(valor == actual.facturas[j].total && usuario == actual.facturas[j].usuario){
                    
                    cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                }
                
                cuerpo = cuerpo + facturasPorValorYUsuarios(valor, usuario,actual.ramas[j+1],cuerpo);
            }
        }
        return cuerpo;
    }
    
    private String facturasPorFechaYUsuarios(NodoAvl usuario,String fechaIni, String fechaFin, String cuerpo, Pagina actual) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            cuerpo = cuerpo + facturasPorFechaYUsuarios(usuario,fechaIni,fechaFin,cuerpo,actual.ramas[0]);
            for (int j= 0; j < (actual.cuenta); j++){
                
                fechaActual = formato.parse(actual.facturas[j].fecha);
                
                if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){
                    
                    if(usuario == actual.facturas[j].usuario){
                    
                        cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                    }
                }
                if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){
                    
                    if(usuario == actual.facturas[j].usuario){
                    
                        cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                    }
                }
                
                cuerpo = cuerpo + facturasPorFechaYUsuarios(usuario,fechaIni,fechaFin,cuerpo,actual.ramas[j+1]);   
            }
        }
        return cuerpo;
    }
    
    private String facturasPorFechaYValor(double valor,String fechaIni, String fechaFin, String cuerpo, Pagina actual) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            cuerpo = cuerpo + facturasPorFechaYValor(valor,fechaIni,fechaFin,cuerpo,actual.ramas[0]);
            for (int j= 0; j < (actual.cuenta); j++){
                
                fechaActual = formato.parse(actual.facturas[j].fecha);
                
                if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){
                    
                    if(valor == actual.facturas[j].total){

                        cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                    }
                }
                if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){
                    
                    if(valor == actual.facturas[j].total){

                        cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                    }
                }
                
                cuerpo = cuerpo + facturasPorFechaYValor(valor,fechaIni,fechaFin,cuerpo,actual.ramas[j+1]);   
            }
        }
        return cuerpo;
    }
    
    private String facturasPorTodo(NodoAvl usuario,double valor,String fechaIni, String fechaFin, String cuerpo, Pagina actual) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            cuerpo = cuerpo + facturasPorTodo(usuario,valor,fechaIni,fechaFin,cuerpo,actual.ramas[0]);
            for (int j= 0; j < (actual.cuenta); j++){
                
                fechaActual = formato.parse(actual.facturas[j].fecha);
                
                if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){
                    
                    if(valor == actual.facturas[j].total){

                        if(usuario == actual.facturas[j].usuario){
                    
                            cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                        }
                    }
                }
                if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){
                    
                    if(valor == actual.facturas[j].total){

                        if(usuario == actual.facturas[j].usuario){
                    
                            cuerpo = cuerpo + "Factura: "+ actual.claves[j] +",Fecha: "+actual.facturas[j].fecha +",Total: "+actual.facturas[j].total +",Usuario: "+actual.facturas[j].usuario.username+";";
                        }
                    }
                }
                
                cuerpo = cuerpo + facturasPorTodo(usuario,valor,fechaIni,fechaFin,cuerpo,actual.ramas[j+1]);   
            }
        }
        return cuerpo;
    }
    
    public String reporte1(int f,int v, int u, String fechaIni, String fechaFin, double valor, NodoAvl usuario) throws ParseException{
        String reporte = "";
        
        if(f == 0 && v == 0 && u == 0){
            //no se puede crear reporte se debe de ingresar algun valor
            reporte = "";
        }
        if(f == 0 && v == 0 && u == 1){
            reporte = facturasPorUsuario(usuario,this.raiz,"");
        }
        if(f == 0 && v == 1 && u == 0){
            reporte = facturasPorValor(valor,this.raiz,"");
        }
        if(f == 0 && v == 1 && u == 1){
            reporte = facturasPorValorYUsuarios(valor,usuario,this.raiz,"");
        }
        if(f == 1 && v == 0 && u == 0){
            reporte = facturasPorFecha(fechaIni,fechaFin,"",this.raiz);
        }
        if(f == 1 && v == 0 && u == 1){
            reporte = facturasPorFechaYUsuarios(usuario,fechaIni,fechaFin,"",this.raiz);
        }
        if(f == 1 && v == 1 && u == 0){
            reporte = facturasPorFechaYValor(valor,fechaIni,fechaFin,"",this.raiz);
        }
        if(f == 1 && v == 1 && u == 1){
            reporte = facturasPorTodo(usuario,valor,fechaIni,fechaFin,"",this.raiz);
        }
        
        return reporte;
    }
     //------- REPORTE3 ----------
    
    private void recorridoReporte3(String fechaIni, String fechaFin, Pagina actual, listaDoble lista) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            recorridoReporte3(fechaIni,fechaFin,actual.ramas[0],lista);
            for (int j= 0; j < (actual.cuenta); j++){
                
                if (actual.facturas[j]!=null){
                    
                    fechaActual = formato.parse(actual.facturas[j].fecha);

                    if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){

                      lista.insertar(actual.facturas[j].usuario.username,1);

                    }
                    if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){

                        lista.insertar(actual.facturas[j].usuario.username,1);
                    }

                    recorridoReporte3(fechaIni,fechaFin,actual.ramas[j+1],lista);    
                }
                   
            }
        }
        
        
    }
    
    
    public String reporte3(String fechaInicial, String fechaFinal){
        try {
            String cuerpo = "";
            
            listaDoble lista = new listaDoble();
            recorridoReporte3(fechaInicial,fechaFinal,this.raiz,lista);
            
            //recorrer la lista y meterla a un string;
            
            if (!lista.estaVacia()){
                
                NodoListaDoble auxiliar =  lista.primerNodo;
                
                while (auxiliar != null ){
                    cuerpo = cuerpo + "Nombre: " + auxiliar.nombre + ",Cantidad: "+ auxiliar.cantidad+";";
                    auxiliar = auxiliar.sig;
                }
                
            }
            
            
            return cuerpo;
        } catch (ParseException ex) {
            Logger.getLogger(ArbolB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
     // ------- REPORTE2 ----------
    
    private void recorridoReporte2(String fechaIni, String fechaFin, Pagina actual, listaDoble lista) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date fechaActual;
        Date fechaInicial = formato.parse(fechaIni);
        Date fechaFinal = formato.parse(fechaFin);
        
        if(actual != null){
            recorridoReporte2(fechaIni,fechaFin,actual.ramas[0],lista);
            for (int j= 0; j < (actual.cuenta); j++){
                
                if (actual.facturas[j]!=null){
                    
                    if (actual.facturas[j].detalle !=null){
                        
                        fechaActual = formato.parse(actual.facturas[j].fecha);

                        if( fechaActual.after(fechaInicial) && fechaActual.before(fechaFinal)){
                            
                            Nodo auxiliar = actual.facturas[j].detalle.root;
                            
                            while (auxiliar != null && auxiliar.prod != null){
                                lista.insertar(""+auxiliar.prod.code,auxiliar.cant);
                                auxiliar = auxiliar.next;
                            }
                            
                            

                        }
                        if(fechaActual.equals(fechaInicial) || fechaActual.equals(fechaInicial)){

                            Nodo auxiliar = actual.facturas[j].detalle.root;
                            
                            while (auxiliar != null && auxiliar.prod != null){
                                lista.insertar(""+auxiliar.prod.code,auxiliar.cant);
                                auxiliar = auxiliar.next;
                            }
                            
                        }

                        recorridoReporte2(fechaIni,fechaFin,actual.ramas[j+1],lista);
                    }
                }
                   
            }
        }
        
        
    }
    
    
    public String reporte2(String fechaInicial, String fechaFinal){
        try {
            String cuerpo = "";
            
            listaDoble lista = new listaDoble();
            recorridoReporte2(fechaInicial,fechaFinal,this.raiz,lista);
            
            //recorrer la lista y meterla a un string;
            
            if (!lista.estaVacia()){
                
                NodoListaDoble auxiliar =  lista.primerNodo;
                
                while (auxiliar != null ){
                    cuerpo = cuerpo + "Producto: " + auxiliar.nombre + ",Cantidad: "+ auxiliar.cantidad+";";
                    auxiliar = auxiliar.sig;
                }
                
            }
            
            return cuerpo;
        } catch (ParseException ex) {
            Logger.getLogger(ArbolB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
        
}

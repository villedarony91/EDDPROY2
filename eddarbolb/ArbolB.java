/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddarbolb;
import Utilities.Log;
import java.io.*;
import avl.*;
import listascolas.*;

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
    
    public void Insertar(int numFactura,String fecha,double total,NodoAvl usuario, Lista detalle){
        
        Pagina nuevo,actual;
        
        Factura factura = new Factura(fecha,total,usuario,detalle);
        
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
    
    
    public void imprimir(Pagina actual){
        
        if(actual != null){
            imprimir(actual.ramas[0]);
            for (int j= 0; j < actual.cuenta; j++){
                System.out.print(actual.claves[j]+", ");
                imprimir(actual.ramas[j+1]);
            }
        }
    }
     
    public void imprimirGraphviz(Pagina actual,PrintWriter archivo){
        
        int j=0;
        
        if(actual != null){
            archivo.println("");
            archivo.print("nodo"+ actual.claves[0] +" [shape = record, label= \" <f0> ");
            for (int i = 0; i<4; i++){
                j++;
                if(actual.claves[i] > 0){
                    if(i != 4){
                      archivo.print("| <f"+(i+j)+">" + actual.claves[i] + " \n "+ actual.facturas[i].fecha + " \n "+ actual.facturas[i].total + " \n "+ actual.facturas[i].usuario +"|<f"+((i+j)+1)+">\n");   
                    }
                    else{
                        archivo.print("| <f"+(i+j)+">" + actual.claves[i] + " \n "+ actual.facturas[i].fecha + " \n "+ actual.facturas[i].total + " \n "+ actual.facturas[i].usuario); 
                    } 
                }  
            }
            archivo.print("\"]; \n");
            archivo.println("");
            
            for (int i = 0; i<5; i++){
                if(actual.ramas[i]!=null){
                    archivo.println("\"nodo"+actual.claves[0]+"\":f"+i*2+" -> \"nodo"+ actual.ramas[i].claves[0] +"\":f2; \n");
                    imprimirGraphviz(actual.ramas[i],archivo);
                }
            }
        }
        
    }
    
    
    public String imprimirGraphviz(Pagina actual,String cuerpo){
        
        int j=0;
        String cadena = cuerpo;
        String punteros ="";
        
        if(actual != null){
            cadena = cadena + "\n ";
            cadena = cadena + "nodo"+ actual.claves[0] +" [shape = record, label= \" <f0> ";
            for (int i = 0; i<4; i++){
                j++;
                if(actual.claves[i] > 0){
                    if(i != 4){
                      cadena = cadena + "| <f"+(i+j)+"> Factura: " + actual.claves[i] + " &#92;n Fecha: "+ actual.facturas[i].fecha + " &#92;n Total: "+ actual.facturas[i].total + " &#92;n Usuario: "+ actual.facturas[i].usuario+"|<f"+((i+j)+1)+">\n";   
                    }
                    else{
                        cadena = cadena + "| <f"+(i+j)+"> Factura: " + actual.claves[i] + " &#92;n Fecha: "+ actual.facturas[i].fecha + " &#92;n Total: "+ actual.facturas[i].total + " &#92;n Usuario: "+ actual.facturas[i].usuario+"\n";   
                    } 
                }  
            }
            cadena = cadena +"\"]; \n";
            cadena = cadena +"\n";
            
            for (int i = 0; i<5; i++){
                if(actual.ramas[i]!=null){
                    punteros = punteros + "\"nodo"+actual.claves[0]+"\":f"+i*2+" -> \"nodo"+ actual.ramas[i].claves[0] +"\":f2; \n";
                    punteros = punteros + imprimirGraphviz(actual.ramas[i],cadena);
                }
            }
            
            cadena = cadena + punteros;
            
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listascolas;

/**
 *clase para manejar las colas y pilas
 * @author rlopez
 */
public class Lista {
    public Nodo root;
    public int id;
    
    public Lista(){
        this.root = null;
        id = 0;
    }
    
   /**
    * inserta en una lista
    * @param user nombre de usuario
    * @param adress direccion 
    * @param env si es direccion de envio 1
    * @param fact si es direccion de facturacion 1
    */
   public void insertList(String user, String adress,
            int env, int fact){
        Nodo newNode = new Nodo(user);
        newNode.address = adress;
        newNode.envio = env;
        newNode.fact = fact;
        id ++;
        newNode.nodeId = id;
        if(root == null){
            root = newNode;
            newNode.next = null;
        }else{
            newNode.next = root;
            root = newNode;
        }
    }
   
   /**
    * borra un nodo de la lista (cola carrito y cola lista de deseos)
    * @param code codigo del producto a eliminar
    */
   public void deleteNode(int code){
       Nodo tmp = root;
       Nodo aux = root;
       if(root.prod.code == code && root.next == null){
           root = null;
       }
       if(root.prod.code == code && root.next != null){
           root = tmp.next;
       }else{
           while(tmp.next != null && tmp.prod.code != code){
               aux = tmp;
               tmp = tmp.next;
           }
           if(tmp.next != null && tmp.prod.code == code){
               aux.next = tmp.next;
               return;
           }
           if(tmp.next == null && tmp.prod.code == code){
               aux.next = null;
           }
       }
   }
    
   /**
    * inserta en la cola de la lista
    * @param user nombre de usuario
    * @param cant cantidad
    * @param prod producto
    */
    public void insertTail(String user, int cant, Producto prod){
        Nodo newNode = new Nodo(user);
        newNode.cant = cant;
        newNode.prod = prod;
        id ++;
        newNode.nodeId = id;
        if(root == null){
            root = newNode;
            root.next = null;
        }else{
            Nodo tmp = root;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }
    
    /**
     * grafica lista de direcciones
     * @return cadena con grafica de lista de direcciones
     */
    public String graphList(){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        sb.append(getNodesAdd());
        if(tmp != null && tmp.next == null){
        }else{
            while(tmp.next != null){
                        String name = "nodo" + count;
                        String name1 = "nodo" + (count + 1);
                        name = "nodo" + count;
                        sb.append(name).append("->").append(name1).append(";\n");
                        tmp = tmp.next;
                        count ++;
            }
        }
        return sb.toString();
    }
    
    /**
     * obtiene los nodos para su graficacion 
     * @return nodos en formato graphviz de la lista de direcciones
     */
    public String getNodesAdd(){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        while(tmp != null){
            String name = "nodo" + count;
            sb.append(name).append("[label =\"").append(tmp.address)
                    .append("\\n").append("Facturacion : ")
                    .append(tmp.fact).append("\\n")
                    .append("Envio :").append(tmp.envio)
                    .append("\"").append("];\n");
            tmp = tmp.next;
            count++;
            }   
        return sb.toString();
    }
    
    /**
     * envia la grafica para ser inculuida en la grafica del arbol avl
     * @param id identificador unico para graphviz
     * @return gráfica para ser inculuida
     */
    public String graphBigList(String id){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("subgraph{ \n");
        Nodo tmp = root;
        sb.append(getNodesBigAdd(id));
        if(tmp.next == null){
        }else{
            while(tmp.next != null){
                        String name;
                        String name1 = id + (count + 1);
                        name = id + count;
                        sb.append(name).append("->").append(name1).append(";\n");
                        tmp = tmp.next;
                        count ++;
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Obtiene los nodos para la grafica de la lista de direcciones en el arbol avl
     * @param id identificador unico para uso de graphviz
     * @return nodos en formato graphViz
     */
    private String getNodesBigAdd(String id){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        while(tmp != null){
            String name = id + count;
            sb.append(name).append("[label =\" Direcciones \n")
                    .append(tmp.address)
                    .append("\\n").append("Facturacion : ")
                    .append(tmp.fact).append("\\n")
                    .append("Envio :").append(tmp.envio)
                    .append("\"").append("];\n");
            tmp = tmp.next;
            count++;
            }   
        return sb.toString();
    }
    public String graphBigQueue(String id, String type){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        sb.append("subgraph {");
        sb.append(getNodesBig(id, type));
        if(tmp != null && tmp.next == null){
            String name = id + count;
            sb.append(name);
        }else{
            if(tmp != null)
            while(tmp.next != null){
                String name;
                String name1 = id + (count + 1);
                name = id + count;
                sb.append(name).append("->").append(name1).append(";\n");
                tmp = tmp.next;
                count ++;
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * Obtiene los nodos del carrito y de la lista de deseos para graficar
     * @param id identificador unico para graphViz
     * @param type si es carrito o lista de deseos
     * @return grafica en formato grapviz
     */
    public String getNodesBig(String id, String type){
        StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        while(tmp != null){
            String name = id + count;
            sb.append(name).append("[label =\"").append(type).append("\\n"
                    + "Codigo: ").append(tmp.prod.code)
                    .append("\\n").append("Nombre : ")
                    .append(tmp.prod.name).append("\\n")
                    .append("Marca :").append(tmp.prod.brand).append("\\n")
                    .append("Precio: ").append(tmp.prod.price).append("\\n")
                    .append("Cantidad: ").append(tmp.cant)
                    .append("\"").append("];\n");
            tmp = tmp.next;
            count++;
            }   
        return sb.toString();
    }
    
    /**
     * Grafica la cola para ser mostrada al usuario final
     * @param user nombre de usuario
     * @return grafica de la cola en formato graphviz
     */
    public String graphQueue(String user){
                StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        sb.append(getNodesQueue());
        if(tmp != null && tmp.next == null){
            String name = "nodo" + count;
            sb.append(name).append("\n");
        }else{
            while(tmp != null && tmp.next != null){
                        String name = "nodo" + count;
                        String name1 = "nodo" + (count + 1);
                        name = "nodo" + count;
                        sb.append(name).append("->").append(name1).append(";\n");
                        tmp = tmp.next;
                        count ++;
            }
        }
        return sb.toString();
        
    }
    
    /**
     * Obtiene los nodos de la cola para graficarlo
     * @return nodos en formato graphViz para graficar
     */
    public String getNodesQueue(){
                StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        while(tmp != null){
            String name = "nodo" + count;
            sb.append(name).append("[label =\"").append(tmp.prod.code)
                    .append("\\n").append("Nombre : ")
                    .append(tmp.prod.name).append("\\n")
                    .append("Marca :").append(tmp.prod.brand).append("\\n")
                    .append("Precio: ").append(tmp.prod.price).append("\\n")
                    .append("Cantidad: ").append(tmp.cant)
                    .append("\"").append("];\n");
            tmp = tmp.next;
            count++;
            }   
        return sb.toString();
    }
    
    /**
     * Obtiene el nombre de los productos para graficar
     * @return nombre de los nodos en formato graphViz
     */
    public String getProdNames(){
        StringBuilder sb = new StringBuilder();
        Nodo tmp = root;
        sb.append("");
        while(tmp != null && tmp.next != null){
            sb.append(tmp.prod.name).append(",");
            tmp = tmp.next;
        }
        if(tmp != null && tmp.next == null){
            sb.append(tmp.prod.name);
        }
        return sb.toString();
    }
    
    /**
     * Obtiene el codigo de producto para los catalogos del cliente
     * @return todos los codigos de producto agregados
     */
    public String getProdCode(){
        StringBuilder sb = new StringBuilder();
        Nodo tmp = root;
        sb.append("");
        while(tmp != null && tmp.next != null){
            sb.append(tmp.prod.code).append(",");
            tmp = tmp.next;
            
        }
        if(tmp != null && tmp.next == null){
            sb.append(tmp.prod.code);
        }
        return sb.toString();
    }
    
    /**
     * obtiene las rutas de las imagenes
     * @return cadena con la ruta de todas la imagenes
     */
    public String getProdImage(){
        StringBuilder sb = new StringBuilder();
        Nodo tmp = root;
        sb.append("");
        while(tmp.next != null){
            sb.append(tmp.prod.image).append(",");
            tmp = tmp.next;
        }
        if(tmp.next == null){
            sb.append(tmp.prod.image);
        }
        return sb.toString();
    }
    
    /**
     * obtiene la cantidad de producto
     * @return la cantidad de los productos
     */
    public String getProdCant(){
        StringBuilder sb = new StringBuilder();
        Nodo tmp = root;
        sb.append("");
        while(tmp.next != null){
            sb.append(tmp.cant).append(",");
            tmp = tmp.next;
        }
        if(tmp.next == null){
            sb.append(tmp.cant);
        }
        return sb.toString();
    }
    
    /**
     * Obtiene los precios para los catalogos
     * @return todos los precios de los productos
     */
    public String getProdPrice(){
        StringBuilder sb = new StringBuilder();
        Nodo tmp = root;
        sb.append("");
        while(tmp.next != null){
            sb.append(tmp.prod.price).append(",");
            tmp = tmp.next;
        }
        if(tmp.next == null){
            sb.append(tmp.prod.price);
        }
        return sb.toString();
    }
    /**
     * inserta en el detalle de la factura
     * @param cant cantidad a insertar
     * @param precioDetalle precio del detalle
     * @param prod precio del producto
     */
    public void insertDetalle(int cant, double precioDetalle, Producto prod){
        Nodo newNode = new Nodo(cant,precioDetalle,prod);
        //le agrege la suma para el id;
        newNode.nodeId = id ++;
        if(root == null){
            root = newNode;
            root.next = null;
            
        }else{
            Nodo tmp = root;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
    }
    

}
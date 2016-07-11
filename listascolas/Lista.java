/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listascolas;

/**
 *
 * @author rlopez
 */
public class Lista {
    public Nodo root;
    public int id;
    
    public Lista(){
        this.root = null;
        id = 0;
    }
    
    
   public void insertList(String user, String adress,
            int env, int fact){
        Nodo newNode = new Nodo(user);
        newNode.address = adress;
        newNode.envio = env;
        newNode.fact = fact;
        id ++;
        newNode.nodeId = ""+id;
        if(root == null){
            root = newNode;
            newNode.next = null;
        }else{
            newNode.next = root;
            root = newNode;
        }
    }
   
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
    
    public void insertTail(String user, int cant, Producto prod){
        Nodo newNode = new Nodo(user);
        newNode.cant = cant;
        newNode.prod = prod;
        id ++;
        newNode.nodeId = ""+id;
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
        if(tmp.next == null){
            String name = id + count;
            sb.append(name);
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
    
    public String graphQueue(String user){
                StringBuilder sb = new StringBuilder();
        int count = 0;
        Nodo tmp = root;
        sb.append(getNodesQueue());
        if(tmp != null && tmp.next == null){
            String name = "nodo" + count;
            sb.append(name).append("\n");
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
public void insertDetalle(int cant, double precioDetalle, Producto prod){
        Nodo newNode = new Nodo(cant,precioDetalle,prod);
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

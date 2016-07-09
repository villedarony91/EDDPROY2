/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listascolas;
import Utilities.*;
/**
 *
 * @author rlopez
 */
public class Hash {
    
    int size;
    Producto[] map;
    int ocup;
    
    public Hash(){
        map = new Producto[23];
        size = 23;
        ocup = 0;
    }
    
    private void initMap(int size){
        for(int i = 0 ; i < size ; i++){
            map[i].code = -1;
        }
    }
    
    public int getSumHalf(int toSum){
        int length = (int)(Math.log10(toSum)+1);
        int []arr = new int[length];
        int tmp = toSum;
        int count = 0;
        for(int i = length; i > 0 ;i--){
            arr[count] = tmp%10;
            tmp = tmp/10;
            count++;
        }
        int half = length/2;
        int num1 = 0;
        int mult = 1;
        for(int i = 0; i < half; i++){
            num1 += arr[i] * mult;
            mult = mult * 10;
        }
        int num2 = 0;
        mult = 1;
        for(int i = half; i < length; i++){
            num2 += arr[i] * mult;
            mult = mult * 10;
        }
        return num1 + num2;
    }
    
    public int getMod(int num){
        return getSumHalf(num) % 10;
    }
    
    public boolean isPrime(int number) {
        if (number % 2 == 0){
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
    public int getNextPrime(int minNumberToCheck) {
        for (int i = minNumberToCheck; true; i++) {
            if (isPrime(i))
                return i;
        }
    }
    
    private boolean checkOcup(){
        double check = (double)ocup/(double)size;
        boolean t = (check < 0.4);
        return check < 0.4;
    }
    
    public void insertProduct(int code, String name,
            String brand, double price, String image){
        insert(new Producto(code,name,brand,price,image));
    }
    
    public void insert(Producto producto){
        int index = getMod(producto.code);
        if(map[index] == null){
            map[index] = producto;
            ocup++;
            Log.logger.info("Insertado prod " + producto.code);
        }else{
            int count = 1;
            boolean col = false;
            int ini = count + index;
            int loop = 1;
            while(map[ini] != null){
                ini = (int)Math.pow(count ++, 2) + index;
                if(ini >= size){
                    while(ini >= size)
                    ini = ini - size;
                }
                if(map[ini] != null && map[ini].code == producto.code){
                    col = true;
                    break;
                }

            }
            if(map[ini] == null && !col){
                map[ini] = producto;
                ocup++;
                Log.logger.info("Insertado prod " + producto.code);
            } 
        }
        if(!checkOcup()){
            reHash();
        }
    }
    
    public String graphHash(){
        StringBuilder sb = new StringBuilder();
        sb.append("digraph hash {\n");
        sb.append("node [shape=record,width=.4,height=.4];\n");
        sb.append("nodeZ[label= \"");
        for(int i = 0 ; i < size; i++){
            if(i != size -1)
            sb.append("<f").append(i).append(">|");
            else sb.append("<f").append(i).append(">");
        }
        sb.append("\"];");
        for(int i = 0; i < size; i++){
            if(map[i] != null){
                String name = "node"+i;
                Producto tmp = map[i];
                sb.append(name).append("[label = \"")
                        .append(tmp.code).append("\\n")
                        .append(tmp.name).append("\\n")
                        .append(tmp.brand).append("\\n")
                        .append(tmp.price).append("\\n")
                        .append(tmp.image).append("\\n");
                sb.append("\"];");
            }
        }
        for(int i = 0; i < size; i++){
            if(map[i] != null){
                String name = "node"+i;
                Producto tmp = map[i];
                sb.append("nodeZ:f").append(i).append("->");
                sb.append("node").append(i).append(";");
            }
        }
        sb.append("}");
        Writer w = new Writer();
        w.write("hash.dot", sb.toString());
        w.compileDot("hash");
        return sb.toString();
        
    }
    
    public Producto search(int code){
        int ind = getMod(code);
        Producto retorno = null;
        if(map[ind]==null){
            Log.logger.warn("Producto no enctonrado " +code);
            return null;
        }else{
            if(map[ind].code == code){ retorno = map[ind];
            Log.logger.info("Poducto valido" + code);
            }
            else{
                int count = 1;
                int ini = count + ind;
                while(map[ini] != null && map[ini].code != code){
                    ini = (int)Math.pow(count ++, 2) + ind;
                    if(ini >= size){
                        while(ini >= size)
                            ini = ini - size;
                    }
                }
                if(map[ini] != null)
                if(map[ini].code == code){
                    retorno = map[ini];
                    Log.logger.info("Producto encontrado " + code);
                }
            }
        }
        if (retorno == null){
            Log.logger.warn("Producto no encontrado " + code);
        }
        return retorno;
    }
    
    
    public void reHash(){
        int oldSize = size;
        int newSize = (getNextPrime(size+1));
        size = newSize;
        Producto[] tmp = map;
        map = new Producto[newSize];
        ocup = 0;
        for(int i = 0; i < oldSize; i++){
            if(tmp[i] != null)
            insert(tmp[i]);
        }
    }
    
    
    public String sendProdCode(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(map != null){
            for(int i = 0 ; i < size ; i++){
                if(map[i] != null){
                    if(i != (size - 2))
                        sb.append(map[i].code).append(",");
                    else
                        sb.append(map[i].code);
                }
            }
        }
        return sb.toString();
        
    }
    
    public String sendProdName(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(map != null){
        for(int i = 0 ; i < size ; i++){
            if(map[i] != null){
                if(i != (size - 2))
                    sb.append(map[i].name).append(",");
                else
                    sb.append(map[i].name);
            }
        }
        }
        return sb.toString();
    }
    
       public String sendProdBrand(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if(map != null)
        for(int i = 0 ; i < size ; i++){
            if(map[i] != null){
                if(i != (size - 2))
                    sb.append(map[i].brand).append(",");
                else
                    sb.append(map[i].brand);
            }
        }
        return sb.toString();
    }
       
       public String sendProdPrice(){
           StringBuilder sb = new StringBuilder();
           sb.append("");
           if(map != null){
               for(int i = 0 ; i < size ; i++){
                   if(map[i] != null){
                       if(i != (size - 2))
                           sb.append(map[i].price).append(",");
                       else
                           sb.append(map[i].price);
                   }
               }
           }
           return sb.toString();
       }
       
       public String sendProdImage(){
           StringBuilder sb = new StringBuilder();
           sb.append("");
           if(map != null){
               for(int i = 0 ; i < size ; i++){
                   if(map[i] != null){
                       if(i != (size - 2))
                           sb.append(map[i].image).append(",");
                       else
                           sb.append(map[i].image);
                   }
               }
           }
           return sb.toString();
       }
    
    
    

}

import java.util.Arrays;
import java.util.ArrayList;
import java.io.*;

public class Floyd
{
    public static Grafo grafo;
    public static int[][] MatrizClon;
    public static ArrayList<ArrayList<Integer>> Pasos = new ArrayList<ArrayList<Integer>>();
    public static long fin, inicio, tiempo;
    
    public static void floyd(Grafo grafo, String args){
        if(Lectura.calcularcoste == true){
                    inicio = System.currentTimeMillis();
                }
        
        MatrizClon = grafo.tabla;
        int anterior, ik, kj, actual, minimo, costo;
        for (int k = 0; k < grafo.numeroLineas; k++) { //LINEAS 
            for (int i = 0; i < grafo.numeroLineas; i++) { //COLUMNAS
                ArrayList<Integer> PasosAux = new ArrayList<Integer>();
                for (int j = 0; j < grafo.numeroLineas; j++) {     
                    if (i != j && k != i && k != j) {
                        anterior = MatrizClon[i][j];
                        ik = MatrizClon[i][k];
                        kj = MatrizClon[k][j];
                        if (ik == -1 || kj == -1) {
                            actual = -1;
                        } else {
                            actual = ik + kj;
                        }
                        if (actual != -1 && (actual < anterior || anterior == -1)) {
                            minimo = actual;
                            PasosAux.add(j);
                        } else {
                            minimo = anterior;
                        }
                        MatrizClon[i][j] = minimo;
                    }
                }
                if(!PasosAux.contains(i+1)){
                    PasosAux.add(i+1);
                }
                Pasos.add(PasosAux);
            }
        }
        
        if(Lectura.calcularcoste == true){
        fin = System.currentTimeMillis();
        tiempo = fin-inicio;
        System.out.println("\nCoste Temporal: " + tiempo +" milisegundos en ejecutarse.");
        }        
        
        if(Lectura.calcularcoste == false){
        try {
            if(!args.isEmpty()){            
                File file = new File(args);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < grafo.numeroLineas; i++) {
                    for (int j = 0; j < grafo.numeroLineas; j++) {
                        costo = MatrizClon[i][j];
                        ArrayList<Integer> lista = Pasos.get(j);
                        String Linea = "";
                        if( i != j){
                            int ii = i+1;
                            int jj = j+1;
                            if (costo == -1) {
                                Linea = Linea +"Nodo Inicial: " + ii + " Nodo Final: " + jj + "-1";
                            } else {
                                Linea = Linea +"[" + ii + "," + jj + "]: ";

                                for(int z=0; z < lista.size(); z++){

                                    if(lista.size() == 1){
                                        Linea = Linea +lista.get(z);
                                    }else if(lista.size()>1){  
                                        Linea = Linea +lista.get(z); 
                                    }
                                    if( z+1 < lista.size()){
                                        Linea = Linea +",";
                                    }
                                }
                                Linea = Linea +":" + " " + costo;
                            }
                        }
                        if(!Linea.isEmpty()){
                            bw.write(Linea + "\n");
                        }
                    }
                }
                bw.close();
            }else{
                for (int i = 0; i < grafo.numeroLineas; i++) {
                    for (int j = 0; j < grafo.numeroLineas; j++) {
                        costo = MatrizClon[i][j];
                        ArrayList<Integer> lista = Pasos.get(j);
                        if( i != j){
                            int ii = i+1;
                            int jj = j+1;
                            if (costo == -1) {
                                System.out.println("Nodo Inicial: " + ii + " Nodo Final: " + jj + "-1");
                            } else {
                                System.out.print("[" + ii + "," + jj + "]: ");

                                for(int z=0; z < lista.size(); z++){

                                    if(lista.size() == 1){
                                        System.out.print(lista.get(z));
                                    }else if(lista.size()>1){  
                                        System.out.print(lista.get(z)); 
                                    }
                                    if( z+1 < lista.size()){
                                        System.out.print(",");
                                    }
                                }
                                System.out.println(":" + " " + costo);
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("Hay un error en la ruta del fichero de salida, se escribirá la solucion en consola\n");
            for (int i = 0; i < grafo.numeroLineas; i++) {
                for (int j = 0; j < grafo.numeroLineas; j++) {
                    costo = MatrizClon[i][j];
                    ArrayList<Integer> lista = Pasos.get(j);
                    if( i != j){
                        int ii = i+1;
                        int jj = j+1;
                        if (costo == -1) {
                            System.out.println("Nodo Inicial: " + ii + " Nodo Final: " + jj + "-1");
                        } else {
                            System.out.print("[" + ii + "," + jj + "]: ");

                            for(int z=0; z < lista.size(); z++){

                                if(lista.size() == 1){
                                    System.out.print(lista.get(z));
                                }else if(lista.size()>1){  
                                    System.out.print(lista.get(z)); 
                                }
                                if( z+1 < lista.size()){
                                    System.out.print(",");
                                }
                            }
                            System.out.println(":" + " " + costo);
                        }
                    }
                }
            }
        }
    }
    } 
}


import java.io.*;
import java.lang.Runtime;

public class Main
{
    static int contador;
    static Grafo grafo;
    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void main(String[] args){
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        try{
            contador = Lectura.ArgCheck(args);
            if(contador == 0 && args.length < 1){
                System.out.println("Necesitamos almenos un archivo de entrada.");
            }else{
                Lectura.CalcularLineas(args[contador]);
                grafo = Lectura.LeerDatos(args[contador]);
            }
            try{
                Floyd.floyd(grafo, args[contador+1]);
            }catch(Exception e){
                Floyd.floyd(grafo, null);
            }
        }catch(Exception e){
        }
        if(Lectura.calcularcoste == true){
         
            long memory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Coste Espacial en Bytes: " + memory +" Bytes.");
            System.out.println("Coste Espacial en MegaBytes: " + bytesToMegabytes(memory) +" MegaBytes.");

        }
        System.exit(0);
    }
}



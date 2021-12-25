import java.io.*;

public class Lectura
{
    static File archivo;
    static FileReader fr;
    static BufferedReader br;
    static int infinito = -1;
    static int NumerodeLineas;
    static boolean trazado = false, imprimir = false, calcularcoste = false;

    public static int ArgCheck(String[] args){
        if(args.length < 1){
            System.out.print("No hay suficientes argumentos para ejecutar el programa. Al menos necesitamos un fichero de entrada.");
            System.exit(0);
        }
        int contador = 0;
        for (String x: args) {
            if (x.equals("-h")) {
                System.out.println("SINTAXIS: Floyd [-t][-h] [fichero entrada] [fichero salida]\n     -t                  Traza cada llamada recursiva y sus parámetros\n     -h                  Muestra esta ayuda\n     [fichero entrada]   Conjunto de edificios de la ciudad\n     [fichero salida]    Secuencia que representan el skyline de la ciudad\n");
                contador++;
            } else if (x.equals("-t")) {
                trazado = true;
                System.out.println("Este programa no utiliza llamadas recursivas.\n");
                contador++;
            } else if(x.equals("-c")){
                calcularcoste = true;
                contador++;
            }
        }
        return contador;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void CalcularLineas(String contador){
        try{
            File archivoo = new File(contador);
            FileReader frr = new FileReader(archivoo);
            BufferedReader brr = new BufferedReader(frr);
            NumerodeLineas = (int)brr.lines().count();
        }catch(Exception e){}
    }

    public static Grafo LeerDatos(String contador)
    {
        Grafo grafo = null; 

        try {
            archivo= new File(contador);
            fr = new FileReader(archivo);
            br= new BufferedReader(fr);
            String linea = br.readLine();
            int NumerodeColumnas;
            int numeroLinea = 0;
            int numeroColumna = 0;
            grafo = new Grafo(NumerodeLineas,NumerodeLineas);

            while (linea != null) {
                String[] parts = linea.split(" ");
                NumerodeColumnas = parts.length;
                if(NumerodeLineas != NumerodeColumnas){
                    System.out.println("El archivo de entrada no tiene el mismo número de filas que de columnas, el grafo es erróneo, el programa se cerrará");
                    System.exit(0);
                }
                for(int i=0;i<NumerodeColumnas;i++){
                    if(isNumeric(parts[i]) && numeroLinea != i){
                        grafo.tabla[numeroLinea][i] = Integer.parseInt(parts[i]);
                    }else if(isNumeric(parts[i]) && numeroLinea == i){
                        grafo.tabla[numeroLinea][i] = 0;
                    }else{
                        grafo.tabla[numeroLinea][i] = infinito;
                    }
                }
                numeroLinea++;
                linea = br.readLine();
            }

        } catch (Exception e) {
            System.out.println("Error en la lectura del archivo");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return grafo;
    }
}

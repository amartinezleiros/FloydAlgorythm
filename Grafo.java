public class Grafo
{
    public int tabla[][];
    public int numeroLineas; 
    public Grafo(int NumerodeLineas, int NumerodeColumnas)
    {
        this.tabla = new int[NumerodeLineas][NumerodeColumnas]; 
        this.numeroLineas = NumerodeLineas;        
    }
}

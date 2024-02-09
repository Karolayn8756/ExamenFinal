package ec.edu.puce.voto.dominio;
import java.util.ArrayList; 

public class Mesas
{
   
    private String nombre;
    private ArrayList<Cursos> cursos;
    private int votosTotales;

    
    public Mesas(String nombre)
    {
        this.nombre = nombre;
        this.votosTotales = 0;
        this.cursos = new ArrayList<Cursos>();
    }


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVotosTotales() {
		return votosTotales;
	}

	public void setVotosTotales(int votosTotales) {
		this.votosTotales = votosTotales;
	}

	public ArrayList<Cursos> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Cursos> cursos) {
		this.cursos = cursos;
	}

	
    
}

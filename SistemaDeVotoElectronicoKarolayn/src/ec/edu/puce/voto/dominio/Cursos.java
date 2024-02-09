package ec.edu.puce.voto.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cursos
{
   
    private String nombreCurso;
    private List<Estudiante> estudiantes;
    private Mesas mesa;
    private int votosCiudad;

    
    public Cursos(String ciudad, Mesas mesa)
    {
        this.nombreCurso = ciudad;
        this.votosCiudad = 0;
        this.mesa = mesa;
        
    }
    

    
    public int getVotos(String nombre){
       
        return 0;
    }
    
    public int votosTotales(){
        votosCiudad = 0;
       
        return votosCiudad;
    }

	public String getCiudad() {
		return nombreCurso;
	}

	public void setCiudad(String ciudad) {
		this.nombreCurso = ciudad;
	}

	

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public Mesas getMesa() {
		return mesa;
	}

	public void setMesa(Mesas mesa) {
		this.mesa = mesa;
	}

	public int getVotosCiudad() {
		return votosCiudad;
	}

	public void setVotosCiudad(int votosCiudad) {
		this.votosCiudad = votosCiudad;
	}


    
	
    
}
package ec.edu.puce.voto.dominio;

import java.util.ArrayList;

public class Prefecto {
    private int id;
    private String nombre;
    private int votos;
    private Mesas mesas; // Cambié "Mesa" a "Mesas"
    private ArrayList<Cursos> cursos; // Agregué la propiedad Cursos
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public Mesas getMesas() { // Cambié el nombre de la función a getMesas
        return mesas;
    }

    public void setMesas(Mesas mesas) { // Cambié el nombre de la función a setMesas
        this.mesas = mesas;
    }

    
    public ArrayList<Cursos> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Cursos> cursos) {
		this.cursos = cursos;
	}}

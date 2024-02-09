package ec.edu.puce.voto.dominio;

import java.util.ArrayList;

public class Estudiante {
	private String id;
	private String nombre;
	private String clavePersonal;
	private Cursos curso;
    private Prefecto voto;
    private boolean haVotado = false; 

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClavePersonal() {
		return clavePersonal;
	}

	public void setClavePersonal(String clavePersonal) {
		this.clavePersonal = clavePersonal;
	}

	public Cursos getCurso() {
		return curso;
	}

	public void setCurso(Cursos curso) {
		this.curso = curso;
	}


	public Prefecto getVoto() {
		return voto;
	}

	public void setVoto(Prefecto voto) {
		this.voto = voto;
	}

	public boolean isHaVotado() {
		return haVotado;
	}

	public void setHaVotado(boolean haVotado) {
		this.haVotado = haVotado;
	}
	
	

}
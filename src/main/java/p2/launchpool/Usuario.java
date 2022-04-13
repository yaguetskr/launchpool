package p2.launchpool;

import java.util.ArrayList;

public class Usuario {

	String username;
	String pwd;
	ArrayList<Candidatura> candidaturas;
	ArrayList<Proyecto> proyectos;
	
	public Usuario(String nombre,String password) {
		
		this.username=nombre;
		this.pwd=password;

		
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public ArrayList<Candidatura> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(ArrayList<Candidatura> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public ArrayList<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(ArrayList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	

}

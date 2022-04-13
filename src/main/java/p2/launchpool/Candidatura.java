package p2.launchpool;

import java.util.List;

public class Candidatura {
	
	long id;
	String nombre;
	String iduser;
	String tlf;
	String email;
	String sobremi;
	String especialidades;
	
	
	
	
	public Candidatura(long id, String nombre, String iduser, String tlf, String email, String sobremi,
			String especialidades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.iduser = iduser;
		this.tlf = tlf;
		this.email = email;
		this.sobremi = sobremi;
		this.especialidades = especialidades;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSobremi() {
		return sobremi;
	}
	public void setSobremi(String sobremi) {
		this.sobremi = sobremi;
	}
	public String getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	
	
	

}

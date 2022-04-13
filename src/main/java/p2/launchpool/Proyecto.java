package p2.launchpool;

import java.util.List;

public class Proyecto {
	long id;
	String iduser;
	String nombre;
	String descripcion;
	String recaudacion;
	String especialidades;
	
	
	
	public Proyecto(long id, String iduser, String nombre, String descripcion, String recaudacion,String especialidades) {
		super();
		this.id = id;
		this.iduser = iduser;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.recaudacion = recaudacion;
		this.especialidades = especialidades;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getRecaudacion() {
		return recaudacion;
	}
	public void setRecaudacion(String recaudacion) {
		this.recaudacion = recaudacion;
	}
	public String getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	
}

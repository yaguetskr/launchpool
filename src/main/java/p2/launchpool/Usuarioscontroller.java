package p2.launchpool;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



import com.google.gson.Gson;
import com.google.gson.JsonIOException;











public class Usuarioscontroller {
	private static List<Usuario> lista=new ArrayList();
	private static final Gson gson=new Gson();
	private static Usuarioscontroller self=null;
	
	private Usuarioscontroller() throws Exception {
		lista.addAll(this.leerjson());
		
	}
	
	
	
	public static Usuarioscontroller controller() throws Exception {
		if(self==null) {
			self=new Usuarioscontroller();
		}
		return self;
		
	}
	
	public List<Usuario> getLista() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	public List<Usuario> leerjson() throws Exception {
		
		try(Reader reader = new FileReader("Usuarios.json")){
			return Arrays.asList(gson.fromJson(reader, Usuario[].class));
			
		}catch(Exception e) {
			
		}
		return new ArrayList();
			
		
		
			
		
	}
	
	public void clear() throws Exception {
		lista=new ArrayList();
		new FileWriter("Usuarios.json", false).close();
		
	}
	
	
	public Usuario buscar(String id) throws IOException {
		
		for (Usuario U:lista) {
			if(  U.getUsername().equals(id) ) {
				return U;
			}
		}
		return null;
	}
	
	
	public Usuario crear(String nombre,String password) throws Exception{		
		
		if(self.buscar(nombre)==null) {
			lista.add(new Usuario(nombre,password));
			try(FileWriter fw=new FileWriter("Usuarios.json")) {
				gson.toJson(lista,fw);
			} catch (JsonIOException e) {
				System.out.print(e);
				e.printStackTrace();
			} 
  
		}
		
		

		return this.buscar(nombre);
	}

}

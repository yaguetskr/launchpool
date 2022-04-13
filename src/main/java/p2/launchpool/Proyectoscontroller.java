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



public class Proyectoscontroller {
	
	private static List<Proyecto> lista=new ArrayList();
	private static final Gson gson=new Gson();
	private static Proyectoscontroller self=null;
	private final AtomicLong counter=new AtomicLong();
	
	private Proyectoscontroller() throws Exception {
		lista.addAll(this.leerjson());
		
	}
	
	
	
	public static Proyectoscontroller controller() throws Exception {
		if(self==null) {
			self=new Proyectoscontroller();
		}
		return self;
		
	}
	
	
	public List<Proyecto> leerjson() throws Exception {
		
		try(Reader reader = new FileReader("Proyectos.json")){
			return Arrays.asList(gson.fromJson(reader, Proyecto[].class));
			
		}catch(Exception e) {
			
		}
		return new ArrayList();
			
		
		
			
		
	}
	
	public void clear() throws Exception {
		lista=new ArrayList();
		new FileWriter("Proyectos.json", false).close();
		counter.set(0);
		
	}
	
	
	public List<Proyecto> buscar(String id) throws IOException {
		
		List<Proyecto> list=new ArrayList();
		
		for (Proyecto U:lista) {
			if(  U.getIduser().equals(id) ) {
				list.add(U);
			}
			
	}
		return list;
	}
	
	public Proyecto buscarid(long id) {
		// TODO Auto-generated method stub
		for (Proyecto U:lista) {
			if(  U.getId()==id ) {
				return U;
			}
			
	}
		return null;
	}
	
	
	public Proyecto crear(String iduser, String nombre, String descripcion, String recaudacion,
			String especialidades) throws Exception{
		
		counter.incrementAndGet();
		while(self.buscarid(  counter.get()  )!=null) {
			counter.incrementAndGet();
		}
		
		lista.add(new Proyecto(counter.get(), iduser, nombre, descripcion, recaudacion,especialidades));
		try(FileWriter fw=new FileWriter("Proyectos.json")) {
			gson.toJson(lista,fw);
		} catch (JsonIOException e) {
			System.out.print(e);
			e.printStackTrace();
		} 
  
		
		
		

		return this.buscarid(counter.get()  );
	}




	public List<Proyecto> getLista() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	public void updatejson() throws Exception {
		try(FileWriter fw=new FileWriter("Proyectos.json")) {
			gson.toJson(lista,fw);
		} catch (JsonIOException e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}
	
	public void borrar(long id) throws IOException {
		
		lista.removeIf(obj -> obj.id == id);
		try {
			
			this.updatejson();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

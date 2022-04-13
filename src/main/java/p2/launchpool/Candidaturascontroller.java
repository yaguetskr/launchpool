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

public class Candidaturascontroller {
	private static List<Candidatura> lista=new ArrayList();
	private static final Gson gson=new Gson();
	private static Candidaturascontroller self=null;
	private final AtomicLong counter=new AtomicLong();
	
	private Candidaturascontroller() throws Exception {
		lista.addAll(this.leerjson());
		
	}
	
	
	
	public static Candidaturascontroller controller() throws Exception {
		if(self==null) {
			self=new Candidaturascontroller();
		}
		return self;
		
	}
	
	
	public List<Candidatura> leerjson() throws Exception {
		
		try(Reader reader = new FileReader("Candidaturas.json")){
			return Arrays.asList(gson.fromJson(reader, Candidatura[].class));
			
		}catch(Exception e) {
			
		}
		return new ArrayList();
			
		
		
			
		
	}
	
	public void clear() throws Exception {
		lista=new ArrayList();
		new FileWriter("Candidaturas.json", false).close();
		counter.set(0);
		
	}
	
	
	public List<Candidatura> buscar(String id) throws IOException {
		
		List<Candidatura> list=new ArrayList();
		
		for (Candidatura U:lista) {
			if(  U.getIduser().equals(id) ) {
				list.add(U);
			}
			
	}
		return list;
	}
	
	public Candidatura buscarid(long id) {
		// TODO Auto-generated method stub
		for (Candidatura U:lista) {
			if(  U.getId()==id ) {
				return U;
			}
			
	}
		return null;
	}
	
	
	public Candidatura crear(String nombre, String iduser,  String tlf, String email, String sobremi,
			String especialidades) throws Exception{
		
		counter.incrementAndGet();
		while(self.buscarid(  counter.get()  )!=null) {
			counter.incrementAndGet();
		}
		
		lista.add(new Candidatura(counter.get(),nombre,iduser, tlf, email,sobremi,especialidades));
		try(FileWriter fw=new FileWriter("Candidaturas.json")) {
			gson.toJson(lista,fw);
		} catch (JsonIOException e) {
			System.out.print(e);
			e.printStackTrace();
		} 
  
		
		
		

		return this.buscarid(counter.get()  );
	}




	public List<Candidatura> getLista() {
		// TODO Auto-generated method stub
		return lista;
	}
	
	public void updatejson() throws Exception {
		try(FileWriter fw=new FileWriter("Candidaturas.json")) {
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

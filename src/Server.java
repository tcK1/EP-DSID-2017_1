import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.UUID;

public class Server implements PartRepository{

	LinkedList<Part> parts;
	
	public Server(){
		this.parts = new LinkedList<Part>();
	}
	
	public void addPart(Part part) throws Exception{
		parts.add(part);
	}
	
	public Part getPart(String code) throws Exception{
		return getPart(UUID.fromString(code));		
	}
	
	public Part getPart(UUID id) throws Exception{
		for(Part part : parts){
			if(part.code == id)
				return part;
		}
		return null;
	}
	
	public void listParts() throws Exception{
		if(parts.isEmpty())
			System.err.println("Repository is empty.");
		else for(Part part : parts){
			System.out.println(part.toString());
		}
	}
		
	public static void main(String[] args){
		try{
			Server object = new Server();
			PartRepository repo = (PartRepository)UnicastRemoteObject.exportObject(object, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind("repo", repo);
		}
		catch(Exception e){
			System.err.println("Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
}

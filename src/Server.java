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
			if(id.equals(part.code))
				return part;
		}
		return null;
	}
	
	public String listParts() throws Exception{
		if(parts.isEmpty())
			return "Repository is empty.";
		else{
			StringBuilder s = new StringBuilder();
			for(Part part : parts){
				s.append(part.toString()).append("\n");
			}
			return s.toString();
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

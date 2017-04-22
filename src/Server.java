import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.UUID;

public class Server implements PartRepository{

	String name;
	LinkedList<Part> parts;
	
	public Server(String name){
		this.name = name;
		this.parts = new LinkedList<Part>();
	}
	
	public void addPart(String name, String description, LinkedList<PartQuantity> subparts) throws Exception{
		parts.add(new Part(name, description, subparts, this.name));
	}
	
	public int countParts() throws Exception{
		return parts.size();
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
			return null;
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
			String name = InetAddress.getLocalHost().getHostName();
			
			Server object = new Server(name);
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

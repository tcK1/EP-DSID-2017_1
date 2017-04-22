import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

public class Server implements PartRepository{

	LinkedList<Part> parts;
	String name;
	
	public Server(){
		this.parts = new LinkedList<Part>();
	}
	
	public void addPart(String partName, String partDescription, LinkedList<PartQuantity> subparts) throws Exception{
		Part part = new Part(partName, partDescription, subparts, name);
		parts.add(part);
	}
	
	public int countParts() throws Exception{
		return parts.size();
	}
	
	public String getName() throws Exception{
		return name;
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
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Please enter registry's host: ");
			String registryHost = scanner.nextLine();
			Registry registry = LocateRegistry.getRegistry(registryHost);
			
			System.out.print("Please enter server's name: ");
			String name = scanner.nextLine();
			PartRepository repo = (PartRepository)UnicastRemoteObject.exportObject(new Server());
			registry.bind(name, repo);
			
			scanner.close();
		}
		catch(Exception e){
			System.err.println("Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
}

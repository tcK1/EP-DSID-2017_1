import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server implements PartRepository{

	ArrayList<Part> parts;
	
	public Server(){
		this.parts = new ArrayList<Part>();
	}
	
	public void listParts(){
		for(Part part : parts){
			System.out.println(part.toString());
		}
	}
	
	public static void main(String[] args){
		try{
			Server object = new Server();
			PartRepository repo = (PartRepository)UnicastRemoteObject.exportObject(object, 0);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("repo", repo);
		}
		catch(Exception e){
			System.err.println("Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
}

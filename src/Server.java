import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements PartRepository{

	public Server(){}
	
	public static void main(String[] args){
		try{
			Server server = new Server();
			PartRepository repo = (PartRepository)UnicastRemoteObject.exportObject(server);
			
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("repo", repo);
		}
		catch(Exception e){
			System.err.println("Exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
}

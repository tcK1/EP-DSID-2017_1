import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public class RepositoryRegistry implements Registry{
	
	private HashMap<String, Remote> repos = new HashMap<String, Remote>();
	
	public RepositoryRegistry(){
		try{
			System.out.println(InetAddress.getLocalHost().getHostName());
		}
		catch(UnknownHostException e){
			System.err.println("Unknown hostname.");
		}
	}
	
	public void bind(String name, Remote repo) throws RemoteException{
		if(repo instanceof PartRepository){
			repos.put(name, repo);
		}
		else
			throw new RemoteException("RMI service is not of type PartRepository.");
	}
	
	public String[] list(){
		return (String[])repos.keySet().toArray();
	}

	public Remote lookup(String name){
		return repos.get(name);
	}
	
	public void rebind(String name, Remote repo) throws RemoteException{
		bind(name, repo);
	}
	
	public void unbind(String name){
		repos.remove(name);
	}
}

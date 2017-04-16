import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client{

	static Scanner scanner = new Scanner(System.in);
	
	static Part currentPart;
	static PartRepository currentRepo;
	
	/*TODO*/
	static void bind() throws Exception{
        System.out.print("Please, enter host: ");
        String host = scanner.nextLine();
		Registry registry = LocateRegistry.getRegistry(host);
		currentRepo = (PartRepository)registry.lookup("repo");
	}
	
	static void getPart() throws Exception{
		System.out.print("Please, enter part's code: ");
		String code = scanner.nextLine();
		currentPart = currentRepo.getPart(code);
	}
	
	static void listParts() throws Exception{
		if(currentRepo == null)
			System.err.println("No repository selected.");
		else
			currentRepo.listParts();
	}
	
    public static void main(String[] args){
    	try{
    		bind();
    		while(true){
                String line = scanner.nextLine();
                switch(line){
                    case "rebind":
                    	bind();
                        break;
                    case "listp":
                    	listParts();
                        break;
                    case "getp":
                    	getPart();
                        break;
                    case "showp":
                        break;
                    case "clearlist":
                        break;
                    case "addsubpart":
                        break;
                    case "addp":
                        break;
                    case "quit":
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.err.println(String.format("Command not found: '%s'.", line));
                        break;
                }
            }
    	}
    	catch(Exception e){
    		System.err.println("Exception: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
}
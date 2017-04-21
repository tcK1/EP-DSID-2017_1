import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

public class Client{

	static Scanner scanner = new Scanner(System.in);
	
	static Part currentPart;
	static PartRepository currentRepo;
	static Registry registry;
	static LinkedList<PartQuantity> subparts = new LinkedList<PartQuantity>();
	
	static void addPart() throws Exception{
		if(currentRepo == null){
			System.err.println("No repository selected.");
			return;
		}
		System.out.print("Please, enter part's name: ");
		String name = scanner.nextLine();
		System.out.print("Please, enter part's description: ");
		String description = scanner.nextLine();
		currentRepo.addPart(new Part(name, description, subparts));
	}
	
	static void addSubpart() throws Exception{
		if(currentPart == null){
			System.err.println("No part selected.");
			return;
		}
		System.out.print("Please, enter quantity: ");
		int quantity = scanner.nextInt();
		subparts.add(new PartQuantity(currentPart, quantity));
	}
	
	static void clearList() throws Exception{
		subparts.clear();
	}
	
	static void countParts() throws Exception{
		if(currentRepo == null){
			System.err.println("No repository selected.");
			return;
		}
		int count = currentRepo.countParts();
		if(count == 0)
			System.err.println("Repository is empty.");
		else
			System.out.println("Repository contains " + count + " part(s).");
	}
	
	static void countSubparts(){
		if(currentPart == null){
			System.err.println("No part selected.");
			return;
		}
		int count = currentPart.countSubparts();
		if(count == 0)
			System.out.println("Part is primitive.");
		else
			System.out.println("Part is aggregate, and contains " + count + " subpart(s).");
	}
	
	static void getPart() throws Exception{
		if(currentRepo == null){
			System.err.println("No repository selected.");
			return;
		}
		System.out.print("Please, enter part's code: ");
		String code = scanner.nextLine();
		try{
			UUID id = UUID.fromString(code);
			currentPart = currentRepo.getPart(id);
			if(currentPart == null)
				System.err.println("Part not found on this server.");
		}
		catch(IllegalArgumentException e){
			System.err.println("Invalid UUID.");
		}
	}
	
	static void getRegistry() throws Exception{
        System.out.print("Please, enter registry's host: ");
        String host = scanner.nextLine();
		registry = LocateRegistry.getRegistry(host);
		listRepos();
	}
	
	static void listParts() throws Exception{
		if(currentRepo == null)
			System.err.println("No repository selected.");
		else{
			String list = currentRepo.listParts();
			if(list == null)
				System.err.println("Repository is empty.");
			else
				System.out.print(currentRepo.listParts());
		}
	}
	
	static void listRepos() throws Exception{
		String[] names = registry.list();
		for(String name : names)
			System.out.println(name);
	}
	
	static void listSubparts() throws Exception{
		if(subparts.isEmpty())
			System.err.println("Subparts list is empty.");
		else for(PartQuantity subpart: subparts)
			System.out.println(subpart.toString());
	}
	
	static void serverLookup() throws Exception{
        System.out.print("Please, enter repository's name: ");
        String name = scanner.nextLine();
		currentRepo = (PartRepository)registry.lookup("repo");
		if(currentRepo == null)
			System.err.println("No repository found.");
		else
	        System.setProperty("java.rmi.server.hostname", name);
	}	
	
	static void showPartInfo() throws Exception{
		if(currentPart == null)
			System.err.println("No part selected.");
		else
			System.out.print(currentPart.showPartInfo());
	}
	
	static void whereIsPart() throws Exception{
		if(currentPart == null)
			System.err.println("No part selected.");
		else{
			String repo = currentPart.getRepo();
			System.out.println("Part is in '" + repo + "' repository.");
		}
	}

	static void whoIsRepo() throws Exception{
		if(currentRepo == null)
			System.err.println("No repository selected.");
		else
			System.out.println(currentRepo.getName());
	}
	
    public static void main(String[] args){
    	try{
    		getRegistry();
    		serverLookup();
    		while(true){
                String line = scanner.nextLine();
                switch(line){
                	case "addPart":
                		addPart();
                		break;
                	case "addSubpart":
                		addSubpart();
                		break;
                	case "clearList":
                		clearList();
                		break;
                	case "countParts":
                		countParts();
                		break;
                	case "countSubparts":
                		countSubparts();
                		break;
                	case "getPart":
                		getPart();
                		break;
                	case "listParts":
                		listParts();
                		break;
                	case "listRepos":
                		listRepos();
                		break;
                	case "listSubparts":
                		listSubparts();
                		break;
                	case "serverLookup":
                		serverLookup();
                		break;                		
                	case "showPart":
                		showPartInfo();
                		break;
                	case "whereIsPart":
                		whereIsPart();
                		break;
                	case "whoIsRepo":
                		whoIsRepo();
                		break;
                	case "quit":
                		scanner.close();
                		System.exit(0);
                	case "":
                		break;
                    default:
                        System.err.println("Command not found: '" + line + "'.");
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
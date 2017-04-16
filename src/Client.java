import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Scanner;

public class Client{

	static Scanner scanner = new Scanner(System.in);
	
	static Part currentPart;
	static PartRepository currentRepo;
	static LinkedList<PartQuantity> subparts = new LinkedList<PartQuantity>();
	
	static void addPart() throws Exception{
		System.out.print("Please, enter part's name: ");
		String name = scanner.nextLine();
		System.out.print("Please, enter part's description: ");
		String description = scanner.nextLine();
		currentRepo.addPart(new Part(name, description, subparts));
	}
	
	static void addSubpart() throws Exception{
		System.out.print("Please, enter quantity: ");
		int quantity = scanner.nextInt();
		subparts.add(new PartQuantity(currentPart, quantity));
	}
	
	static void bind() throws Exception{
        System.out.print("Please, enter host: ");
        String host = scanner.nextLine();
		Registry registry = LocateRegistry.getRegistry(host);
		currentRepo = (PartRepository)registry.lookup("repo");
	}
	
	static void clearList() throws Exception{
		subparts.clear();
	}
	
	static void getPart() throws Exception{
		System.out.print("Please, enter part's code: ");
		String code = scanner.nextLine();
		currentPart = currentRepo.getPart(code);
	}
	
	static void showPartInfo() throws Exception{
		if(currentPart == null)
			System.err.println("No part selected.");
		else
			currentPart.showPartInfo();
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
                    	showPartInfo();
                        break;
                    case "clearlist":
                    	clearList();
                        break;
                    case "addsubpart":
                    	addSubpart();
                        break;
                    case "addp":
                    	addPart();
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
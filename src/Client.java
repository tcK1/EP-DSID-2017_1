import java.util.Scanner;

public class Client{

	static Scanner scanner = new Scanner(System.in);
	
	/*TODO*/
	static void bind(){
        System.out.print("Please, enter host: ");
        String host = scanner.nextLine();
		System.err.println(host);
	}
	
    public static void main(String[] args){
    	bind();
        while(true){
            String line = scanner.nextLine();
            switch(line){
                case "rebind":
                	bind();
                    break;
                case "listp":
                    break;
                case "getp":
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
}
import java.util.ArrayList;
import java.util.UUID;

public class Part{

	UUID code;
	String name;
	String description;
	ArrayList<PartQuantity> parts;
	
	public Part(String name, String description){
		this.code = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.parts = new ArrayList<PartQuantity>();
	}
	
	public void showPartInfo(){
		System.out.println(String.format("[%s] %s", code, name));
		System.out.println(String.format("\t%s", description));
		if(!parts.isEmpty()){
			System.out.println("\tSubparts: [");
			for(PartQuantity tuple : parts){
				System.out.println(String.format("\t\t%s", tuple.toString()));
			}
			System.out.println("\t]");
		}
	}
	
	public String toString(){
		return String.format("%s %s", code, name);
	}
	
}
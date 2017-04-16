import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class Part implements Serializable{

	static final long serialVersionUID = 1L;
	UUID code;
	String name;
	String description;
	LinkedList<PartQuantity> parts;
	
	public Part(String name, String description, LinkedList<PartQuantity> parts){
		this.code = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.parts = parts;
	}
	
	public void showPartInfo(){
		System.out.println(this.toString());
		System.out.println("\tDescription: " + description);
		if(!parts.isEmpty()){
			System.out.println("\tSubparts: [");
			for(PartQuantity tuple : parts){
				System.out.println("\t\t" + tuple.toString());
			}
			System.out.println("\t]");
		}
	}
	
	public String toString(){
		return name + " [" + code.toString() + "]";
	}
	
}
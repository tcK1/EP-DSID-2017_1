import java.util.ArrayList;
import java.util.UUID;

public class Part {

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
	
}
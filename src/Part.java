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
	
	public String showPartInfo(){
		StringBuilder s = new StringBuilder(this.toString());
		s.append("\n").append("\tDescription: ").append(description).append("\n");
		if(!parts.isEmpty()){
			s.append("\tSubparts(").append(parts.size()).append("): [").append("\n");
			for(PartQuantity tuple : parts){
				s.append("\t\t").append(tuple.toString()).append("\n");
			}
			s.append("\t]");
		}
		return s.toString();
	}
	
	public String toString(){
		return name + " [" + code.toString() + "]";
	}
	
}
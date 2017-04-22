import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class Part implements Serializable{

	static final long serialVersionUID = 1L;
	UUID code;
	String name;
	String description;
	LinkedList<PartQuantity> parts;
	String repo;
	
	public Part(String name, String description, LinkedList<PartQuantity> parts, String repo){
		this.code = UUID.randomUUID();
		this.name = name;
		this.description = description;
		this.parts = parts;
		this.repo = repo;
	}
	
	public int countSubparts(){
		int count = 0;
		for(PartQuantity tuple : parts)
			count += tuple.quantity;
		return count;
	}
	
	public String getRepo(){
		return this.repo;
	}
	
	public String showPartInfo(){
		StringBuilder s = new StringBuilder(this.toString());
		s.append("\n").append("\tDescription: ").append(description).append("\n");
		if(!parts.isEmpty()){
			s.append("\tSubparts(").append(parts.size()).append("): [").append("\n");
			for(PartQuantity tuple : parts){
				s.append("\t\t").append(tuple.toString()).append("\n");
			}
			s.append("\t]").append("\n");
		}
		return s.toString();
	}
	
	public String toString(){
		return name + " [" + code.toString() + "]";
	}
	
}
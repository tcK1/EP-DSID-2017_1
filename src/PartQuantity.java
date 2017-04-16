public class PartQuantity {

	Part part;
	int quantity;
	
	public PartQuantity(Part part, int quantity){
		this.part = part;
		this.quantity = quantity;
	}
	
	public String toString(){
		return String.format("%dx\t%s [%s]", quantity, part.name, part.code);
	}
}

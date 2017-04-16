public class PartQuantity {

	Part part;
	int quantity;
	
	public PartQuantity(Part part, int quantity){
		this.part = part;
		this.quantity = quantity;
	}
	
	public String toString(){
		return "<" + part.code + ": " + part.name + " x " + quantity + ">";
	}
}

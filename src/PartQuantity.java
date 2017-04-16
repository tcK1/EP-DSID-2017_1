public class PartQuantity {

	Part part;
	int quantity;
	
	public PartQuantity(Part part, int quantity){
		this.part = part;
		this.quantity = quantity;
	}
	
	public String toString(){
		return quantity + "x\t" + part.toString();
	}
}

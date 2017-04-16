import java.rmi.Remote;
import java.util.UUID;

public interface PartRepository extends Remote{
	
	void addPart(Part part);
	Part getPart(String code);
	Part getPart(UUID id);
	
	void listParts();
}

import java.rmi.Remote;
import java.util.UUID;

public interface PartRepository extends Remote{
	
	void addPart(Part part) throws Exception;
	Part getPart(String code) throws Exception;
	Part getPart(UUID id) throws Exception;
	void listParts() throws Exception;
}

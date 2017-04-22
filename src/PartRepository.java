import java.rmi.Remote;
import java.util.LinkedList;
import java.util.UUID;

public interface PartRepository extends Remote{
	
	void addPart(String partName, String partDescription, LinkedList<PartQuantity> subparts) throws Exception;
	int countParts() throws Exception;
	String getName() throws Exception;
	Part getPart(String code) throws Exception;
	Part getPart(UUID id) throws Exception;
	String listParts() throws Exception;
}

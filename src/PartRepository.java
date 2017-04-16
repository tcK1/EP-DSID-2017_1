import java.rmi.Remote;
import java.util.UUID;

public interface PartRepository extends Remote{
	
	Part getPart(String code);
	Part getPart(UUID id);
	
	void listParts();
}

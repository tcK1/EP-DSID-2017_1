import java.rmi.Remote;

public interface PartRepository extends Remote{
	
	void listParts();
}

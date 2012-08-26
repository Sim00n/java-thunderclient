import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws IOException {
		
		Thunder thunder = new Thunder("localhost", 8080, "key", "secret");		
		System.out.println(thunder.getUserInChannel("default-channel"));
		System.out.println(thunder.getUserCount());
	}	
}

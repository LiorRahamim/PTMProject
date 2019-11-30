
import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.Server;

public class RunProj {

	public static void main(String[] args) {
		Server s;
		int port = 8000;
		
		s = new MySerialServer();
		s.start(port, new MyTestClientHandler());
	}

}

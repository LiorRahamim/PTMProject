package boot;

import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.Server;

public class Main {
	
	static Server s;
	
	public static void main(String[] args) {
		s=new MySerialServer(Integer.parseInt(args[0])); 
		s.start(new MyTestClientHandler());
	}
}

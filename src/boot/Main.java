package boot;

import server_side.MySerialServer;
import server_side.MyTestClientHandler;
import server_side.Server;

public class Main {
	
	static Server s;
	
	public static void main(String[] args) {
		s=new MySerialServer(); 
		s.start(Integer.parseInt(args[0]), new MyTestClientHandler());
	}
}

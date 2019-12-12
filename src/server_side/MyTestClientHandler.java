package server_side;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MyTestClientHandler implements ClientHandler {
	
	CacheManager cm;
	Solver solver;
	
	@Override
	public void handleClient(InputStream inputStream, OutputStream outputStream) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter out = new PrintWriter(outputStream);
				
		String line;
		
		try {
			line = in.readLine();
			while (!line.equals("end")) {
				out.println(new StringBuilder(line).reverse().toString());
				out.flush();
				
				line = in.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

package server_side;

import java.net.*;
import java.io.*;

public class MySerialServer implements Server {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private volatile boolean stop;
    
    public MySerialServer() {
    	this.serverSocket = null;
    	this.clientSocket = null;
    	stop = false;
    }
	
    @Override
    public void start(int port, ClientHandler ch) {
    	new Thread(()->{
			try {
				runServer(port, ch);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
    }
    
    @Override
    public void stop() {
    	stop = true;
    }
    
    private void runServer(int port, ClientHandler ch) throws Exception {
    	serverSocket = new ServerSocket(port);
    	serverSocket.setSoTimeout(3000);
    	while(!stop) {
    		try {
    			clientSocket = serverSocket.accept();
    			try {
    				ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream());
    				
    				clientSocket.close();
    			}
    			catch(IOException e) {
    				System.out.println("PROBLEM- MySerialServer line 57");
    				e.printStackTrace();
    				// TODO: handle exception
    			}
    		}
    		catch (SocketTimeoutException e) {
				// TODO: handle exception
			}
    	}
    	serverSocket.close();
    }
}


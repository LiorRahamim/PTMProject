package server_side;

import java.net.*;
import java.io.*;

public class MySerialServer implements Server {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private volatile boolean stop;
    private int port;
    
    public MySerialServer() {
    	this.serverSocket = null;
    	this.clientSocket = null;
    	stop = false;
    }
	
    public MySerialServer(int port) {
    	this.port = port;
	}

	@Override
    public void start(ClientHandler ch) {
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


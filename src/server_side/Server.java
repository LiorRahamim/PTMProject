package server_side;

public interface Server {
	void start(ClientHandler c);
	void stop();
}

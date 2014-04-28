package fb.server;

public class AirlineServer {
	public static void main(String[] args) {
		AirlineServerHandler handler = new AirlineServerHandler();
		new Thread(handler).start();
	}
}

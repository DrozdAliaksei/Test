import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
    
	
	private Socket socket;

	public Connection(String ip, int port) throws IOException {
		this.socket = new Socket(ip,port);
	}

	public void closeConnection() {
		try {
            out.writeObject("close");
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	
	public void sendMassage(String str) throws IOException {
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
            toServer.println(str);
    }

    public String receiveMassage()throws IOException{
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String result = fromServer.readLine();
        return result;
    }
}

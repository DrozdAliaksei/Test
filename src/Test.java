import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Test {

	interface CallBack<T>{
		void callingBack(T data);
	}
	
	public static void getPinsStatus(CallBack <List<Integer>> callback) {
		Thread thread = new Thread(){
		    public void run(){
		    	List<Integer> controllers = new ArrayList <Integer>();
		    	try {
		    		// call socket
					Thread.sleep(500);
					 controllers.add(1);
					 controllers.add(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	callback.callingBack(controllers);

		    }
		  };
		  thread.start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	/*	getPinsStatus(new CallBack<List<Integer>>() {
			@Override
			public void callingBack(List<Integer> data) {
				// TODO Auto-generated method stub
				for(int i = 0; i< data.size();i++) {
					System.out.println(data.get(i));
				}
			}
		});
		Thread.sleep(2000);
	*/

	String ip = "192.168.100.14";
	int port = 5050;

        try {
            Socket socket = new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

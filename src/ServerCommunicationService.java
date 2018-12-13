import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Short.valueOf;


public class ServerCommunicationService {

    interface CallBack<T> {
        void callingBack(T data);
    }

    public static void checkConnection(String ip, int port, CallBack<Boolean> callBack) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean status = true;
                Connection connection = null;
                try {
                        connection = new Connection(ip, port);
                } catch (UnknownHostException e) {
                    System.out.println("checkConnection UnknownHostException");
                    status = false;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("checkConnection IOException");
                    status = false;
                } finally {
                    if (connection != null) {
                        connection.closeConnection();
                    }
                }
                callBack.callingBack(status);
            }
        };
        thread.start();
    }

    public void getPinsStatus(String ip, int port, CallBack<List<Controller>> callback) {
        Thread thread = new Thread() {
            public void run() {
                List<Controller> controllers = new ArrayList<>();
                Connection connection = null;// call socket
                try {
                    connection = new Connection(ip, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    connection.sendMassage("getPinsStatus");
                    String controllersCount = connection.receiveMassage();
                    for (int i = 0; i < valueOf(controllersCount); i++) {
                        String controllerInfo = connection.receiveMassage();
                        parseItems(controllers, controllerInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                callback.callingBack(controllers);

            }
        };
        thread.start();
    }

    private void parseItems(List<Controller> controllers, String contrillerInfo) {
        String separator = ";";
        String[] info = contrillerInfo.split(separator);
        Controller controller = new Controller();
        controller.setID(Math.toIntExact(valueOf(info[1])));
        controller.setName(info[2]);
        controller.setGPIO(Math.toIntExact(valueOf(info[3])));
        controller.setStatus(Boolean.parseBoolean(info[4]));
        controllers.add(controller);
    }

}

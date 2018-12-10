
public class Main {

    public static void main(String[] args) throws InterruptedException {
        String ip = "192.168.100.14";
        int port = 5050;
        System.out.println("Main class");

        ServerCommunicationService.checkConnection(ip,port, new ServerCommunicationService.CallBack<Boolean>(){
            @Override
            public void callingBack(Boolean data) {
                if(data == true){
                    System.out.println("Connection done");
                }else {
                    System.out.println("Connection failed");
                }
            }
        });
        Thread.sleep(2000);
    }
}


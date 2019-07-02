import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestSocket {

    public static void main(String[] args) {
        Socket socket = null;
        String host = "127.0.0.1";
        for (int i = 0; i <= 1024; i++) {
            try {
                socket = new Socket(host,i);
                System.out.println("This is a server on "+i+" of 127.0.0.1");
                socket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                break;
            }
        }

    }
}

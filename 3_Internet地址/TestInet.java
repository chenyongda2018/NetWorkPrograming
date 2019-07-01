import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInet {

    public static void main(String[] args) {
        InetAddress inetAddress = null;
//        try {
//            inetAddress = InetAddress.getByName("www.oreilly.com");
//            System.out.println(inetAddress.getHostAddress());
//        } catch (UnknownHostException e) {
//            System.out.println("找不到地址");
//        }
        try {
            inetAddress = InetAddress.getByName("localhost");
            System.out.println(inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


    }
}

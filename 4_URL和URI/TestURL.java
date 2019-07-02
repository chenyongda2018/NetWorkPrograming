import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TestURL {
    public static void main(String[] args) {

        InputStream in = null;

        try {
            URL url = new URL("https://item.jd.com/10380592313.html");
            URLConnection connection = url.openConnection();
            in = connection.getInputStream();
            in = new BufferedInputStream(in);
            Reader reader = new InputStreamReader(in,"gbk");
            int c = 0;
            while ((c = reader.read()) != -1) {
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}

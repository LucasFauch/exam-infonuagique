import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        Stub s = new Stub("localhost", 8080);
        int r = s.addition(3,4);
        System.out.println(r);
        s.disconnect();
    }
}
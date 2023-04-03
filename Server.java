import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Skeleton s = new Skeleton(8080);
        s.receiveLoop();
    }
}

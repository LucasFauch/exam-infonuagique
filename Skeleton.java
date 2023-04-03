import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Skeleton {
    ServerSocket serverSocket;
    OutputStream outputStream;
    InputStream inputStream;
    ObjectInputStream objectInputStream;
    Calculatrice calc;

    Skeleton(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.calc = new Calculatrice();
    }

    public void sendResult(int result) throws IOException {
        outputStream.write(Integer.toString(result).getBytes());
    }

    public void receiveLoop() throws IOException, ClassNotFoundException {
        while(true) {
            Socket clientSocket = serverSocket.accept();
            this.outputStream = clientSocket.getOutputStream();
            this.inputStream = clientSocket.getInputStream();
            this.objectInputStream = new ObjectInputStream(this.inputStream);

            Message msg = (Message) this.objectInputStream.readObject();

            switch (msg.method) {
                case "ADD" -> this.sendResult(calc.addition(msg.a, msg.b));
                case "SUB" -> this.sendResult(calc.subtraction(msg.a, msg.b));
                case "MUL" -> this.sendResult(calc.multiplication(msg.a, msg.b));
                default -> {
                    System.out.println("ERROR");
                }
            }
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Skeleton {
    ServerSocket serverSocket;
    OutputStream outputStream;
    InputStream inputStream;
    Calculatrice calc;

    Skeleton(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.calc = new Calculatrice();
    }

    public void sendResult(int result) throws IOException {
        outputStream.write(Integer.toString(result).getBytes());
    }

    public void receiveLoop() throws IOException {
        while(true) {
            Socket clientSocket = serverSocket.accept();
            this.outputStream = clientSocket.getOutputStream();
            this.inputStream = clientSocket.getInputStream();

            byte[] message = new byte[1024];
            int length = inputStream.read(message);
            String str = new String(message, 0, length);

            String operation = str.substring(0, 3);
            String[] operands = str.substring(4).split("-");

            int a = Integer.parseInt(operands[0]);
            int b = Integer.parseInt(operands[1]);

            switch (operation) {
                case "ADD" -> this.sendResult(calc.addition(a, b));
                case "SUB" -> this.sendResult(calc.subtraction(a, b));
                case "MUL" -> this.sendResult(calc.multiplication(a, b));
                default -> {
                    System.out.println("ERROR");
                }
            }

            //clientSocket.close();
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Stub implements CalculatriceItf{

    Socket socket;
    OutputStream outputStream;
    InputStream inputStream;
    ObjectOutputStream objectOutputStream;

    Stub(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.outputStream = this.socket.getOutputStream();
        this.inputStream = this.socket.getInputStream();

        this.objectOutputStream = new ObjectOutputStream(this.outputStream);
    }

    public void sendComputation(Message msg) throws IOException {
        objectOutputStream.writeObject(msg);
    }

    public int receiveResult() throws IOException {
        byte[] message = new byte[1024];
        int length = inputStream.read(message);
        String str =  new String(message, 0, length);
        return Integer.parseInt(str);
    }

    public void disconnect() throws IOException {
        this.socket.close();
    }

    @Override
    public int addition(int a, int b) throws IOException {
        Message msg = new Message("ADD", a, b);
        this.sendComputation(msg);
        return this.receiveResult();
    }

    @Override
    public int subtraction(int a, int b) throws IOException {
        Message msg = new Message("SUB", a, b);
        this.sendComputation(msg);
        return this.receiveResult();
    }

    @Override
    public int multiplication(int a, int b) throws IOException {
        Message msg = new Message("MUL", a, b);
        this.sendComputation(msg);
        return this.receiveResult();
    }
}

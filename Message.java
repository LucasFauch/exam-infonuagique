import java.io.Serializable;

public class Message implements Serializable {
    String method;
    int a;
    int b;

    Message(String method, int a, int b){
        this.method = method;
        this.a = a;
        this.b = b;
    }
}

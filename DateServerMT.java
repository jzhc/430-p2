import java.net.*;
import java.io.*;

public class DateServerMT {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java DateServerMT <port>");
            System.exit(1);
        }
        
        int port = Integer.parseInt(args[0]);

        try (ServerSocket sock = new ServerSocket(port)) {
            System.out.println("DateServerMT listening on port " + port);
            
            while (true) {
                Socket client = sock.accept();
                Thread t = new Thread(new Worker(client));
                t.start();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
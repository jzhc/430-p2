import java.net.*;
import java.io.*;

public class Worker implements Runnable {
    private Socket client;

    public Worker(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            pout.println(new java.util.Date().toString());
            
            client.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
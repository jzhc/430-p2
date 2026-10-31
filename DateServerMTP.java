import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class DateServerMTP {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java DateServerMTP <port>");
            System.exit(1);
        }
        
        int port = Integer.parseInt(args[0]);
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket sock = new ServerSocket(port)) {
            System.out.println("DateServerMTP listening on port " + port);
            
            while (true) {
                Socket client = sock.accept();
                pool.execute(new Worker(client));
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            pool.shutdown();
        }
    }
}
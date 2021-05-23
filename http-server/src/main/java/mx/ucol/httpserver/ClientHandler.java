package mx.ucol.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Import the File class
import java.io.File; 
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
  final Socket socket;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    PrintWriter output = null;
    BufferedReader input = null;

    try {
      output = new PrintWriter(socket.getOutputStream(), true);
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String received;
      while ((received = input.readLine()) != null) {
        String requestArray[] = received.split(" ");

        if (requestArray[0].equals("GET")) {
          // Get the resource name and read its contents in the /www folder
          // If the resource equals "/" it should open index.html

          if (requestArray[1].equals("/index.html")||requestArray[1].equals("/about.html")){ 
            System.out.println("Resource: " + requestArray[1]);

            // Update the htmlResponse variable with the file contents
            File res = new File("./www" + requestArray[1]);
            String htmlResponse = readContent(res);            
            int contentLength = htmlResponse.length();
            
            // This line should not be modified just yet
            output.write("HTTP/1.1 200 OK\r\nContent-Length: " + String.valueOf(contentLength) + "\r\n\r\n" + htmlResponse);

            // We already sent the response, break the loop
            break;
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        output.close();
        input.close();
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public static String readContent(File myFile){
    try {
      String data = new String(Files.readAllBytes(Paths.get(myFile.getAbsolutePath())));
      return data;
    } catch (IOException e) {
      System.out.println("An error occurred."+ e.getMessage());
      return "";
    }
  }
}
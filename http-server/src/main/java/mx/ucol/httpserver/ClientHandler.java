package mx.ucol.httpserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// Import the File class
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
  final Socket socket;

  public ClientHandler(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    DataOutputStream output = null;
    BufferedReader input = null;

    try {
      output = new DataOutputStream(socket.getOutputStream());
      input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      String received;
      while ((received = input.readLine()) != null) {
        String requestArray[] = received.split(" ");

        if (requestArray[0].equals("GET")) {
          System.out.println("Resource: " + requestArray[1]);

          String resouceName = requestArray[1].equals("/") ? "/index.html" : requestArray[1];
          String resourcePath = "./www" + resouceName;
          Path filePath = Paths.get(resourcePath);

          boolean fileExists = Files.exists(filePath, LinkOption.NOFOLLOW_LINKS);

          if (!fileExists) {
            filePath = Paths.get("./www/notFound.html");
          }

          String response = null;
          byte[] fileContent = null;
          int contentLength = 0;

          if (fileExists) {
            response = "HTTP/1.1 200 OK\r\n";
          } else {
            response = "HTTP/1.1 404\r\n";
          }
          fileContent = Files.readAllBytes(filePath);
          contentLength = fileContent.length;
          String mimeType = Files.probeContentType(filePath);
          System.out.println("MIME type: " + mimeType);

          response += "Content-Type: " + mimeType + "\r\n";
          response += "Content-Length: " + String.valueOf(contentLength) + "\r\n\r\n";

          output.writeBytes(response);
          output.write(fileContent, 0, contentLength);
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

  public static String readContent(File myFile) {
    try {
      String data = new String(Files.readAllBytes(Paths.get(myFile.getAbsolutePath())));
      return data;
    } catch (IOException e) {
      System.out.println("An error occurred." + e.getMessage());
      return "";
    }
  }
}
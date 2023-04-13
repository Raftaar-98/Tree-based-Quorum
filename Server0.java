/*******************************************************************************************/
/*******************************************************************************************/
/************ Author: Shishir Sunil Yalburgi                             *******************/
/************ NETID: ssy220000                                           *******************/
/************ Email: ssy220000@utdallas.edu                              *******************/
/************ Server0.java                                               *******************/
/************ This file implements Server 0 for tree based quorum        *******************/
/*******************************************************************************************/
/*******************************************************************************************/

package S0;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Server0 {

  public static boolean completed1 = false;// Flag to check if Client 1 is completed
  public static boolean completed2 = false;// Flag to check if Client 1 is completed
  public static boolean completed3 = false;// Flag to check if Client 1 is completed
  public static boolean completed4 = false;// Flag to check if Client 1 is completed
  public static boolean completed5 = false;// Flag to check if Client 1 is completed
  
  public static OutputStream out11 = null;
  public static OutputStream out12 = null;
  public static OutputStream out13 = null;
  public static OutputStream out14 = null;
  public static OutputStream out15 = null;
  
  
  class c1thread extends Thread {
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() {
      InputStream in = null;
      byte[] inputByte = new byte[300];
      String inputString;
      String message;
      try {
        System.out.println("Waiting for Client 1");
        server = new ServerSocket(11000); // Connect with Client 1
        socketServer = server.accept();
        System.out.println("Connected with Client 1");
        in = socketServer.getInputStream();
        out11 = socketServer.getOutputStream();
        in.read(inputByte); // Get message
        inputString = new String(inputByte);

        StringTokenizer st = new StringTokenizer(inputString, " "); // Tokenize the string message

        if (st.hasMoreTokens()) {
          message = new String(st.nextToken());
        } else {
          message = new String();
        }
        if (message.contains("COMPLETE")) { // If the message contains complete
          System.out.println("C1 Complete");
          completed1 = true; // Set flag as 1
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
    }
  }
  class c2thread extends Thread {
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() {
      InputStream in = null;
      byte[] inputByte = new byte[300];
      String inputString;
      String message;
      try {
        System.out.println("Waiting for Client 2");
        server = new ServerSocket(11001);
        socketServer = server.accept();
        System.out.println("Connected with Client 2");
        in = socketServer.getInputStream();
        out12 = socketServer.getOutputStream();
        in.read(inputByte);
        inputString = new String(inputByte);

        StringTokenizer st = new StringTokenizer(inputString, " ");

        if (st.hasMoreTokens()) {
          message = new String(st.nextToken());
        } else {
          message = new String();
        }
        if (message.contains("COMPLETE")) {
          System.out.println("C2 Complete");
          completed2 = true;
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
    }
  }
  class c3thread extends Thread {
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() {
      InputStream in = null;
      byte[] inputByte = new byte[300];
      String inputString;
      String message;
      try {
        System.out.println("Waiting for Client 3");
        server = new ServerSocket(11002);
        socketServer = server.accept();
        System.out.println("Connected with Client 3");
        in = socketServer.getInputStream();
        out13 = socketServer.getOutputStream();
        in.read(inputByte);
        inputString = new String(inputByte);

        StringTokenizer st = new StringTokenizer(inputString, " ");

        if (st.hasMoreTokens()) {
          message = new String(st.nextToken());
        } else {
          message = new String();
        }
        if (message.contains("COMPLETE")) {
          System.out.println("C3 Complete");
          completed3 = true;
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
    }
  }
  class c4thread extends Thread {
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() {
      InputStream in = null;
      byte[] inputByte = new byte[300];
      String inputString;
      String message;
      try {
        System.out.println("Waiting for Client 4");
        server = new ServerSocket(11003);
        socketServer = server.accept();
        System.out.println("Connected with Client 4");
        
        in = socketServer.getInputStream();
        out14 = socketServer.getOutputStream();
        in.read(inputByte);
        inputString = new String(inputByte);

        StringTokenizer st = new StringTokenizer(inputString, " ");

        if (st.hasMoreTokens()) {
          message = new String(st.nextToken());
        } else {
          message = new String();
        }
        if (message.contains("COMPLETE")) {
          System.out.println("C4 Complete");
          completed4 = true;
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
    }
  }
  class c5thread extends Thread {
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() {
      InputStream in = null;
      byte[] inputByte = new byte[300];
      String inputString;
      String message;
      try {
        System.out.println("Waiting for Client 5");
        server = new ServerSocket(11004);
        socketServer = server.accept();
        System.out.println("Connected with Client 5");
        in = socketServer.getInputStream();
        out15 = socketServer.getOutputStream();
        in.read(inputByte);
        inputString = new String(inputByte);

        StringTokenizer st = new StringTokenizer(inputString, " ");

        if (st.hasMoreTokens()) {
          message = new String(st.nextToken());
        } else {
          message = new String();
        }
        if (message.contains("COMPLETE")) {
          System.out.println("C5 Complete");
          completed5 = true;
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
    }
  }
  public static void main(String args[]) throws Exception {
    Socket socketServer1 = null;
    ServerSocket server1 = null;
    Socket socketServer2 = null;
    ServerSocket server2 = null;
    Socket socketServer3 = null;
    ServerSocket server3 = null;
    Socket socketServer4 = null;
    ServerSocket server4 = null;
    Socket socketServer5 = null;
    ServerSocket server5 = null;
    Socket socketServer6 = null;
    ServerSocket server6 = null;
    Socket socketServer7 = null;
    ServerSocket server7 = null;

    String outMessage = new String("COMPLETE");
    byte[] outbyte;
    outbyte = outMessage.getBytes();

    OutputStream out1;
    OutputStream out2;
    OutputStream out3;
    OutputStream out4;
    OutputStream out5;
    OutputStream out6;
    OutputStream out7;

    server1 = new ServerSocket(11010); // Instantiation
    server2 = new ServerSocket(11011);
    server3 = new ServerSocket(11012);
    server4 = new ServerSocket(11013);
    server5 = new ServerSocket(11014);
    server6 = new ServerSocket(11015);
    server7 = new ServerSocket(11016);

    System.out.println("Waiting for Server1");
    socketServer1 = server1.accept();
    System.out.println("Connected with Server1");
    System.out.println("Waiting for Server2");
    socketServer2 = server2.accept();
    System.out.println("Connected with Server2");
    System.out.println("Waiting for Server3");
    socketServer3 = server3.accept();
    System.out.println("Connected with Server3");
    System.out.println("Waiting for Server4");
    socketServer4 = server4.accept();
    System.out.println("Connected with Server4");
    System.out.println("Waiting for Server5");
    socketServer5 = server5.accept();
    System.out.println("Connected with Server5");
    System.out.println("Waiting for Server6");
    socketServer6 = server6.accept();
    System.out.println("Connected with Server6");
    System.out.println("Waiting for Server7");
    socketServer7 = server7.accept();
    System.out.println("Connected with Server7");

    Server0 computer = new Server0();
    Server0.c1thread c1t = computer.new c1thread();
    Server0.c2thread c2t = computer.new c2thread();
    Server0.c3thread c3t = computer.new c3thread();
    Server0.c4thread c4t = computer.new c4thread();
    Server0.c5thread c5t = computer.new c5thread();

    c1t.start(); // Start the threads
    c2t.start();
    c3t.start();
    c4t.start();
    c5t.start();

    c1t.join();
    c2t.join();
    c3t.join();
    c4t.join();
    c5t.join();
    // If all completed flags are set all servers will be brought to halt
    if ((completed1 == true) && (completed2 == true) && (completed3 == true) && (completed4 == true) && (completed4 == true)) {
      out1 = socketServer1.getOutputStream();
      out2 = socketServer2.getOutputStream();
      out3 = socketServer3.getOutputStream();
      out4 = socketServer4.getOutputStream();
      out5 = socketServer5.getOutputStream();
      out6 = socketServer6.getOutputStream();
      out7 = socketServer7.getOutputStream();

      out1.write(outbyte);
      out2.write(outbyte);
      out3.write(outbyte);
      out4.write(outbyte);
      out5.write(outbyte);
      out6.write(outbyte);
      out7.write(outbyte);
      
      out11.write(outbyte);
      out12.write(outbyte);
      out13.write(outbyte);
      out14.write(outbyte);
      out15.write(outbyte);
    }
    TimeUnit.SECONDS.sleep(10);

    server1.close();
    server2.close();
    server3.close();
    server4.close();
    server5.close();
    server6.close();
    server7.close();

  }
}
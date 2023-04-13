/*******************************************************************************************/
/*******************************************************************************************/
/************ Author: Shishir Sunil Yalburgi                             *******************/
/************ NETID: ssy220000                                           *******************/
/************ Email: ssy220000@utdallas.edu                              *******************/
/************ Server1.java                                               *******************/
/************ This file implements Server 1 for tree based quorum        *******************/
/*******************************************************************************************/
/*******************************************************************************************/


package S1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Server1 {
  //Global variable declaration
  public static boolean completed = false;
  public boolean locked = false; // Server locked variable
  public static int msg_cnt = 0;
  public static int rcv_cnt = 0;
  Lock threadLock = new ReentrantLock(); // Mutex lock
  public static Queue < String > requestQueue1 = new LinkedList < > (); // Queue to store requests
  public static Queue < String > requestQueue2 = new LinkedList < > ();
  public static Queue < String > requestQueue3 = new LinkedList < > ();
  public static Queue < String > requestQueue4 = new LinkedList < > ();
  public static Queue < String > requestQueue5 = new LinkedList < > ();
  public static String Server0 = new String("10.176.69.44"); // Server 0 IP address

  public class C1thread extends Thread {
    
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run() // Thread execution starts

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 1");
        server = new ServerSocket(10000); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 1");

        

        server.setSoTimeout(60 * 1000); // Set timeout at 60 seconds if server does not get connection request
        in = socketServer.getInputStream();
        out = socketServer.getOutputStream();
      } catch (Exception exp) {
        System.out.println(exp);
      }

      long timestamp;
      boolean executed = false;
      String timeStamp = new String();
      String timeStamp1 = new String();
      String message = new String();
      String message1 = new String();
      byte[] buffer = new byte[300];
      byte[] buffer1 = new byte[300];
      byte[] outBuffer = new byte[300];
      String outBufferString;
      String bufferString;
      String bufferString1;
      String peekString1;
      String peekString2;
      String peekString3;
      String peekString4;
      String peekString5;
      long peekTsInt1;
      long peekTsInt2;
      long peekTsInt3;
      long peekTsInt4;
      long peekTsInt5;
      while (completed != true) {

        try {

          {

            if (completed != true) {
              in.read(buffer); // Read request message from client 1
            }
            if (completed == true) {
              return;
            }

            bufferString = new String(buffer); 

            StringTokenizer st = new StringTokenizer(bufferString, " ");
            if (st.hasMoreTokens()) {
              timeStamp = st.nextToken();
              message = st.nextToken();

            } // Tokenize the string message
            if (message.contains("REQUEST")) {

              requestQueue1.add(timeStamp); // If message contains REQUEST add it to queue

              while (executed == false) {
                threadLock.lock(); // Mutex lock
                if (requestQueue1.isEmpty() == false) {
                  peekString1 = new String(requestQueue1.peek()); // Check if Client 1 has sent request if it has read the queue value
                } else {
                  peekString1 = new String("9999999"); // If not set the value as maximum
                }
                if (requestQueue2.isEmpty() == false) {
                  peekString2 = new String(requestQueue2.peek());
                } else {
                  peekString2 = new String("9999999");
                }
                if (requestQueue3.isEmpty() == false) {
                  peekString3 = new String(requestQueue3.peek());
                } else {
                  peekString3 = new String("9999999");
                }
                if (requestQueue4.isEmpty() == false) {
                  peekString4 = new String(requestQueue4.peek());
                } else {
                  peekString4 = new String("9999999");
                }
                if (requestQueue5.isEmpty() == false) {
                  peekString5 = new String(requestQueue5.peek());
                } else {
                  peekString5 = new String("9999999");
                }
                peekTsInt1 = Integer.parseInt(peekString1); // Parse string value to int
                peekTsInt2 = Integer.parseInt(peekString2);
                peekTsInt3 = Integer.parseInt(peekString3);
                peekTsInt4 = Integer.parseInt(peekString4);
                peekTsInt5 = Integer.parseInt(peekString5);
                // If request value of 1 is less than or equal to all other values then grant for 1
                if (((peekTsInt1 < peekTsInt2) || (peekTsInt1 == peekTsInt2)) && ((peekTsInt1 < peekTsInt3) || (peekTsInt1 == peekTsInt3)) && ((peekTsInt1 < peekTsInt4) || (peekTsInt1 == peekTsInt4)) && ((peekTsInt1 < peekTsInt5) || (peekTsInt1 == peekTsInt5)) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true; // Server locked
                  executed = true; // Grant executed
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER1");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer); // Send grant
                  msg_cnt++;// Message counter incremented
                  rcv_cnt++;
                  peekTsInt1 = 999999999;

                }
                threadLock.unlock();
              }
              if (executed == true) { // Once already a grant has been issued

                executed = false; // Reset the flag
                in.read(buffer1); // Block for RELEASE message
                bufferString1 = new String(buffer1);

                StringTokenizer st1 = new StringTokenizer(bufferString1, " ");
                if (st1.hasMoreTokens()) {
                  timeStamp1 = st1.nextToken();
                  message1 = st1.nextToken();
                }

                if (message1.contains("RELEASE")) { // If release message is received
                  threadLock.lock();//Mutex lock
                  rcv_cnt++;
                  System.out.println(timeStamp1 + " RELEASE"); 
                  requestQueue1.remove();// Pop queue
                  locked = false;// Unlock server
                  threadLock.unlock(); // Mutex unlock
                }

              }
            }

          }

        } catch (Exception exp) {

        }
      }
      return;
    }
  }
  public class C2thread extends Thread {

    int count = 0;
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run()

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 2");
        server = new ServerSocket(10001); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 2");
        

        server.setSoTimeout(60 * 1000);
        in = socketServer.getInputStream();
        out = socketServer.getOutputStream();
      } catch (Exception exp) {
        System.out.println(exp);
      }

      long timestamp;
      boolean executed = false;
      String timeStamp = new String();
      String timeStamp1 = new String();
      String message = new String();
      String message1 = new String();
      byte[] buffer = new byte[300];
      byte[] buffer1 = new byte[300];
      byte[] outBuffer = new byte[300];
      String outBufferString;
      String bufferString;
      String bufferString1;
      String peekString1;
      String peekString2;
      String peekString3;
      String peekString4;
      String peekString5;
      long peekTsInt1;
      long peekTsInt2;
      long peekTsInt3;
      long peekTsInt4;
      long peekTsInt5;
      while (completed != true) {

        try {

          {

            if (completed != true) {
              in.read(buffer);
            }
            if (completed == true) {
              return;
            }

            bufferString = new String(buffer);

            StringTokenizer st = new StringTokenizer(bufferString, " ");
            if (st.hasMoreTokens()) {
              timeStamp = st.nextToken();
              message = st.nextToken();

            }
            if (message.contains("REQUEST")) {

              requestQueue2.add(timeStamp);

              while (executed == false) {

                threadLock.lock();

                if (requestQueue1.isEmpty() == false) {
                  peekString1 = new String(requestQueue1.peek());
                } else {
                  peekString1 = new String("9999999");
                }
                if (requestQueue2.isEmpty() == false) {
                  peekString2 = new String(requestQueue2.peek());
                } else {
                  peekString2 = new String("9999999");
                }
                if (requestQueue3.isEmpty() == false) {
                  peekString3 = new String(requestQueue3.peek());
                } else {
                  peekString3 = new String("9999999");
                }
                if (requestQueue4.isEmpty() == false) {
                  peekString4 = new String(requestQueue4.peek());
                } else {
                  peekString4 = new String("9999999");
                }
                if (requestQueue5.isEmpty() == false) {
                  peekString5 = new String(requestQueue5.peek());
                } else {
                  peekString5 = new String("9999999");
                }

                peekTsInt1 = Integer.parseInt(peekString1);
                peekTsInt2 = Integer.parseInt(peekString2);
                peekTsInt3 = Integer.parseInt(peekString3);
                peekTsInt4 = Integer.parseInt(peekString4);
                peekTsInt5 = Integer.parseInt(peekString5);

                if ((peekTsInt2 < peekTsInt1) && ((peekTsInt2 < peekTsInt3) || (peekTsInt2 == peekTsInt3)) && ((peekTsInt2 < peekTsInt4) || (peekTsInt2 == peekTsInt4)) && ((peekTsInt2 < peekTsInt5) || (peekTsInt2 == peekTsInt5)) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true;
                  executed = true;
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER1");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;
                  peekTsInt2 = 999999999;

                }

                threadLock.unlock();
              }
              if (executed == true) {

                executed = false;
                in.read(buffer1);

                bufferString1 = new String(buffer1);
                StringTokenizer st1 = new StringTokenizer(bufferString1, " ");
                if (st1.hasMoreTokens()) {
                  timeStamp1 = st1.nextToken();
                  message1 = st1.nextToken();
                }
                if (message1.contains("RELEASE")) {
                  threadLock.lock();
                  rcv_cnt++;
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue2.remove();
                  locked = false;
                  threadLock.unlock();
                }

              }
            }

          }

        } catch (Exception exp) {

        }
        return;
      }
    }
  }
  public class C3thread extends Thread {

    int count = 0;
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run()

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 3");
        server = new ServerSocket(10002); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 3");
        

        server.setSoTimeout(60 * 1000);
        in = socketServer.getInputStream();
        out = socketServer.getOutputStream();
      } catch (Exception exp) {
        System.out.println(exp);
      }

      long timestamp;
      boolean executed = false;
      String timeStamp = new String();
      String timeStamp1 = new String();
      String message = new String();
      String message1 = new String();
      byte[] buffer = new byte[300];
      byte[] buffer1 = new byte[300];
      byte[] outBuffer = new byte[300];
      String outBufferString;
      String bufferString;
      String bufferString1;
      String peekString1;
      String peekString2;
      String peekString3;
      String peekString4;
      String peekString5;
      long peekTsInt1;
      long peekTsInt2;
      long peekTsInt3;
      long peekTsInt4;
      long peekTsInt5;
      while (completed != true) {

        try {

          {

            if (completed != true) {
              in.read(buffer);
            }
            if (completed == true) {
              return;
            }

            bufferString = new String(buffer);

            StringTokenizer st = new StringTokenizer(bufferString, " ");
            if (st.hasMoreTokens()) {
              timeStamp = st.nextToken();
              message = st.nextToken();

            }
            if (message.contains("REQUEST")) {

              requestQueue3.add(timeStamp);

              while (executed == false) {
                threadLock.lock();
                if (requestQueue1.isEmpty() == false) {
                  peekString1 = new String(requestQueue1.peek());
                } else {
                  peekString1 = new String("9999999");
                }
                if (requestQueue2.isEmpty() == false) {
                  peekString2 = new String(requestQueue2.peek());
                } else {
                  peekString2 = new String("9999999");
                }
                if (requestQueue3.isEmpty() == false) {
                  peekString3 = new String(requestQueue3.peek());
                } else {
                  peekString3 = new String("9999999");
                }
                if (requestQueue4.isEmpty() == false) {
                  peekString4 = new String(requestQueue4.peek());
                } else {
                  peekString4 = new String("9999999");
                }
                if (requestQueue5.isEmpty() == false) {
                  peekString5 = new String(requestQueue5.peek());
                } else {
                  peekString5 = new String("9999999");
                }

                peekTsInt1 = Integer.parseInt(peekString1);
                peekTsInt2 = Integer.parseInt(peekString2);
                peekTsInt3 = Integer.parseInt(peekString3);
                peekTsInt4 = Integer.parseInt(peekString4);
                peekTsInt5 = Integer.parseInt(peekString5);

                if ((peekTsInt3 < peekTsInt2) && (peekTsInt3 < peekTsInt1) && ((peekTsInt3 < peekTsInt4) || (peekTsInt3 == peekTsInt4)) && ((peekTsInt3 < peekTsInt5) || (peekTsInt3 == peekTsInt5)) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true;
                  executed = true;
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER1");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;
                  peekTsInt3 = 999999999;

                }
                threadLock.unlock();
              }
              if (executed == true) {
                executed = false;
                in.read(buffer1);
                bufferString1 = new String(buffer1);
                StringTokenizer st1 = new StringTokenizer(bufferString1, " ");
                if (st1.hasMoreTokens()) {
                  timeStamp1 = st1.nextToken();
                  message1 = st1.nextToken();
                }
                if (message1.contains("RELEASE")) {
                  threadLock.lock();
                  rcv_cnt++;
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue3.remove();
                  locked = false;
                  threadLock.unlock();
                }

              }
            }

          }

        } catch (Exception exp) {

        }
      }
      return;
    }
  }
  public class C4thread extends Thread {

    int count = 0;
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run()

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 4");
        server = new ServerSocket(10003); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 4");
        

        server.setSoTimeout(60 * 1000);
        in = socketServer.getInputStream();
        out = socketServer.getOutputStream();
      } catch (Exception exp) {
        System.out.println(exp);
      }

      long timestamp;
      boolean executed = false;
      String timeStamp = new String();
      String timeStamp1 = new String();
      String message = new String();
      String message1 = new String();
      byte[] buffer = new byte[300];
      byte[] buffer1 = new byte[300];
      byte[] outBuffer = new byte[300];
      String outBufferString;
      String bufferString;
      String bufferString1;
      String peekString1;
      String peekString2;
      String peekString3;
      String peekString4;
      String peekString5;
      long peekTsInt1;
      long peekTsInt2;
      long peekTsInt3;
      long peekTsInt4;
      long peekTsInt5;
      while (completed != true) {

        try {

          {

            if (completed != true) {
              in.read(buffer);
            }
            if (completed == true) {
              return;
            }

            bufferString = new String(buffer);

            StringTokenizer st = new StringTokenizer(bufferString, " ");
            if (st.hasMoreTokens()) {
              timeStamp = st.nextToken();
              message = st.nextToken();

            }
            if (message.contains("REQUEST")) {

              requestQueue4.add(timeStamp);

              while (executed == false) {
                threadLock.lock();
                if (requestQueue1.isEmpty() == false) {
                  peekString1 = new String(requestQueue1.peek());
                } else {
                  peekString1 = new String("9999999");
                }
                if (requestQueue2.isEmpty() == false) {
                  peekString2 = new String(requestQueue2.peek());
                } else {
                  peekString2 = new String("9999999");
                }
                if (requestQueue3.isEmpty() == false) {
                  peekString3 = new String(requestQueue3.peek());
                } else {
                  peekString3 = new String("9999999");
                }
                if (requestQueue4.isEmpty() == false) {
                  peekString4 = new String(requestQueue4.peek());
                } else {
                  peekString4 = new String("9999999");
                }
                if (requestQueue5.isEmpty() == false) {
                  peekString5 = new String(requestQueue5.peek());
                } else {
                  peekString5 = new String("9999999");
                }

                peekTsInt1 = Integer.parseInt(peekString1);
                peekTsInt2 = Integer.parseInt(peekString2);
                peekTsInt3 = Integer.parseInt(peekString3);
                peekTsInt4 = Integer.parseInt(peekString4);
                peekTsInt5 = Integer.parseInt(peekString5);

                if ((peekTsInt4 < peekTsInt1) && (peekTsInt4 < peekTsInt2) && (peekTsInt4 < peekTsInt3) && ((peekTsInt4 < peekTsInt5) || (peekTsInt4 == peekTsInt5)) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true;
                  executed = true;
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER1");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;
                  peekTsInt4 = 999999999;

                }
                threadLock.unlock();
              }
              if (executed == true) {
                executed = false;
                in.read(buffer1);
                bufferString1 = new String(buffer1);
                StringTokenizer st1 = new StringTokenizer(bufferString1, " ");
                if (st1.hasMoreTokens()) {
                  timeStamp1 = st1.nextToken();
                  message1 = st1.nextToken();
                }
                if (message1.contains("RELEASE")) {
                  threadLock.lock();
                  rcv_cnt++;
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue4.remove();
                  locked = false;
                  threadLock.unlock();
                }

              }
            }

          }

        } catch (Exception exp) {

        }
      }
      return;
    }
  }
  public class C5thread extends Thread {

    int count = 0;
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run()

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 5");
        server = new ServerSocket(10004); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 5");

        server.setSoTimeout(60 * 1000);
        in = socketServer.getInputStream();
        out = socketServer.getOutputStream();
      } catch (Exception exp) {
        System.out.println(exp);
      }

      long timestamp;
      boolean executed = false;
      String timeStamp = new String();
      String timeStamp1 = new String();
      String message = new String();
      String message1 = new String();
      byte[] buffer = new byte[300];
      byte[] buffer1 = new byte[300];
      byte[] outBuffer = new byte[300];
      String outBufferString;
      String bufferString;
      String bufferString1;
      String peekString1;
      String peekString2;
      String peekString3;
      String peekString4;
      String peekString5;
      long peekTsInt1;
      long peekTsInt2;
      long peekTsInt3;
      long peekTsInt4;
      long peekTsInt5;
      while (completed != true) {

        try {

          {

            if (completed != true) {
              in.read(buffer);
            }
            if (completed == true) {
              return;
            }

            bufferString = new String(buffer);

            StringTokenizer st = new StringTokenizer(bufferString, " ");
            if (st.hasMoreTokens()) {
              timeStamp = st.nextToken();
              message = st.nextToken();

            }
            if (message.contains("REQUEST")) {

              requestQueue5.add(timeStamp);

              while (executed == false) {
                threadLock.lock();
                if (requestQueue1.isEmpty() == false) {
                  peekString1 = new String(requestQueue1.peek());
                } else {
                  peekString1 = new String("9999999");
                }
                if (requestQueue2.isEmpty() == false) {
                  peekString2 = new String(requestQueue2.peek());
                } else {
                  peekString2 = new String("9999999");
                }
                if (requestQueue3.isEmpty() == false) {
                  peekString3 = new String(requestQueue3.peek());
                } else {
                  peekString3 = new String("9999999");
                }
                if (requestQueue4.isEmpty() == false) {
                  peekString4 = new String(requestQueue4.peek());
                } else {
                  peekString4 = new String("9999999");
                }
                if (requestQueue5.isEmpty() == false) {
                  peekString5 = new String(requestQueue5.peek());
                } else {
                  peekString5 = new String("9999999");
                }

                peekTsInt1 = Integer.parseInt(peekString1);
                peekTsInt2 = Integer.parseInt(peekString2);
                peekTsInt3 = Integer.parseInt(peekString3);
                peekTsInt4 = Integer.parseInt(peekString4);
                peekTsInt5 = Integer.parseInt(peekString5);

                if ((peekTsInt5 < peekTsInt1) && (peekTsInt5 < peekTsInt2) && (peekTsInt5 < peekTsInt3) && (peekTsInt5 < peekTsInt4) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true;
                  executed = true;
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER1");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;
                  peekTsInt5 = 999999999;

                }
                threadLock.unlock();
              }
              if (executed == true) {
                executed = false;
                in.read(buffer1);
                bufferString1 = new String(buffer1);
                StringTokenizer st1 = new StringTokenizer(bufferString1, " ");
                if (st1.hasMoreTokens()) {
                  timeStamp1 = st1.nextToken();
                  message1 = st1.nextToken();
                }
                if (message1.contains("RELEASE")) {
                  threadLock.lock();
                  rcv_cnt++;
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue5.remove();
                  locked = false;
                  threadLock.unlock();
                }

              }
            }

          }

        } catch (Exception exp) {

        }
      }
      return;
    }
  }

  public class s0thread extends Thread {

    public Socket socket = null;

    byte[] inbyte = new byte[300];
    String inString;
    String Token;
    public void run()

    {
      InputStream in = null;

      try {

        socket = new Socket(Server0, 11010);

        in = socket.getInputStream();

        in.read(inbyte);
        inString = new String(inbyte);
        StringTokenizer st = new StringTokenizer(inString, " ");
        System.out.println("COMPLETE"); // Check for Complete message from server 0
        if (inString.contains("COMPLETE")) {
          completed = true;
        }

      } catch (Exception exp) {
        System.out.println(exp);
      }
      return;
    }
  }

  public static void main(String arg[]) throws Exception {

    Server1 computer = new Server1();
    Server1.C1thread client1 = computer.new C1thread();
    Server1.C2thread client2 = computer.new C2thread();
    Server1.C3thread client3 = computer.new C3thread();
    Server1.C4thread client4 = computer.new C4thread();
    Server1.C5thread client5 = computer.new C5thread();
    Server1.s0thread server0 = computer.new s0thread();

    client1.start(); // Thread start
    client2.start();
    client3.start();
    client4.start();
    client5.start();
    server0.start();
    server0.join(); // Received Complete message from S0
    
    System.out.println("Total sent messages = " + msg_cnt); // Data collection
    System.out.println("Total received messages = " + rcv_cnt);
    System.out.println("Total exchanged messages at this node = " + (rcv_cnt + msg_cnt));

    client1.stop(); // Kill all threads
    client2.stop();
    client3.stop();
    client4.stop();
    client5.stop();

  }

}
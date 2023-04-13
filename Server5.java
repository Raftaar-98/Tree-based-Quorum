/*******************************************************************************************/
/*******************************************************************************************/
/************ Author: Shishir Sunil Yalburgi                             *******************/
/************ NETID: ssy220000                                           *******************/
/************ Email: ssy220000@utdallas.edu                              *******************/
/************ Server5.java                                               *******************/
/************ This file implements Server 5 for tree based quorum        *******************/
/************ Please refer to Server1.java for documentation, this file  *******************/
/************ is replica of Server1.java                                 *******************/
/*******************************************************************************************/
/*******************************************************************************************/

package S5;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Server5 {

  public static boolean completed = false;
  public boolean locked = false;
  public static int msg_cnt = 0;
  public static int rcv_cnt = 0;
  Lock threadLock = new ReentrantLock();
  public boolean thread_sync1 = false;
  public boolean thread_sync2 = false;
  public boolean thread_sync3 = false;
  public boolean thread_sync4 = false;
  public boolean thread_sync5 = false;
  public long max_time = 9999999;
  public static Queue < String > requestQueue1 = new LinkedList < > ();
  public static Queue < String > requestQueue2 = new LinkedList < > ();
  public static Queue < String > requestQueue3 = new LinkedList < > ();
  public static Queue < String > requestQueue4 = new LinkedList < > ();
  public static Queue < String > requestQueue5 = new LinkedList < > ();
  public static String Server0 = new String("10.176.69.44");

  public class C1thread extends Thread {
    int count = 0;
    public Socket socketServer = null;
    public ServerSocket server = null;
    public void run()

    {
      InputStream in = null;
      OutputStream out = null;
      try {

        System.out.println("waiting for Client 1");
        server = new ServerSocket(5000); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 1");
        
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

              requestQueue1.add(timeStamp);

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

                if (((peekTsInt1 < peekTsInt2) || (peekTsInt1 == peekTsInt2)) && ((peekTsInt1 < peekTsInt3) || (peekTsInt1 == peekTsInt3)) && ((peekTsInt1 < peekTsInt4) || (peekTsInt1 == peekTsInt4)) && ((peekTsInt1 < peekTsInt5) || (peekTsInt1 == peekTsInt5)) && (locked == false)) {
                  timestamp = System.currentTimeMillis() / 1000;
                  timestamp = timestamp % 10000;

                  locked = true;
                  executed = true;
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER5");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);

                  msg_cnt++;
                  rcv_cnt++;

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
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue1.remove();
                  locked = false;
                  rcv_cnt++;
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
        server = new ServerSocket(5001); // setup server side socket				
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
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER5");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);

                  msg_cnt++;
                  rcv_cnt++;

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
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue2.remove();
                  locked = false;
                  rcv_cnt++;
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
        server = new ServerSocket(5002); // setup server side socket				
        socketServer = server.accept(); // Listen to incoming connections
        System.out.println("Connected with Client 3");
        thread_sync3 = true;

        {
          System.out.println("Thread Sync wait");
        }
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
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER5");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;

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
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue3.remove();
                  locked = false;
                  rcv_cnt++;
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
        server = new ServerSocket(5003); // setup server side socket				
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
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER5");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);

                  msg_cnt++;
                  rcv_cnt++;

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
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue4.remove();
                  locked = false;
                  rcv_cnt++;
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
        server = new ServerSocket(5004); // setup server side socket				
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
                  outBufferString = new String(Integer.toString((int) timestamp) + " " + "GRANT" + " " + "SERVER5");
                  System.out.println(outBufferString);
                  outBuffer = outBufferString.getBytes();
                  out.write(outBuffer);
                  msg_cnt++;
                  rcv_cnt++;

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
                  System.out.println(timeStamp1 + " RELEASE");
                  requestQueue5.remove();
                  locked = false;
                  rcv_cnt++;
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

        socket = new Socket(Server0, 11014);

        in = socket.getInputStream();

        in.read(inbyte);

        inString = new String(inbyte);
        StringTokenizer st = new StringTokenizer(inString, " ");
        System.out.println("COMPLETE");
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

    Server5 computer = new Server5();
    Server5.C1thread client1 = computer.new C1thread();
    Server5.C2thread client2 = computer.new C2thread();
    Server5.C3thread client3 = computer.new C3thread();
    Server5.C4thread client4 = computer.new C4thread();
    Server5.C5thread client5 = computer.new C5thread();
    Server5.s0thread server0 = computer.new s0thread();

    client1.start();
    client2.start();
    client3.start();
    client4.start();
    client5.start();
    server0.start();
    server0.join();
    
    System.out.println("Total sent messages = " + msg_cnt);
    System.out.println("Total received messages = " + rcv_cnt);
    System.out.println("Total exchanged messages at this node = " + (rcv_cnt + msg_cnt));

    client1.stop();
    client2.stop();
    client3.stop();
    client4.stop();
    client5.stop();

  }

}
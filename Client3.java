/*******************************************************************************************/
/*******************************************************************************************/
/************ Author: Shishir Sunil Yalburgi                             *******************/
/************ NETID: ssy220000                                           *******************/
/************ Email: ssy220000@utdallas.edu                              *******************/
/************ Client3.java                                               *******************/
/************ This file implements client 3 for tree based quorum        *******************/
/************ Please refer to Client1.java for documentation, this file  *******************/
/************ is replica of Client1.java                                 *******************/
/*******************************************************************************************/
/*******************************************************************************************/


package C3;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client3 {
  public static int count = 0;
  public static String Server0 = new String("10.176.69.44");
  public static String Server1 = new String("10.176.69.37");
  public static String Server2 = new String("10.176.69.38");
  public static String Server3 = new String("10.176.69.39");
  public static String Server4 = new String("10.176.69.40");
  public static String Server5 = new String("10.176.69.41");
  public static String Server6 = new String("10.176.69.42");
  public static String Server7 = new String("10.176.69.43");
  public static int msg_cnt = 0;
  public static int rcv_cnt = 0;
  public static int req_time;
  public static int enter_time;
  public static Socket socketClient0 = null;
  public static Socket socketClient1 = null;
  public static Socket socketClient2 = null;
  public static Socket socketClient3 = null;
  public static Socket socketClient4 = null;
  public static Socket socketClient5 = null;
  public static Socket socketClient6 = null;
  public static Socket socketClient7 = null;
  long timestamp;
  Lock threadLock = new ReentrantLock();
  public class client {

    public client() throws Exception {
      socketClient0 = new Socket(Server0, 11002);
      socketClient1 = new Socket(Server1, 10002);
      socketClient2 = new Socket(Server2, 2002);
      socketClient3 = new Socket(Server3, 3002);
      socketClient4 = new Socket(Server4, 4012);
      socketClient5 = new Socket(Server5, 5002);
      socketClient6 = new Socket(Server6, 6002);
      socketClient7 = new Socket(Server7, 7012);

    }

  }
  public static void main(String arg[]) throws Exception {
    Client3 computer = new Client3();
    Client3.client client1 = computer.new client();
    InputStream in0 = null;
    InputStream in1 = null;
    InputStream in2 = null;
    InputStream in3 = null;
    InputStream in4 = null;
    InputStream in5 = null;
    InputStream in6 = null;
    InputStream in7 = null;
    OutputStream out0 = null;
    OutputStream out1 = null;
    OutputStream out2 = null;
    OutputStream out3 = null;
    OutputStream out4 = null;
    OutputStream out5 = null;
    OutputStream out6 = null;
    OutputStream out7 = null;
    int granted[] = {
      0,
      0,
      0,
      0,
      0,
      0,
      0
    };
    byte[] completeBuffer;
    byte[] buffer = new byte[300];
    byte[] inBuffer1 = new byte[300];
    byte[] inBuffer2 = new byte[300];
    byte[] inBuffer3 = new byte[300];
    byte[] inBuffer4 = new byte[300];
    byte[] inBuffer5 = new byte[300];
    byte[] inBuffer6 = new byte[300];
    byte[] inBuffer7 = new byte[300];
    long timestamp;
    String BufferString = new String();
    String inString1;
    String inString2;
    String inString3;
    String inString4;
    String inString5;
    String inString6;
    String inString7;
    String message1;
    String message2;
    String message3;
    String message4;
    String message5;
    String message6;
    String message7;
    String complete = new String("COMPLETE");
    completeBuffer = complete.getBytes();
    boolean executed = false;
    out0 = socketClient0.getOutputStream();
    out1 = socketClient1.getOutputStream();
    out2 = socketClient2.getOutputStream();
    out3 = socketClient3.getOutputStream();
    out4 = socketClient4.getOutputStream();
    out5 = socketClient5.getOutputStream();
    out6 = socketClient6.getOutputStream();
    out7 = socketClient7.getOutputStream();

    in0 = socketClient0.getInputStream();
    in1 = socketClient1.getInputStream();
    in2 = socketClient2.getInputStream();
    in3 = socketClient3.getInputStream();
    in4 = socketClient4.getInputStream();
    in5 = socketClient5.getInputStream();
    in6 = socketClient6.getInputStream();
    in7 = socketClient7.getInputStream();
    while (count < 20) {
      TimeUnit.SECONDS.sleep(1 + (int)(Math.random() * ((5 - 1) + 1)));
      timestamp = System.currentTimeMillis() / 1000;
      timestamp = timestamp % 10000;
      req_time = (int) timestamp;
      BufferString = (Integer.toString((int) timestamp) + " " + "REQUEST" + " ");
      System.out.println(BufferString);
      buffer = BufferString.getBytes();

      out1.write(buffer); //Send request to Server1	
      msg_cnt++;
      out2.write(buffer); //to Server 2
      msg_cnt++;
      out3.write(buffer); //..
      msg_cnt++;
      out4.write(buffer); //..
      msg_cnt++;
      out5.write(buffer); //..
      msg_cnt++;
      out6.write(buffer); //..
      msg_cnt++;
      out7.write(buffer); //to server7
      msg_cnt++;

      TimeUnit.SECONDS.sleep(1);
      while (executed == false) {
        if (in1.available() > 0) {
          in1.read(inBuffer1);
          rcv_cnt++;
          inString1 = new String(inBuffer1);

        } else {
          inString1 = new String("99" + " " + "LOCKED");
        }

        if (in2.available() > 0) {
          in2.read(inBuffer2);
          rcv_cnt++;
          inString2 = new String(inBuffer2);

        } else {
          inString2 = new String("99" + " " + "LOCKED");
        }

        if (in3.available() > 0) {
          in3.read(inBuffer3);
          rcv_cnt++;
          inString3 = new String(inBuffer3);

        } else {
          inString3 = new String("99" + " " + "LOCKED");
        }

        if (in4.available() > 0) {
          in4.read(inBuffer4);
          rcv_cnt++;
          inString4 = new String(inBuffer4);

        } else {
          inString4 = new String("99" + " " + "LOCKED");
        }

        if (in5.available() > 0) {
          in5.read(inBuffer5);
          rcv_cnt++;
          inString5 = new String(inBuffer5);

        } else {
          inString5 = new String("99" + " " + "LOCKED");
        }

        if (in6.available() > 0) {
          in6.read(inBuffer6);
          rcv_cnt++;
          inString6 = new String(inBuffer6);

        } else {
          inString6 = new String("99" + " " + "LOCKED");
        }

        if (in7.available() > 0) {
          in7.read(inBuffer7);
          rcv_cnt++;
          inString7 = new String(inBuffer7);

        } else {
          inString7 = new String("99" + " " + "LOCKED");
        }

        StringTokenizer st1 = new StringTokenizer(inString1, " ");
        StringTokenizer st2 = new StringTokenizer(inString2, " ");
        StringTokenizer st3 = new StringTokenizer(inString3, " ");
        StringTokenizer st4 = new StringTokenizer(inString4, " ");
        StringTokenizer st5 = new StringTokenizer(inString5, " ");
        StringTokenizer st6 = new StringTokenizer(inString6, " ");
        StringTokenizer st7 = new StringTokenizer(inString7, " ");
        st1.nextToken();
        st2.nextToken();
        st3.nextToken();
        st4.nextToken();
        st5.nextToken();
        st6.nextToken();
        st7.nextToken();

        message1 = st1.nextToken();
        message2 = st2.nextToken();
        message3 = st3.nextToken();
        message4 = st4.nextToken();
        message5 = st5.nextToken();
        message6 = st6.nextToken();
        message7 = st7.nextToken();

        if ((message1.compareTo("GRANT") == 0) && (granted[0] != 1)) {

          granted[0] = 1;
        }
        if ((message2.compareTo("GRANT") == 0) && (granted[1] != 1)) {
          granted[1] = 1;
        }
        if ((message3.compareTo("GRANT") == 0) && (granted[2] != 1)) {
          granted[2] = 1;
        }
        if ((message4.compareTo("GRANT") == 0) && (granted[3] != 1)) {
          granted[3] = 1;
        }
        if ((message5.compareTo("GRANT") == 0) && (granted[4] != 1)) {
          granted[4] = 1;
        }
        if ((message6.compareTo("GRANT") == 0) && (granted[5] != 1)) {
          granted[5] = 1;
        }
        if ((message7.compareTo("GRANT") == 0) && (granted[6] != 1)) {
          granted[6] = 1;
        }

        if ((granted[0] == 1) && (granted[1] == 1) && (granted[3] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if (((granted[0] == 1) && (granted[1] == 1) && (granted[4] == 1)) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if (((granted[0] == 1) && (granted[3] == 1) && (granted[4] == 1)) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 3 entering critical section for " + (count + 1) + " count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        } else {
          TimeUnit.SECONDS.sleep(2);
        }
      }
      if (executed == true) {
        executed = false;
        for (int i = 0; i < 7; i++) {
          granted[i] = 0;
        }

        timestamp = System.currentTimeMillis() / 1000;
        timestamp = timestamp % 10000;
        BufferString = (Integer.toString((int) timestamp) + " " + "RELEASE" + " ");
        System.out.println(BufferString);
        buffer = BufferString.getBytes();

        out4.write(buffer);
        msg_cnt++;
        out5.write(buffer);
        msg_cnt++;
        out6.write(buffer);
        msg_cnt++;
        out7.write(buffer);
        msg_cnt++;
        out1.write(buffer);
        msg_cnt++;
        out2.write(buffer);
        msg_cnt++;
        out3.write(buffer);
        msg_cnt++;

      }

    }
    //TimeUnit.SECONDS.sleep(55);
    byte[] completeBuff = new byte[100];
    byte[] empty;
    String dummy = new String(" ");
    empty = dummy.getBytes();
    out0.write(completeBuffer);
    
    
    in0.read(completeBuff);
    
    
    
    
    System.out.println(complete);
    System.out.println("Total sent messages = " + msg_cnt);
    System.out.println("Total received messages = " + rcv_cnt);
    System.out.println("Total exchanged messages at this node = " + (rcv_cnt + msg_cnt));

  }
}
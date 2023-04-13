/*******************************************************************************************/
/*******************************************************************************************/
/************ Author: Shishir Sunil Yalburgi                             *******************/
/************ NETID: ssy220000                                           *******************/
/************ Email: ssy220000@utdallas.edu                              *******************/
/************ Client1.java                                               *******************/
/************ This file implements client 1 for tree based quorum        *******************/
/*******************************************************************************************/
/*******************************************************************************************/


package C1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client1 {
  public static int count = 0;
  public static Socket socketClient0 = null;
  public static String Server0 = new String("10.176.69.44");//Server 0 IP address
  public static String Server1 = new String("10.176.69.37");//Server 1 IP address
  public static String Server2 = new String("10.176.69.38");//Server 2 IP address
  public static String Server3 = new String("10.176.69.39");//Server 3 IP address
  public static String Server4 = new String("10.176.69.40");//Server 4 IP address
  public static String Server5 = new String("10.176.69.41");//Server 5 IP address
  public static String Server6 = new String("10.176.69.42");//Server 6 IP address
  public static String Server7 = new String("10.176.69.43");//Server 7 IP address
  public static int msg_cnt = 0; // Variable to count sent messages
  public static int rcv_cnt = 0; // Variable to count received messages
  public static int req_time; // variable to hold request time stamp
  public static int enter_time; // variable to hole critical section entering time stamp
  public static Socket socketClient1 = null; // Socket API
  public static Socket socketClient2 = null;
  public static Socket socketClient3 = null;
  public static Socket socketClient4 = null;
  public static Socket socketClient5 = null;
  public static Socket socketClient6 = null;
  public static Socket socketClient7 = null;
  long timestamp; // Variable to calculate time stamp
  Lock threadLock = new ReentrantLock(); // Mutex lock
  public class client {

    public client() throws Exception {          //Constructor
      socketClient0 = new Socket(Server0, 11000);
      socketClient1 = new Socket(Server1, 10000);
      socketClient2 = new Socket(Server2, 2000);
      socketClient3 = new Socket(Server3, 3000);
      socketClient4 = new Socket(Server4, 4010);
      socketClient5 = new Socket(Server5, 5000);
      socketClient6 = new Socket(Server6, 6000);
      socketClient7 = new Socket(Server7, 7010);

    }

  }
  public static void main(String arg[]) throws Exception {
    Client1 computer = new Client1();
    Client1.client client1 = computer.new client(); // Instantiation
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
    };                  // Array to check quorum
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
    String complete = new String("COMPLETE"); // String for sending complete message
    completeBuffer = complete.getBytes();  // Conversion to bytes
    boolean executed = false; // Variable to hold temporary executed status
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
    while (count < 20) {  // repeat for 10 times
      TimeUnit.SECONDS.sleep(1 + (int)(Math.random() * ((5 - 1) + 1))); // wait for uniformly distributed time in the range[5,10]
      timestamp = System.currentTimeMillis() / 1000; // Get time stamp from system time
      timestamp = timestamp % 10000;
      req_time = (int) timestamp;
      BufferString = (Integer.toString((int) timestamp) + " " + "REQUEST" + " "); // Construct request message
      System.out.println(BufferString);
      buffer = BufferString.getBytes();

      out1.write(buffer); //Send request to Server1
      msg_cnt++;// increment sent message count by 1
      out2.write(buffer); //Send request to Server2
      msg_cnt++;
      out3.write(buffer); //..
      msg_cnt++;
      out4.write(buffer); //..
      msg_cnt++;
      out5.write(buffer); //..
      msg_cnt++;
      out6.write(buffer); //..
      msg_cnt++;
      out7.write(buffer); //Send request to Server7
      msg_cnt++;

      TimeUnit.SECONDS.sleep(1); // wait for 1 second to get response

      while (executed == false) { // Check if critical section has already been executed in current cycle
        if (in1.available() > 0) {
          in1.read(inBuffer1);
          rcv_cnt++;
          inString1 = new String(inBuffer1); // Get grant message from server1 

        } else {
          inString1 = new String("99" + " " + "LOCKED"); // If server is locked it will not send grant so wait for grant from another server
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

        if ((message1.compareTo("GRANT") == 0) && (granted[0] != 1)) { // Set corresponding array element to 1 if server number of [index +1] has issued grant

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

        if ((granted[0] == 1) && (granted[1] == 1) && (granted[3] == 1) && (executed == false)) { // Quorum 1
          executed = true;
          timestamp = System.currentTimeMillis() / 1000; // get seconds from system time
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time)); //calculate latency
          TimeUnit.SECONDS.sleep(3); // critical section for 3 time units
          count++;
        }
        if (((granted[0] == 1) && (granted[1] == 1) && (granted[4] == 1)) && (executed == false)) {// Quorum 2
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if (((granted[0] == 1) && (granted[3] == 1) && (granted[4] == 1)) && (executed == false)) {// Quorum 3
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {// Quorum 4
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 5
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[0] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 6
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {// Quorum 7
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 8
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[3] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 9
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {// Quorum 10
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 11
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[1] == 1) && (granted[4] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 12
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[5] == 1) && (executed == false)) {// Quorum 13
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[2] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 14
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        }
        if ((granted[3] == 1) && (granted[4] == 1) && (granted[5] == 1) && (granted[6] == 1) && (executed == false)) {// Quorum 15
          executed = true;
          timestamp = System.currentTimeMillis() / 1000;
          timestamp = timestamp % 10000;
          enter_time = (int) timestamp;
          System.out.println("Client 1 entering critical section for " + (count + 1) + "count time: " + enter_time);
          System.out.println("Latency = " + (enter_time - req_time));
          TimeUnit.SECONDS.sleep(3);
          count++;
        } else {
          TimeUnit.SECONDS.sleep(2);
        }
      }
      if (executed == true) { // If already executed critical section 
        executed = false;
        for (int i = 0; i < 7; i++) { // clear the granted map array
          granted[i] = 0;
        }

        timestamp = System.currentTimeMillis() / 1000;
        timestamp = timestamp % 10000;
        BufferString = (Integer.toString((int) timestamp) + " " + "RELEASE" + " "); // send release to all servers
        System.out.println(BufferString);
        buffer = BufferString.getBytes();

        out3.write(buffer);
        msg_cnt++; // sent message count increment by 1
        out1.write(buffer);
        msg_cnt++;
        out5.write(buffer);
        msg_cnt++;
        out2.write(buffer);
        msg_cnt++;
        out6.write(buffer);
        msg_cnt++;
        out7.write(buffer);
        msg_cnt++;
        out4.write(buffer);
        msg_cnt++;

      }

    }
   // TimeUnit.SECONDS.sleep(55); // wait until all other clients complete
    byte[] empty;
    String dummy = new String(" ");
    empty = dummy.getBytes();
    byte[] completeBuff = new byte[100];
    out0.write(completeBuffer);
    
    
    in0.read(completeBuff);
    
   
   
    
    System.out.println(complete); // Send complete message to S0
    System.out.println("Total sent messages = " + msg_cnt); // Data collection
    System.out.println("Total received messages = " + rcv_cnt);
    System.out.println("Total exchanged messages at this node = " + (rcv_cnt + msg_cnt));

  }
}
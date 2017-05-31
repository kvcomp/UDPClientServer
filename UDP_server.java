/**
 * Created by forch on 30-May-17.
 */

import org.postgresql.ds.PGConnectionPoolDataSource;
import java.net.*;
import java.sql.*;
import java.util.LinkedList;

class UDP_server {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        LinkedList<Baby> test = new LinkedList<Baby>();
        test.add(new Baby("lolek",true,"doubt",234));
        test.add(new Baby("golek", false, "meeen", 324234));
        test.add(new Baby("gosdflek", false, "meesdfsdfen", 324223434));
        Sql.setValues(test);
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            Baby sentence = Serializer.deserialize(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence.getName() + " " + sentence.getSex());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            sendData = Serializer.serialize(sentence);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }

}


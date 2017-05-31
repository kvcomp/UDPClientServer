/
        **
 * Created by forch on 30-May-17.
 */

import org.postgresql.ds.PGConnectionPoolDataSource;

import java.io.*;
import java.net.*;
import java.sql.*;

class UDP_server {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }

    public static void sqlQuery(String sql) {
        Connection connection = null;
        Statement stmt = null;
        try {
            PGConnectionPoolDataSource source = new PGConnectionPoolDataSource();
            source.setUser("postgres");
            source.setPassword("12345");
            source.setUrl("jdbc:postgresql://localhost:5432/UDP");
            connection = source.getConnection();
            stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }
}


/**
 * Created by forch on 30-May-17.
 */

import java.io.*;
import java.net.*;

class UDP_client {
    public static void main(String args[]) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        Baby baby = new Baby("Enot",true,"lol",23232323);
        byte[] sendData = Serializer.serialize(baby);
        byte[] receiveData = new byte[1024];
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        BabyPackage baby2 = Serializer.deserialize(receivePacket.getData());
        System.out.println("FROM SERVER:" + baby2.getBaby().getName() + " " + baby2.getBaby().getSex());
        clientSocket.close();
    }
}
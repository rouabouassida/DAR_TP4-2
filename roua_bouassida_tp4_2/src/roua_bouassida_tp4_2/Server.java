package roua_bouassida_tp4_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
	
	private static final int PORT = 1234;
	private static final int buffLen = 1024;
	private static byte[] buffer = new byte[buffLen];
	
	private static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:mm:dd hh:mm:ss");
		return format.format(new Date());
	}
	
	public static void main(String[] args) {
		try {
			// Reservation du port 1234
			DatagramSocket socket = new DatagramSocket(PORT);
			
			System.out.println("Démarrage du server.");
			
			while(true) {
				
				// Preparation du packet dont on collecte les bytes reçus du Client
				DatagramPacket packet = new DatagramPacket(buffer, buffLen);
				
				// Preparation du packet à envoyer.
				DatagramPacket packetSend = new DatagramPacket(getCurrentTime().getBytes(), getCurrentTime().length(), packet.getAddress(), packet.getPort());
				
				// L'envoi du noveau packet.
				socket.send(packetSend);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

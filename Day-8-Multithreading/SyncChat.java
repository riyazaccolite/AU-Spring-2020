import java.net.*;
import java.io.*;
import java.util.*;

public class SyncChat {
	InetAddress group;
	final int port;
	MulticastSocket ms;
	
	boolean stopSending = false;
	
	SyncChat(int port) {
		this.port = 8088;
	}
	
	public static void main(String args[]) throws Exception{
		
		SyncChat syncChat = new SyncChat(8088);
		
		syncChat.group = InetAddress.getByName("228.5.4.103");
		syncChat.ms = new MulticastSocket(syncChat.port);
		syncChat.ms.joinGroup(syncChat.group);
		System.out.println("Connected");
		
		new Thread(new Sender(syncChat)).start();
		new Thread(new Receiver(syncChat)).start();
	}
	
	synchronized void toggleSending(boolean flag) {
		this.stopSending = flag;
		notifyAll();
	}
	
	boolean shouldStopSending() {
		return this.stopSending;
	}
	
}


class Sender implements Runnable{
	MulticastSocket ms;
	InetAddress group;
	int port;
	static DatagramPacket sentPacket;
	SyncChat syncChat;
	
	Sender(SyncChat syncChat){
		this.syncChat = syncChat;
		this.ms = syncChat.ms;
		this.group = syncChat.group;
		this.port = syncChat.port;
	}
	
	synchronized static void setSentPacket(DatagramPacket sentPacket) {
		Sender.sentPacket = sentPacket;
	}
	
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			synchronized(this.syncChat) {
				try{
					while(this.syncChat.shouldStopSending()) {
						this.syncChat.wait();
					}
					System.out.println("Enter your message");
					String message = sc.next();
					DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),group,8088);
					ms.send(packet);
					setSentPacket(packet);
					System.out.println("Sent "+message);
					this.syncChat.toggleSending(true);
					System.out.println("--Waiting for response--");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}

class Receiver implements Runnable{
	MulticastSocket ms;
	InetAddress group;
	int port;
	
	
	SyncChat syncChat;
	
	Receiver(SyncChat syncChat){
		this.syncChat = syncChat;
		this.ms = syncChat.ms;
		this.group = syncChat.group;
		this.port = syncChat.port;
	}
	
	public void run(){
		while(true){
				try{
					byte[] data = new byte[1024];
					DatagramPacket packet = new DatagramPacket(data,1024);
					
					ms.receive(packet);
					String message = new String(packet.getData()).trim();
					if(!(Sender.sentPacket!=null && message.equals((new String(Sender.sentPacket.getData())).trim()))) {
						System.out.println("Received: "+ message);
						Sender.setSentPacket(null);
						this.syncChat.toggleSending(false);
					}
					else {
						Sender.setSentPacket(null);
					}
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			
		}
	}
}
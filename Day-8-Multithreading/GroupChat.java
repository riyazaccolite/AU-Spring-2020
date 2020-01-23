import java.net.*;
import java.io.*;
import java.util.*;

public class GroupChat {
	InetAddress group;
	final int port;
	MulticastSocket ms;
	
	boolean stopSending = false;
	
	GroupChat(int port) {
		this.port = 8088;
	}
	
	public static void main(String args[]) throws Exception{
		
		GroupChat groupChat = new GroupChat(8088);
		
		groupChat.group = InetAddress.getByName("228.5.4.103");
		groupChat.ms = new MulticastSocket(groupChat.port);
		groupChat.ms.joinGroup(groupChat.group);
		System.out.println("Connected");
		
		new Thread(new Sender(groupChat)).start();
		new Thread(new Receiver(groupChat)).start();
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
	GroupChat groupChat;
	
	Sender(GroupChat groupChat){
		this.groupChat = groupChat;
		this.ms = groupChat.ms;
		this.group = groupChat.group;
		this.port = groupChat.port;
	}
	
	synchronized static void setSentPacket(DatagramPacket sentPacket) {
		Sender.sentPacket = sentPacket;
	}
	
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			synchronized(this.groupChat) {
				try{
					while(this.groupChat.shouldStopSending()) {
						this.groupChat.wait();
					}
					System.out.println("Enter your message");
					String message = sc.next();
					DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),group,8088);
					ms.send(packet);
					setSentPacket(packet);
					System.out.println("Sent "+message);
					this.groupChat.toggleSending(true);
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
	
	
	GroupChat groupChat;
	
	Receiver(GroupChat groupChat){
		this.groupChat = groupChat;
		this.ms = groupChat.ms;
		this.group = groupChat.group;
		this.port = groupChat.port;
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
						this.groupChat.toggleSending(false);
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
import java.net.*;
import java.util.*;

public class SyncChat {
	
	SyncChat(int port) {
		this.port = 8088;
	}
	
	private InetAddress group;
	private final int port;
	private MulticastSocket ms;
	private boolean stopSendingFlag = false;
	
	public InetAddress getGroup() {
		return group;
	}

	public void setGroup(InetAddress group) {
		this.group = group;
	}
	
	public int getPort() {
		return port;
	}
	
	public MulticastSocket getMs() {
		return ms;
	}

	public void setMs(MulticastSocket ms) {
		this.ms = ms;
	}
	
	synchronized void setStopSendingFlag(boolean flag) {
		this.stopSendingFlag = flag;
		notifyAll();
	}

	boolean getStopSendingFlag() {
		return this.stopSendingFlag;
	}
	
	synchronized void checkpoint() throws InterruptedException {
		while(this.getStopSendingFlag())
			wait();
	}
	
	public static void main(String args[]) throws Exception{
		
		SyncChat syncChat = new SyncChat(8088);
		
		syncChat.setGroup(InetAddress.getByName("228.5.4.103"));
		syncChat.setMs( new MulticastSocket(syncChat.port));
		syncChat.getMs().joinGroup(syncChat.group);
		System.out.println("Connected");
		
		new Thread(new Sender(syncChat)).start();
		new Thread(new Receiver(syncChat)).start();
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
		this.ms = syncChat.getMs();
		this.group = syncChat.getGroup();
		this.port = syncChat.getPort();
	}
	
	synchronized static void setSentPacket(DatagramPacket sentPacket) {
		Sender.sentPacket = sentPacket;
	}
	
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				this.syncChat.checkpoint();
				System.out.println("Enter your message");
				String message = sc.next();
				DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(),group,8088);
				this.ms.send(packet);
				setSentPacket(packet);
				System.out.println("Sent "+message);
				
				this.syncChat.setStopSendingFlag(true);
				System.out.println("--Waiting for response--");
			}
			catch(Exception e){
				e.printStackTrace();
				sc.close();
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
		this.ms = syncChat.getMs();
		this.group = syncChat.getGroup();
		this.port = syncChat.getPort();
	}
	
	public void run(){
		while(true){
			try{
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data,1024);
				
				this.ms.receive(packet);
				String message = new String(packet.getData()).trim();
				if(!(Sender.sentPacket!=null && message.equals((new String(Sender.sentPacket.getData())).trim()))) {
					System.out.println("Received: "+ message);
					Sender.setSentPacket(null);
					this.syncChat.setStopSendingFlag(false);
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
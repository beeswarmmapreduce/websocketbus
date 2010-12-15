package fi.helsinki.cs.wschat;

import java.util.HashSet;
import java.util.Set;

public class EndpointStore
{
	private String serviceName;
	
	private Set<ChatEndpoint> clients;
	
	public EndpointStore(String serviceName)
	{
		this.serviceName = serviceName;
		this.clients = new HashSet<ChatEndpoint>();
	}

	public void addEndpoint(ChatEndpoint chatEndpoint)
	{
		synchronized (clients) {
			clients.add(chatEndpoint);
			broadcast("A new ChatEndpoint has joined", chatEndpoint);
		}
	}

	public void removeEndpoint(ChatEndpoint chatEndpoint)
	{
		synchronized (clients) {
			clients.remove(chatEndpoint);
			broadcast("Has left", chatEndpoint);
		}
		
	}
	
	public void broadcast(String msg, ChatEndpoint sender)
	{
		synchronized (clients) {
			for (ChatEndpoint endpoint : clients) {
				if (endpoint == sender) continue;
				
				endpoint.sendMessage(sender.getName(), msg);
			}
		}
	}
	
}

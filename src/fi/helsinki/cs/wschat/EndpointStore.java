package fi.helsinki.cs.wschat;

import java.util.HashSet;
import java.util.Set;

public class EndpointStore
{
	@SuppressWarnings("unused")
	private String requestPath;
	
	private Set<ChatEndpoint> clients;
	
	public EndpointStore(String serviceName)
	{
		this.requestPath = serviceName;
		this.clients = new HashSet<ChatEndpoint>();
	}

	public void addEndpoint(ChatEndpoint chatEndpoint)
	{
		synchronized (clients) {
			clients.add(chatEndpoint);
		}
	}

	public void removeEndpoint(ChatEndpoint chatEndpoint)
	{
		synchronized (clients) {
			clients.remove(chatEndpoint);
		}
		
	}
	
	public void broadcast(String msg, ChatEndpoint sender)
	{
		synchronized (clients) {
			for (ChatEndpoint endpoint : clients) {
				endpoint.sendMessage(msg);
			}
		}
	}
	
}

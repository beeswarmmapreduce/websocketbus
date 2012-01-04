package fi.helsinki.cs.wschat;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;

public class ChatEndpoint implements WebSocket, WebSocket.OnTextMessage
{
	private EndpointStore store;
	private Connection out;
	
	public ChatEndpoint(EndpointStore es)
	{
		store = es;
	}

	public void sendMessage(String msg)
	{
		try {
			out.sendMessage(msg);
		} catch(IOException ie) {
			out.close();
		}
	}
	
	@Override
	public void onOpen(Connection out) 
	{
		this.out = out;
		store.addEndpoint(this);
	}

	@Override
	public void onClose(int i, String s) 
	{
		store.removeEndpoint(this);
	}

	@Override
	public void onMessage(String msg) 
	{
		store.broadcast(msg, this);
	}


	
	public String getName()
	{
		// TODO: ...
		return "Jack";
	}


}

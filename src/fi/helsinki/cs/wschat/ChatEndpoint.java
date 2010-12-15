package fi.helsinki.cs.wschat;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;

public class ChatEndpoint implements WebSocket
{
	private EndpointStore store;
	private Outbound out;
	
	public ChatEndpoint(EndpointStore es)
	{
		store = es;
	}

	public void sendMessage(String from, String msg)
	{
		try {
			out.sendMessage(from+": "+msg);
		} catch(IOException ie) {
			out.disconnect();
		}
	}
	
	@Override
	public void onConnect(Outbound out) 
	{
		this.out = out;
		store.addEndpoint(this);
	}

	@Override
	public void onDisconnect() 
	{
		store.removeEndpoint(this);
	}

	@Override
	public void onMessage(byte arg0, String msg) 
	{
		store.broadcast(msg, this);
	}


	
	public String getName()
	{
		// TODO: ...
		return "Jack";
	}

	@Override
	public void onFragment(boolean arg0, byte arg1, byte[] arg2, int arg3,
			int arg4) 
	{
		throw new RuntimeException("onFragment() is not supported!");
	}
	
	@Override
	public void onMessage(byte arg0, byte[] arg1, int arg2, int arg3)
	{
		throw new RuntimeException("onFragment() is not supported!");

	}
}

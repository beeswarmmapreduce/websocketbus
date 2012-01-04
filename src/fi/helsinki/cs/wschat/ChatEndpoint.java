package fi.helsinki.cs.wschat;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;

public class ChatEndpoint implements WebSocket, WebSocket.OnTextMessage, WebSocket.OnBinaryMessage, WebSocket.OnFrame
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

	@Override
	public boolean onFrame(byte arg0, byte arg1, byte[] arg2, int arg3, int arg4) 
	{
		throw new RuntimeException("onFragment() is not supported!");
	}
	
	@Override
	public void onMessage(byte[] arg0, int arg1, int arg2)
	{
		throw new RuntimeException("onMessage(byte[],int,int) is not supported!");

	}

	@Override
	public void onHandshake(FrameConnection arg0) {
		throw new RuntimeException("onHandsake() is not supported!");		
	}
}

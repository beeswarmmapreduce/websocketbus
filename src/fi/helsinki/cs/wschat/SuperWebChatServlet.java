package fi.helsinki.cs.wschat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class SuperWebChatServlet extends WebSocketServlet 
{
	private static final long serialVersionUID = 1L;

	
	private Map<String, EndpointStore> endpoints;
	
	public SuperWebChatServlet()
	{
		endpoints = new HashMap<String, EndpointStore>();
	}
	
	private synchronized EndpointStore getEndpointStore(String service)
	{
		EndpointStore ret = endpoints.get(service);
		if (ret == null) {
			ret = new EndpointStore(service);
			endpoints.put(service, ret);
		}
		
		return ret;
	}
	
	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest req, String service) 
	{	
		EndpointStore es = getEndpointStore(service);
		
		return new ChatEndpoint(es);
	}

}

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
	
	private synchronized EndpointStore getEndpointStore(String requestPath)
	{
		EndpointStore ret = endpoints.get(requestPath);
		if (ret == null) {
			ret = new EndpointStore(requestPath);
			endpoints.put(requestPath, ret);
		}
		
		return ret;
	}
	
	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest req, String service) 
	{	
		// Turn "//"'s into "/"'s
		StringBuffer path = new StringBuffer(req.getPathInfo());
		
		int i;
		while ( (i=path.indexOf("//")) != -1) {
			path.replace(i, i+2, "/");
		}
		
		EndpointStore es = getEndpointStore(path.toString());
		
		return new ChatEndpoint(es);
	}

}

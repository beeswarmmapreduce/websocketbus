import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class SuperWebChatServer 
{

	private static void setLogLevel(Level logLevel) 
	{
		Logger.getLogger("").setLevel(logLevel);
		for (Handler h : Logger.getLogger( "" ).getHandlers()) {
	        h.setLevel( logLevel );
	    }
	}
	
	public static void main(String[] args) throws Exception
	{		
		setLogLevel(Level.FINE);
		
		Server server = new Server(8080);
	
		server.setHandler(createSuperWebChat());
        
		try {
			server.start();			
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static WebAppContext createSuperWebChat() 
	{
		WebAppContext context = new WebAppContext();
		context.setResourceBase("webapp");
		context.setDescriptor("webapp/WEB-INF/web.xml");
		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		return context;
	}
	
}
package Unity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * This class accepts a request and returns a String with the request information.
 */
public class XMLRPCServer
{
	private String				response	= "";
	private ArrayList<String>	params		= new ArrayList<>();
// TODO DOCUMENT
	
	public String startRequest(String requestIn, ArrayList<String> paramsIn)
	{
		params = paramsIn;
		URL url = null;
		try
		{
			url = new URL("http://localhost:9002/ticketinfo.rem");
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XmlRpcClient client = new XmlRpcClient();
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(url);
		config.setConnectionTimeout(300000);
		config.setReplyTimeout(300000);
		
		client.setConfig(config);
		Object preResponse;
		/*
		 * I set it as an Object first to avoid any exceptions, and then parse it into
		 * the String, because that doesn't cause any problems
		 */
		try
		{
			preResponse = client.execute(requestIn, params);
			response = preResponse.toString();
		}
		catch (XmlRpcException e)
		{
			e.printStackTrace();
		}
		System.out.println(response.toString());
		return response;
	}
	
}
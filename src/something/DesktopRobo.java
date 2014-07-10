package something;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Virtual robot for desktop helping
 */
public class DesktopRobo {
	String strUrl;
	URI uri;
	URL url;
	URLConnection mURLConnection;
	CookieManager mCookieManager;
	Object mObject;
	CookieStore mCookieStore;
	String command;

	/**
	 * Construct Desktop Robot Object. Construct with input URL.
	 * 
	 * @throws IOException
	 */
	public DesktopRobo() throws IOException {
		this.mCookieManager = new CookieManager();
		mCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		CookieHandler.setDefault(mCookieManager);
	}

	/**
	 * Execute browsing url
	 */
	void OpenUrl() throws URISyntaxException {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error browsing url");
			}
		}
	}

	/**
	 * Get cookies
	 */
	private boolean GetCookie() throws IOException {
		mURLConnection = url.openConnection();
		mObject = mURLConnection.getContent();
		mCookieStore = mCookieManager.getCookieStore();
		List<HttpCookie> cookies = mCookieStore.getCookies();
		for (HttpCookie cookie : cookies) {
			System.out.println("Got cookies");
			System.out.println("Name: " + cookie.getName());
			System.out.println("Domain: " + cookie.getDomain());
			// System.out.println(cookie.getComment());
			// System.out.println(cookie.getValue());
			System.out.println("Value: " + cookie);
		}
		return true;
	}
	/**
	 * Ping particular ip address
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private boolean PingIP() throws IOException, InterruptedException{
		String command = "ping -c 3 " + strUrl;
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }
        proc.waitFor();
        return true;
	}
	/**
	 * Get local IP address
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private boolean IpStat() throws IOException, InterruptedException{
		String command = "ifconfig";
		Process proc = Runtime.getRuntime().exec(command);
        BufferedReader reader = 
            new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }
        proc.waitFor();
        return true;
	}
	/**
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * 
	 */
	public boolean inputCommand(String c) throws IOException, InterruptedException, URISyntaxException{
		String url;
		switch(c){
		case "help":
			System.out.println("Commands:help, local ip, ping, get cookie.");
			return true;
		case "local ip":
			return IpStat();
		case "ping":
			System.out.print("Enter url:");
			url = "http://"
					+ new BufferedReader(new InputStreamReader(System.in))
							.readLine();
			strUrl = url.substring(7);
			return PingIP();
		case "get cookie":
			System.out.print("Enter url:");
			url = "http://"
					+ new BufferedReader(new InputStreamReader(System.in))
							.readLine();
			this.url = new URL(url);
			return GetCookie();
		case "quit":
			System.out.print("Shut down");
			System.exit(0);
		default:
			return true;
		}
	}
}

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
	private String strUrl;
	private URI uri;
	private URL url;
	private URLConnection mURLConnection;
	private CookieManager mCookieManager;
	private CookieStore mCookieStore;
	private String command;

	/**
	 * Construct Desktop Robot Object.
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
		mURLConnection.getContent();
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
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private boolean PingIP() throws IOException, InterruptedException {
		command = "ping -c 3 " + strUrl;
		Process proc = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
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
	private boolean IpStat() throws IOException, InterruptedException {
		command = "ifconfig";
		Process proc = Runtime.getRuntime().exec(command);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				proc.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			System.out.print(line + "\n");
		}
		proc.waitFor();
		return true;
	}
	/**
	 * 
	 */
	private void info(){
		System.out.println("Develop by HR Wu\n2014/7/10");
	}

	/**
	 * Print Hi in star.
	 */
	private void printHi() {
		for (int i = 1; i <= 10; i++) {
			if (i == 1 || i == 4) {
				for (int j = 1; j <= 18; j++) {
					if (j == 1 || j == 2 || j == 11 || j == 12) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println("");
			} else if (i == 2 || i == 3 || i == 7 || i == 8 || i == 9
					|| i == 10) {
				for (int j = 1; j <= 18; j++) {
					if (j == 1 || j == 2 || j == 11 || j == 12 || j == 17
							|| j == 18) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				System.out.println("");
			} else {
				for (int j = 1; j <= 18; j++) {
					if (j == 13 || j == 14 || j == 15 || j == 16) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
				System.out.println("");
			}
		}
	}

	/**
	 * List all commands
	 */
	private void printCom() {
		System.out.println("Commands: info, help, my ip, ping, get cookie.");
	}

	/**
	 * To call sub function of DesktopRobo.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws URISyntaxException
	 * 
	 */
	public boolean inputCommand(String c) throws IOException,
			InterruptedException, URISyntaxException {
		String url;
		switch (c) {
		case "info":
			info();
			return true;
		case "hi":
			printHi();
			return true;
		case "help":
			printCom();
			return true;
		case "my ip":
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

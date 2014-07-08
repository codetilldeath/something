package something;

import java.awt.Desktop;
import java.io.IOException;
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
	URI uri;
	URL url;
	URLConnection mURLConnection;
	CookieManager mCookieManager;
	Object mObject;
	CookieStore mCookieStore;
	/**
	 * Construct Dektop Robot Object.
	 * Construct with input URL.
	 * @throws IOException 
	 */
	public DesktopRobo(String url) throws IOException {
		// TODO Auto-generated constructor stub
		try {
			this.uri = new URI("http://www.eyny.com");
			this.url = new URL(url);
			this.mCookieManager = new CookieManager();
			mCookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(mCookieManager);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Custom constructor with url
	 */
	public DesktopRobo(URI uri){
		this.uri = uri;
	}
	/**
	 * Execute browsing url
	 */
	void OpenUrl() throws URISyntaxException{
		if(Desktop.isDesktopSupported())
		{
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
	public void GetCookie() throws IOException{
		mURLConnection = url.openConnection();
		mObject = mURLConnection.getContent();
		mCookieStore = mCookieManager.getCookieStore();
		List<HttpCookie> cookies = mCookieStore.getCookies();
	    for (HttpCookie cookie : cookies) {
	    	System.out.println("Got cookies");
	    	System.out.println(cookie.getName());
//	    	System.out.println(cookie.getComment());
//	    	System.out.println(cookie.getValue());
	    	System.out.println(cookie);
	    }
	}
}

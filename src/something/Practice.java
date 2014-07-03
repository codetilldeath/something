package something;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


public class Practice {
	static DesktopRobo mDesktopRobo;

	public static void main(String[] args) throws IOException, URISyntaxException {
		String read;
		URI uri;
		System.out.println("Application initialized");
		System.out.println("Enter URL:");
		read = "http://"+new BufferedReader(new InputStreamReader(System.in)).readLine();
		uri = new URI(read);
		mDesktopRobo = new DesktopRobo(uri);
		mDesktopRobo.OpenUrl();
	}

}

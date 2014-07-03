package something;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DesktopRobo {
	URI uri;
	/*Default url set with eyny*/
	public DesktopRobo() {
		// TODO Auto-generated constructor stub
		try {
			uri = new URI("http://www.eyny.com");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DesktopRobo(URI uri){
		this.uri = uri;
	}
	void OpenUrl() throws URISyntaxException{
		if(Desktop.isDesktopSupported())
		{
		  try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}

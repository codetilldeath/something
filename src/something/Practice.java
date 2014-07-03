package something;

import java.net.URISyntaxException;


public class Practice {
	static DesktopRobo mDesktopRobo;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mDesktopRobo = new DesktopRobo();
		System.out.print("Application initialized");
		try {
			mDesktopRobo.OpenUrl();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

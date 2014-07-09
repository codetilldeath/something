package something;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practice {
	static DesktopRobo mDesktopRobo;
	static Thread intTest;

	static Runnable connect = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			try {
				mDesktopRobo.IpStat();
				mDesktopRobo.PingIP();
				mDesktopRobo.GetCookie();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	public static void main(String[] args) throws IOException {
		String read;
		System.out.println("Application initialized");
		System.out.println("Enter URL:");
		read = "http://"
				+ new BufferedReader(new InputStreamReader(System.in))
						.readLine();
		mDesktopRobo = new DesktopRobo(read);
		intTest = new Thread(connect);
		intTest.start();
	}
}

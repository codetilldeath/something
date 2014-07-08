package something;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practice {
	static DesktopRobo mDesktopRobo;

	public static void main(String[] args) throws IOException {
		String read;
		System.out.println("Application initialized");
		Runtime.getRuntime().exec("/bin/bash -c ifconfig");
		System.out.println("Enter URL:");
		read = "http://"
				+ new BufferedReader(new InputStreamReader(System.in))
						.readLine();
		mDesktopRobo = new DesktopRobo(read);
		mDesktopRobo.GetCookie();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mDesktopRobo.IpStat();
					mDesktopRobo.PingIP();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}

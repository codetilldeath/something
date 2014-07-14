package something;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class Practice {
	private static DesktopRobo mDesktopRobo;
	private static String read;
	private static boolean isFinished = false;

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		System.out.println("Starting application......");
		mDesktopRobo = new DesktopRobo();
		System.out.println("Application initialized");
		System.out.println("Enter command");
		while (true) {
			System.out.print(">>");
			read = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			isFinished = mDesktopRobo.inputCommand(read);
			while(true){
				if(isFinished){
					break;
				}
			}
		}

	}
}

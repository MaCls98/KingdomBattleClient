package run;

import java.io.IOException;

import controller.Controller;

public class RunnerClient {
	
	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
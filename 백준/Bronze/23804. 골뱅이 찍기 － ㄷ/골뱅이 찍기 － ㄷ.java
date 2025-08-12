import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		int a = Integer.parseInt(br.readLine());
		for (int i = 0; i < a; i++) {
			System.out.println("@".repeat(a * 5));
		}
		for (int i = 0; i < a * 3; i++) {
			System.out.println("@".repeat(a));
		}
		for (int i = 0; i < a; i++) {
			System.out.println("@".repeat(a * 5));
		}

	}
}

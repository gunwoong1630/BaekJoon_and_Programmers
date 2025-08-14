import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		int t= Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			int d = Integer.parseInt(br.readLine());
			int s = 0;
			int e = (int)Math.sqrt(d);
			while (s <= e) {
				int m = (s + e) / 2;
				int time = calculateRemainTime(m, d);
				if (time < 0) {
					e = m - 1;
				} else {
					s = m + 1;
				}
			}
			System.out.println(e);
		}
	}

	static int calculateRemainTime(int rate, int d) {
		return d - rate - rate * rate;
	}

}

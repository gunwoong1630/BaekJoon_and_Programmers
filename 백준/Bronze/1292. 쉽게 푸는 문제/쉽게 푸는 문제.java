import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 1 2 2 3 3 3 ... 47
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		String[] input = br.readLine().split(" ");
		int a = Integer.parseInt(input[0]);
		int b = Integer.parseInt(input[1]);

		List<Integer> sumList = new ArrayList<>();
		sumList.add(0);

		for (int i = 1; i < 47; i++) {
			for (int j = 0; j < i; j++) {
				sumList.add(sumList.get(sumList.size() - 1) + i);
			}
		}
		System.out.println(sumList.get(b) - sumList.get(a - 1));
	}
}

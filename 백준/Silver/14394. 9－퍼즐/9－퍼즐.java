import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		Map<Character, Integer> before = getMap(br.readLine().toCharArray());
		Map<Character, Integer> after = getMap(br.readLine().toCharArray());

		int diff = 0;
		for (Character c : List.of('R', 'G', 'B', 'Y')) {
			diff += Math.abs(before.get(c) - after.get(c));
		}
		System.out.println(diff/2);
	}

	private static Map<Character, Integer> getMap(char[] arr) {
		Map<Character, Integer> result = new HashMap<>();
		result.put('R', 0);
		result.put('G', 0);
		result.put('B', 0);
		result.put('Y', 0);

		for (char c : arr) {
			if (c != '*') {
				result.put(c, result.get(c) + 1);
			}
		}
		return result;
	}
}


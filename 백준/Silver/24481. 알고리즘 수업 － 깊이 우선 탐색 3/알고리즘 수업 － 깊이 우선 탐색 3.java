import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	private static int[] depth = new int[100_000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int r = Integer.parseInt(input[2]);

		Map<Integer, List<Integer>> rel = new HashMap<>();
		for (int i = 0; i < m; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1;
			int b = Integer.parseInt(input[1]) - 1;
			if (rel.containsKey(a)) {
				rel.get(a).add(b);
			} else {
				rel.put(a, new ArrayList<>(List.of(b)));
			}
			if (rel.containsKey(b)) {
				rel.get(b).add(a);
			} else {
				rel.put(b, new ArrayList<>(List.of(a)));
			}
		}

		for (List<Integer> value : rel.values()) {
			value.sort(Integer::compareTo);
		}

		Arrays.fill(depth, -1);
		depth[r - 1] = 0;
		boolean[] visit = new boolean[n];
		visit[r - 1] = true;
		dfs(r - 1, visit, rel);

		for (int i = 0; i < n; i++) {
			System.out.println(depth[i]);
		}

	}

	private static void dfs(int idx, boolean[] visit, Map<Integer, List<Integer>> rel) {
		if (!rel.containsKey(idx)) {
			return;
		}
		for (int nextIdx : rel.get(idx)) {
			if (!visit[nextIdx]) {
				visit[nextIdx] = true;
				depth[nextIdx] = depth[idx] + 1;
				dfs(nextIdx, visit, rel);
			}
		}

	}
}


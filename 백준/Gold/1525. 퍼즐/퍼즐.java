import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
	private static final int[][] MOVE = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (String s : br.readLine().split(" ")) {
				sb.append(s);
			}
		}
		String answer = "123456780";
		System.out.println(bfs(sb.toString(), answer));
	}

	private static int bfs(String init, String answer) {
		Map<String, Integer> visit = new HashMap<>();
		visit.put(init, 0);
		Queue<String> queue = new LinkedList<>();
		queue.add(init);

		while (!queue.isEmpty()) {
			String current = queue.poll();
			int moveCnt = visit.get(current);
			if (current.equals(answer)) {
				return moveCnt;
			}
			int zeroIdx = findZero(current);
			for (int i = 0; i < 4; i++) {
				int nextIdx = findNext(zeroIdx, i);
				if (nextIdx < 0) {
					continue;
				}
				String next = swap(current, nextIdx);

				if (visit.containsKey(next)) {
					continue;
				}
				visit.put(next, moveCnt + 1);
				queue.add(next);
			}

		}
		return -1;
	}

	private static int findNext(int idx, int d) {
		int[] coord = findCoord(idx);
		coord[0] += MOVE[d][0];
		coord[1] += MOVE[d][1];
		if (-1 < coord[0] && coord[0] < 3 && -1 < coord[1] && coord[1] < 3) {
			return findIdx(coord[0], coord[1]);
		}
		return -1;
	}

	private static int findZero(String puzzel) {
		return puzzel.indexOf('0');
	}

	private static int[] findCoord(int idx) {
		return new int[] {idx / 3, idx % 3};
	}

	private static int findIdx(int y, int x) {
		return y * 3 + x;
	}

	private static String swap(String before, int idx) {
		char idxValue = before.charAt(idx);
		return before.replace(idxValue, 't').replace('0', idxValue).replace('t', '0');

	}
}


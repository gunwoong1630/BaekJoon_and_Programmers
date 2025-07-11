import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	public int[] solution(int n, int[][] roads, int[] sources, int destination) {

		Map<Integer, List<Integer>> roadMap = new HashMap<>();
		for (int[] road : roads) {
			if (roadMap.containsKey(road[0])) {
				roadMap.get(road[0]).add(road[1]);
			} else {
				roadMap.put(road[0], new ArrayList<>(List.of(road[1])));
			}
			if (roadMap.containsKey(road[1])) {
				roadMap.get(road[1]).add(road[0]);
			} else {
				roadMap.put(road[1], new ArrayList<>(List.of(road[0])));
			}
		}
		int[] time = new int[n + 1];
		Arrays.fill(time, Integer.MAX_VALUE);
		time[destination] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(destination);
		while (!queue.isEmpty()) {
			Integer now = queue.poll();
			for (Integer next : roadMap.get(now)) {
				if (time[now] + 1 < time[next]) {
					time[next] = time[now] + 1;
					queue.add(next);
				}
			}
		}

		int[] ans = new int[sources.length];
		for (int i = 0; i < ans.length; i++) {
			if (time[sources[i]] == Integer.MAX_VALUE) {
				ans[i] = -1;
			} else {
				ans[i] = time[sources[i]];
			}
		}
		return ans;
	}

}
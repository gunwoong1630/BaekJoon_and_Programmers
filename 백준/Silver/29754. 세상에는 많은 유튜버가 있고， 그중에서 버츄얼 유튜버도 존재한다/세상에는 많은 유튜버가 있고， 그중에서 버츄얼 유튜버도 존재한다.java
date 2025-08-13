import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	// 일 월 화 수 목 금 토
	// 0 1 2 3 4 5 6
	// ... 362 363
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);

		Map<String, Map<Integer, WeekInfo>> broadcast = new HashMap<>();

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			String name = input[0];
			int day = Integer.parseInt(input[1]) - 1;
			int st = calculateTime(input[2]);
			int ed = calculateTime(input[3]);
			if (!broadcast.containsKey(name)) {
				broadcast.put(name, new HashMap<>());
			}
			Map<Integer, WeekInfo> scheduler = broadcast.get(name);
			if (scheduler.containsKey(day / 7)) {
				scheduler.get(day / 7).broadcast(st, ed);
			} else {
				scheduler.put(day / 7, new WeekInfo(st, ed));
			}
		}

		List<String> realVtubers = new ArrayList<>();
		for (Map.Entry<String, Map<Integer, WeekInfo>> entry : broadcast.entrySet()) {
			if (isRealVtuber(entry.getValue(), M / 7)) {
				realVtubers.add(entry.getKey());
			}
		}
		if (realVtubers.isEmpty()) {
			System.out.println(-1);
			return;
		}
		realVtubers.sort(String::compareTo);
		for (String realVtuber : realVtubers) {
			System.out.println(realVtuber);
		}
	}

	private static int calculateTime(String time) {
		String[] parts = time.split(":");
		int hour = Integer.parseInt(parts[0]);
		int minute = Integer.parseInt(parts[1]);
		return hour * 60 + minute;
	}

	private static boolean isRealVtuber(Map<Integer, WeekInfo> scheduler, int week) {
		for (WeekInfo weekInfo : scheduler.values()) {
			if (weekInfo.getCnt() < 5 || weekInfo.getTime() < 60 * 60) {
				return false;
			}
		}
		return scheduler.keySet().size() == week;
	}

	static class WeekInfo {
		private int cnt;
		private int time;

		public WeekInfo(int st, int ed) {
			this.cnt = 1;
			this.time = ed - st;
		}

		public int getCnt() {
			return cnt;
		}

		public int getTime() {
			return time;
		}

		public void broadcast(int st, int ed) {
			this.cnt++;
			this.time += (ed - st);
		}
	}

}


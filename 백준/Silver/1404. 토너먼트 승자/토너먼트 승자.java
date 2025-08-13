import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {

	// 일 월 화 수 목 금 토
	// 0 1 2 3 4 5 6
	// ... 362 363
	// 0 1 2 3 4 5 6 7
	private static final Map<Integer, List<Integer>> ROUND_2 = Map.of(
		0, List.of(2, 3),
		1, List.of(2, 3),
		2, List.of(0, 1),
		3, List.of(0, 1),
		4, List.of(6, 7),
		5, List.of(6, 7),
		6, List.of(4, 5),
		7, List.of(4, 5)
	);
	private static final Map<Integer, List<Integer>> ROUND_3 = Map.of(
		0, List.of(4, 5, 6, 7),
		1, List.of(4, 5, 6, 7),
		2, List.of(4, 5, 6, 7),
		3, List.of(4, 5, 6, 7),
		4, List.of(0, 1, 2, 3),
		5, List.of(0, 1, 2, 3),
		6, List.of(0, 1, 2, 3),
		7, List.of(0, 1, 2, 3)
	);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		//
		// 7 6 5 4 3 2 1
		String[] input = br.readLine().split(" ");

		double[][] win = new double[8][8];
		double[][] winRate = new double[3][8];

		int idx = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 8; j++) {
				win[i][j] = Double.parseDouble(input[idx]) / 100.0;
				win[j][i] = 1.0 - win[i][j];
				idx++;
			}
		}

		// 1 round
		for (int i = 0; i < 8; i++) {
			winRate[0][i] = win[i][i % 2 == 0 ? i + 1 : i - 1];
		}
		// 2 round
		for (int i = 0; i < 8; i++) {
			for (Integer enemy : ROUND_2.get(i)) {
				winRate[1][i] += winRate[0][i] * winRate[0][enemy] * win[i][enemy];
			}
		}
		// 3 round
		for (int i = 0; i < 8; i++) {
			for (Integer enemy : ROUND_3.get(i)) {
				winRate[2][i] += winRate[1][i] * winRate[1][enemy] * win[i][enemy];
			}
		}

		for (double v : winRate[2]) {
			System.out.println(BigDecimal.valueOf(v)
				.stripTrailingZeros()
				.toPlainString());

		}

	}

}


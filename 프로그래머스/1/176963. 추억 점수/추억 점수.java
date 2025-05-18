import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        Map<String, Integer> scoreMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            scoreMap.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            for (String photoName : photo[i]) {
                answer[i] += scoreMap.getOrDefault(photoName, 0);
            }
        }
        return answer;
    }
}
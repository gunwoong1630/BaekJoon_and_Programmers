import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Homework> homework = new PriorityQueue<>();
        Stack<Homework> stoppedHomework = new Stack<>();

        for (String[] plan : plans) {
            homework.add(new Homework(plan));
        }
        List<String> answer = new ArrayList<>();

        while (!homework.isEmpty()) {
            Homework now = homework.poll();

            if (homework.isEmpty()) {
                answer.add(now.getName());
                continue;
            }

            Homework next = homework.peek();

            if (now.isFinishBefore(next)) {
                answer.add(now.getName());
                int time = now.getReservedTime() + now.getProcessTime();
                while (!stoppedHomework.isEmpty()) {
                    Homework stopped = stoppedHomework.pop();
                    if (stopped.isFinishBefore(time, next)) {
                        answer.add(stopped.getName());
                        time += stopped.getProcessTime();
                    } else {
                        stopped.process(next.getReservedTime() - time);
                        stoppedHomework.add(stopped);
                        break;
                    }
                }
            } else {
                now.process(next.getReservedTime() - now.getReservedTime());
                stoppedHomework.add(now);
            }
        }
        while (!stoppedHomework.isEmpty()) {
            answer.add(stoppedHomework.pop().getName());
        }
        return answer.toArray(new String[0]);
    }
}

class Homework implements Comparable<Homework> {
    private final String name;
    private final int reservedTime;
    private int processTime;

    public Homework(String[] data) {
        this.name = data[0];
        String[] timeInfo = data[1].split(":");
        this.reservedTime = 60 * Integer.parseInt(timeInfo[0]) + Integer.parseInt(timeInfo[1]);
        this.processTime = Integer.parseInt(data[2]);
    }

    public String getName() {
        return name;
    }

    public int getReservedTime() {
        return reservedTime;
    }

    public int getProcessTime() {
        return processTime;
    }

    public void process(int processTime) {
        this.processTime -= processTime;
    }

    public boolean isFinishBefore(Homework target) {
        return reservedTime + processTime <= target.getReservedTime();
    }

    public boolean isFinishBefore(int time, Homework target) {
        return time + this.processTime <= target.getReservedTime();
    }

    @Override
    public int compareTo(Homework o) {
        return Integer.compare(this.reservedTime, o.getReservedTime());
    }

    @Override
    public String toString() {
        return "Homework{" +
                "name='" + name + '\'' +
                ", time=" + reservedTime +
                ", processTime=" + processTime +
                '}';
    }
}

//public class Test {
//    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Solution().solution(new String[][]{
//                {"science", "12:40", "50"},
//                {"music", "12:20", "40"},
//                {"history", "14:00", "30"},
//                {"computer", "12:30", "100"}
//        })));
//    }
//}

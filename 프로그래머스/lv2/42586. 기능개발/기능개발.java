import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<progresses.length; i++) {
            dq.offer((int)Math.ceil((100 - progresses[i]) / (double)speeds[i]));
        }
        int first = dq.poll();
        int cnt = 1;
        while(!dq.isEmpty()) {
            if(first >= dq.peek()) {
                dq.poll();
                cnt++;
            } else {
                list.add(cnt);
                first = dq.poll();
                cnt = 1;
            }
        }
        list.add(cnt);
        answer = list.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
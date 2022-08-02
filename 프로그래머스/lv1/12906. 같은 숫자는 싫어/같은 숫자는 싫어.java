import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0; i<arr.length; i++) {
            if (deque.peekLast() == null || deque.peekLast() != arr[i]) {
                deque.offer(arr[i]);
            }
        }

        int[] answer = deque.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
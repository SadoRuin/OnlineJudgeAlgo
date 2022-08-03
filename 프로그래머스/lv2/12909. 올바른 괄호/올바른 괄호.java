import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Deque<Character> dq = new ArrayDeque<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                dq.offer(s.charAt(i));
            } else if(s.charAt(i) == ')') {
                if(dq.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    dq.poll();
                }
            }
        }
        if(!dq.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}
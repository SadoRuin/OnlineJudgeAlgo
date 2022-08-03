class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int cnt = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                cnt++;
            } else if(s.charAt(i) == ')') {
                cnt--;
                if(cnt < 0) {
                    answer = false;
                    return answer;
                }
            }
        }
        if(cnt == 0) {
            answer = true;
        } else if(cnt > 0) {
            answer = false;
        }

        return answer;
    }
}
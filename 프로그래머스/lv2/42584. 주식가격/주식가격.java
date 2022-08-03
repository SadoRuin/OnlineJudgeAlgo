class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        int now = 0;
        for(int i=0; i<n; i++) {
            now = prices[i];
            int cnt = 0;
            for(int j=i+1; j<n; j++) {
                if(now > prices[j]) {
                    cnt++;
                    break;
                } else {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }
        return answer;
    }
}
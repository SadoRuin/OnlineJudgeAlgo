import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n :
                scoville) {
            pq.offer(n);
        }
        while(pq.peek() < K) {
            if(pq.size() == 1) {
                answer = -1;
                break;
            }
            int min1 = pq.poll();
            int min2 = pq.poll();
            int newValue = min1 + (min2 * 2);
            pq.offer(newValue);
            answer++;
        }
        return answer;
    }
}
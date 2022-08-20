import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        Queue<Integer> q = new ArrayDeque<>();  // 방문할 컴퓨터를 기록할 큐
        boolean[] visited = new boolean[n]; // 방문 여부를 기록할 boolean 배열

        for(int i=0; i<n; i++) {
            if(visited[i]) continue;    // 방문한적 있다면 다음 컴퓨터로

            q.offer(i); // 현재 컴퓨터부터 탐색

            while(!q.isEmpty()) {   // 방문할 컴퓨터가 없을때까지
                int cur = q.poll(); // 현재 컴퓨터

                for (int j = 0; j < n; j++) {
                    if (!visited[j] && computers[cur][j] == 1) {    // 방문한적 없거나 연결된 컴퓨터라면
                        visited[j] = true;  // 방문 처리를 하고
                        q.offer(j); // 방문할 컴퓨터로 큐에 추가
                    }
                }
            }

            answer++;   // 연결된 모든 컴퓨터의 방문이 끝나면 하나의 네트워크 개수 증가
        }

        return answer;
    }
}
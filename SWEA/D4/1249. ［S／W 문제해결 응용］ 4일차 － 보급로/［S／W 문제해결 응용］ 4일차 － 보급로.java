import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static final int[] dx = {0, 1, 0, -1};   // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};   // 우, 하, 좌, 상

    static class Point {
        int x, y, d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        for(int tc = 1; tc<=T; tc++) {  // 테스트 케이스 반복
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];    // 지도
            int[][] cost = new int[N][N];   // 비용을 나타내는 배열
            for(int i=0; i<N; i++) {
                String st = br.readLine();
                for(int j=0; j<N; j++) {
                    map[i][j] = st.charAt(j) - '0';
                    cost[i][j] = 987654321; // 각 위치에서의 비용은 모두 큰값으로 초기화
                }
            }

            Queue<Point> queue = new ArrayDeque<>();
            queue.offer(new Point(0, 0, 0));    // 처음 위치 큐에 저장
            // bfs 수행
            while(!queue.isEmpty()) {
                Point p = queue.poll();

                for(int i=0; i<4; i++) {
                    int nextX = p.x + dx[i];    // 다음 행 위치
                    int nextY = p.y + dy[i];    // 다음 열 위치
                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {    // 다음 위치가 범위 내이고
                        if(p.d + map[nextX][nextY] < cost[nextX][nextY]) {  // 다음 위치까지의 비용이 저장된 비용보다 더 작을 경우
                            cost[nextX][nextY] = p.d + map[nextX][nextY];   // 최소 비용을 갱신
                            queue.offer(new Point(nextX, nextY, p.d + map[nextX][nextY]));  // 그 위치를 방문하기위해 큐에 저장
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(cost[N-1][N-1]).append("\n");  // 출력물 저장
        }

        System.out.println(sb); // 최종 출력
    }
}

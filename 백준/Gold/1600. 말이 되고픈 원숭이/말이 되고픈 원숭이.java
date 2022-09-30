import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static final int[] dx = {0, 1, 0, -1};  // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};  // 우, 하, 좌, 상
    static final int[] dhx = {-2, -1, 1, 2, 2, 1, -1, -2};  // 말처럼 행 이동
    static final int[] dhy = {1, 2, 2, 1, -1, -2, -2, -1};  // 말처럼 열 이동

    static class Point {
        int x, y, k, d;

        public Point(int x, int y, int k, int d) {
            this.x = x;
            this.y = y;
            this.k = k; // 말처럼 이동 가능한 횟수
            this.d = d; // 이동횟수
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split(" ");
        int W = Integer.parseInt(st[0]);
        int H = Integer.parseInt(st[1]);
        int[][] map = new int[H][W];
        boolean[][][] visited = new boolean[H][W][K+1]; // 각 위치의 k값에 따른 방문처리 배열

        for (int i = 0; i < H; i++) {
            st = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st[j]);
                if (map[i][j] == 1) {   // 장애물이라면
                    for (int k = 0; k <= K; k++) {   // 해당 위치의 방문처리배열 모두 방문처리
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        // bfs 수행
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, K, 0));    // 초기좌표 0, 0과 말처럼 이동가능한 횟수 K
        for(int k=0; k<=K; k++) {
            visited[0][0][k] = true;    // 처음 위치의 모든 방문기록 방문처리
        }

        int D = -1; // 최종 이동 거리 (마지막위치까지 도달 못할 경우 -1로 유지)

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (p.x == H - 1 && p.y == W - 1) { // 마지막 위치라면
                System.out.println(p.d);    // 지금까지의 이동 횟수 출력
                System.exit(0); // 처음으로 마지막 위치에 방문할 때가 최소이동이므로 프로그램 종료
            }

            if (p.k > 0) {  // 말처럼 이동가능한 횟수가 남았다면
                for (int i = 0; i < 8; i++) {   // 말처럼 이동
                    int nextX = p.x + dhx[i];   // 다음 행 위치
                    int nextY = p.y + dhy[i];   // 다음 열 위치
                    if (nextX >= 0 && nextX < H && nextY >= 0 && nextY < W  // 다음 위치가 범위 내이고
                            && !visited[nextX][nextY][p.k - 1]) {    // k-1값으로 방문한 적이 없고 장애물이 없다면
                        visited[nextX][nextY][p.k - 1] = true;  // k-1값일때의 그 위치를 방문처리
                        queue.offer(new Point(nextX, nextY, p.k - 1, p.d + 1));  // 큐에 이동거리+1, 말처럼 이동가능 횟수-1하고 저장
                    }
                }
            }

            for (int i = 0; i < 4; i++) {   // 우, 하, 좌, 상 이동
                int nextX = p.x + dx[i];    // 다음 행위치
                int nextY = p.y + dy[i];    // 다음 열위치
                if (nextX >= 0 && nextX < H && nextY >= 0 && nextY < W  // 다음 위치가 범위 내이고
                        && !visited[nextX][nextY][p.k]) {    // 현재 k값으로 방문한 적이 없고 장애물이 없다면
                    visited[nextX][nextY][p.k] = true;  // 현재 k값일때의 그 위치를 방문처리
                    queue.offer(new Point(nextX, nextY, p.k, p.d + 1));  // 큐에 이동거리+1, k값 유지하고 저장
                }
            }

        }

        System.out.println(D);  // 최종 이동횟수 출력
    }
}

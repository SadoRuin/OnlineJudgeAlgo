import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static final int[] dx = {0, 1, 0, -1};  // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};  // 우, 하, 좌, 상

    static int N, M, cheeseCnt;
    static int[][] cheese;
    static boolean[][] visited;
    static Queue<int[]> queue;

    static void bfs() {
        for (boolean[] v :
                visited) {
            Arrays.fill(v, false);  // 방문배열 초기화
        }
        cheeseCnt = 0;  // 현재 bfs에서의 제거되는 치즈의 개수 카운팅
        queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});  // 시작 지점 0,0을 큐에 저장
        visited[0][0] = true;   // 0,0을 방문처리

        while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
            int[] cur = queue.poll();   // 현재 위치를 꺼냄
            int x = cur[0]; // 현재 행 위치
            int y = cur[1]; // 현재 열 위치

            for(int i=0; i<4; i++) {    // 우, 하, 좌, 상 반복
                int nextX = x + dx[i];  // 다음 행 위치
                int nextY = y + dy[i];  // 다음 열 위치
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M  // 다음 위치가 범위 내이고
                    && !visited[nextX][nextY]) {    // 방문하지 않았다면
                    if(cheese[nextX][nextY] == 0) { // 치즈가 없는 칸이면
                        visited[nextX][nextY] = true;   // 방문 처리하고
                        queue.offer(new int[] {nextX, nextY});  // 다음 방문할 위치로 큐에 저장
                    } else if(cheese[nextX][nextY] == 1){   // 치즈가 있다면
                        cheeseCnt++;    // 제거할 치즈 개수 카운팅 증가
                        visited[nextX][nextY] = true;   // 방문처리
                        cheese[nextX][nextY] = 0;   // 치즈 삭제
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);
        cheese = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                cheese[i][j] = Integer.parseInt(st[j]);
            }
        }

        int cnt = 0;    // 치즈가 녹는데 걸리는 시간
        int remainCheese = 0;   // 남은 치즈 개수
        while(true) {   // 무한반복
            bfs();  // bfs 수행
            if(cheeseCnt == 0) break;   // 제거되는 치즈가 없었다면 반복 종료
            remainCheese = cheeseCnt;   // 남은 치즈개수에 제거된 치즈개수 저장 (마지막에 제거되는 치즈 개수가 마지막에 남은 치즈개수)
            cnt++;  // 시간 증가
        }

        System.out.println(cnt);
        System.out.println(remainCheese);
    }
}

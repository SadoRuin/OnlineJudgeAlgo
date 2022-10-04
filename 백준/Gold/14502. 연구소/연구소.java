import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static final int[] dx = {0, 1, 0, -1};  // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};  // 우, 하, 좌, 상

    static int N, M, max;
    static int[][] map;

    // 3개의 방벽 위치를 정하고 그 경우에 바이러스가 퍼진다면 남는 안전영역 개수 계산
    static void dfs(int cnt) {
        if(cnt == 3) {  // 벽 3개를 다 설치 했다면
            Queue<int[]> queue = new ArrayDeque<>();
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] == 2) {    // 바이러스 위치
                        queue.offer(new int[] {i, j});  // 전부 큐에 저장
                    }
                }
            }
            int[][] newMap = new int[N][M];
            for(int i=0; i<N; i++) {    // 원본이 변경되지 않도록 카피
                System.arraycopy(map[i], 0, newMap[i], 0, M);
            }

            while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for(int i=0; i<4; i++) {
                    int nextX = x + dx[i];  // 다음 행 위치
                    int nextY = y + dy[i];  // 다음 열 위치
                    if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M   // 다음 위치가 범위내이면서
                        && newMap[nextX][nextY] == 0) { // 빈칸이라면
                        newMap[nextX][nextY] = 2;   // 바이러스가 퍼지고
                        queue.offer(new int[] {nextX, nextY});  // 다음 위치를 큐에 저장
                    }
                }
            }
            int safeArea = 0;   // 안전영역 개수
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {    // 모든 영역 탐색
                    if(newMap[i][j] == 0) { // 빈칸일경우
                        safeArea++; // 안전영역 개수 증가
                    }
                }
            }

            max = Math.max(max, safeArea);  // 최대값 갱신

            return;
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {    // 모든 영역 반복
                if(map[i][j] == 0) {    // 빈칸일 경우
                    map[i][j] = 1;  // 벽을 세우고
                    dfs(cnt + 1);   // 벽개수 증가한 상태로 다시 dfs 수행
                    map[i][j] = 0;  // 다른 위치에 설치하기 위해 벽 초기화
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st[j]);
            }
        }

        dfs(0); // dfs 수행

        System.out.println(max);    // 최대개수 출력
    }
}

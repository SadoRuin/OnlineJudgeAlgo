import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {1, 0, -1, 0};

    static class Point {
        int x, y, d, k;

        public Point(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);

        char[][] maze = new char[N][M];
        Queue<Point> queue = new ArrayDeque<>();
        // 어떤 키를 가지고 있냐에 따라 다른 방문처리
        // 각 키를 가지고 있는 상태를 비트마스킹으로 나타냄
        // a : 000001, b : 000010, c : 000100, d : 001000, e : 010000, f : 100000
        boolean[][][] visited = new boolean[N][M][64];

        for(int i=0; i<N; i++) {
            String s = br.readLine();
            for(int j=0; j<M; j++) {
                maze[i][j] = s.charAt(j);
                if(maze[i][j] == '0') {
                    queue.offer(new Point(i, j, 0, 0));
                    visited[i][j][0] = true;
                }
            }
        }

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            if(maze[p.x][p.y] == '1') { // 출구에 도달 했다면
                System.out.println(p.d);    // 현재까지의 이동횟수가 최소이므로 출력
                System.exit(0); // 프로그램 종료
            }

            for(int i=0; i<4; i++) {
                int nextX = p.x + dx[i];    // 다음 행 위치
                int nextY = p.y + dy[i];    // 다음 열 위치
                if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M   // 다음 위치가 범위내이고
                    && !visited[nextX][nextY][p.k]) {   // 현재 보유한 키로 방문한적이 없다면
                    char c = maze[nextX][nextY];
                    if(c == '#') continue;  // 벽일 경우는 스킵
                    if(c >= 'a' && c <= 'f') {  // 소문자 'a'부터 'f'사이일 경우
                        int key = p.k | 1<<(c - 'a');   // 현재 보유한 키들과 현재 위치의 키와 or 연산
                        visited[nextX][nextY][p.k] = true;  // 현재 보유한 키의 방문처리
                        visited[nextX][nextY][key] = true;  // 현재 위치의 키를 주웠을 때 방문처리
                        queue.offer(new Point(nextX, nextY, p.d + 1, key)); // 다음 위치에 새로운 키보유 상태로 큐에 추가
                    } else if(c >= 'A' && c <= 'F') {   // 대문자일 경우
                        if((p.k & 1<<(c - 'A')) != 0) { // 현재 대문자와 대응되는 키를 보유했다면
                            visited[nextX][nextY][p.k] = true;  // 방문처리 후
                            queue.offer(new Point(nextX, nextY, p.d + 1, p.k)); // 다음 위치 큐에 추가
                        }
                    } else {    // 그 외에는
                        visited[nextX][nextY][p.k] = true;  // 방문처리 후 
                        queue.offer(new Point(nextX, nextY, p.d + 1, p.k)); // 다음 위치 큐에 추가
                    }
                }
            }
        }

        System.out.println(-1); // 출구에 도달 못하면 -1 출력
    }
}

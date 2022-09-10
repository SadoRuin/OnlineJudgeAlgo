import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static final int[] dx = {0, 1, 0, -1};  // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};  // 우, 하, 좌, 상

    static int N, L, R, totalCnt, sum;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> list;

    static void dfs(int x, int y) {

        for(int i=0; i<4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY]) {
                int diff = Math.abs(map[x][y] - map[nextX][nextY]);
                if(diff >= L && diff <= R) {
                    visited[nextX][nextY] = true;
                    list.add(new int[] {nextX, nextY});
                    sum += map[nextX][nextY];
                    dfs(nextX, nextY);
                }
            }
        }
    }

    static int move() {
        for (boolean[] v :
                visited) {
            Arrays.fill(v, false);
        }
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if (!visited[i][j]) {
                    sum = map[i][j];
                    visited[i][j] = true;
                    list.add(new int[] {i, j});
                    dfs(i, j);
                    int size = list.size();
                    if(size > 1) {
                        for (int[] p :
                                list) {
                            int x = p[0];
                            int y = p[1];
                            map[x][y] = sum / size;
                        }
                        cnt++;
                    }
                    list.clear();
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");

        N = Integer.parseInt(st[0]);
        L = Integer.parseInt(st[1]);
        R = Integer.parseInt(st[2]);

        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            st = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st[j]);
            }
        }

        sum = 0;
        list = new ArrayList<>();
        while(move() > 0) {
            totalCnt++;
        }

        System.out.println(totalCnt);
    }
}

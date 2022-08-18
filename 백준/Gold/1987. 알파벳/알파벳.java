import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static final int[] dx = {0, 1, 0, -1};  // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};  // 우, 하, 좌, 상

    static char[][] board;
    static int R, C, max;
    static ArrayList<Character> log;

    static void dfs(int depth, int r, int c) {
        if(depth == R*C) {  // 모든 칸을 다 탐색했을 경우
            max = Math.max(max, log.size());    // 리스트 내에 있는 알파벳 수 최대값 비교
            return;
        }
        if(log.indexOf(board[r][c]) != -1) {    // 알파벳이 리스트내에 이미 있을 경우
            max = Math.max(max, log.size());    // 여태 지나온 알파벳 개수 최대값 비교 후 종료
            return;
        }
        log.add(board[r][c]);   // 리스트에 현재 알파벳 추가
        for(int i=0; i<4; i++) {    // 4방향 탐색
            if(r+dx[i]<0 || r+dx[i]>=R || c+dy[i]<0 || c+dy[i]>=C) {
                continue;   // 범위 벗어나면 다른 방향으로
            }
            dfs(depth+1, r+dx[i], c+dy[i]); // 이동한 위치에서 새로 dfs실행
        }
        log.remove(log.size()-1);   // 4방향 탐색 끝나면 현재 알파벳 제거
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        R = Integer.parseInt(st[0]);
        C = Integer.parseInt(st[1]);

        board = new char[R][C];     // 알파벳 보드
        for(int i=0; i<R; i++) {
            String s = br.readLine();
            for(int j=0; j<C; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        log = new ArrayList<>();
        dfs(0, 0, 0);

        System.out.println(max);

    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] board;
    static boolean end; // 실행중인 재귀를 모두 종료시키기 위한 flag변수

    static void dfs(int cnt) {
        if(cnt == 81) { // 0,0 ~ 8,8 까지
            end = true; // 스도쿠가 완성됐으므로 다른 실행중인 재귀를 모두 종료
            return;
        }
        int x = cnt / 9;    // 현재의 행 좌표
        int y = cnt % 9;    // 현재의 열 좌표

        if(board[x][y] != 0) {  // 현재 위치가 0이 아니라면
            dfs(cnt + 1);   // 다음 위치에서 다시 dfs 수행
        } else {    // 0이 아니라면
            for(int i=1; i<=9; i++) {   // 넣을 숫자 1부터 9까지 반복
                board[x][y] = i;    // 해당 숫자 삽입
                if(check(x, y)) {   // 해당 위치에서 규칙에 맞는 숫자인지를 판별
                    dfs(cnt + 1);   // 규칙에 맞다면 다음 위치에서 다시 dfs 수행
                }
                if(end) return; // 스도쿠가 완성 되었으면 현재 재귀 종료
                board[x][y] = 0;    // 다른 숫자 삽입을 위해 0으로 초기화
            }
        }
    }

    // 현재 위치의 숫자가 스도쿠 규칙에 맞는 숫자인지 판별하는 메소드
    private static boolean check(int x, int y) {
        // 가로체크
        for(int i=0; i<9; i++) {
            if(y == i) continue;    // 현재 위치는 스킵
            if(board[x][i] == board[x][y]) return false;    // 다른 숫자들 중 현재 위치의 숫자와 같으면 false
        }
        // 세로체크
        for(int i=0; i<9; i++) {
            if(x == i) continue;    // 현재 위치는 스킵
            if(board[i][y] == board[x][y]) return false;    // 다른 숫자들 중 현재 위치의 숫자와 같으면 false
        }
        // 스퀘어 체크
        int nx = x / 3 * 3; // 현재 위치가 포함된 스퀘어의 첫번째 행위치
        int ny = y / 3 * 3; // 현재 위치가 포함된 스퀘어의 첫번째 열위치
        for(int i=nx; i<nx+3; i++) {
            for(int j=ny; j<ny+3; j++) {    // 스퀘어내 반복
                if(i == x && j == y) continue;  // 현재 위치는 스킵
                if(board[i][j] == board[x][y]) return false;    // 다른 숫자들 중 현재 위치의 숫자와 같으면 false
            }
        }

        return true;    // 전부 통과하면 true
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        board = new int[9][9];
        for(int i=0; i<9; i++) {
            String st = br.readLine();
            for(int j=0; j<9; j++) {
                board[i][j] = st.charAt(j) - '0';   // 스도쿠판 입력
            }
        }

        dfs(0); // dfs 수행

        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                sb.append(board[i][j]); // board 내용 출력 저장
            }
            sb.append("\n");
        }

        System.out.println(sb); // 출력
    }
}

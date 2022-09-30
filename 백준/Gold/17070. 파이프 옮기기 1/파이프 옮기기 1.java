import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            String[] st = br.readLine().split(" ");
            for(int j=1; j<=N; j++) {
                board[i][j] = Integer.parseInt(st[j-1]);
            }
        }
        int[][][] dp = new int[N+1][N+1][3];    // 0:가로, 1:세로, 2:대각
        dp[1][2][0] = 1;    // 가로 파이프의 처음 위치에서 끝부분의 좌표

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==1 && j==2) continue;  // 처음 위치가 0으로 갱신되지 않게 스킵
                if(board[i][j] == 1) continue;  // 벽이라면 스킵
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];    // 지금 가로가 되려면 이전위치에서 가로 or 대각
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];    // 지금 세로가 되려면 이전위치에서 세로 or 대각

                if(board[i-1][j] == 0 && board[i][j-1] == 0) {  // 대각 범위 안에 벽이 없을 경우만
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];  // 지금 대각이 되려면 이전 위치에서 가로 or 세로 or 대각
                }
            }
        }

        System.out.println(Arrays.stream(dp[N][N]).sum());  // 마지막 위치에서의 모든 경우의 개수 출력
    }
}

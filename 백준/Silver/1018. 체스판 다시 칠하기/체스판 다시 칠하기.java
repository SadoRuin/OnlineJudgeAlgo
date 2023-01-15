import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);
        char[][] board = new char[N][M];

        String s;
        for(int i=0; i<N; i++) {
            s = br.readLine();
            for(int j=0; j<M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int min = 987654321;
        for(int n=0; n<N-7; n++) {
            for(int m=0; m<M-7; m++) {
                int cnt = 0;
                for(int i=n; i<n+8; i++) {
                    for(int j=m; j<m+8; j++) {
                        int diff = (i+j) - (n+m);
                        if(diff % 2 == 0) {
                            if('W' != board[i][j]) {
                                cnt++;
                            }
                        } else {
                            if('W' == board[i][j]) {
                                cnt++;
                            }
                        }
                    }
                }
                min = Math.min(min, cnt);
                cnt = 0;
                for(int i=n; i<n+8; i++) {
                    for(int j=m; j<m+8; j++) {
                        int diff = (i+j) - (n+m);
                        if(diff % 2 == 0) {
                            if('B' != board[i][j]) {
                                cnt++;
                            }
                        } else {
                            if('B' == board[i][j]) {
                                cnt++;
                            }
                        }
                    }
                }
                min = Math.min(min, cnt);
            }
        }

        System.out.println(min);
    }
}

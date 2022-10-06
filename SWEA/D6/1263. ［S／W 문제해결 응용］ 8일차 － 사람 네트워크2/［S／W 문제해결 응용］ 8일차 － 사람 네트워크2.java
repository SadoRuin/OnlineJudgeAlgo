import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            String[] st = br.readLine().split(" ");
            int N = Integer.parseInt(st[0]);
            int[][] D = new int[N][N];

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    D[i][j] = Integer.parseInt(st[i * N + j % N + 1]);
                    if(i != j && D[i][j] == 0) {
                        D[i][j] = INF;
                    }
                }
            }

            for(int k=0; k<N; k++) {
                for(int i=0; i<N; i++) {
                    if(i == k) continue;
                    for(int j=0; j<N; j++) {
                        if(i == j || k == j) continue;
                        D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
                    }
                }
            }

            int min = INF;
            for(int i=0; i<N; i++) {
                int sum = 0;
                for(int j=0; j<N; j++) {
                    if(D[i][j] != INF) {
                        sum += D[i][j];
                    }
                }
                min = Math.min(min, sum);
            }

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}

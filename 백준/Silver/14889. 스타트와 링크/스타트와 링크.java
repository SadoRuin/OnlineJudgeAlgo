import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, min;
    static int[][] S;
    static int[] startTeam, linkTeam;

    static void comb(int cnt, int start) {
        if(cnt == N/2) {
            int index1 = 0;
            int index2 = 0;
            for(int i=0; i<N; i++) {
                if(index1 >= N/2) {
                    linkTeam[index2++] = i;
                    continue;
                }
                if(startTeam[index1] != i) {
                    linkTeam[index2++] = i;
                } else {
                    index1++;
                }
            }
            int diff = 0;
            for (int i :
                    startTeam) {
                for (int j :
                        startTeam) {
                    diff += S[i][j];
                }
            }
            for (int i :
                    linkTeam) {
                for (int j :
                        linkTeam) {
                    diff -= S[i][j];
                }
            }

            min = Math.min(min, Math.abs(diff));
            return;
        }

        for(int i=start; i<N; i++) {
            startTeam[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        for(int i=0; i<N; i++) {
            String[] st = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                S[i][j] = Integer.parseInt(st[j]);
            }
        }

        startTeam = new int[N/2];
        linkTeam = new int[N/2];
        min = (int) 1e9;
        comb(0, 0);

        System.out.println(min);
    }
}

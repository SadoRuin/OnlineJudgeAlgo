import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);
        int B = Integer.parseInt(st[2]);

        int[][] map = new int[N+1][M+1];
        int max = 0;
        int min = (int) 1e9;
        for(int i=0; i<N; i++) {
            st = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st[j]);
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        int minTime = (int) 1e9;
        int height = -1;
        for(int h=min; h<=max; h++) {
            int t = 0;
            int inven = B;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    int diff = map[i][j] - h;
                    if(diff > 0) {
                        t += diff * 2;
                        inven += diff;
                    } else if(diff < 0) {
                        t += diff * -1;
                        inven -= diff * -1;
                    }
                }
            }
            if(inven < 0) {
                continue;
            }
            if(t <= minTime) {
                minTime = t;
                height = h;
            }
        }

        System.out.println(minTime + " " + height);

        br.close();
    }
}

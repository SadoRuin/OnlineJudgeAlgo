import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];   // 각 집별 R, G, B로 칠했을때의 비용

        for(int i=0; i<N; i++) {
            String[] st = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                cost[i][j] = Integer.parseInt(st[j]);
            }
        }

        for(int i=1; i<N; i++) {    // 2번집부터 반복
            cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]); // i번째집을 R로 칠할 경우 i-1번째 집은 G와 B 중 비용이 더 적은 것을 선택
            cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]); // i번째집을 G로 칠할 경우 i-1번째 집은 R와 B 중 비용이 더 적은 것을 선택
            cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]); // i번째집을 B로 칠할 경우 i-1번째 집은 R와 G 중 비용이 더 적은 것을 선택
        }

        System.out.println(Math.min(Math.min(cost[N-1][0], cost[N-1][1]), cost[N-1][2]));   // 마지막 집까지의 비용 합중 가장 적은 것을 출력
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n+1];

        for(int i=1; i<=n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n+1];
        dp[1] = stairs[1];
        if(n > 1) {
            dp[2] = dp[1] + stairs[2];
        }
        if(n > 2) {
            for(int i=3; i<=n; i++) {
                dp[i] = Math.max(dp[i-2] + stairs[i], dp[i-3] + stairs[i-1] + stairs[i]);
            }
        }

        System.out.println(dp[n]);

        br.close();
    }
}


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];    // dp 배열
        for(int i=2; i<=N; i++) {   // 2부터 N까지 반복 (1을 만들어야해서)
            dp[i] = dp[i-1] + 1;    // 1을 뺐을때의 최적해 + 1 저장
            if(i % 2 == 0) {    // 2로 나누어 떨어질 경우
                dp[i] = Math.min(dp[i], dp[i/2] + 1);   // 2로 나눈 값에서의 연산수+1과 최소값 비교
            }
            if(i % 3 == 0) {    // 3으로 나누어 떨어질 경우
                dp[i] = Math.min(dp[i], dp[i/3] + 1);   // 3으로 나눈 값에서의 연산수+1과 최소값 비교
            }
        }

        System.out.println(dp[N]);  // N에서의 최소 연산수 출력
    }
}

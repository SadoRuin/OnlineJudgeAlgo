import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int M = Integer.parseInt(st[0]);
        int N = Integer.parseInt(st[1]);

        boolean[] prime = new boolean[N+1];
        prime[0] = prime[1] = true;

        // 에라토스테네스의 체
        for(int i=2; i*i<=N; i++) {
            if(!prime[i]) {
                for(int j=i*i; j<=N; j+=i) {
                    prime[j] = true;
                }
            }
        }

        for(int i=M; i<=N; i++) {
            if(!prime[i]) {
                System.out.println(i);
            }
        }

        br.close();
    }
}

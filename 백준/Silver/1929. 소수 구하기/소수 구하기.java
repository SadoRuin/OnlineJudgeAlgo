import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] st = br.readLine().split(" ");
        int M = Integer.parseInt(st[0]);
        int N = Integer.parseInt(st[1]);

        boolean[] prime = new boolean[N+1];
        prime[0] = prime[1] = true;

        for(int i=2; i<=Math.sqrt(N); i++) {
            for(int j=2; i*j<=N; j++) {
                prime[i*j] = true;
            }
        }

        for(int i=M; i<=N; i++) {
            if(!prime[i]) {
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);

        br.close();
    }
}

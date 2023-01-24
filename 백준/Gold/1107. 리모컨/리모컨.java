import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        boolean[] broken = new boolean[10];
        if(M != 0) {
            String[] st = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                int btn = Integer.parseInt(st[i]);
                broken[btn] = true;
            }
        }

        int ans = Math.abs(N - 100);
        for(int i=0; i<1000000; i++) {
            String btn = String.valueOf(i);
            boolean isBroken = false;

            for(int j=0; j<btn.length(); j++) {
                if(broken[btn.charAt(j) - '0']) {
                    isBroken = true;
                    break;
                }
            }
            if(!isBroken) {
                ans = Math.min(ans, Math.abs(N - i) + btn.length());
            }
        }

        System.out.println(ans);

        br.close();
    }
}

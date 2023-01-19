import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int K = Integer.parseInt(st[0]);
        int N = Integer.parseInt(st[1]);

        int[] cables = new int[K];
        long max = 0;
        long min = 1;
        for(int i=0; i<K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, cables[i]);
        }

        while(min <= max) {
            int cnt = 0;
            long mid = (min + max) / 2;

            for(int i=0; i<K; i++) {
                cnt += cables[i] / mid;
            }

            if(cnt < N) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }

        }

        System.out.println(max);
        br.close();
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);

        int[] trees = new int[N];
        st = br.readLine().split(" ");
        long max = 0;
        long min = 1;
        for(int i=0; i<N; i++) {
            trees[i] = Integer.parseInt(st[i]);
            max = Math.max(max, trees[i]);
        }

        while(min <= max) {
            long sum = 0;
            long diff = 0;
            long mid = (min + max) / 2;

            for(int i=0; i<N; i++) {
                diff = trees[i] - mid;
                if(diff > 0) {
                    sum += diff;
                }
            }

            if(sum < M) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(max);
        br.close();
    }
}

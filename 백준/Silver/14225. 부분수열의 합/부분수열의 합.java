import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] S;
    static boolean[] nums;

    static void subset(int index, int sum) {
        if(index == N) {
            nums[sum] = true;
            return;
        }

        subset(index + 1, sum + S[index]);
        subset(index + 1, sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N];

        String[] st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st[i]);
        }

        nums = new boolean[N * 100000 + 1];

        subset(0, 0);

        for(int i=1; i<nums.length; i++) {
            if(!nums[i]) {
                System.out.println(i);
                System.exit(0);
            }
        }
    }
}

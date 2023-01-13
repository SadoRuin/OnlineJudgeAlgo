import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] S, nums;
    static boolean[] isSelected;

    static void subset(int index) {
        if(index == N) {
            int sum = 0;
            for(int i=0; i<N; i++) {
                if(isSelected[i]) {
                    sum += S[i];
                }
            }
            nums[sum]++;
            return;
        }

        isSelected[index] = true;
        subset(index + 1);
        isSelected[index] = false;
        subset(index + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        S = new int[N];

        String[] st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st[i]);
        }

        isSelected = new boolean[N];
        nums = new int[2000000];

        subset(0);

        for(int i=1; i<2000000; i++) {
            if(nums[i] == 0) {
                System.out.println(i);
                System.exit(0);
            }
        }
    }
}

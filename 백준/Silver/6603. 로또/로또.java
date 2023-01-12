import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int k;
    static int[] nums, input;
    static StringBuilder sb;

    static void comb(int cnt, int start) {
        if(cnt == 6) {
            for(int i=0; i<6; i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<k; i++) {
            nums[cnt] = input[i];
            comb(cnt+1, i+1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String[] st;
        nums = new int[6];

        while(true) {
            st = br.readLine().split(" ");
            k = Integer.parseInt(st[0]);
            if(k == 0) {
                break;
            }
            input = new int[k];

            for(int i=0; i<k; i++) {
                input[i] = Integer.parseInt(st[i+1]);
            }

            comb(0, 0);

            sb.append("\n");
        }

        System.out.println(sb);

    }
}

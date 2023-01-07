import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[] signs;
    static int[] nums;
    static int k;
    static long max;
    static long min = Long.MAX_VALUE;

    static boolean check(int now, int next, char sign) {
        if(sign == '<') {
            return now < next;
        } else {
            return now > next;
        }
    }

    static void perm(int cnt, int flag) {
        if(cnt == k+1) {
            long sum = 0;
            for(int i=0; i<k+1; i++) {
                sum *= 10;
                sum += nums[i];
            }
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<10; i++) {
            if((flag & 1<<i) == 0) {
                if(cnt > 0 && !check(nums[cnt-1], i, signs[cnt-1])) continue;
                nums[cnt] = i;
                perm(cnt+1, flag | 1<<i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split(" ");
        signs = new char[k];

        for(int i=0; i<k; i++) {
            signs[i] = st[i].charAt(0);
        }

        nums = new int[k+1];
        perm(0, 0);

        System.out.println(String.format("%0" + (k+1) + "d", max));
        System.out.println(String.format("%0" + (k+1) + "d", min));
    }
}

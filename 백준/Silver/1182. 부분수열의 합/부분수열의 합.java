import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, S, totalCnt;
    static int[] arr;

    static void subset(int cnt, int sum) {
        if(cnt == N) {
            if(sum == S) {
                totalCnt++;
            }
            return;
        }

        subset(cnt+1, sum+arr[cnt]);
        subset(cnt+1, sum);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        S = Integer.parseInt(st[1]);
        arr = new int[N];

        st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st[i]);
        }

        subset(0, 0);

        if(S == 0) totalCnt--;

        System.out.println(totalCnt);

    }
}

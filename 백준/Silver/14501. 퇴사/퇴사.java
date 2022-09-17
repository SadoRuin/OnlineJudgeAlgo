import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N];
        int[] P = new int[N];
        for(int i=0; i<N; i++) {
            String[] st = br.readLine().split(" ");
            T[i] = Integer.parseInt(st[0]);
            P[i] = Integer.parseInt(st[1]);
        }

        int max = 0;
        for(int flag=1, caseCnt=1<<N; flag<caseCnt; flag++) {
            int days = 0;
            int profit = 0;
            for(int i=0; i<N; i++) {
                if(days <= i) {
                    if((flag & 1<<i) != 0) {
                        if(i + T[i] > N) continue;
                        days += T[i];
                        profit += P[i];
                    } else {
                        days++;
                    }
                }
            }
            max = Math.max(max, profit);
        }
        System.out.println(max);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int S = Integer.parseInt(st[1]);
        int[] arr = new int[N];

        st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st[i]);
        }

        int cnt = 0;
        for(int flag=1, caseCnt=1<<N; flag<caseCnt; flag++) {
            int sum = 0;
            for(int i=0; i<N; i++) {
                if((flag & 1<<i) != 0) {
                    sum += arr[i];
                }
            }
            if(sum == S) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}

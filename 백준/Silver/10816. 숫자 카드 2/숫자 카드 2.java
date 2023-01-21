import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N];
        String[] st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            cards[i] = Integer.parseInt(st[i]);
            map.merge(cards[i], 1, Integer::sum);
        }

        int M = Integer.parseInt(br.readLine());
        int[] nums = new int[M];
        st = br.readLine().split(" ");
        for(int i=0; i<M; i++) {
            nums[i] = Integer.parseInt(st[i]);
            map.merge(nums[i], 0, Integer::sum);
            sb.append(map.get(nums[i])).append(" ");
        }

        System.out.println(sb);

        br.close();
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] op = new int[4];
        String[] st = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st[i]);
        }

        st = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            op[i] = Integer.parseInt(st[i]);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {1, A[0], op[0], op[1], op[2], op[3]});

        int max = (int) -1e9;
        int min = (int) 1e9;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if(now[0] == N) {
                max = Math.max(max, now[1]);
                min = Math.min(min, now[1]);
            }

            if(now[2] > 0) {
                queue.offer(new int[] {now[0]+1, now[1] + A[now[0]], now[2]-1, now[3], now[4], now[5]});
            }
            if(now[3] > 0) {
                queue.offer(new int[] {now[0]+1, now[1] - A[now[0]], now[2], now[3]-1, now[4], now[5]});
            }
            if(now[4] > 0) {
                queue.offer(new int[] {now[0]+1, now[1] * A[now[0]], now[2], now[3], now[4]-1, now[5]});
            }
            if(now[5] > 0) {
                queue.offer(new int[] {now[0]+1, now[1] / A[now[0]], now[2], now[3], now[4], now[5]-1});
            }
        }

        System.out.println(max);
        System.out.println(min);
    }
}

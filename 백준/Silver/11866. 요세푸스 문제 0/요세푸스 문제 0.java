import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int K = Integer.parseInt(st[1]);

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            queue.offer(i);
        }

        sb.append("<");
        while(!queue.isEmpty()) {
            for(int i=0; i<K-1; i++) {
                queue.offer(queue.poll());
            }
            sb.append(queue.poll());
            if(queue.isEmpty()) {
                sb.append(">");
            } else {
                sb.append(", ");
            }
        }

        System.out.println(sb);
        br.close();
    }
}

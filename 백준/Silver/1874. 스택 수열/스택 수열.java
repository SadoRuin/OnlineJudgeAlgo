import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int start = 1;

        for(int i=1; i<=n; i++) {
            stack.push(i);
            sb.append("+").append("\n");

            while(!stack.isEmpty() && stack.peek() == nums[start-1]) {
                stack.pop();
                sb.append("-").append("\n");
                start++;
            }
        }

        if(stack.isEmpty()) {
            System.out.println(sb);
        } else {
            System.out.println("NO");
        }

        br.close();
    }
}

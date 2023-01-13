import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            String[] st = br.readLine().split(" ");

            int R = Integer.parseInt(st[0]);
            for(int i=0; i<st[1].length(); i++) {
                for(int j=0; j<R; j++) {
                    sb.append(st[1].charAt(i));
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

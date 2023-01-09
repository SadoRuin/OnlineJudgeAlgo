import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        int[] alpha = new int[26];
        for(int i=0; i<N; i++) {
            int pv = (int) Math.pow(10, words[i].length() - 1);
            for(int j=0; j<words[i].length(); j++) {
                int index = words[i].charAt(j) - 'A';
                alpha[index] += pv;
                pv /= 10;
            }
        }

        Arrays.sort(alpha);
        int max = 0;
        int w = 9;
        for(int i=25; i>=0; i--) {
            if(alpha[i] == 0) break;
            max += alpha[i] * w--;
        }

        System.out.println(max);
    }
}

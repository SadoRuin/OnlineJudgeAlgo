import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        TreeSet<String> words = new TreeSet<>(((o1, o2) -> {
            if(o1.length() != o2.length()) {
                return o1.length() - o2.length();
            } else {
                return o1.compareTo(o2);
            }
        }));

        for(int i=0; i<N; i++) {
            words.add(br.readLine());
        }

        for (String word :
                words) {
            sb.append(word).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}

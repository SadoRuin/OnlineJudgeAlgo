import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] scale = new int[8];
        String[] st = br.readLine().split(" ");
        for(int i=0; i<8; i++) {
            scale[i] = Integer.parseInt(st[i]);
        }

        if(scale[0] == 1) {
            for(int i=1; i<8; i++) {
                if(scale[i] - scale[i-1] != 1) {
                    System.out.println("mixed");
                    System.exit(0);
                }
            }
            System.out.println("ascending");
        } else if(scale[0] == 8) {
            for(int i=1; i<8; i++) {
                if(scale[i] - scale[i-1] != -1) {
                    System.out.println("mixed");
                    System.exit(0);
                }
            }
            System.out.println("descending");
        } else {
            System.out.println("mixed");
        }

        br.close();
    }
}

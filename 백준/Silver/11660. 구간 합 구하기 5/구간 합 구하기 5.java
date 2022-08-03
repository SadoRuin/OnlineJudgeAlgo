import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] st = br.readLine().split(" ");
		int n = Integer.parseInt(st[0]);
		int m = Integer.parseInt(st[1]);
		int[][] arr = new int[n+1][n+1];
		
		for(int i=1; i<n+1; i++) {
			st = br.readLine().split(" ");
			for(int j=1; j<n+1; j++) {
				arr[i][j] = arr[i][j-1] + Integer.parseInt(st[j-1]);
			}
		}
		
		for(int k=m; k>0; k--) {
			st = br.readLine().split(" ");
			int startI = Integer.parseInt(st[0]);
			int startJ = Integer.parseInt(st[1]);
			int endI = Integer.parseInt(st[2]);
			int endJ = Integer.parseInt(st[3]);
			int sum = 0;
			for(int i=startI; i<=endI; i++) {
				sum += arr[i][endJ] - arr[i][startJ-1];
			}
			sb.append(sum).append("\n");
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}

}

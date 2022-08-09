
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {	
	
	static int cnt;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] room = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String[] st = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					room[i][j] = Integer.parseInt(st[j]);
				}
			}
			
			int num = 1;
			int max = 0;
			int start = 0;
			while(num <= N*N) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(room[i][j] == num) {
							cnt = 1;
							nav(room, N, i, j);
							if(max < cnt) {
								start = room[i][j];
								max = cnt;
							}
							num++;
						}
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(start).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
		
	}
	
	static void nav(int[][] room, int N, int i, int j) {
		if(i > 0 && room[i-1][j] == room[i][j]+1) {
			cnt++;
			nav(room, N, i-1, j);
		}
		if(i < N-1 && room[i+1][j] == room[i][j]+1) {
			cnt++;
			nav(room, N, i+1, j);
		}
		if(j > 0 && room[i][j-1] == room[i][j]+1) {
			cnt++;
			nav(room, N, i, j-1);
		}
		if(j < N-1 && room[i][j+1] == room[i][j]+1) {
			cnt++;
			nav(room, N, i, j+1);
		}
		
		return;
	}

}

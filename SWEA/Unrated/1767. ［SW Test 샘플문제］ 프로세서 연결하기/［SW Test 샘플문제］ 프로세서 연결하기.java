import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Solution {
	
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	
	static int N, M, mCnt, min;
	static int[][] map;
	static ArrayList<int[]> cores;
	
	static void dfs(int idx, int cnt, int sum) {
		if(idx == M) {
			if(mCnt < cnt) {
				mCnt = cnt;
				min = sum;
			} else if(mCnt == cnt) {
				min = Math.min(min, sum);
			}
			return;
		}
		
		int[] cur = cores.get(idx);
		
		Loop:
		for(int j=0; j<4; j++) {
			int count = 0;
			int nextX = cur[0] + dx[j];
			int nextY = cur[1] + dy[j];
			while(nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
				if(map[nextX][nextY] == 1) {
					continue Loop;
				}
				count++;
				nextX += dx[j];
				nextY += dy[j];
			}

			nextX = cur[0];
			nextY = cur[1];
			for(int i=0; i<count; i++) {
				nextX += dx[j];
				nextY += dy[j];
				map[nextX][nextY] = 1;
			}
			
			dfs(idx+1, cnt+1, sum+count);
			
			nextX = cur[0];
			nextY = cur[1];
			for(int i=0; i<count; i++) {
				nextX += dx[j];
				nextY += dy[j];
				map[nextX][nextY] = 0;
			}
			
		}
		dfs(idx+1, cnt, sum);
		
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("sample_input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			cores = new ArrayList<>();
			for(int i=0; i<N; i++) {
				String[] st = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st[j]);
					if(map[i][j] == 1 
							&& i != 0 && i != N-1 && j != 0 && j != N-1) {
						cores.add(new int[] {i, j});
					}
				}
			}
			M = cores.size();
			min = Integer.MAX_VALUE;
			mCnt = 0;
			dfs(0, 0, 0);
				
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb);
	}

}

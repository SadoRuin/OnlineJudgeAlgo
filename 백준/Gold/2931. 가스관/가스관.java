import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {	// start class
	static final int[] dr1 = {-1, 1, 0, 0};	// 상, 하, 좌, 우 방향벡터
	static final int[] dc1 = {0, 0, -1, 1};	// 상, 하, 좌, 우 방향벡터
	static final int[] dr2 = {-1, 0, 1, 0};	// 상, 우, 하, 좌 방향벡터
	static final int[] dc2 = {0, 1, 0, -1};	// 상, 우, 하, 좌 방향벡터
	static final int[] dr3 = {-1, 0};	// 상, 좌 방향벡터
	static final int[] dc3 = {0, -1};	// 상, 좌 방향벡터
	
	public static void main(String[] args) throws Exception {	// start main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// BufferedReader 선언
		StringBuilder sb = new StringBuilder();	// StringBuilder 선언

		String[] st = br.readLine().split(" ");	// 한줄 입력을 공백으로 분리
		int R = Integer.parseInt(st[0]);	// 행 크기
		int C = Integer.parseInt(st[1]);	// 열 크기
		char[][] map = new char[R][C];	// 지형맵
		boolean[][] visited = new boolean[R][C];	// 방문처리 배열
		Queue<int[]> queue = new ArrayDeque<>();	// 방문할 위치를 저장할 큐

		for(int i=0; i<R; i++) {	// 지도 입력을 위한 행 반복
			String s = br.readLine();	// 한줄 입력
			for(int j=0; j<C; j++) {	// 지도 입력을 위한 열 반복
				map[i][j] = s.charAt(j);	// 문자열의 각 위치의 char를 지도에 입력
				if(map[i][j] == 'M') {	// 만약 입력한 문자가 'M'이라면
					queue.offer(new int[] {i, j});	// 시작 지점이므로 큐에 위치 저장
					visited[i][j] = true;
				}	// end if
			}	// end for_j
		}	// end for_i

		int ansR = 0;	// 블록이 지워진 행 위치
		int ansC = 0;	// 블록이 지워진 열 위치
		Loop:
		while(!queue.isEmpty()) {	// 큐가 빌때까지 반복
			int[] cur = queue.poll();	// 현재 위치를 꺼냄
			int r = cur[0];	// 현재 행 위치
			int c = cur[1];	// 현재 열 위치
			char b = map[r][c];	// 현재 블록 모양
			int start = 0;	// 방향벡터 반복 시작 인덱스
			int end = 4;	// 방향벡터 반복 끝 인덱스
			int[] dr = dr1;	// 상하좌우 사방탐색 방향벡터
			int[] dc = dc1;	// 상하좌우 사방탐색 방향백터
			switch(b) {	// 현재 위치 블록의 모양에 따라 다른 방향 벡터 사용
				case '|':	// 상하를 잇는 블록이면
					start = 0;	// 시작 인덱스 0
					end = 2;	// 끝 인덱스 2
					break;	// switch문 종료
				case '-':	// 좌우를 잇는 블록이면
					start = 2;	// 시작 인덱스 2
					end = 4;	// 끝 인덱스 4
					break;	// switch문 종료
				case '1':	// 우하를 잇는 블록이라면
					dr = dr2;	// 방향벡터 변경
					dc = dc2;	// 방향벡터 변경
					start = 1;	// 시작 인덱스 1
					end = 3;	// 끝 인덱스 3
					break;	// switch문 종료
				case '2':	// 상우를 잇는 블록이라면
					dr = dr2;	// 방향벡터 변경
					dc = dc2;	// 방향벡터 변경
					start = 0;	// 시작 인덱스 0
					end = 2;	// 끝 인덱스 2
					break;	// switch문 종료
				case '3':
					dr = dr3;	// 방향벡터 변경
					dc = dc3;	// 방향벡터 변경
					start = 0;	// 시작 인덱스0
					end = 2;	// 끝 인덱스 2
					break;	// switch문 종료
				case '4':
					dr = dr2;	// 방향벡터 변경
					dc = dc2;	// 방향벡터 변경
					start = 2;	// 시작 인덱스 2
					end = 4;	// 끝 인덱스 4
					break;	// switch문 종료
			}	// end switch

			for(int i=start; i<end; i++) {	// 방향벡터 반복
				int nextR = r + dr[i];	// 다음 행 위치
				int nextC = c + dc[i];	// 다음 열 위치
				if(nextR >= 0 && nextR < R && nextC >= 0 && nextC < C	// 다음 위치가 범위 내이고
						&& !visited[nextR][nextC]) {	// 방문하지 않았다면
					if(b == '.' && map[nextR][nextC] == '.') continue;	// 현재 블록이 시작지점이고 다음 위치가 빈칸이면 다른 방향으로
					visited[nextR][nextC] = true;	// 방문 처리
					queue.offer(new int[] {nextR, nextC});	// 다음 위치 큐에 저장
				}	// end if
			}	// end for
			if(b == '.') {	// 만약 현재 빈칸이라면 지워진 위치이므로
				for(int i=0; i<4; i++) {	// 방향벡터 반복
					int nextR = r + dr1[i];	// 다음 행 위치
					int nextC = c + dc1[i];	// 다음 열 위치
					if(nextR >= 0 && nextR < R && nextC >= 0 && nextC < C	// 다음 위치가 범위 내이고
							&& map[nextR][nextC] != '.') {	// 빈칸이 아니라면
						if(i == 0) {
							switch (map[nextR][nextC]) {
								case '|':
								case '+':
								case '1':
								case '4':
									ansR = r;	// 행위치 저장
									ansC = c;	// 열위치 저장 하고
									break Loop;	// while문 종료
							}
						} else if(i == 1) {
							switch (map[nextR][nextC]) {
								case '|':
								case '+':
								case '2':
								case '3':
									ansR = r;	// 행위치 저장
									ansC = c;	// 열위치 저장 하고
									break Loop;	// while문 종료
							}
						} else if(i == 2) {
							switch (map[nextR][nextC]) {
								case '-':
								case '+':
								case '1':
								case '2':
									ansR = r;	// 행위치 저장
									ansC = c;	// 열위치 저장 하고
									break Loop;	// while문 종료
							}
						} else if(i == 3) {
							switch (map[nextR][nextC]) {
								case '-':
								case '+':
								case '3':
								case '4':
									ansR = r;	// 행위치 저장
									ansC = c;	// 열위치 저장 하고
									break Loop;	// while문 종료
							}
						}
					}
				}
			}	// end if
		}	// end while

		int dir = 0;	// 지워진 칸의 상하좌우에 빈칸이 아닌 블록이 있는 위치의 방향벡터 인덱스 합
		boolean up = false;
		for(int i=0; i<4; i++) {	// 방향벡터 반복
			int nextR = ansR + dr1[i];	// 다음 행 위치
			int nextC = ansC + dc1[i];	// 다음 열 위치
			if(nextR >= 0 && nextR < R && nextC >= 0 && nextC < C	// 다음 위치가 범위 내이고
					&& map[nextR][nextC] != '.') {	// 빈칸이 아니라면
				if(i == 0) {
					switch (map[nextR][nextC]) {
						case '|':
						case '+':
						case '1':
						case '4':
							up = true;
							dir += i;
							break;
					}
				} else if(i == 1) {
					switch (map[nextR][nextC]) {
						case '|':
						case '+':
						case '2':
						case '3':
							dir += i;
							break;
					}
				} else if(i == 2) {
					switch (map[nextR][nextC]) {
						case '-':
						case '+':
						case '1':
						case '2':
							dir += i;
							break;
					}
				} else if(i == 3) {
					switch (map[nextR][nextC]) {
						case '-':
						case '+':
						case '3':
						case '4':
							dir += i;
							break;
					}
				}
			}
		}
		char ans = 0;	// 지워진 칸의 블록 모양
		switch(dir) {	// 방향 인덱스 합에 대해 switch
			case 1:	// 1이라면
				ans = '|';	// 상하 모양
				break;	// switch문 종료
			case 2:	// 2라면
				ans = '3';	// 3번 블록
				break;	// switch문 종료
			case 3:	// 3은 2번블록, 4번블록 2가지가 가능함
				if(up) {	// 빈칸이 아니라면
					ans = '2';	// 2번 블록
				} else {	// 아니라면
					ans = '4';	// 4번 블록
				}	//  end if-else
				break;	// switch문 종료
			case 4:	// 4라면
				ans = '1';	// 1번 블록
				break;	// switch문 종료
			case 5:	// 5라면
				ans = '-';	// 좌우블록
				break;	// switch문 종료r
			case 6:	// 6이라면
				ans = '+';	// 십자블록
				break;	// switch문 종료
		}	// end switch

		// 블록 위치는 1부터 세므로 구한 행위치와 열위치 값을 1씩 더하고 출력
		sb.append(ansR+1).append(" ").append(ansC+1).append(" ").append(ans).append("\n");	// 출력물 저장

		System.out.println(sb);	// 최종 출력
	}	// end main

}	// end class

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 개수
		
		for(int tc=1; tc<=T; tc++) {	// 테스트 케이스 개수만큼 반복
			String[] st = br.readLine().split(" ");
			int h = Integer.parseInt(st[0]);	// 맵의 높이
			int w = Integer.parseInt(st[1]);	// 맵의 너비
			int cordI = 0;	// 현재 탱크의 행 위치를 나타낼 변수
			int cordJ = 0;	// 현재 탱크의 열 위치를 나타낼 변수
			
			char[][] map = new char[h][w];	// 맵 배열 선언
			for(int i=0; i<h; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j=0; j<w; j++) {	// 맵 초기화
					// 탱크의 초기 위치 저장
					if(ch[j] == '<' || ch[j] == '>' || ch[j] == '^' || ch[j] == 'v') {
						cordI = i;
						cordJ = j;
					}
					// 맵 요소 저장
					map[i][j] = ch[j];
				}
			}
			
			int n = Integer.parseInt(br.readLine());
			char[] action = br.readLine().toCharArray();	// 실행할 동작 저장
			
			// 저장된 동작을 하나씩 실행
			for (char act : action) {
				switch(act) {
					// Up일 경우
					case 'U':
						map[cordI][cordJ] = '^';	// 탱크가 위쪽을 바라보게
						if(cordI-1 >= 0) {	// 맵을 벗어나지 않을 경우에만
							if(map[cordI-1][cordJ] == '.') {	// 위쪽이 평지일경우
								map[cordI][cordJ] = '.';	// 현재 위치를 평지로 바꾸고
								map[cordI-1][cordJ] = '^';	// 탱크를 위쪽으로 이동
								cordI = cordI - 1;	// 현재 위치 변경
							}
						}
						break;
					// Down일 경우
					case 'D':
						map[cordI][cordJ] = 'v';	// 탱크가 아래쪽을 바라보게
						if(cordI+1 <= h-1) {	// 맵을 벗어나지 않을 경우에만
							if(map[cordI+1][cordJ] == '.') {	// 아래쪽이 평지일경우
								map[cordI][cordJ] = '.';	// 현재 위치를 평지로 바꾸고
								map[cordI+1][cordJ] = 'v';	// 탱크를 아래쪽으로 이동
								cordI = cordI + 1;	// 현재 위치 변경
							}
						}
						break;
					// Left일 경우
					case 'L':
						map[cordI][cordJ] = '<';	// 탱크가 왼쪽을 바라보게
						if(cordJ-1 >= 0) {	// 맵을 벗어나지 않을 경우에만
							if(map[cordI][cordJ-1] == '.') {	// 왼쪽이 평지일경우
								map[cordI][cordJ] = '.';	// 현재 위치를 평지로 바꾸고
								map[cordI][cordJ-1] = '<';	// 탱크를 왼쪽으로 이동
								cordJ = cordJ - 1;	// 현재 위치 변경
							}
						}
						break;
					// Right일 경우
					case 'R':
						map[cordI][cordJ] = '>';	// 탱크가 오른쪽을 바라보게
						if(cordJ+1 <= w-1) {	// 맵을 벗어나지 않을 경우에만
							if(map[cordI][cordJ+1] == '.') {	// 오른쪽이 평지일경우
								map[cordI][cordJ] = '.';	// 현재 위치를 평지로 바꾸고
								map[cordI][cordJ+1] = '>';	// 탱크를 오른쪽으로 이동
								cordJ = cordJ + 1;	// 현재 위치 변경
							}
						}
						break;
					// Shoot일 경우
					case 'S':
						// 현재 탱크의 위치를 포탄의 위치로 할당
						int shotI = cordI;
						int shotJ = cordJ;
						// 현재 탱크가 바라보는 방향에 따라 다른 결과
						switch(map[cordI][cordJ]) {
							// 위쪽을 바라볼 경우
							case '^':
								while(shotI-1 >= 0) {	// 맵을 벗어나지 않을 동안
									if(map[shotI-1][shotJ] == '*') {	// 벽돌벽을 만나면
										map[shotI-1][shotJ] = '.';	// 벽돌벽을 평지로 바꾼다
										break;
									} else if(map[shotI-1][shotJ] == '#') {	// 강철벽을 만나면 멈춘다
										break;
									}
									shotI--;	// 위쪽으로 포탄 이동
								}
								break;
							// 아래쪽을 바라볼 경우
							case 'v':
								while(shotI+1 <= h-1) {	// 맵을 벗어나지 않을 동안
									if(map[shotI+1][shotJ] == '*') {	// 벽돌벽을 만나면
										map[shotI+1][shotJ] = '.';	// 벽돌벽을 평지로 바꾼다
										break;
									} else if(map[shotI+1][shotJ] == '#') {	// 강철벽을 만나면 멈춘다
										break;
									}
									shotI++;	// 아래쪽으로 포탄 이동
								}
								break;
							// 왼쪽을 바라볼 경우
							case '<':
								while(shotJ-1 >= 0) {	// 맵을 벗어나지 않을 동안
									if(map[shotI][shotJ-1] == '*') {	// 벽돌벽을 만나면
										map[shotI][shotJ-1] = '.';	// 벽돌벽을 평지로 바꾼다
										break;
									} else if(map[shotI][shotJ-1] == '#') {	// 강철벽을 만나면 멈춘다
										break;
									}
									shotJ--;	// 왼쪽으로 포탄 이동
								}
								break;
							// 오른쪽을 바라볼 경우
							case '>':
								while(shotJ+1 <= w-1) {	// 맵을 벗어나지 않을 동안
									if(map[shotI][shotJ+1] == '*') {	// 벽돌벽을 만나면
										map[shotI][shotJ+1] = '.';	// 벽돌벽을 평지로 바꾼다
										break;
									} else if(map[shotI][shotJ+1] == '#') {	// 강철벽을 만나면 멈춘다
										break;
									}
									shotJ++;	// 왼쪽으로 포탄 이동
								}
								break;
						}
						break;
				}
			}
			// 출력내용 StringBuilder에 저장
			sb.append("#" + tc + " ");
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());	// 최종내용 출력
		bw.flush();
		bw.close();
		br.close();
	}
}
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
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String[] st = br.readLine().split(" ");
			int h = Integer.parseInt(st[0]);
			int w = Integer.parseInt(st[1]);
			int cordI = 0;
			int cordJ = 0;
			
			char[][] map = new char[h][w];
			for(int i=0; i<h; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j=0; j<w; j++) {
					if(ch[j] == '<' || ch[j] == '>' || ch[j] == '^' || ch[j] == 'v') {
						cordI = i;
						cordJ = j;
					}
					map[i][j] = ch[j];
				}
			}
			
			int n = Integer.parseInt(br.readLine());
			char[] action = br.readLine().toCharArray();
			
			for (char act : action) {
				switch(act) {
					case 'U':
						map[cordI][cordJ] = '^';
						if(cordI-1 >= 0) {
							if(map[cordI-1][cordJ] == '.') {
								map[cordI][cordJ] = '.';
								map[cordI-1][cordJ] = '^';
								cordI = cordI - 1;
							}
						}
						break;
					case 'D':
						map[cordI][cordJ] = 'v';
						if(cordI+1 <= h-1) {
							if(map[cordI+1][cordJ] == '.') {
								map[cordI][cordJ] = '.';
								map[cordI+1][cordJ] = 'v';
								cordI = cordI + 1;
							}
						}
						break;
					case 'L':
						map[cordI][cordJ] = '<';
						if(cordJ-1 >= 0) {
							if(map[cordI][cordJ-1] == '.') {
								map[cordI][cordJ] = '.';
								map[cordI][cordJ-1] = '<';
								cordJ = cordJ - 1;
							}
						}
						break;
					case 'R':
						map[cordI][cordJ] = '>';
						if(cordJ+1 <= w-1) {
							if(map[cordI][cordJ+1] == '.') {
								map[cordI][cordJ] = '.';
								map[cordI][cordJ+1] = '>';
								cordJ = cordJ + 1;
							}
						}
						break;
					case 'S':
						int shotI = cordI;
						int shotJ = cordJ;
						switch(map[cordI][cordJ]) {
							case '^':
								while(shotI-1 >= 0) {
									if(map[shotI-1][shotJ] == '*') {
										map[shotI-1][shotJ] = '.';
										break;
									} else if(map[shotI-1][shotJ] == '#') {
										break;
									}
									shotI--;
								}
								break;
							case 'v':
								while(shotI+1 <= h-1) {
									if(map[shotI+1][shotJ] == '*') {
										map[shotI+1][shotJ] = '.';
										break;
									} else if(map[shotI+1][shotJ] == '#') {
										break;
									}
									shotI++;
								}
								break;
							case '<':
								while(shotJ-1 >= 0) {
									if(map[shotI][shotJ-1] == '*') {
										map[shotI][shotJ-1] = '.';
										break;
									} else if(map[shotI][shotJ-1] == '#') {
										break;
									}
									shotJ--;
								}
								break;
							case '>':
								while(shotJ+1 <= w-1) {
									if(map[shotI][shotJ+1] == '*') {
										map[shotI][shotJ+1] = '.';
										break;
									} else if(map[shotI][shotJ+1] == '#') {
										break;
									}
									shotJ++;
								}
								break;
						}
						break;
				}
			}
			sb.append("#" + tc + " ");
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
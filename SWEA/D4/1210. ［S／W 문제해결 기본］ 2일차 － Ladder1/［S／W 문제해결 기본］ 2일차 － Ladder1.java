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
import java.util.StringTokenizer;

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

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=10; tc++) { // 테스트 케이스 10번 반복
			int T = Integer.parseInt(br.readLine());
			int start = 0;
			int[][] ladders = new int[100][100];	// 사다리 배열 초기화
			for(int i=0; i<100; i++) {	// 사다리 내용 초기화
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					ladders[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int j=0; j<100; j++) {
				if(ladders[0][j] == 1) {	// 사다리 시작 부분일 경우 시작
					start = j;
					if(path(ladders, start) == 2) {	// 최종 도착점이 2일 경우 
						break;
					}
				}
			}
			
			bw.write("#" + T + " " + start);
			bw.newLine();
			
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	// 사다리의 특정 시작점에서의 도착값 반복
	static int path(int[][] ladders, int j) {
		int i = 0;
		while(i < 99) {		// 제일 아래 도달할때까지 반복
			int direction = 0;	// 좌, 우 방
			while(j > 0 && ladders[i][j-1] == 1) {	// 왼쪽으로 갈 수 있을 경우
				j--;	// 갈 수 있을때까지 왼쪽으로
				direction = -1;
			}
			if(direction == -1) {	// 왼쪽 방향으로 다 갔으면
				i++;				// 아래로 한칸 내려가
				continue;			// 다시 while 처음으로
			}
			while(j < 99 && ladders[i][j+1] == 1) {	// 오른쪽으로 갈 수 있을 경우
				j++;	// 갈 수 있을때까지 오른쪽으로
				direction = 1;
			}
			if(direction == 1) {	// 오른쪽 방향으로 다 갔으면
				i++;				// 아래로 한칸 내려
				continue;			// 다시 while 처음으로
			}
			i++;	// 좌 우 갈 곳 없을 경우 아래로
		}
		return ladders[i][j];	// 도착값 반환
	}
}
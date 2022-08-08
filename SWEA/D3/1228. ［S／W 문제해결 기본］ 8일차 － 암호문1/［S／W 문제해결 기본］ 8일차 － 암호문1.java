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
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=10; tc++) {	// 테스트 케이스 10개 반복
			int N = Integer.parseInt(br.readLine());	// 입력되는 암호문의 개수
			List<Integer> plain = new ArrayList<>();	// 암호문을 저장할 리스트
			String[] st = br.readLine().split(" ");
			
			for(int i=0; i<N; i++) {
				plain.add(Integer.parseInt(st[i]));		// 초기 암호문을 저장
			}
			
			int M = Integer.parseInt(br.readLine());	// 명령의 개수
			st = br.readLine().split(" ");
			int into = 0;	// 삽입할 위치를 저장하는 변수
			int num = 0;	// 삽입할 암호문의 개수를 저장하는 변수
			for(int i=0; i<st.length; i++) {	// 명령어 개수 만큼 반복
				if(st[i].equals("I")) {		// I로 시작하면 명령 시작
					into = Integer.parseInt(st[++i]);	// 삽입할 위치
					num = Integer.parseInt(st[++i]);	// 삽입할 암호문의 개수
					List<Integer> order = new ArrayList<>();	// 현재 삽입할 암호문을 저장하기 위한 리스트
					for(int j=0; j<num; j++) {
						order.add(Integer.parseInt(st[++i]));	// 삽입할 암호문을 저장
					}
					plain.addAll(into, order);	// 삽입할 위치에 암호문을 삽입
				}
			}
			
			sb.append("#").append(tc).append(" ");
			for(int i=0; i<10; i++) {
				sb.append(plain.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}

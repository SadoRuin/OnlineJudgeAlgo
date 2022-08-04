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
import java.util.ArrayDeque;
import java.util.Deque;

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
		
		for(int tc=1; tc<=10; tc++) {
			int n = Integer.parseInt(br.readLine());	// 괄호의 개수
			char[] ch = br.readLine().toCharArray();	// 괄호를 담을 char 배열
			Deque<Character> dq = new ArrayDeque<>();	// deque를 stack으로 활용
			int valid = 1;	// 유효성 확인 변수
			
			for (char c : ch) {	// 괄호 배열을 하나씩 탐색
				if(c == '(' || c == '[' || c == '{' || c == '<') {	// 여는 괄호일 경우는 스택에 무조건 삽입
					dq.offer(c);
				} else {	// 닫는 괄호일 경우
					if(dq.isEmpty()) {	// 스택이 비어 있을 경우에 닫는 괄호가 나온다면
						valid = 0;	// 유효하지 않은 경우 이므로 valid를 0으로
						break;		// 반복문 break
					}
					char now = dq.pollLast();	// 비어 있지 않을경우 마지막 요소를 꺼냄
					if(c == ')') {	// 현재 닫는 괄호가 무엇인지에 따라
						if(now != '(') {	// 마지막 요소가 알맞은 여는 괄호가 아닐 경우
							valid = 0;		// 유효하지 않은 경우 이므로 valid를 0으로
							break;			// 반복문 break
						}
					} else if(c == ']') {	// 위와 동일
						if(now != '[') {
							valid = 0;
							break;
						}
					} else if(c == '}') {	// 위와 동일
						if(now != '{') {
							valid = 0;
							break;
						}
					} else if(c == '>') {	// 위와 동일
						if(now != '<') {
							valid = 0;
							break;
						}
					}
				}
			}
			if(!dq.isEmpty()) {	// 괄호를 전부 처리하였지만 스택에 괄호가 남아있다면 유효하지 않은 경우이므로
				valid = 0;	// valid를 0으로
			}
			
			sb.append("#").append(tc).append(" ").append(valid).append("\n");
			
		}
		
		System.out.println(sb);
	}
}
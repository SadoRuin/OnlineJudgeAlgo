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
import java.util.ArrayList;
import java.util.Collections;
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
		
		for(int i=0; i<10; i++) {	// 10개의 테스트 케이스 반복
			ArrayList<Integer> list = new ArrayList<>();	// 상자가 쌓인 것을 list로 선언
			int cnt = Integer.parseInt(br.readLine());	//	덤프 횟수
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<100; j++) {	//	박스 초기화
				list.add(Integer.parseInt(st.nextToken()));
			}
			dump(list, cnt);	//	덤프 실행
			bw.write("#" + (i+1) + " " + getDiff(list));	// 결과값 출력
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	// 덤프를 실행하는 메소드
	static void dump(ArrayList<Integer> list, int cnt) {
		for(int i=0; i<cnt; i++) {	// 덤프 횟수만큼 반복
			int max = Collections.max(list);	// 가장 높은 박스 개수
			int min = Collections.min(list);	// 가장 낮은 박스 개수
			if(max-min > 0) {	// 평탄화가 완료되지 않았을 경우
				int maxIndex = list.indexOf(max);	// 가장 높은 박스가 있는 위치
				int minIndex = list.indexOf(min);	// 가장 낮은 박스가 있는 위치
				list.set(maxIndex, max-1);		// 가장 높은 박스 개수-1
				list.set(minIndex, min+1);		// 가장 낮은 박스 개수+1
			} else {	// 평탄화가 완료 되었을 경우
				return;
			}
		}
	}
	
	// 가장 높은 박스개수와 가장 낮은 박스개수의 차이를 반환하는 메소드
	static int getDiff(ArrayList<Integer> list) {
		int max = Collections.max(list);
		int min = Collections.min(list);
		return max - min;
	}

}
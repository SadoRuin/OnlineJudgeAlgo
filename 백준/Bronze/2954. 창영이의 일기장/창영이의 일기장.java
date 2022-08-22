import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {	// start main
	public static void main(String[] args) throws Exception {	// start main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력을 빨리 받기 위한 BufferedReader
		
		String input = br.readLine();	// 입력 받을 문자열
		StringBuilder newInput = new StringBuilder();	// 원래 문장으로 변환하여 저장할 StringBuilder
		
		int n = input.length();		// 문자열 길이 저장
		
		for(int i=0; i<n; i++) {	// 문자열 길이만큼 반복
			char now = input.charAt(i);	// 현재 문자를 char 타입으로 저장
			switch(now) {	// 현자 문자가 무엇인지에 따라 다른 결과
				case 'a':	// a이거나
				case 'e':	// e이거나
				case 'i':	// i이거나
				case 'o':	// o이거나
				case 'u':	// u라면
					i += 2;	// 다음 두 문자는 p와 자기 자신이므로 무시하기 위해 인덱스 2 상승
				default:	// 모음은 인덱스 증가 후에, 모음이 아니라면 바로
					newInput.append(now);	// 현재 문자를 StringBuilder에 저장
					break;	// switch문 종료
			}	// end switch
		}	// end for_i
		
		System.out.println(newInput.toString());	// 변환된 원래 문자 출력
	}	// end main
}	// end class

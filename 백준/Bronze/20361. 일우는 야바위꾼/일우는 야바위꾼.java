import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {	// start class
	public static void main(String[] args) throws Exception {	// start main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력을 빨리 받기 위한 BufferedReader
		
        String[] st = br.readLine().split(" ");	// 입력받은 문자열을 공백으로 나눔 
        int N = Integer.parseInt(st[0]);	// 첫번째는 종이컵의 수 N
        int X = Integer.parseInt(st[1]);	// 두번째는 간식이 들어있는 위치 X
        int K = Integer.parseInt(st[2]);	// 세번째는 컵의 위치를 맞바꾸는 회수 K

        int[] cups = new int[N+1];	// 컵 배열,1부터 사용하기 위해 N+1사이즈로 초기화

        cups[X] = 1;	// 간식이 들어있는 컵만 1로 표시


        for(int k=0; k<K; k++) {	// K번 만큼 위치를 바꿈
            st = br.readLine().split(" ");	// 위치를 바꾸는 두 위치를 입력 받음
            int i = Integer.parseInt(st[0]);	// 바뀌는 A 위치
            int j = Integer.parseInt(st[1]);	// 바뀌는 B 위치
            int tmp = cups[i];	// swap을 위해 하나를 임시 변수에 저장
            cups[i] = cups[j];	// A위치에 B를 저장
            cups[j] = tmp;	// 임시로 저장한 A를 B위치에 저장
        }	// end for =_k


        for(int i=1; i<=N; i++) {	// 모든 컵들에 대해 반복
            if(cups[i] == 1) {	// 컵 내용이 1이라면 간식이 들어 있으므로
                System.out.println(i);	// 그 위치를 출력하는 내용을 sb에 저장
                break;	// 반복문 종료
            }	// end if
        }	// end for_i
		
	}	// end main
}	// end class

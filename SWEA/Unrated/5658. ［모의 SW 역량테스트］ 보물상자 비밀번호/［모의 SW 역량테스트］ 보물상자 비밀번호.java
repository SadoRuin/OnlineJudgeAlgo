import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테스트케이스 개수
        for(int tc=1; tc<=T; tc++) {    // 테스트케이스 반복
            String[] st = br.readLine().split(" ");
            int N = Integer.parseInt(st[0]);
            int K = Integer.parseInt(st[1]);
            StringBuilder lock = new StringBuilder(br.readLine());  // 보물상자 뚜껑의 16진수 숫자
            TreeSet<String> set = new TreeSet<>(Comparator.reverseOrder()); // 내림차순으로 중복없이 저장하기 위한 TreeSet
            for(int i=0; i<N/4; i++) {  // 같은 수가 나올때까지 N/4번 회전 가능
                for(int j=0; j<lock.length(); j+=N/4) { // 숫자를 N/4개씩 끊어서 TreeSet에 저장
                    set.add(lock.substring(j, j+N/4));
                }
                lock.append(lock.charAt(0));    // 맨 앞 숫자를 맨 뒤에 저장
                lock.deleteCharAt(0);   // 맨 앞 숫자 삭제
            }

            String num = null;  // K번째 수를 저장할 변수
            int k = 1;  // 1부터 K까지 확인할 인덱싱 변수
            for (String s :
                    set) {  // TreeSet 내용 Foreach
                if(k == K) {    // K번째 수라면
                    num = s;    // num에 저장하고
                    break;  // 반복 종료
                }
                k++;    // k 인덱스 증가
            }
            // 구한 String타입의 16진수를 10진수의 int형으로 변환하여 출력물 저장
            sb.append("#").append(tc).append(" ").append(Integer.parseInt(num, 16)).append("\n");
        }

        System.out.println(sb); // 출력
    }
}

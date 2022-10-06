import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];   // 수열의 수들
            String[] st = br.readLine().split(" ");
            for(int i=0; i<N; i++) {
                A[i] = Integer.parseInt(st[i]);
            }

            int[] B = new int[N];   // 동적테이블 : 각 원소를 끝으로하는 LIS값
            int max = 0;
            for(int i=0; i<N; i++) {    // 앞쪽부터 모든 원소기준으로 자신을 끝으로하는 LIS 계산
                B[i] = 1;
                for(int j=0; j<i; j++) {
                    if(A[j] < A[i] && B[i] < B[j] + 1) {
                        B[i] = B[j] + 1;
                    }
                }
                max = Math.max(max, B[i]);
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }
}

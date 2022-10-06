import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
			int[] C = new int[N];   // 동적테이블 C[k] : 해당(k) 길이를 만족하는 자리(k자리)에 오는 수의 최소값
            int size = 0;

            for(int i=0; i<N; i++) {
                int pos = Arrays.binarySearch(C, 0, size, A[i]);
                if(pos >= 0) continue;

                int insertPos = Math.abs(pos) - 1;  // 맨뒤 또는 기존원소 대체자리
                C[insertPos] = A[i];

                if(insertPos == size) size++;
            }

            sb.append("#").append(tc).append(" ").append(size).append("\n");
        }

        System.out.println(sb);
    }
}

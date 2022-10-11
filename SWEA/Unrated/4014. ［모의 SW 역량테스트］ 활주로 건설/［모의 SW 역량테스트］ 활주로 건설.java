import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    static int N, X;
    static int[][] land, land_T;

    static int search() {
        int count = 0;
        for(int i=0; i<N; i++) {
            if(makeRoad(land[i])) count++;
            if(makeRoad(land_T[i])) count++;
        }
        return count;
    }

    // 해당 지형 정보로 활주로 건설이 가능하면 true, 불가능하면 false 리턴
    static boolean makeRoad(int[] road) {
        int beforeHeight = road[0], size = 0;
        int j = 0;
        while(j<N) {
            if(beforeHeight == road[j]) {   // 동일 높이
                size++;
                j++;
            } else if(beforeHeight+1 == road[j]) {  // 이전높이보다 1 높음 : 오르막 경사로 설치 체크
                if(size < X) return false;  // X길이 미만이면 활주로 건설 불가

                beforeHeight++;
                size = 1;
                j++;
            } else if(beforeHeight-1 == road[j]) {  // 이전 높이보다 1 작음
                int count = 0;
                for(int k=j; k<N; k++) {
                    if(road[k] != beforeHeight - 1) return false;
                    if(++count == X) break;
                }
                if(count < X) return false;

                beforeHeight--;
                j += X;
                size = 0;

            } else {    // 높이가 2이상 차이
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
 //       System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            String[] st = br.readLine().split(" ");
            N = Integer.parseInt(st[0]);    // 배열 크기
            X = Integer.parseInt(st[1]);    // 경사로 밑변 길이
            land = new int[N][N];
            land_T = new int[N][N];

            for(int i=0; i<N; i++) {
                st = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    land[i][j] = land_T[j][i] = Integer.parseInt(st[j]);
                }
            }

            sb.append("#").append(tc).append(" ").append(search()).append("\n");
        }
        System.out.println(sb);
    }
}

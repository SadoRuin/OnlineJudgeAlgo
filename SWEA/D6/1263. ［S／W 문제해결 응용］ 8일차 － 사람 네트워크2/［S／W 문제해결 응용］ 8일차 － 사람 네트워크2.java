import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    static final int INF = 987654321;   // 갈 수 없는 거리

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        for(int tc=1; tc<=T; tc++) {    // 테스트 케이스 반복
            String[] st = br.readLine().split(" ");
            int N = Integer.parseInt(st[0]);
            int[][] D = new int[N][N];  // 인접행렬

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    D[i][j] = Integer.parseInt(st[i * N + j % N + 1]);  // 1줄 입력을 인접행렬로 변환
                    if(i != j && D[i][j] == 0) {    // 자기자신으로 가는 경로가 아닌데 거리가 0인경우
                        D[i][j] = INF;  // INF로 저장
                    }
                }
            }

            for(int k=0; k<N; k++) {
                for(int i=0; i<N; i++) {
                    if(i == k) continue;    // 출발점이 경유지라면 스킵
                    for(int j=0; j<N; j++) {
                        if(i == j || k == j) continue;  // 출발점이 도착점과 같거나 경유지가 도착점과 같으면 스킵
                        D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]); // 최단거리 갱신
                    }
                }
            }

            int min = INF;
            for(int i=0; i<N; i++) {
                int sum = 0;    // 출발점 i에서의 CC값
                for(int j=0; j<N; j++) {
                    if(D[i][j] != INF) {    // INF일 경우 더하지 않음
                        sum += D[i][j];
                    }
                }
                min = Math.min(min, sum);   // CC값중 최소 갱신
            }

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}

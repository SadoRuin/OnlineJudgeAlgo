import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        for(int tc=1; tc<=T; tc++) {    // 테스트케이스 반복
            int N = Integer.parseInt(br.readLine());    // 학생 수
            int M = Integer.parseInt(br.readLine());    // 키 비교 횟수

            int[][] adjMat = new int[N][N]; // 인접매트릭스
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(i == j) continue;    // 비교대상이 서로 자기자신이 아니면 INF 저장
                    adjMat[i][j] = INF;
                }
            }

            for(int i=0; i<M; i++) {
                String[] st = br.readLine().split(" ");
                int a = Integer.parseInt(st[0]) - 1;    // 키가 작은 학생 (인덱스 0부터)
                int b = Integer.parseInt(st[1]) - 1;    // 키가 큰 학생 (인덱스 0부터)
                adjMat[a][b] = 1;   // a -> b를 인접매트릭스에 1로 저장
            }

            // 플로이드 워셜 알고리즘 수행
            for(int k=0; k<N; k++) {
                for(int i=0; i<N; i++) {
                    if(i == k) continue;
                    for(int j=0; j<N; j++) {
                        if(i == j || k == j) continue;
                        adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                    }
                }
            }
            int answer = 0; // 자신의 키가 몇번째인지 알 수 있는 학생 수
            for(int i=0; i<N; i++) {
                int cnt = 0;    // 비교 된 횟수 카운트
                for(int j=0; j<N; j++) {
                    if(adjMat[i][j] != 0 && adjMat[i][j] != INF) cnt++; // i가 j보다 작은 비교가 있었으면 카운트 증가
                    if(adjMat[j][i] != 0 && adjMat[j][i] != INF) cnt++; // i가 j보다 큰 비교가 있었으면 카운트 증가
                }
                if(cnt == N-1) answer++;    // i가 포함된 비교횟수가 학생 인원수-1이면 자신의 키가 몇번째인지 알수 있으므로 answer 증가
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");  // 출력물 저장
        }
        System.out.println(sb); // 출력
    }
}

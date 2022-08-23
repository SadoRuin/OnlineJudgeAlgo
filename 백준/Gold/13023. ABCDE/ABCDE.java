import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int N, answer;
    static boolean[] visited;

    // dfs 탐색
    static void dfs(int cur, int cnt) {
        if(cnt == 4) {  // 방문이 4번 일 경우
            answer = 1; // 존재가능
            return; // dfs 종료
        }

        visited[cur] = true;    // 방문 처리
        for(int to : adjList[cur]) { // 현재 노드와 연결된 모든 노드 반복
            if(!visited[to]) {  // 방문하지 않았으면
                dfs(to, cnt+1);    // 그 노드 기준으로 다시 dfs 수행
            }
        }
        visited[cur] = false;   // 돌아갈때 방문처리 취소
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);    // 노드 개수
        int M = Integer.parseInt(st[1]);    // 간선 개수
        adjList = new ArrayList[N];  // 인접리스트

        for(int i=0; i<N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = br.readLine().split(" ");
            int from = Integer.parseInt(st[0]);
            int to = Integer.parseInt(st[1]);
            adjList[from].add(to);    // 인접리스트 생성
            adjList[to].add(from);    // 인접리스트 생성
        }
        for(int i=0; i<N; i++) {    // 모든 노드를 시작 노드로 반복
            visited = new boolean[N];   // 매 시작 노드마다 방문기록 초기화
            dfs(i, 0); // i를 시작노드로 dfs 수행
            if (answer == 1) {   // 존재 가능하면
                break;  // for문 종료
            }
        }
        System.out.println(answer); // 정답 출력
    }
}

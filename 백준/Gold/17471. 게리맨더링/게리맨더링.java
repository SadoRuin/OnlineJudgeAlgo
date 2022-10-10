import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static Queue<Integer> queue;
    static boolean[] visited;
    static Node[] adjList;

    static class Node { // 인접리스트에 사용할 노드 클래스
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    // bfs 수행 메소드
    static boolean bfs(List<Integer> area) {
        queue.clear();  // 큐 초기화
        Arrays.fill(visited, false);    // 방문배열 초기화
        int cnt = 1;    // 방문한 지역수 카운트

        queue.offer(area.get(0));   // 선거구 처음 지역을 큐에 저장
        visited[area.get(0)] = true;    // 방문 처리
        while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
            int cur = queue.poll(); // 현재 위치 꺼냄
            // 인점리스트 반복
            for(Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
                if(area.contains(tmp.to) && !visited[tmp.to]) { // 다음 연결지역이 선거구에 포함되면서 방문하지 않았으면
                    cnt++;  // 방문지역 카운트 증가
                    visited[tmp.to] = true; // 방문처리
                    queue.offer(tmp.to);    // 다음 위치 큐에 저장
                }
            }
        }
        // 방문지역 카운트가 선거구 수와 같으면 모든 선거구가 연결되어 있으므로 true 반환
        return cnt == area.size();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        String[] st = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            population[i] = Integer.parseInt(st[i-1]);
        }
        adjList = new Node[N+1];    // 인접리스트 생성
        for(int i=1; i<=N; i++) {
            st = br.readLine().split(" ");
            for(int j=1; j<=Integer.parseInt(st[0]); j++) {
                adjList[i] = new Node(Integer.parseInt(st[j]), adjList[i]);
            }
        }
        queue = new ArrayDeque<>();
        visited = new boolean[N+1];
        List<Integer> areaA = new ArrayList<>();    // A선거구 번호를 담는 리스트
        List<Integer> areaB = new ArrayList<>();    // B선거구 번호를 담는 리스트
        int min = 1000; // 최소 인구수 차이 초기값 세팅
        for(int flag=1, caseCnt=1<<N; flag<caseCnt-1; flag++) { // 비트마스킹을 이용한 부분집합
            areaA.clear();  // A선거구 리스트 초기화
            areaB.clear();  // B선거구 리스트 초기화
            for(int i=0; i<N; i++) {
                if((flag & 1<<i) != 0) {    // 선택된 번호는
                    areaA.add(i+1); // A선거구에 추가
                } else {    // 나머지 번호는
                    areaB.add(i+1); // B선거구에 추가
                }
            }
            if(bfs(areaA) && bfs(areaB)) {  // 각 선거구 번호끼리 연결되었는지 bfs로 확인
                int sum = 0;
                for (int a :
                        areaA) {
                    sum += population[a];   // A선거구 인구합
                }
                for (int b :
                        areaB) {
                    sum -= population[b];   // A선거구 인구합에서 B선거구 인구들을 뺌
                }
                min = Math.min(min, Math.abs(sum)); // 인구수 차의 최소값 갱신
            }
        }

        if(min == 1000) min = -1;   // 최소값이 초기값과 같으면 선거구를 나눌수 없으므로 -1로
        System.out.println(min);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static final int[] dx = {0, 1, 0, -1};   // 우, 하, 좌, 상
    static final int[] dy = {1, 0, -1, 0};   // 우, 하, 좌, 상
    static int INF = 987654321;
    static int N, M;
    static int[] dist, parents;
    static int[][] map;
    static boolean[][] visited;

    // 크루스칼에 사용할 간선 클래스
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    // 좌표를 나타낼 클래스
    static class Point {
        int x, y, idx;

        public Point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx; // 섬의 번호
        }
    }

    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aR = find(a);
        int bR = find(b);

        if(aR == bR) return false;

        parents[bR] = aR;
        return true;
    }

    // k 방향으로만 진행되는 dfs
    static void dfs(Point p, int k, int d) {
        int nextX = p.x + dx[k];
        int nextY = p.y + dy[k];
        if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M   // 다음위치가 범위내이고
                && map[nextX][nextY] != p.idx) {    // 현재 섬이 아니라면
            if(map[nextX][nextY] == 0) {    // 바다라면
                dfs(new Point(nextX, nextY, p.idx), k, d+1);    // 거리 증가시키고 다시 dfs 수행
            } else {    // 다른 섬이라면
                dist[map[nextX][nextY]] = Math.min(d, dist[map[nextX][nextY]]); // 최단 거리 갱신
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        N = Integer.parseInt(st[0]);
        M = Integer.parseInt(st[1]);

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st[j]);
                if(map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        // 섬에 번호 부여
        Queue<Point> queue = new ArrayDeque<>();
        int idx = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!visited[i][j] && map[i][j] == -1) { // 방문하지 않았으면서 섬일 경우
                    queue.offer(new Point(i, j, idx));  // 현재 위치와 섬번호로 객체를 만들어 큐에 저장
                    visited[i][j] = true;   // 방문 처리
                    map[i][j] = idx;    // 섬번호 부여

                    while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
                        Point p = queue.poll(); // 현재 위치 꺼냄

                        for(int k=0; k<4; k++) {    // 사방탐색
                            int nextX = p.x + dx[k];    // 다음 행위치
                            int nextY = p.y + dy[k];    // 다음 열위치

                            if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M   // 다음 위치가 범위내이고
                                && !visited[nextX][nextY] && map[nextX][nextY] == -1) { // 방문하지 않았으며 섬일경우
                                visited[nextX][nextY] = true;   // 방문처리
                                map[nextX][nextY] = p.idx;  // 같은 섬 번호 부여
                                queue.offer(new Point(nextX, nextY, p.idx));    // 다음 위치 큐에 저장
                            }
                        }
                    }
                    idx++;  // 섬 번호 증가
                }
            }
        }

        parents = new int[idx]; // union find를 위한 부모 배열
        for(int i=0; i<idx; i++) {  // idx : 섬의개수+1 (인덱스 1부터)
            parents[i] = i; // 초기 부모는 자기 자신
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 각 섬에서 다른 섬들에 연결할 수 있는 다리들의 최소 값을 구함
        dist = new int[idx];    // 각 섬마다 구한 거리를 임시 저장할 배열
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 0) {    // 바다가 아니라면
                    Arrays.fill(dist, INF); // 거리 배열 INF로 초기화
                    for(int k=0; k<4; k++) {    // 한방향으로만 가는 dfs 사방 반복
                        dfs(new Point(i, j, map[i][j]), k, 0);
                    }
                    for(int k=0; k<idx; k++) {  // 현재 섬의 거리 배열 확인
                        if(dist[k] != INF && dist[k] > 1) { // INF가 아니면서 1보다 크면
                            pq.offer(new Edge(map[i][j], k, dist[k]));  // PQ에 Edge 객체로 저장
                        }
                    }
                }
            }
        }

        // 크루스칼 알고리즘 수행
        int result = 0; // 최소신장트리 간선 가중치 합
        int count = 0;  // 간선의 개수
        while(!pq.isEmpty()) {  // 큐가 빌때까지 반복
            Edge e = pq.poll(); // 간선 꺼냄
            if(union(e.from, e.to)) {   // union이 가능하다면
                result += e.weight; // 간선의 가중치 결과에 더함
                if(++count == idx - 2) break;   // 간선의 수가 섬의개수-1이라면 최소신장트리이므로 반복문 종료
            }
        }
        if(count != idx - 2) result = -1;   // 간선의 수가 섬의개수-1이 아니라면 최소신장트리가 아니므로 모든 섬이 연결되지 않았다
        System.out.println(result);
    }
}

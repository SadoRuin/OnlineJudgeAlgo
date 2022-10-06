import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    // 인접리스트를 만들기 위한 노드 클래스
    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    // 맨하튼 거리를 계산하는 메소드
    static int getDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] cord = new int[n+2][2]; // 집 - 편의점 - 락페 좌표

            for(int i=0; i<n+2; i++) {
                String[] st = br.readLine().split(" ");
                cord[i][0] = Integer.parseInt(st[0]);
                cord[i][1] = Integer.parseInt(st[1]);
            }

            Node[] adjList = new Node[n+2]; // 인접리스트 생성
            for(int i=0; i<n+2; i++) {  // 모든 좌표 탐색
                for(int j=i+1; j<n+2; j++) {    // i 이후의 좌표만 탐색
                    if(getDistance(cord[i], cord[j]) <= 1000) { // 거리가 1000(맥주 20병) 이하이면
                        adjList[i] = new Node(j, adjList[i]);   // 인접리스트 갱신
                        adjList[j] = new Node(i, adjList[j]);   // 무향이므로 반대도 갱신
                    }
                }
            }

            Queue<Integer> queue = new ArrayDeque<>();  // 노드 번호를 담는 큐
            boolean[] visited = new boolean[n+2];   // 노드 방문처리 배열

            queue.offer(0); // 집을 큐에 저장
            visited[0] = true;  // 집 방문처리

            while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
                int cur = queue.poll(); // 현재 노드 꺼냄
                if(cur == n+1) {    // 락페 노드이면
                    break;  // while문 종료
                }

                for(Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) { // 현재 노드의 인접노드 반복
                    if(!visited[tmp.to]) {  // 인접노드를 방문하지 않았다면
                        visited[tmp.to] = true; // 방문처리하고
                        queue.offer(tmp.to);    // 다음 방문위치 큐에 저장
                    }
                }
            }

            if(visited[n+1]) {  // 락페노드를 방문했다면
                sb.append("happy").append("\n");    // happy 출력
            } else {    // 못했다면
                sb.append("sad").append("\n");  // sad 출력
            }
        }

        System.out.println(sb);
    }
}

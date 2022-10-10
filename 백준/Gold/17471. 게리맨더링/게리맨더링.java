import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] population;
    static Queue<Integer> queue;
    static boolean[] visited;
    static Node[] adjList;

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static boolean bfs(List<Integer> area) {
        queue.clear();
        Arrays.fill(visited, false);
        int cnt = 1;

        queue.offer(area.get(0));
        visited[area.get(0)] = true;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(Node tmp = adjList[cur]; tmp != null; tmp = tmp.next) {
                if(area.contains(tmp.to) && !visited[tmp.to]) {
                    cnt++;
                    visited[tmp.to] = true;
                    queue.offer(tmp.to);
                }
            }
        }
        if(cnt == area.size()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        String[] st = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            population[i] = Integer.parseInt(st[i-1]);
        }
        adjList = new Node[N+1];
        for(int i=1; i<=N; i++) {
            st = br.readLine().split(" ");
            for(int j=1; j<=Integer.parseInt(st[0]); j++) {
                adjList[i] = new Node(Integer.parseInt(st[j]), adjList[i]);
            }
        }
        queue = new ArrayDeque<>();
        visited = new boolean[N+1];
        List<Integer> areaA = new ArrayList<>();
        List<Integer> areaB = new ArrayList<>();
        int min = 1000;
        for(int flag=1, caseCnt=1<<N; flag<caseCnt-1; flag++) {
            areaA.clear();
            areaB.clear();
            for(int i=0; i<N; i++) {
                if((flag & 1<<i) != 0) {
                    areaA.add(i+1);
                } else {
                    areaB.add(i+1);
                }
            }
            if(bfs(areaA) && bfs(areaB)) {
                int sum = 0;
                for (int a :
                        areaA) {
                    sum += population[a];
                }
                for (int b :
                        areaB) {
                    sum -= population[b];
                }
                min = Math.min(min, Math.abs(sum));
            }
        }

        if(min == 1000) min = -1;
        System.out.println(min);
    }
}

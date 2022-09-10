import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    static int N;
    static String[] picked;
    static String[][] tickets;
    static boolean[] visited;
    static List<String[]> list;

    static void dfs(int idx) {
        if(idx == N) {
            list.add(Arrays.copyOf(picked, N+1));
            return;
        }

        for(int i=0; i<N; i++) {
            if(picked[idx].equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                picked[idx+1] = tickets[i][1];
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }

    public String[] solution(String[][] tickets) {
        N = tickets.length;
        this.tickets = tickets;
        picked = new String[N + 1];   // 결과물 저장
        visited = new boolean[N];
        list = new ArrayList<>();

        Arrays.sort(tickets, Comparator.comparing(o -> o[1]));
        picked[0] = "ICN";
        dfs(0);

        return list.get(0);
    }
}
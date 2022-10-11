import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);    // 벨트에 놓인 접시 수
        int d = Integer.parseInt(st[1]);    // 초밥의 가짓수
        int k = Integer.parseInt(st[2]);    // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st[3]);    // 쿠폰 번호
        int[] dish = new int[N];    // 접시 위에 놓인 초밥
        Map<Integer, Integer> map = new HashMap<>();    // 집은 초밥을 기록할 HashMap
        for(int i=0; i<N; i++) {
            dish[i] = Integer.parseInt(br.readLine());
        }
        int max = 0;
        int idx = 0;
        while(idx < N+k) {  // 한바퀴 돌고 k-1번째까지 반복
            if(idx < k-1) { // k-1보다 작으면 map에 저장만 한다
                map.merge(dish[idx], 1, Integer::sum);  // map에 저장
                idx++;  // 인덱스 증가
                continue;   // 다시
            }
            int i = idx;    // 현재 인덱스 저장
            if(idx >= N) i -= N;    // 한바퀴를 돌았으면 접시수만큼 빼서 첫부분을 집을 수 있게
            map.merge(dish[i], 1, Integer::sum);    // map에 저장
            int cnt = map.size();   // map 크기가 집은 초밥의 종류 수
            if(map.get(c) == null) cnt++;   // 쿠폰 초밥을 안집었으면 집은 초밥수 증가
            if(max < cnt) max = cnt;    // 최대 종류 수 갱신
            int j = i+1-k < 0 ? i+1-k+N : i+1-k;    // 집은 초밥 중 가장 먼저 집은 초밥 인덱스
            map.merge(dish[j], -1, Integer::sum);   // 초밥 제거
            if(map.get(dish[j]) == 0) { // 집은 초밥 개수가 0개이면 map에서 제거
                map.remove(dish[j]);
            }
            idx++;  // 다음 초밥으로
        }

        System.out.println(max);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Point start;
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // AB벡터와 BC벡터의 외적(크로스 프로덕트)를 구하는 메소드
    static long cross(Point A, Point B, Point C) {
        return (B.x-A.x)*(C.y-B.y) - (C.x-B.x)*(B.y-A.y);
    }

    // 두 점의 유클리드거리를 구하는 메소드(제곱근은 생략)
    static long distance(Point A, Point B) {
        return (A.x - B.x)*(A.x - B.x) + (A.y - B.y)*(A.y - B.y);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Point> list = new ArrayList<>();   // 점들을 담는 리스트
        for(int i=0; i<N; i++) {
            String[] st = br.readLine().split(" ");
            long x = Long.parseLong(st[0]);
            long y = Long.parseLong(st[1]);
            list.add(new Point(x, y));
        }

        // 기준점 선정
        start = list.get(0);
        for(int i=1; i<list.size(); i++) {
            Point p = list.get(i);
            if(p.y < start.y) { // y가 더 작으면
                start = p;  // 기준점 갱신
            } else if(p.y == start.y && p.x < start.x) {    // y값이 같고 x가 더 작으면
                start = p;  // 기준점 갱신
            }
        }

        list.sort((o1, o2) -> { // 그라함 스캔을 위해 남은 점들을 정렬
            long ccw = cross(start, o1, o2);
            if(ccw > 0) {   // 기준점과 o1의 각도가 o2와의 각도보다 작으면
                return -1;  // o1을 먼저
            } else if(ccw < 0) {    // o2와의 각도가 더 작으면
                return 1;   // o2를 먼저
            } else {    // 각도가 같으면
                if(distance(start, o1) < distance(start, o2)) { // 더 가까운 것부터
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        Deque<Point> stack = new ArrayDeque<>();    // 스택에 볼록 다각형을 만들 점들을 담음
        stack.push(start);  // 기준점을 넣고
        stack.push(list.get(1));    // 리스트 제일 앞의 점을 넣음
        for (int i=2; i<list.size(); i++) { // 리스트 인덱스 1부터 끝까지 반복
            Point C = list.get(i);  // 현재 점
            while(stack.size() > 1) {   // 스택에 들어있는 점이 2개이상이면
                Point B = stack.pop();  // 제일 위에꺼
                Point A = stack.pop();  // 그다음껄 꺼냄
                if (cross(A, B, C) <= 0) {  // 3개의 점의 외적이 0보다 같거나 작으면
                    stack.push(A);  // A-B-C가 시계방향이므로 스택에 A만 집어넣음
                } else {    // 0보다 크다면 A-B-C가 반시계방향이므로
                    stack.push(A);  // A와
                    stack.push(B);  // B를 그대로 다시 넣음
                    break;  // 더이상 스택에서 뺄 점이 없으므로 while문 종료
                }
            }
            stack.push(C);  // C를 스택에 넣음
        }

        System.out.println(stack.size());   // 스택의 크기가 다각형을 이루는 점의 개수
    }
}

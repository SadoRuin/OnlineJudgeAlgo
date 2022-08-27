import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    // comparable을 사용한 Student class
    static class Student implements Comparable<Student> {
        int num, rcm, order;    // 학생번호, 추천수, 사진틀에 추가된 순서

        public Student(int num, int rcm, int order) {   // 생성자
            this.num = num;
            this.rcm = rcm;
            this.order = order;
        }

        @Override
        public int compareTo(Student o) {
            if(this.rcm == o.rcm) { // 추천수가 같으면
                return this.order - o.order;    // 가장 먼저 추가된 학생부터
            } else {    // 그 외엔
                return this.rcm - o.rcm;    // 추천수 기준 오름차순
            }
        }
    }

    // 우선순위 큐를 재정렬하는 메소드
    static void reSort(PriorityQueue<Student> pq) {
        PriorityQueue<Student> tmp = new PriorityQueue<>();
        while(!pq.isEmpty()) {
            tmp.offer(pq.poll());
        }
        while(!tmp.isEmpty()) {
            pq.offer(tmp.poll());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 사진틀 수
        int M = Integer.parseInt(br.readLine());    // 추천 수

        PriorityQueue<Student> pq = new PriorityQueue<>();  // 사진틀에 추가할 학생을 정할 우선순위 큐
        String[] st = br.readLine().split(" ");
        int order = 0;  // 사진틀에 추가된 학생의 순서

        Loop:
        for(int i=0; i<M; i++) {    // 추천 수 만큼 반복
            int num = Integer.parseInt(st[i]);  // 추천 받은 학생 번호
            for (Student s :
                    pq) {   // 사진틀의 학생 반복
                if (s.num == num) { // 현재 추천받은 학생의 번호가 있다면
                    s.rcm++;    // 추천수 증가
                    reSort(pq); // 우선순위큐 재정렬
                    continue Loop;  // 반복 종료
                }
            }
            if(pq.size() >= N) {    // 사진틀의 학생 수가 N명이상 이라면
                pq.poll();  // 사진틀에서 학생 제거
            }
            pq.offer(new Student(num, 1, order++)); // 사진틀에 학생 새로 추가
        }

        // 사진틀 우선순위큐를 학생 번호만 오름차순으로 정렬해서 출력
        pq.stream().mapToInt(s -> s.num).sorted().forEach(s -> System.out.print(s +" "));

    }
}

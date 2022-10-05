import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static final int[] dr = {-1, 0, 1, 0};  // 상, 좌, 하, 우
    static final int[] dc = {0, -1, 0, 1};  // 상, 좌, 하, 우

    static int R, C, M;
    static Shark[][] map;
    static Queue<Shark> queue;

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    // 상어가 이동하는 메소드
    static void moveShark() {
        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                if(map[i][j] != null) { // 상어가 있는 위치
                    queue.offer(map[i][j]); // 상어를 큐에 저장
                    map[i][j] = null;   // 상어가 있던 위치 null로 초기화
                }
            }
        }
        while(!queue.isEmpty()) {   // 큐가 빌때까지 반복
            Shark shark = queue.poll(); // 상어 꺼냄
            int speed = shark.s;    // 상어의 속력
            if(shark.d == 0 || shark.d == 2) {  // 상, 하 이동일 경우
                speed %= (R - 1) * 2;   // (R-1)*2만큼 이동하면 현재 위치이므로 나눈 나머지만큼만 이동하면 됨
            } else {    // 좌, 우 이동일 경우
                speed %= (C - 1) * 2;   // (C-1)*2만큼 이동하면 현재 위치이므로 나눈 나머지만큼만 이동하면 됨
            }
            for(int i=0; i<speed; i++) {
                int nextR = shark.r + dr[shark.d];  // 다음 행위치
                int nextC = shark.c + dc[shark.d];  // 다음 열 위치
                if(nextR <= 0 || nextR > R || nextC <= 0 || nextC > C) {    // 범위를 벗어나면
                    shark.d = (shark.d + 2) % 4;    // 반대 방향
                    shark.r += dr[shark.d]; // 반대방향으로 한번 이동
                    shark.c += dc[shark.d]; // 반대방향으로 한번 이동
                } else {    // 범위를 안벗어나면
                    shark.r = nextR;    // 다음 행위치 저장
                    shark.c = nextC;    // 다음 열위치 저장
                }
            }
            if(map[shark.r][shark.c] != null) { // 이동한 위치에 이미 상어가 있으면
                if(map[shark.r][shark.c].z < shark.z) { // 현재 상어가 더 크면
                    map[shark.r][shark.c] = shark;  // 현재 상어를 새로 저장
                }
            } else {    // 없다면
                map[shark.r][shark.c] = shark;  // 현재 상어 저장
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");

        R = Integer.parseInt(st[0]);
        C = Integer.parseInt(st[1]);
        M = Integer.parseInt(st[2]);

        map = new Shark[R+1][C+1];  // 상어가 있는 위치에 상어객체를 저장하는 2차원배열
        queue = new ArrayDeque<>(); // 상어를 이동시에 담는 큐
        for(int i=0; i<M; i++) {
            st = br.readLine().split(" ");
            int r = Integer.parseInt(st[0]);
            int c = Integer.parseInt(st[1]);
            int s = Integer.parseInt(st[2]);
            int d = Integer.parseInt(st[3]);
            int z = Integer.parseInt(st[4]);
            // 방향 전환 계산을 수월하게 하기 위해 방향 숫자 변경
            if(d == 1) {    // 상 이동은
                d = 0;  // 0으로 바꿈
            } else if(d == 4) { // 좌 이동은
                d = 1;  // 1로 바꿈
            }
            map[r][c] = new Shark(r, c, s, d, z); // 상어가 있는 위치에 상어 객체 생성
        }

        int totalSize = 0;  // 낚시왕이 잡은 상어 크기 합
        for(int j=1; j<=C; j++) {   // 1번위치부터 C번위치까지 반복
            for(int i=1; i<=R; i++) {   // 위에서부터 탐색
                if(map[i][j] != null) { // 상어가 있다면
                    totalSize += map[i][j].z;   // 그 상어 크기를 잡은 상어에 더하고
                    map[i][j] = null;   // 상어를 제거
                    break;  // 다음 위치로
                }
            }
            moveShark();    // 상어 이동 및 같은 위치 제거
        }

        System.out.println(totalSize);
    }
}

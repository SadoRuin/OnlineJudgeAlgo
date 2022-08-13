class Solution {
    public String solution(String number, int k) {
        int N = number.length();    // 주어진 숫자의 자리수
        int R = N - k;      // 남겨야 하는 자리수
        StringBuilder answer = new StringBuilder(); // 선택된 숫자를 저장할 StringBuilder

        int index = 0;
        int bound = N - R;
        // 앞의 자리수가 클수록 큰 수가 만들어지므로
        // 만들 수 있는 자리수가 되면서 가장 앞자리수의 최대값을 결정할 수 있는 최대 범위가 bound이다
        while(answer.length() != R) {     // 선택된 숫자가 남겨야하는 자리수만큼 선택될때까지
            int max = 0;
            for(int i=index; i<=bound; i++) {    // 특정 index부터 bound까지
                if(i >= N) break;   // 탐색 범위가 주어진 숫자 길이를 벗어나면 break
                int num = number.charAt(i) - '0';
                if(max < num) {     // 큰 숫자가 있으면
                    max = num;  // 큰 숫자를 저장하고
                    index = i+1;    // 그 큰 순자의 다음 자리를 새로운 index로
                }
            }
            answer.append(max);    // 범위에서 정해진 최종 큰 숫자를 선택하고
            bound++;    // bound를 하나 늘린다
        }

        return answer.toString();
    }
}
import sys
from itertools import permutations

input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))
exp = [['+'], ['-'], ['*'], ['/']]
op = []
for i, m in enumerate(input().split()):
    for _ in range(int(m)):
        op += exp[i]

mxm = -1e9
mnm = 1e9

for perm in permutations(op):
    answer = a[0]
    for i, p in enumerate(perm):
        if p == '+':
            answer += a[i+1]
        elif p == '-':
            answer -= a[i+1]
        elif p == '*':
            answer *= a[i+1]
        elif p == '/':
            if answer < 0:
                answer = ((answer * -1) // a[i+1]) * -1
            else:
                answer = answer // a[i+1]
    mxm = max(mxm, answer)
    mnm = min(mnm, answer)


print(mxm)
print(mnm)
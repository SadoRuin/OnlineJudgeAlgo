import sys
from itertools import combinations

input = sys.stdin.readline
N = int(input())
S = [list(map(int, input().split())) for _ in range(N)]

mnm = 1e9

for comb in combinations(range(N), N//2):
    complement = list(set(range(N)) - set(comb))
    diff = 0
    for i in comb:
        for j in comb:
            diff += S[i][j]
    for i in complement:
        for j in complement:
            diff -= S[i][j]
    mnm = min(mnm, abs(diff))


print(mnm)
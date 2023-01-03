import sys
from collections import deque

input = sys.stdin.readline
n = int(input())
a = list(map(int, input().split()))
op = list(map(int, input().split()))

mxm = -1e9
mnm = 1e9

queue = deque([[a[0], op[0], op[1], op[2], op[3], 1]])

while queue:
    num, add, sub, mul, div, cnt = queue.popleft()
    if cnt == n:
        mxm = max(mxm, num)
        mnm = min(mnm, num)

    if add > 0:
        queue.append([num + a[cnt], add-1, sub, mul, div, cnt+1])
    if sub > 0:
        queue.append([num - a[cnt], add, sub-1, mul, div, cnt+1])
    if mul > 0:
        queue.append([num * a[cnt], add, sub, mul-1, div, cnt+1])
    if div > 0:
        queue.append([int(num / a[cnt]), add, sub, mul, div-1, cnt+1])

print(mxm)
print(mnm)
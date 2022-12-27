import sys

input = sys.stdin.readline
n = int(input())
words = [list(input().rstrip()) for _ in range(n)]
dic = dict()

for word in words:
    for i, w in enumerate(word):
        if w not in dic:
            dic[w] = 10 ** (len(word) - i - 1)
        else:
            dic[w] += 10 ** (len(word) - i - 1)

answer = 0
num = 9
for word in sorted(dic.values(), reverse=True):
    answer += num * word
    num -= 1

print(answer)
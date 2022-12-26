import itertools

n = int(input())
signs = input().split()
nums = list(range(10))

perms = itertools.permutations(nums, n+1)
answer = []

for p in perms:
    flag = True
    for i, s in enumerate(signs):
        if s == "<" and p[i] < p[i+1]:
            continue
        elif s == ">" and p[i] > p[i+1]:
            continue
        else:
            flag = False
            break
    if flag:
        answer.append(''.join(map(str, p)))

print(max(answer))
print(min(answer))
def matrix_chain(p, n):
    m = [[0]*(n+1) for _ in range(n+1)]
    s = [[0]*(n+1) for _ in range(n+1)]

    for i in range(1, n+1):
        for j in range(1, i+1):
            m[i][j] = 0
            s[i][j] = 0

    for r in range(2, n+1):
        for i in range(1, n-r+2):
            j = i + r - 1
            m[i][j] = m[i+1][j] + p[i-1]*p[i]*p[j]
            s[i][j] = i
            for k in range(i+1, j):
                t = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j]
                if t < m[i][j]:
                    m[i][j] = t
                    s[i][j] = k

    return m, s

def traceback(s, i, j):
    if i == j:
        print(f"A{i}", end="")
    else:
        print("(", end="")
        traceback(s, i, s[i][j])
        traceback(s, s[i][j] + 1, j)
        print(")", end="")

# 主程序
if __name__ == "__main__":
    n = 6
    p = [25, 20, 10, 35, 5, 25, 10]
    m, s = matrix_chain(p, n)

    print("连乘矩阵最优计算次序是：")
    traceback(s, 1, n)
    print()

    print("次序矩阵s为：")
    print("{:<5}".format("坐标"), end="")
    for j in range(1, n+1):
        print(f"{'j'+str(j):<4}", end="")
    print()

    for i in range(1, n+1):
        print(f"{i:<4}", end="")
        for j in range(1, n+1):
            print(f"{s[i][j]:<4}", end="")
        print()

    print()

    print("最优解矩阵m为：")
    print("{:<5}".format("坐标"), end="")
    for j in range(1, n+1):
        print(f"{'j'+str(j):<4}", end="")
    print()

    for i in range(1, n+1):
        print(f"{i:<4}", end="")
        for j in range(1, n+1):
            print(f"{m[i][j]:<10}", end="")
        print()
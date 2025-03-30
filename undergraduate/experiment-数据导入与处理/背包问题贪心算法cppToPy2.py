# https://blog.csdn.net/lytwy123/article/details/83216843

def knapsack(n, M, v, w, x):
    i = 0
    total = 0  # 背包的价值
    while i < n and w[i] < M:  # 判断当前物品的重量是否能装入背包
        x[i] = 1
        total += v[i]
        M -= w[i]
        i += 1
    if i < n:  # 这是最后装入背包的物品
        x[i] = M / w[i]
        total += x[i] * v[i]
    return total


def main():
    n = int(input("input n:\n"))
    W = float(input("input W:\n\n"))

    w = [0.0] * n
    v = [0.0] * n
    a = [0.0] * n
    in_index = [0] * n

    print("input numbergroup:")
    for i in range(n):
        w[i], v[i] = map(float, input().split())
        a[i] = v[i] / w[i]

    # 对数组a即vi/wi进行冒泡降序排序，并且记录下标到in_index[]数组
    for i in range(n):
        for j in range(n - i - 1):
            if a[j] < a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
                w[j], w[j + 1] = w[j + 1], w[j]
                v[j], v[j + 1] = v[j + 1], v[j]
                in_index[j], in_index[j + 1] = in_index[j + 1], in_index[j]

    # 将原来输入的w[i]、v[i]数组进行排列，按照v[i]/w[i]的顺序
    v1 = [0.0] * n
    w1 = [0.0] * n
    for i in range(n):
        v1[i] = v[in_index[i]]
        w1[i] = w[in_index[i]]

    x = [0.0] * n
    print(knapsack(n, W, v1, w1, x))


if __name__ == "__main__":
    main()
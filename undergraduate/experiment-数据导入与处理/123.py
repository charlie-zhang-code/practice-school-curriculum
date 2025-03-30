# 初始化变量
best_value = 0  # 最佳价值
weight = 0  # 当前重量
value = 0  # 当前价值
best_result = None  # 最佳选择结果


def output(solution):
    for item in solution:
        print(" ", item, end="")
    print()


def bound(temp, total):
    remain = total - weight
    result = best_value

    items = sorted(zip(v, w), key=lambda s: s[0] / s[1], reverse=True)

    while temp < n and items[temp][1] <= remain:
        remain -= items[temp][1]
        result += items[temp][0]
        temp += 1
    if temp < n:
        result += items[temp][0] / items[temp][1] * remain
    return result


def zero_one_bag(temp):
    global best_value, value, weight, best_result, temp
    if temp >= n:
        if best_value < current_value:
            best_value = current_value
            best_result = x[:]
    else:
        if current_weight + w[temp] <= c:
            x[temp] = True
            current_weight += w[temp]
            current_value += v[temp]
            zero_one_bag(temp + 1)
            current_weight -= w[temp]
            current_value -= v[temp]
        if bound(temp, c) > best_value:
            x[temp] = False
            zero_one_bag(temp + 1)


if __name__ == "__main__":
    n = 5  # 物品数量
    c = 70  # 背包最大容量
    v = [80, 30, 60, 100, 70]  # 物品价值
    w = [30, 20, 20, 50, 40]  # 物品重量
    temp = [False for _ in range(n)]  # 选择数组

    zero_one_bag(0)

    print("Best Value :", best_value)
    output(best_result)  # 打印最佳选择

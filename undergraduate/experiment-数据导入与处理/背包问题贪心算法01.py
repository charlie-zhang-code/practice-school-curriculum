def unbounded_knapsack(capacity, weights, values):
    # 初始化dp数组，dp[i]表示背包容量为i时能装的最大价值
    dp = [0] * (capacity + 1)

    # 遍历所有物品
    for i in range(len(values)):
        # 价值密度，即单位重量的价值
        value_per_unit_weight = values[i] / weights[i]

        # 从后向前遍历背包容量，确保每种物品都可以无限取用
        for w in range(weights[i], capacity + 1):
            # 如果取用当前物品后的价值大于不取用当前物品的价值，则更新dp数组
            dp[w] = max(dp[w], dp[w - weights[i]] + values[i])

    # dp数组的最后一个元素即为背包容量为capacity时能装的最大价值
    return dp[capacity]


# 物品的价值列表
values = [80, 30, 60, 100, 70]
# 物品的重量列表
weights = [30, 20, 20, 50, 40]
# 背包的最大承重
capacity = 70

# 调用函数
max_value = unbounded_knapsack(capacity, weights, values)
print("Maximum value in knapsack:", max_value)
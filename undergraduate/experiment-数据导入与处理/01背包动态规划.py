# import time
#
#
# def knapsack(dimension, limit, weight, value):
#     result = [[0 for _ in range(limit + 1)] for _ in range(dimension + 1)]
#     for i in range(1, dimension + 1):
#         for j in range(1, limit + 1):
#             if j < weight[i - 1]:
#                 result[i][j] = result[i - 1][j]
#             else:
#                 result[i][j] = max(result[i - 1][j], result[i - 1][j - weight[i - 1]] + value[i - 1])
#     return result
#
#
# def main():
#     number = 5
#     weight = 70
#     w = [30, 20, 20, 50, 40]
#     v = [80, 30, 60, 100, 70]
#     result = knapsack(number, weight, w, v)
#
#     print("最优解为：" + str(result[number][weight]) + "\n")
#     print("所选取的物品为：")
#     item = [0 for i in range(number + 1)]
#     j = weight
#     for i in range(1, number + 1):
#         if result[i][j] > result[i - 1][j]:
#             item[i - 1] = 1
#             j -= w[i - 1]
#     for i in range(number):
#         if item[i] == 1:
#             print("第" + str(i + 1) + "件")
#
#
# if __name__ == '__main__':
#     main()
#
# # https://blog.csdn.net/weixin_44227192/article/details/106931543






#coding:utf-8

def bag(n, c, w, v):
    """
    测试数据：
    n = 6  物品的数量，
    c = 10 书包能承受的重量，
    w = [2, 2, 3, 1, 5, 2] 每个物品的重量，
    v = [2, 3, 1, 5, 4, 3] 每个物品的价值
    """
    # 置零，表示初始状态
    value = [[0 for j in range(c + 1)] for i in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(1, c + 1):
            value[i][j] = value[i - 1][j]
            # 背包总容量够放当前物体，遍历前一个状态考虑是否置换
            if j >= w[i - 1] and value[i][j] < value[i - 1][j - w[i - 1]] + v[i - 1]:
                value[i][j] = value[i - 1][j - w[i - 1]] + v[i - 1]
    for x in value:
        print(x)
    return value

def show(n, c, w, value):
    print('最大价值为:', value[n][c])
    x = [False for i in range(n)]
    j = c
    for i in range(n, 0, -1):
        if value[i][j] > value[i - 1][j]:
            x[i - 1] = True
            j -= w[i - 1]
    print('背包中所装物品为:')
    for i in range(n):
        if x[i]:
            print('第', i+1, '个,', end='')

def bag1(n, c, w, v):
    values = [0 for i in range(c+1)]
    for i in range(1, n + 1):
        for j in range(c, 0, -1):
            # 背包总容量够放当前物体，遍历前一个状态考虑是否置换
            if j >= w[i-1]:
                values[j] = max(values[j-w[i-1]]+v[i-1], values[j])
    return values


if __name__ == '__main__':
    n = 5
    c = 70
    w = [30, 20, 20, 50, 40]
    v = [80, 30, 60, 100, 70]
    value = bag(n, c, w, v)
    # [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    # [0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2]
    # [0, 0, 3, 3, 5, 5, 5, 5, 5, 5, 5]
    # [0, 0, 3, 3, 5, 5, 5, 6, 6, 6, 6]
    # [0, 5, 5, 8, 8, 10, 10, 10, 11, 11, 11]
    # [0, 5, 5, 8, 8, 10, 10, 10, 12, 12, 14]
    # [0, 5, 5, 8, 8, 11, 11, 13, 13, 13, 15]
    show(n, c, w, value)
    # 最大价值为: 15
    # 背包中所装物品为:
    # 第 2 个,第 4 个,第 5 个,第 6 个,
    print('\n空间复杂度优化为N(c)结果:', bag1(n, c, w, v))
    #空间复杂度优化为N(c)结果: [0, 5, 5, 8, 8, 11, 11, 13, 13, 13, 15]

    # https://blog.csdn.net/qq_34178562/article/details/79959380
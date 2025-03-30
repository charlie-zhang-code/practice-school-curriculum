# # -*- coding: utf-8 -*-
# """
# Created on Mon Oct 22 08:49:13 2018
# @author: pprp
# """
# best_value = 0  # best value
# current_weight = 0  # current weight
# current_value = 0  # current value
# best_result = None  # best x result
#
#
# def output(x):
#     for i in x:
#         print(" ", i, end="")
#     print()
#
#
# class node(object):
#     def __init__(self, v, w):
#         self.v = v
#         self.w = w
#         self.per = float(v) / float(w)
#
#
# def bound_function(t):
#     left_ = c - current_weight
#     best_r = best_value
#
#     nodes = []
#     for i in range(n):
#         nodes.append(node(v[i], w[i]))
#     nodes.sort(key=lambda x: x.per, reverse=True)
#
#     while t < n and w[t] <= left_:
#         left_ -= w[t]
#         best_r += v[t]
#         t += 1
#     if t < n:
#         best_r += float(v[t]) / float(w[t]) * left_
#     return best_r
#
#
# def backtrack(t, n):
#     global best_value, current_value, current_weight, x, best_result
#     if t >= n:
#         if best_value < current_value:
#             best_value = current_value
#             best_result = x[:]
#     else:
#         if current_weight + w[t] <= c:
#             x[t] = True
#             current_weight += w[t]
#             current_value += v[t]
#             backtrack(t + 1, n)
#             current_weight -= w[t]
#             current_value -= v[t]
#         if bound_function(t) > best_value:
#             x[t] = False
#             backtrack(t + 1, n)
#
#
# if __name__ == "__main__":
#     n = 5
#     c = 70
#     x = [False for i in range(n)]
#     w = [30, 20, 20, 50, 40]
#     v = [80, 30, 60, 100, 70]
#
#     backtrack(0, n)
#
#     print("Best Value :", best_value)
#     print("Best Choice:", best_result)
#
# # https://www.cnblogs.com/pprp/p/9880073.html


max, weight, value, result = 0, 0, 0, []  # 最大价值、当前重量、当前价值、最优解、商品列表
dimension = 5
limit = 70
# print('请输入物品数量、背包容积，空格隔开：')
# n, c = map(int, input().split())
# for i in range(n):
#     print(f'请输入第{i + 1}个物品的重量和价值，空格隔开：')
#     goods.append(list(map(int, input().split())))
item = [[30, 80], [20, 30], [20, 60], [50, 100], [40, 70]]
temp = [0 for i in range(dimension)]  # 初始化当前解


def knapsack(layer):
    global max, weight, value, result
    if layer == dimension:
        if max < value:
            max = value
            result = temp[:]
        return
    if weight + item[layer][0] <= limit:
        temp[layer] = 1
        weight += item[layer][0]
        value += item[layer][1]
        knapsack(layer + 1)
        weight -= item[layer][0]
        value -= item[layer][1]
    temp[layer] = 0
    knapsack(layer + 1)


knapsack(0)  # 从第0层开始搜索
print(f'最大价值为：{max}')
print(f'应装物品编号为：{[i + 1 for i in range(dimension) if result[i]]}')


# def knapsack(layer, weight, value, max, temp, limit, items):
#     if layer == len(items):
#         if max < value:
#             return value, temp[:]
#         return max, temp
#     if weight + items[layer][0] <= limit:
#         temp[layer] = 1
#         new_weight = weight + items[layer][0]
#         new_value = value + items[layer][1]
#         new_max, new_temp = knapsack(layer + 1, new_weight, new_value, max, temp[:], limit, items)
#         if new_max > max:
#             max, temp = new_max, new_temp
#     else:
#         temp[layer] = 0
#     temp[layer] = 0
#     max, temp = knapsack(layer + 1, weight, value, max, temp[:], limit, items)
#     return max, temp
#
# # 初始化参数
# dimension = 5
# limit = 70
# items = [[30, 80], [20, 30], [20, 60], [50, 100], [40, 70]]
# temp = [0 for _ in range(dimension)]
#
# # 开始递归搜索
# max_value, result = knapsack(0, 0, 0, 0, temp, limit, items)
#
# # 输出结果
# print(f'最大价值为：{max_value}')
# print(f'应装物品编号为：{[i + 1 for i, x in enumerate(result) if x]}')

# https://blog.csdn.net/weixin_44289959/article/details/117232707

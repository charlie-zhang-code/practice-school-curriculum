# https://blog.csdn.net/wmx1117/article/details/105755498


# class Goods:
#     def __init__(self, weight, value):
#         self.weight = weight
#         self.value = value
#         self.N = 0  # 物品装入背包的部分
#
#     @property
#     def P(self):
#         return self.value / self.weight if self.weight != 0 else 0
#
# def compare(a, b):
#     return a.P > b.P
#
# def greedy(goods, capacity):
#     for item in goods:
#         if capacity > item.weight:
#             capacity -= item.weight
#             item.N = 1
#         elif capacity > 0:
#             item.N = capacity / item.weight
#             capacity = 0
#
# def main():
#     # 预定义数据
#     goods = [
#         Goods(30, 80),
#         Goods(20, 30),
#         Goods(20, 60),
#         Goods(50, 100),
#         Goods(40, 70)
#     ]
#     capacity = 70  # 背包的容量
#
#     # 根据价值/重量比进行排序
#     goods.sort(key=lambda x: x.P, reverse=True)
#
#     # 执行贪心算法
#     greedy(goods, capacity)
#
#     total_value = 0
#     total_weight = 0
#     for item in goods:
#         if item.N == 0:
#             break
#         total_value += item.value * item.N
#         total_weight += item.weight * item.N
#         print(f"weight: {item.weight}  value: {item.value}  the part of goods: {item.N}")
#
#     print(f"背包的容量为: {capacity}")
#     print(f"装入背包中的物品的总重量为: {total_weight}")
#     print(f"装入背包中的物品的总价值为: {total_value}")
#
# if __name__ == "__main__":
#     main()


# 定义商品信息
items = [
    {'w': 30, 'v': 80, 'p': 0, 'n': 0},
    {'w': 20, 'v': 30, 'p': 0, 'n': 0},
    {'w': 20, 'v': 60, 'p': 0, 'n': 0},
    {'w': 50, 'v': 100, 'p': 0, 'n': 0},
    {'w': 40, 'v': 70, 'p': 0, 'n': 0}
]

# 背包容量
limit = 70

for item in items:
    item['p'] = item['v'] / item['w']

items.sort(key=lambda x: x['p'], reverse=True)

for i in items:
    print(i)

for item in items:
    if limit > item['w']:
        limit -= item['w']
        item['n'] = 1
    elif limit > 0:
        item['n'] = limit / item['w']
        limit = 0

total_value = 0
total_weight = 0

for item in items:
    if item['n'] > 0:
        total_value += item['v'] * item['n']
        total_weight += item['w'] * item['n']

# 输出结果
print(f"背包的容量为: {limit}")
print(f"装入背包中的物品的总重量为: {total_weight}")
print(f"装入背包中的物品的总价值为: {total_value}")

# 输出每个商品的重量、价值和装入背包的部分
for item in items:
    if item['n'] > 0:
        print(f"weight: {item['w']}  value: {item['v']}  the part of goods: {item['n']}")

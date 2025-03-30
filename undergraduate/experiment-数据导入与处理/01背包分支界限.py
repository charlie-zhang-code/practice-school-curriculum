class node:
    def __init__(self, level, value, weight, flag):  # 当前在解空间数的层次、价值、重量、左右子树标记左1右0
        self.level = level
        self.value = value
        self.weight = weight
        self.flag = flag
        self.bound = 0
        self.container = []


def bound(i, node, items, w):
    re_weight = w - node.weight
    bound = node.value
    while (i < len(items)):
        if (items[i][1] < re_weight):
            bound = bound + items[i][0]
            re_weight = re_weight - items[i][1]
            i = i + 1
        else:
            bound = bound + items[i][0] / items[i][1] * re_weight
            break
    return bound


def bag(n, w, items):
    items.sort(key=lambda x: x[0] / x[1], reverse=True)
    q = []
    myset = []
    maxvalue = 0
    q.append(node(0, 0, 0, -1))
    while (q != []):
        u = q.pop(0)
        if (u.level < len(items)):
            left_value = u.value + items[u.level][0]
            left_weight = u.weight + items[u.level][1]

            left = node(u.level + 1, left_value, left_weight, 1)

            left.bound = bound(left.level, left, items, w)

            left.container = u.container.copy()
            left.container.append(1)

            if (left.weight < w):
                q.append(left)
                if (left.value > maxvalue):
                    maxvalue = left.value
                    myset = left.container

            right = node(u.level + 1, u.value, u.weight, 0)
            right.bound = bound(right.level, right, items, w)
            right.container = u.container.copy()
            right.container.append(0)
            if (right.bound >= maxvalue):
                q.append(right)

            q.sort(key=lambda x: x.bound, reverse=True)

    print('装入背包的物品编号为：', end="")
    for i in range(len(myset)):
        if (myset[i] == 1):
            print(items[i][2], end='号 ')

    return maxvalue


if __name__ == '__main__':
    w = 70
    items = [(80, 30, 1), (30, 20, 2), (60, 20, 3), (100, 50, 4), (70, 40, 5)]  # 物品的价值、重量、编号
    n = len(items)
    maxvalue = bag(n, w, items)
    print()
    print("最大价值为:", maxvalue)

    # https://blog.csdn.net/Rynnnnnnnnn/article/details/133997020
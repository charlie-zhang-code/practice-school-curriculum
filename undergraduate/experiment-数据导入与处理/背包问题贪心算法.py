m = 70
h =  [30, 20, 20, 50, 40]
v = [80, 30, 60, 100, 70] # 价值

# 计算权重, 整合得到一个数组
arr = [(i, v[i] / h[i], h[i], v[i]) for i in range(len(h))]

# 输出处理的权重
print('处理后的权重：', arr)

# 按照list中的权重，从大到小排序
arr.sort(key=lambda x: x[1], reverse=True)  # list.sort() list排序函数

bagVal = 0
bagList = []
for i, w, h, v in arr:
    # 1 如果能放的下宝物，那就把宝物全放进去
    if w <= m:
        m -= h
        bagVal += v
        bagList.append(i)

    # 2 如果宝物不能完全放下，考虑放入部分宝物
    else:
        bagVal += m * w
        bagList.append(i)
        break

print('\n排序后：', arr)
print('能运走的最大价值：%.2f' % bagVal, '此时承载的宝物有：', bagList)

# https://blog.csdn.net/qq_36888550/article/details/105910137
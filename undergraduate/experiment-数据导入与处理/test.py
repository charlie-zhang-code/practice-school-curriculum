import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False


def binary_search(arr, target):
    low, high = 0, len(arr) - 1
    comp = 0
    steps = []

    while low <= high:
        mid = (low + high) // 2
        comp += 1
        steps.append((low, high, mid))

        if arr[mid] == target:
            return comp, steps
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1

    return -1, steps


arr = [1, 2, 6, 12, 25, 35, 38, 45, 75, 77, 82, 90, 98]
target = 6

result, steps = binary_search(arr, target)

plt.figure(figsize=(10, 5))
for i, (low, high, mid) in enumerate(steps):
    plt.plot([low, high], [i, i], color='blue', alpha=0.5)
    plt.plot(mid, i, 'ro')
    plt.text(mid, i, str(arr[mid]), ha='center', va='bottom')

for index, value in enumerate(arr):
    if index not in [step[2] for step in steps]:
        plt.text(index, 0, str(value), ha='center')

plt.title('二分查找 查找元素 {}'.format(target))
plt.xlabel('索引值index')
plt.ylabel('次数')
plt.xticks(range(len(arr)))
plt.yticks(range(len(steps)), [f'第 {i + 1} 次' for i in range(len(steps))])
plt.grid(True)
plt.show()

#include <iostream>

// 二分查找函数
int binarySearch(int arr[], int l, int r, int x) {
	while (l <= r) {
		// 计算中间位置
		int m = l + (r - l) / 2;

		// 检查中间位置的元素是否是目标值
		if (arr[m] == x) {
			return m; // 元素找到，返回其索引
		}

		// 如果中间位置的元素大于目标值，则在左侧子数组中查找
		if (arr[m] > x) {
			r = m - 1;
		}
		// 否则，在右侧子数组中查找
		else {
			l = m + 1;
		}
	}

	// 如果没有找到元素，则返回-1
	return -1;
}

int main() {
	int arr[] = { 22, 33, 44, 55, 66, 77, 88, 99, 100 };
	int n = sizeof(arr) / sizeof(arr[0]); // 数组中元素的数量
	int x = 33; // 要查找的元素

	// 调用二分查找函数
	int result = binarySearch(arr, 0, n - 1, x);

	if (result != -1) {
		std::cout << "元素 " << x << " 在索引 " << result << std::endl;
	}
	else {
		std::cout << "元素 " << x << " 没有在数组中发现" << std::endl;
	}

	return 0;
}
#include <iostream>

int num = 0;

template<class Type>
int binary_search(Type a[], const Type x, int n) {
	int i = 0, j = 0;
	int left = 0;
	int right = n - 1;
	while (left <= right) {
		int middle = (left + right) / 2;
		num += 1;
		std::cout << "第" << num << "次选取的中间元素为：" << a[middle] << std::endl;
		if (x == a[middle])
			return middle;
		else if (x > a[middle])
			left = middle + 1;
		else
			right = middle - 1;
	}
	i = right;
	j = left;
	std::cout << "小于x的最大元素的位置i为:" << i << "，大于x的最小元素的位置j为:" << j << std::endl;
	return -1;
}

int recursion_binary_search(int a[], int x, int left, int right, int right_one) {
	if (left > right) {
		int i = right;
		int j = left;
		if (j > right_one) {
			std::cout << "小于x的最大元素的位置i为:" << right_one << ",没有大于x的元素" << std::endl;
		}
		else if (i < 0) {
			std::cout << "没有小于x的元素,大于x的最小元素的位置j为0." << std::endl;
		}
		else {
			std::cout << "小于x的最大元素的位置i为:" << i << "，大于x的最小元素的位置j为:" << j << std::endl;
		}
		return -1;
	}
	int middle = (left + right) / 2;
	num += 1;
	std::cout << "第" << num << "次选取的中间元素为：" << a[middle] << std::endl;

	if (x == a[middle]) {
		return middle;
	}
	else if (x > a[middle]) {
		return recursion_binary_search(a, x, middle + 1, right, right_one);
	}
	else {
		return recursion_binary_search(a, x, left, middle - 1, right_one);
	}
}

int main() {
	int i, x, ret, left = 0;
	int a[] = {22, 33, 44, 55, 66, 77, 88, 99, 100 };
	int right = sizeof(a) / sizeof(int);
	int right_one = right - 1;

	for (i = 0; i < right; i++)
		std::cout << a[i] << "  ";
	std::cout << "\n请输入所要查找的元素：";
	std::cin >> x;
	// ret = binary_search(a, x, right);
	ret = recursion_binary_search(a, x, left, right, right_one);
	if (-1 == ret)
		std::cout << "查找失败" << std::endl;
	else {
		std::cout << "查找成功" << std::endl;
		std::cout << "查找元素的下标是:" << ret << std::endl;
	}
	return 0;
}
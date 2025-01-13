#include <iostream>

// 合并两个子数组的函数
void merge(int arr[], int l, int m, int r) {
	int i, j, k;
	int n1 = m - l + 1; // 左子数组的大小
	int n2 = r - m;     // 右子数组的大小

	// 创建临时数组
	int* L = new int[n1]; // 使用new分配内存
	int* R = new int[n2]; // 使用new分配内存

	// 拷贝数据到临时数组 L[] 和 R[]
	for (i = 0; i < n1; i++)
		L[i] = arr[l + i];
	for (j = 0; j < n2; j++)
		R[j] = arr[m + 1 + j];

	// 合并临时数组回 arr[l..r]
	i = 0; // 初始索引第一个子数组
	j = 0; // 初始索引第二个子数组
	k = l; // 初始索引合并的子数组
	while (i < n1 && j < n2) {
		if (L[i] <= R[j]) {
			arr[k] = L[i];
			i++;
		}
		else {
			arr[k] = R[j];
			j++;
		}
		k++;
	}

	// 拷贝 L[] 的剩余元素
	while (i < n1) {
		arr[k] = L[i];
		i++;
		k++;
	}

	// 拷贝 R[] 的剩余元素
	while (j < n2) {
		arr[k] = R[j];
		j++;
		k++;
	}

	// 释放临时数组占用的内存
	delete[] L;
	delete[] R;
}

// l 是数组的左边界，r 是右边界
void mergeSort(int arr[], int l, int r) {
	if (l < r) {
		// 同 [l, m] 和 [m+1, r] 找中间点
		int m = l + (r - l) / 2;

		// 分别排序
		mergeSort(arr, l, m);
		mergeSort(arr, m + 1, r);

		// 合并
		merge(arr, l, m, r);
	}
}

// 打印数组的函数
void printArray(int A[], int size) {
	for (int i = 0; i < size; i++)
		std::cout << A[i] << " ";
	std::cout << "\n";
}

// 主函数
int main() {
	int arr[] = { 7, 2, 1, 6, 5, 4, 3, 8 };
	int arr_size = sizeof(arr) / sizeof(arr[0]);

	std::cout << "给定的数组 \n";
	printArray(arr, arr_size);

	mergeSort(arr, 0, arr_size - 1);

	std::cout << "\n排序后的数组 \n";
	printArray(arr, arr_size);
	return 0;
}
#include <iostream>

// 递归函数计算阶乘
unsigned long long factorial(int n) {
	if (n == 0) {
		return 1; // 0! = 1
	}
	else {
		return n * factorial(n - 1); // n! = n * (n-1)!
	}
}

int main() {
	int number = 10;
	unsigned long long result = factorial(number);
	std::cout << number << " != " << result << std::endl;
	return 0;
}
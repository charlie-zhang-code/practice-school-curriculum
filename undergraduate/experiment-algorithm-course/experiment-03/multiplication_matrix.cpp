#include <iostream>
#include <stdio.h>

void Matrixchain(int* p, int n, int** m, int** s) {
	int i, j, k, r, t;
	for (i = 1; i <= n; i++)
		for (j = 1; j <= i; j++) {
			m[i][j] = 0;
			s[i][j] = 0;
		}

	for (r = 2; r <= n; r++)
		for (i = 1; i <= n - r + 1; i++) {
			j = i + r - 1;
			m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];
			s[i][j] = i;
			for (k = i + 1; k < j; k++) {
				t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
				if (t < m[i][j]) {
					m[i][j] = t;
					s[i][j] = k;
				}
			}
		}
}

void Traceback(int** s, int i, int j) {
	if (i == j) {
		std::cout << "A" << i;
	}
	else {
		std::cout << "(";
		Traceback(s, i, s[i][j]);
		Traceback(s, s[i][j] + 1, j);
		std::cout << ")";
	}
}

int main() {
	int n, i, j;
	int* p;
	int** m, ** s;

	n = 7;
	p = new int[n + 1];
	p[0] = 20;
	p[1] = 25;
	p[2] = 18;
	p[3] = 20;
	p[4] = 26;
	p[5] = 25;
	p[6] = 45;
	p[7] = 21;

	m = new int* [n];
	s = new int* [n];
	for (i = 0; i <= n; i++) {
		m[i] = new int[n];
		s[i] = new int[n];
	}

	std::cout << "连乘矩阵最优计算次序是：";
	Matrixchain(p, n, m, s);
	Traceback(s, 1, n);
	std::cout << std::endl;

	std::cout << "次序矩阵s为：" << std::endl;
	std::cout << "坐标" << '\t';
	for (j = 1; j <= n; j++)
		std::cout << "j" << j << '\t';
	std::cout << std::endl;

	for (i = 1; i <= n; i++) {
		std::cout << "i" << i << '\t';
		for (j = 1; j <= n; j++)
			std::cout << s[i][j] << '\t';
		std::cout << std::endl;
	}
	std::cout << std::endl;

	std::cout << "最优解矩阵m为：" << std::endl;
	std::cout << "坐标" << '\t';
	for (j = 1; j <= n; j++)
		std::cout << "j" << j << '\t';
	std::cout << std::endl;

	for (i = 1; i <= n; i++) {
		std::cout << "i" << i << '\t';
		for (j = 1; j <= n; j++)
			std::cout << m[i][j] << '\t';
		std::cout << std::endl;
	}

	// 释放动态分配的内存
	for (i = 0; i <= n; i++) {
		delete[] m[i];
		delete[] s[i];
	}
	delete[] m;
	delete[] s;
	delete[] p;

	return 0;
}
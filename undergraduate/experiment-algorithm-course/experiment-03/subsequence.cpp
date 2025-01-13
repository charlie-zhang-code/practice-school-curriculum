#include <iostream>

void LCSLength(int m, int n, char* x, char* y, int** c, int** b) {
	for (int i = 1; i <= m; i++)
		c[i][0] = 0;
	for (int j = 0; j <= n; j++)
		c[0][j] = 0;

	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) {
			if (x[i] == y[j]) {
				c[i][j] = c[i - 1][j - 1] + 1;
				b[i][j] = 1;
			}
			else if (c[i - 1][j] >= c[i][j - 1]) {
				c[i][j] = c[i - 1][j];
				b[i][j] = 2;
			}
			else {
				c[i][j] = c[i][j - 1];
				b[i][j] = 3;
			}
		}
	}
}

void LCS(int i, int j, char* x, int** b) {
	if (i == 0 || j == 0) {
		return;
	}
	if (b[i][j] == 1) {
		LCS(i - 1, j - 1, x, b);
		std::cout << x[i];
	}
	else if (b[i][j] == 2) {
		LCS(i - 1, j, x, b);
	}
	else {
		LCS(i, j - 1, x, b);
	}
}

void output(char* s, int n) {
	for (int i = 1; i <= n; i++) {
		std::cout << s[i] << " ";
	}
	std::cout << std::endl;
}

int main() {
	char x[] = { ' ', 'A', 'B', 'C', 'B', 'D', 'A', 'B' };
	char y[] = { ' ', 'B', 'D', 'C', 'A', 'B', 'A' };
	const int M = 7;
	const int N = 6;

	int** c = new int* [M + 1];
	int** b = new int* [M + 1];

	for (int i = 0; i <= M; i++) {
		c[i] = new int[N + 1];
		b[i] = new int[N + 1];
	}

	std::cout << "序列X：" << std::endl;
	output(x, M);
	std::cout << "序列Y：" << std::endl;
	output(y, N);

	LCSLength(M, N, x, y, c, b);

	std::cout << "序列X、Y最长公共子序列长度为：" << c[M][N] << std::endl;
	std::cout << "序列X、Y最长公共子序列为：";
	LCS(M, N, x, b);
	std::cout << std::endl;

	for (int i = 0; i <= M; i++) {
		delete[] c[i];
		delete[] b[i];
	}
	delete[] c;
	delete[] b;

	return 0;
}
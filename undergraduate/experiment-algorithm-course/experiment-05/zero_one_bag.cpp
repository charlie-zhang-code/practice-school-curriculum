#include <iostream>
#include <vector>

int n;
int c;
std::vector<int> w;
std::vector<int> v;
double cp = 0;
int cw = 0;
double bestp = 0;
std::vector<int> bestx;
std::vector<int> cx;

double Bound(int i) {
	double leftw = c - cw;
	double b = cp;
	while (i <= n && w[i] <= leftw) {
		leftw -= w[i];
		b += v[i];
		i++;
	}
	if (i <= n) {
		b += v[i] / (double)w[i] * leftw;
	}
	return b;
}

void Backtrack(int i) {
	if (i > n) {
		if (cp > bestp) {
			bestp = cp;
			bestx = cx;
		}
		return;
	}
	if (cw + w[i] <= c) {
		cx[i] = 1;
		cw += w[i];
		cp += v[i];
		Backtrack(i + 1)+;
		cw -= w[i];
		cp -= v[i];
	}
	cx[i] = 0;
	if (Bound(i + 1) > bestp) {
		Backtrack(i + 1);
	}
}

int main() {
	n = 5;
	c = 7;
	w = { 0, 2, 3, 5, 2, 4 };
	v = { 0, 6, 8, 10, 3, 7 };
	cx.resize(n + 1, 0);
	bestx.resize(n + 1, 0);

	Backtrack(1);

	std::cout << "背包的获得的最大价值: " << bestp << std::endl;
	std::cout << "所装的物品: ";
	for (int i = 1; i <= n; i++) {
		if (bestx[i] == 1) {
			std::cout << i << " ";
		}
	}
	std::cout << std::endl;

	return 0;
}
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>

struct Item {
	int weight;
	int value;
};

struct Node {
	int level;
	int weight;
	int value;
	int bound;
	bool selected[3];
};

struct Compare {
	bool operator()(Node a, Node b) {
		return a.bound < b.bound;
	}
};

int bound(Node u, int n, int W, std::vector<Item>& items) {
	if (u.weight >= W) return 0;
	int w = u.weight;
	int v = u.value;
	int j = u.level + 1;
	while ((j < n) && (w + items[j].weight <= W)) {
		w += items[j].weight;
		v += items[j].value;
		j++;
	}
	if (j < n) {
		v += (W - w) * items[j].value / items[j].weight;
	}
	return v;
}

int knapsack(int W, std::vector<Item>& items, bool selected[]) {
	std::priority_queue<Node, std::vector<Node>, Compare> Q;
	Node u, v;
	int n = items.size();
	int maxv = 0;

	memset(u.selected, 0, sizeof(u.selected));
	u.level = -1;
	u.weight = 0;
	u.value = 0;
	u.bound = bound(u, n, W, items);
	Q.push(u);

	while (!Q.empty()) {
		u = Q.top();
		Q.pop();
		if (u.level == n - 1) continue;

		int j = u.level + 1;

		// 选择第j个物品
		memcpy(v.selected, u.selected, sizeof(v.selected));
		v.selected[j] = true;
		v.level = u.level + 1;
		v.weight = u.weight + items[j].weight;
		v.value = u.value + items[j].value;
		if (v.weight <= W && v.value > maxv) {
			maxv = v.value;
			memcpy(selected, v.selected, sizeof(v.selected));
		}
		v.bound = bound(v, n, W, items);
		if (v.bound > maxv) Q.push(v);

		memcpy(v.selected, u.selected, sizeof(v.selected));
		v.level = u.level + 1;
		v.weight = u.weight;
		v.value = u.value;
		v.bound = bound(v, n, W, items);
		if (v.bound > maxv) Q.push(v);
	}

	return maxv;
}

int main() {
	std::vector<Item> items = { {16, 45}, {15, 25}, {15, 25} };
	int W = 30;
	bool selected[3] = { false };
	int max_value = knapsack(W, items, selected);
	std::cout << "背包的获得的最大价值：" << max_value << std::endl;
	std::cout << "装入的物品编号：";
	for (int i = 0; i < 3; ++i) {
		if (selected[i]) {
			std::cout << i + 1 << " ";
		}
	}
	std::cout << std::endl;
	return 0;
}
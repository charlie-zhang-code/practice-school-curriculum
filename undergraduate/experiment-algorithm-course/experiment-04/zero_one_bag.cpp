#include <iostream>
#include <vector>
#include <algorithm>

struct Item {
	int value;
	int weight;
};

bool cmp(Item a, Item b) {
	double r1 = static_cast<double>(a.value) / a.weight;
	double r2 = static_cast<double>(b.value) / b.weight;
	return r1 > r2;
}

double fractionalKnapsack(int W, std::vector<Item>& items, std::vector<double>& itemFraction) {
	std::sort(items.begin(), items.end(), cmp);
	int curWeight = 0;
	double finalValue = 0.0;

	for (int i = 0; i < items.size(); i++) {
		if (curWeight + items[i].weight <= W) {
			curWeight += items[i].weight;
			finalValue += items[i].value;
			itemFraction[i] = 1.0;
		}
		else {
			int remain = W - curWeight;
			finalValue += items[i].value * (static_cast<double>(remain) / items[i].weight);
			itemFraction[i] = static_cast<double>(remain) / items[i].weight;
			break;
		}
	}

	return finalValue;
}

int main() {
	int W = 5;
	std::vector<Item> items = { {3, 2}, {6, 3}, {9, 4}, {7, 2} };
	std::vector<double> itemFraction(items.size());

	double maxValue = fractionalKnapsack(W, items, itemFraction);

	std::cout << "背包中的最大价值 " << maxValue << std::endl;
	for (int i = 0; i < items.size(); i++) {
		if (itemFraction[i] > 0) {
			std::cout << "物品索引 " << i << " - 分割比例 " << itemFraction[i] << std::endl;
		}
	}

	return 0;
}
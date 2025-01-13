#include <iostream>
#include <cmath>
#include <iomanip>

int tile = 1; // L型骨牌号初始值为1
int Board[100][100];

void chess_board(int tr, int tc, int dr, int dc, int size);

int main() {
	int size, r, c, row, col, k;
	std::cout << "请输入k值（棋盘的行和列数为2^k）:";
	std::cin >> k;
	size = static_cast<int>(std::pow(2, k)); // 2的k次方
	std::cout << "请输入特殊方格的位置行，列:";
	std::cin >> row >> col;
	chess_board(0, 0, row, col, size);

	for (r = 0; r < size; r++) {
		for (c = 0; c < size; c++) {
			std::cout << std::setw(3) << Board[r][c];
		}
		std::cout << std::endl;
	}

	return 0;
}

void chess_board(int tr, int tc, int dr, int dc, int size) {
	if (size == 1) return;
	int s = size / 2;
	static int t = 0;

	if (dr < tr + s && dc < tc + s) {
		chess_board(tr, tc, dr, dc, s);
	}
	else {
		Board[tr + s - 1][tc + s - 1] = ++t;
		chess_board(tr, tc, tr + s - 1, tc + s - 1, s);
	}

	if (dr < tr + s && dc >= tc + s) {
		chess_board(tr, tc + s, dr, dc, s);
	}
	else {
		Board[tr + s - 1][tc + s] = ++t;
		chess_board(tr, tc + s, tr + s - 1, tc + s, s);
	}

	if (dr >= tr + s && dc < tc + s) {
		chess_board(tr + s, tc, dr, dc, s);
	}
	else {
		Board[tr + s][tc + s - 1] = ++t;
		chess_board(tr + s, tc, tr + s, tc + s - 1, s);
	}

	if (dr >= tr + s && dc >= tc + s) {
		chess_board(tr + s, tc + s, dr, dc, s);
	}
	else {
		Board[tr + s][tc + s] = ++t;
		chess_board(tr + s, tc + s, tr + s, tc + s, s);
	}
}
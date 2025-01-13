#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h> //导入头文件winsock2.h

#define BUF_SIZE 1024
#define RLT_SIZE 4
#define OPSZ 4
void ErrorHandling(const char *message);

int main(int argc, char *argv[])
{
	WSADATA wsaData;
	SOCKET hSocket;
	char opmsg[BUF_SIZE];
	int opndCnt, result, i;
	SOCKADDR_IN servAdr;
	if (argc != 3)
	{
		printf("用法: %s <IP> <port>\n", argv[0]);
		exit(1);
	}

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) // 设置程序中用到的Winsock版本，并初始化相应版本的库
		ErrorHandling("WSAStartup() error!");

	hSocket = socket(PF_INET, SOCK_STREAM, 0);
	if (hSocket == INVALID_SOCKET)
		ErrorHandling("socket() error");

	memset(&servAdr, 0, sizeof(servAdr));
	servAdr.sin_family = AF_INET;
	servAdr.sin_addr.s_addr = inet_addr(argv[1]);
	servAdr.sin_port = htons(atoi(argv[2]));

	if (connect(hSocket, (SOCKADDR *)&servAdr, sizeof(servAdr)) == SOCKET_ERROR)
		ErrorHandling("connect() error!");
	else
		puts("已连接...........");

	fputs("操作数的个数: ", stdout);
	scanf("%d", &opndCnt);
	opmsg[0] = (char)opndCnt;

	for (i = 0; i < opndCnt; i++)
	{
		printf("操作数 %d: ", i + 1);
		scanf("%d", (int *)&opmsg[i * OPSZ + 1]);
	}
	fgetc(stdin);
	fputs("运算符（+、-、*）: ", stdout);
	scanf("%c", &opmsg[opndCnt * OPSZ + 1]);
	send(hSocket, opmsg, opndCnt * OPSZ + 2, 0);
	recv(hSocket, (char *)&result, RLT_SIZE, 0);

	printf("运算结果: %d \n", result);
	closesocket(hSocket); // 客户端关闭连接
	WSACleanup();
	return 0;
}

void ErrorHandling(const char *message)
{
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}
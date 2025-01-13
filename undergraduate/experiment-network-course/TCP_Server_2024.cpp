#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h> //导入TCP/IP的库函数

#define BUF_SIZE 1024
#define OPSZ 4
void ErrorHandling(const char *message);
int calculate(int opnum, int opnds[], char oprator);

int main(int argc, char *argv[])
{
	WSADATA wsaData;
	SOCKET hServSock, hClntSock;
	char opinfo[BUF_SIZE];
	int result, i;
	int recvCnt, opndCnt, recvLen;
	SOCKADDR_IN servAdr, clntAdr;
	int clntAdrSize;
	if (argc != 2)
	{
		printf("用法: %s <port>\n", argv[0]);
		exit(1);
	}

	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) // 设置程序中用到的Winsock版本，并初始化相应版本的库
		ErrorHandling("WSAStartup() error!");

	hServSock = socket(PF_INET, SOCK_STREAM, 0);
	if (hServSock == INVALID_SOCKET)
		ErrorHandling("socket() error");

	memset(&servAdr, 0, sizeof(servAdr));
	servAdr.sin_family = AF_INET;
	servAdr.sin_addr.s_addr = htonl(INADDR_ANY);
	servAdr.sin_port = htons(atoi(argv[1]));

	if (bind(hServSock, (SOCKADDR *)&servAdr, sizeof(servAdr)) == SOCKET_ERROR) // 调用bind函数绑定本地端口号和本地IP地址，服务器端主进程准备就绪
		ErrorHandling("bind() error");
	if (listen(hServSock, 5) == SOCKET_ERROR) // 服务器端主进程侦听是否有客户端连接，如果有，就开启从属进程向对应的客户端提供服务
		ErrorHandling("listen() error");
	clntAdrSize = sizeof(clntAdr);

	for (i = 0; i < 5; i++)
	{
		opndCnt = 0;
		hClntSock = accept(hServSock, (SOCKADDR *)&clntAdr, &clntAdrSize);
		recv(hClntSock, (char *)&opndCnt, 1, 0); // 接收客户端发来的第1个数字

		recvLen = 0;
		while ((opndCnt * OPSZ + 1) > recvLen) // 接收客户端发来的第2个之后的数字
		{
			recvCnt = recv(hClntSock, &opinfo[recvLen], BUF_SIZE - 1, 0);
			recvLen += recvCnt;
		}
		result = calculate(opndCnt, (int *)opinfo, opinfo[recvLen - 1]); // 计算
		send(hClntSock, (char *)&result, sizeof(result), 0);			 // 将计算结果发送给对应的客户端
		closesocket(hClntSock);											 // 关闭服务器端从属进程的连接
	}
	closesocket(hServSock); // 关闭服务器端主进程的连接
	WSACleanup();
	return 0;
}

int calculate(int opnum, int opnds[], char op)
{
	int result = opnds[0], i;

	switch (op)
	{
	case '+':
		for (i = 1; i < opnum; i++)
			result += opnds[i];
		break;
	case '-':
		for (i = 1; i < opnum; i++)
			result -= opnds[i];
		break;
	case '*':
		for (i = 1; i < opnum; i++)
			result *= opnds[i];
		break;
	}
	return result;
}

void ErrorHandling(const char *message)
{
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}
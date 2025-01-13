#define _CRT_SECURE_NO_WARNINGS
#define _WINSOCK_DEPRECATED_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h> //导入头文件winsock2.h,才可以在程序中调用socket、bind、listen等函数。

#define BUF_SIZE 30
void ErrorHandling(const char *message);

int main(int argc, char *argv[])
{
	WSADATA wsaData;
	SOCKET sock;
	char message[BUF_SIZE];
	int strLen;

	SOCKADDR_IN servAdr;
	if (argc != 3)
	{
		printf("用法: %s <IP> <port>\n", argv[0]);
		exit(1);
	}
	if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) // 调用WSAStartup函数，设置程序中用到的Winsock版本，并初始化相应版本的库
		ErrorHandling("WSAStartup() error!");

	sock = socket(PF_INET, SOCK_DGRAM, 0); // 创建UDP套接字，socket函数第一个参数表示选择IPv4协议，第二个参数表示支持UDP
	if (sock == INVALID_SOCKET)
		ErrorHandling("socket() error");

	memset(&servAdr, 0, sizeof(servAdr));
	servAdr.sin_family = AF_INET;
	servAdr.sin_addr.s_addr = inet_addr(argv[1]);
	servAdr.sin_port = htons(atoi(argv[2]));

	connect(sock, (SOCKADDR *)&servAdr, sizeof(servAdr)); // 与服务器建立连接

	while (1)
	{
		fputs("发送消息给服务器(按q退出):", stdout);
		fgets(message, sizeof(message), stdin);
		if (!strcmp(message, "q\n") || !strcmp(message, "Q\n")) // 输入字符q或Q退出程序
			break;

		send(sock, message, strlen(message), 0);			  // 向服务器发送数据
		strLen = recv(sock, message, sizeof(message) - 1, 0); // 接收服务器发来的数据

		message[strLen] = 0;
		printf("从服务器发来消息: %s", message);
	}
	closesocket(sock); // 关闭套接字
	WSACleanup();
	return 0;
}

void ErrorHandling(const char *message)
{
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}

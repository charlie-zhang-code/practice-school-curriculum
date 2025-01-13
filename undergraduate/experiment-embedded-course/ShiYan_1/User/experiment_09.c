#include "stm32f10x.h" 
#include <stdio.h>

// º¯ÊýÉùÃ÷
void Delay_MS(u16 dly);
void RCC_Configuration(void);
void GPIO_Configuration(void);
void USART1_Configuration(void);

// ÖØ¶¨Òåfputcº¯Êý¡£Ê¹µÃprintfº¯ÊýÄÜÍ¨¹ýUSART1·¢ËÍÊý¾Ý
int fputc(int ch, FILE *f)
{
    // ½«×Ö·ûch·¢ËÍ¸øUSART1
    USART_SendData(USART1, ch);
    // µÈ´ý·¢ËÍÍê±Ï
    while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET);
    // ·µ»Ø·¢ËÍµÄ×Ö·û
    return (ch);
}

int main(void)
{
    u8 i;
    u8 data[] = "ÎÂ¶ÈÖµ£º14.5¡ãC";  // Òª·¢ËÍµÄ×Ö·û´®

    // ³õÊ¼»¯
    RCC_Configuration();
    GPIO_Configuration();
    USART1_Configuration();

    while (1)
    {
        for (i = 0; i < sizeof(data) - 1; i++)  // ?????????????,????1?????????'\0'
        {
            USART_SendData(USART1, data[i]);
            while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET);
        }
        Delay_MS(2000);  // ????,??????,?????????????
    }
}

// ÑÓÊ±º¯Êý£¬ÊµÏÖ¼òµ¥µÄºÁÃë¼¶ÑÓÊ±
void Delay_MS(u16 dly)
{
    u16 i, j;
    for (i = 0; i < dly; i++)
        for (j = 1000; j > 0; j--);
}

// Ê±ÖÓ
void RCC_Configuration(void)
{
    // ??RCC?????????
    RCC_DeInit();
    //¿ªÆô¸ßËÙÍâ²¿Ê±ÖÓ(HSE)
    RCC_HSEConfig(RCC_HSE_ON);
    // µÈ´ýhse×¼±¸¾ÍÐ÷
    while (RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET);
    // ???????
    FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);
    // ÉèÖÃFlashµÈ´ýÖÜÆÚÎª2¸ö×´Ì¬
    FLASH_SetLatency(FLASH_Latency_2);
    // ÅäÖÃAHBÊ±ÖÓ(HCLK)µÈÓÚ(SYSCLK)
    RCC_HCLKConfig(RCC_SYSCLK_Div1);
    // ÅäÖÃAPB2Ê±ÖÓ(PCLK2)µÈÓÚHCLK
    RCC_PCLK2Config(RCC_HCLK_Div1);
    // ÅäÖÃAPB1Ê±ÖÓ(PCLK1)µÈÓÚHCLK/2
    RCC_PCLK1Config(RCC_HCLK_Div2);
    // ÅäÖÃPLLÊ±ÖÓÔ´¼°±¶ÆµÏµÊý,PLLCLK = 8MHZ * 9 = 72MHZ
    RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);
    // ÅäÖÃPLLÊ±ÖÓ
    RCC_PLLCmd(ENABLE);
    // µÈ´ýPLLÊ±ÖÓµÈ´ý¾ÍÐ÷
    while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET);
    // Ñ¡ÔñPLL×÷ÎªÏµÍ³Ê±ÖÓÔ´
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);
    //µÈ´ýPLLÈ·Êµ±»µ±×÷ÏµÍ³Ê±ÖÓÔ´
    while (RCC_GetSYSCLKSource()!= 0x08);

    // ¿ªÆô¶ÔÓ¦ÍâÉèÊ±ÖÓÊ¹ÄÜ
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_USART1, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_AFIO, ENABLE);
}

// GPIO
void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;

    // ÅäÖÃUSART1µÄTXÒý½Åp49Îª·þ¸´ÓÃÍÆÍìÊä³öÄ£Ê½£¬ËÙ¶ÈÎª50mhz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_9;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    // ÅäÖÃUSART1µÄRXÒý½Å(PA10)Îª¸¡¿ÕÊäÈë£¬ËÙ¶È50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_10;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IN_FLOATING;
    GPIO_Init(GPIOA, &GPIO_InitStructure);
}

// USART1ÅäÖÃº¯Êý£¬ÉèÖÃ²¨ÌØÂÊ£¬Êý¾ÝÎ»£¬Í£Ö¹Î»
void USART1_Configuration(void)
{
    USART_InitTypeDef USART_InitStructure;

    // ??USART1?????????
    USART_DeInit(USART1);

    // ÉèÖÃ²¨ÌØÂÊ9600
    USART_InitStructure.USART_BaudRate = 9600;
    // Êý¾Ý³¤¶ÈÎª8Î»
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;
    // Í£Ö¹Î»Îª1Î»
    USART_InitStructure.USART_StopBits = USART_StopBits_1;
    // ÉèÖÃÎÞÐ£ÑéÎ»
    USART_InitStructure.USART_Parity = USART_Parity_No;
    // ·¢ËÍÄ£Ê½
    USART_InitStructure.USART_Mode = USART_Mode_Tx;
    // ÎÞÓ²¼þÁ÷¿ØÖÆ
    USART_InitStructure.USART_HardwareFlowControl = USART_HardwareFlowControl_None;

    // °´ÕÕÉÏÊöÅäÖÃ³õÊ¼»¯USART1
    USART_Init(USART1, &USART_InitStructure);
    // Ê¹ÄÜUSART1
    USART_Cmd(USART1, ENABLE);
}
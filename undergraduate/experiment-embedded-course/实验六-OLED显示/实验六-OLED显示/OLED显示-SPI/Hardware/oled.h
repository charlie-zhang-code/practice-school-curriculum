#ifndef __OLED_H
#define __OLED_H 
#include "stm32f10x.h"

/**********************************
											STM32
 * �ļ�			:	OLED��ʾ��(7��SPI)h�ļ�                     
 * MCU			:	STM32F103RC
 * �ӿ�			:	������							

**********************BEGIN***********************/

//----------------OLED�˿ڶ���----------------- 
/***************�����Լ��������****************/
#define OLED_SCL_GPIO_PORT			GPIOC
#define OLED_SCL_GPIO_PIN				GPIO_Pin_13

#define OLED_SDA_GPIO_PORT			GPIOC
#define OLED_SDA_GPIO_PIN				GPIO_Pin_0

#define OLED_RST_GPIO_PORT			GPIOC
#define OLED_RST_GPIO_PIN				GPIO_Pin_1

#define OLED_DC_GPIO_PORT				GPIOC
#define OLED_DC_GPIO_PIN				GPIO_Pin_2

#define OLED_CS_GPIO_PORT				GPIOC
#define OLED_CS_GPIO_PIN				GPIO_Pin_3

/*********************END**********************/


#define OLED_SCL_Clr() GPIO_ResetBits(OLED_SCL_GPIO_PORT,OLED_SCL_GPIO_PIN)
#define OLED_SCL_Set() GPIO_SetBits(OLED_SCL_GPIO_PORT,OLED_SCL_GPIO_PIN)

#define OLED_SDA_Clr() GPIO_ResetBits(OLED_SDA_GPIO_PORT,OLED_SDA_GPIO_PIN)//DIN
#define OLED_SDA_Set() GPIO_SetBits(OLED_SDA_GPIO_PORT,OLED_SDA_GPIO_PIN)

#define OLED_RST_Clr() GPIO_ResetBits(OLED_RST_GPIO_PORT,OLED_RST_GPIO_PIN)//RES
#define OLED_RST_Set() GPIO_SetBits(OLED_RST_GPIO_PORT,OLED_RST_GPIO_PIN)

#define OLED_DC_Clr() GPIO_ResetBits(OLED_DC_GPIO_PORT,OLED_DC_GPIO_PIN)//DC
#define OLED_DC_Set() GPIO_SetBits(OLED_DC_GPIO_PORT,OLED_DC_GPIO_PIN)
 		     
#define OLED_CS_Clr()  GPIO_ResetBits(OLED_CS_GPIO_PORT,OLED_CS_GPIO_PIN)//CS
#define OLED_CS_Set()  GPIO_SetBits(OLED_CS_GPIO_PORT,OLED_CS_GPIO_PIN)

#define OLED_CMD  0	//д����
#define OLED_DATA 1	//д����
#define u8 unsigned char
#define u32 unsigned int

void OLED_ClearPoint(u8 x,u8 y);
void OLED_ColorTurn(u8 i);
void OLED_DisplayTurn(u8 i);
void OLED_WR_Byte(u8 dat,u8 cmd);
void OLED_DisPlay_On(void);
void OLED_DisPlay_Off(void);
void OLED_Refresh(void);
void OLED_Clear(void);
void OLED_DrawPoint(u8 x,u8 y);
void OLED_DrawLine(u8 x1,u8 y1,u8 x2,u8 y2);
void OLED_DrawCircle(u8 x,u8 y,u8 r);
void OLED_ShowChar(u8 x,u8 y,u8 chr,u8 size1);
void OLED_ShowString(u8 x,u8 y,u8 *chr,u8 size1);
void OLED_ShowNum(u8 x,u8 y,u32 num,u8 len,u8 size1);
void OLED_ShowChinese(u8 x,u8 y,u8 num,u8 size1);
void OLED_ScrollDisplay(u8 num,u8 space);
void OLED_WR_BP(u8 x,u8 y);
void OLED_ShowPicture(u8 x0,u8 y0,u8 x1,u8 y1,u8 BMP[]);
void OLED_Init(void);

#endif


#include "stm32f10x.h"
#include "oled.h"
#include "delay.h"
#include "bmp.h"

/***********************************
											STM32
 * 项目			:	OLED显示屏(7针SPI)实验                     
 * 日期			: 2024.11.26
 * MCU			:	STM32F103RC
 * 接口			:	参看oled.h							

**********************BEGIN***********************/

int main(void)
{
	//SystemInit();//配置系统时钟为72M	
	delay_init(72);
	OLED_Init();

	//OLED_ShowChinese(u8 x,u8 y,u8 num,u8 size1) 
	//x,y:起点坐标 num:汉字对应的序号 
	OLED_ShowChinese(0,0,0,16); 	//你
	OLED_ShowChinese(16,0,1,16);	//好
	OLED_ShowChinese(32,0,2,16); 	//杨
	OLED_ShowChinese(48,0,3,16);	//毅
	OLED_ShowChinese(64,0,4,16);	//毅
	OLED_ShowChinese(80,0,5,16);	//毅
	OLED_ShowChinese(96,0,6,16);	//毅
	
	
	OLED_ShowChinese(0,16,7,16); 	//你
	OLED_ShowChinese(16,16,8,16);	//好
	OLED_ShowChinese(32,16,9,16); 	//杨
	OLED_ShowChinese(48,16,10,16);	//毅
	OLED_ShowChinese(64,16,11,16);	//毅
	OLED_ShowChinese(80,16,12,16);	//毅
	OLED_ShowChinese(96,16,13,16);	//毅
	OLED_ShowChinese(112,16,14,16);	//毅
	
	//OLED_ShowChinese(0,32,0,16); 	//你
	//OLED_ShowChinese(16,32,1,16);	//好
	//OLED_ShowChinese(32,32,2,16); 	//杨
	
	OLED_ShowChinese(0,32,15,16); 	//姓
	OLED_ShowChinese(16,32,16,16);	//名
	//OLED_ShowChinese(32,32,25,16);	//:
	OLED_ShowChar(32,32,':', 16);

	OLED_ShowChinese(48,32,20,16);	//c
	OLED_ShowChinese(64,32,21,16);	//z
	OLED_ShowChinese(80,32,23,16);	//j
	OLED_ShowChinese(96,32,24,16);	//h
	//OLED_ShowChinese(112,32,24,16);	//h

	OLED_ShowChinese(0,48,17,16); 	//x
	OLED_ShowChinese(16,48,18,16);	//h
	OLED_ShowChar(32,48,':', 16);
	OLED_ShowNum(48,48,2022402083,10,16);
	

	
	//OLED_ShowString(u8 x,u8 y,u8 *chr,u8 size1);
	//x,y:起点坐标 size1:字体大小 *chr:字符串起始地址 
	//OLED_ShowString(0,32,"CC",16);
		
		
	//OLED_ShowNum(u8 x,u8 y,u32 num,u8 len,u8 size1);
	//x,y :起点坐标 num :要显示的数字 len :数字的位数 size:字体大小 
	//OLED_ShowNum(0,32,2022402083,10,16);
	
	
	//OLED_ShowPicture(u8 x,u8 y,u8 sizex,u8 sizey,u8 BMP[])
	//x,y：起点坐标 sizex,sizey,图片长宽 BMP[]：要写入的图片数组
	//OLED_ShowPicture(0,0,128,8,BMP5);
	//delay_ms(1000);
}


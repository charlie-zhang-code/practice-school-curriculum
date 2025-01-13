#include "stm32f10x.h"
#include "oled.h"
#include "delay.h"
#include "bmp.h"
#include "adc.h"

/***********************************
											STM32
 * 项目:温度计--内部温度传感器采集温度数据，通过ADC1转换后OLED显示当前温度值
 * 日期:2024.11.27
 * MCU :STM32F103RC
 * 接口:参看oled.h

**********************BEGIN***********************/

int main(void)
{
	SystemInit(); // 配置系统时钟为72M
	delay_init(72);
	OLED_Init();
	ADC1_Init();

	OLED_ShowChinese(40, 0, 0, 16);
	OLED_ShowChinese(56, 0, 1, 16);
	OLED_ShowChinese(72, 0, 2, 16);

	OLED_ShowChinese(0, 16, 3, 16);
	OLED_ShowChinese(16, 16, 4, 16);
	OLED_ShowChinese(32, 16, 5, 16);
	OLED_ShowChinese(48, 16, 6, 16);
	OLED_ShowChinese(64, 16, 7, 16);
	OLED_ShowChinese(80, 16, 8, 16);

	OLED_ShowString(0, 32, "Temp", 16); // 温度
	OLED_ShowChar(32, 32, ':', 16);		// 冒号

	// 显示温度值的小数点
	while (1)
	{
		u16 tempZS = TempZS_Get(); // 假设这是整数部分
		u16 tempXS = TempXS_Get(); // 假设这是小数部分

		// 将温度值显示到OLED上
		OLED_ShowNum(40, 32, tempZS, 2, 16); // 十位整数部分
		OLED_ShowNum(64, 32, tempXS, 1, 16); // 小数部分

		// OLED_ShowNum(40, 32, TempZS_Get(), 2, 16);
		OLED_ShowChar(56, 32, '.', 16);
		// OLED_ShowNum(64, 32, TempXS_Get(), 1, 16);
		OLED_ShowChinese(80, 32, 9, 16); // 显示摄氏度
	}
}
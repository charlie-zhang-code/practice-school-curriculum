
#include "stm32f10x.h"
#include <stdio.h>
#include "oled.h"
#include "delay.h"
#include "bmp.h"
// #include "adc.h"
#include <string.h>
#include <stm32f10x_usart.h>
#include <stm32f10x_rcc.h>
#include <stm32f10x_flash.h>
#include <stm32f10x_gpio.h>

/*******************************************************************************************************************/
#include "stm32f10x.h"
#include <stm32f10x_adc.h>
#include <stm32f10x_rcc.h>
#include <misc.h>
#include <stdio.h>

u16 V_SENSOR; // 定义12位编码的变量
u16 Temp;     // 定义当前温度值变量
u16 TempZS, TempXS;

void ADC1_Init(void)
{
    ADC_InitTypeDef ADC_InitStructure;
    NVIC_InitTypeDef NVIC_InitStructure;
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE);
    RCC_ADCCLKConfig(RCC_PCLK2_Div6);
    ADC_DeInit(ADC1);

    // 添加ADC1的初始化程序
    /**************************************************************************/

    // ADC1的初始化程序
    ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;                  // 独立模式，不使用DMA
    ADC_InitStructure.ADC_ScanConvMode = DISABLE;                       //  单通道模式
    ADC_InitStructure.ADC_ContinuousConvMode = DISABLE;                 // 单次转换模式
    ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; // 不使用外部触发
    ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
    ADC_InitStructure.ADC_NbrOfChannel = 1;

    ADC_Init(ADC1, &ADC_InitStructure);
    ADC_RegularChannelConfig(ADC1, ADC_Channel_TempSensor, 1, ADC_SampleTime_55Cycles5);
    ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);

    /**************************************************************/
    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);   // ADC1的中断优先级设置
    NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn; // ADC1的中断通道
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);

    // 添加ADC1的初始化程序
    /**************************************************************/

    // todo

    // 使能温度传感器和参考电压
    ADC_TempSensorVrefintCmd(ENABLE);
    ADC_Cmd(ADC1, ENABLE); // 使能ADC1，启动ADC

    /***************************************************************/
    ADC_ResetCalibration(ADC1);                 // ADC自校准
    while (ADC_GetResetCalibrationStatus(ADC1)) // 等待校准完成
        ;
    ADC_StartCalibration(ADC1);            // 开始校准
    while (ADC_GetCalibrationStatus(ADC1)) // 等待校准完成
        ;
    ADC_SoftwareStartConvCmd(ADC1, ENABLE); // 启动ADC转换
}

// ADC1的中断服务程序
void ADC1_2_IRQHandler(void)
{
    /*************************************************************************/
    if (ADC_GetITStatus(ADC1, ADC_IT_EOC)) // 检查转换完成中断发生
    {
        V_SENSOR = ADC_GetConversionValue(ADC1); // 读取转换结果
        Temp = (25 + (1.43 - V_SENSOR * 3.3 / 4096) / 0.0043) * 10;
        ADC_ClearITPendingBit(ADC1, ADC_IT_EOC); // 清除中断标志
    }

    /*************************************************************************/
}

u16 TempZS_Get(void)
{
    TempZS = Temp / 10; // Temp除以10取整得到温度值的2位整数值
    return TempZS;
}

u16 TempXS_Get(void)
{
    TempXS = Temp % 10; // Temp求余得到温度值的1位小数值
    return TempXS;
}

/*****************************************************************************************************************/

// 实现一个简单的微秒级延时函数
void Delay_MS(u16 dly)
{
    u16 i, j;
    for (i = 0; i < dly; i++)
        for (j = 1000; j > 0; j--)
            ;
}

// 配置系统时钟
void RCC_Configuration(void)
{
    // 重置RCC设置到默认状态
    RCC_DeInit();
    // 开启高速外部时钟(HSE)
    RCC_HSEConfig(RCC_HSE_ON);
    // 等待HSE稳定
    while (RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET)
        ;
    // 启用Flash预取缓冲
    FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);
    // 设置Flash等待周期为2个周期
    FLASH_SetLatency(FLASH_Latency_2);
    // 配置AHB时钟(HCLK)等于SYSCLK
    RCC_HCLKConfig(RCC_SYSCLK_Div1);
    // 配置AHB时钟(HCLK)等于SYSCLK
    RCC_PCLK2Config(RCC_HCLK_Div1);
    // 配置APB1时钟(PCLK1)等于HCLK/2
    RCC_PCLK1Config(RCC_HCLK_Div2);
    // 配置PLL源及时钟倍频，PLLCLK = HSE * 9 = 72MHz
    RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);
    // 使能PLL
    RCC_PLLCmd(ENABLE);
    // 等待PLL稳定
    while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)
        ;
    // 设置PLL作为系统时钟源
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);
    // 等待系统时钟源切换完成，确认PLL被选作系统时钟源
    while (RCC_GetSYSCLKSource() != 0x08)
        ;

    // 开启必要的外设时钟
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_USART1, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_AFIO, ENABLE);
}

// GPIO
void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;

    // 配置USART1的TX引脚PA9为复用推挽输出模式，速度50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_9;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    // 配置USART1的RX引脚PA10为浮空输入模式，速度50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_10;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IN_FLOATING;
    GPIO_Init(GPIOA, &GPIO_InitStructure);
}

// USART1配置函数，设置波特率、数据位、停止位等参数
void USART1_Configuration(void)
{
    USART_InitTypeDef USART_InitStructure;

    // 初始化USART1
    USART_DeInit(USART1);

    // 配置USART1的波特率、数据位、停止位等参数，配置波特率为9600
    USART_InitStructure.USART_BaudRate = 9600;
    // 数据长度为8位
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;
    // 停止位为1位
    USART_InitStructure.USART_StopBits = USART_StopBits_1;
    // 无奇偶校验，不使用校验位
    USART_InitStructure.USART_Parity = USART_Parity_No;
    USART_InitStructure.USART_Mode = USART_Mode_Rx | USART_Mode_Tx; // 收发模式
    // 不使用硬件流控制
    // USART_InitStructure.USART_HardwareFlowControl = USART_HardwareFlowControl_None;

    // 初始化USART1
    USART_Init(USART1, &USART_InitStructure);
    // USART_ITConfig(USART1, USART_IT_RXNE, ENABLE); // 使能接收中断
    // 使能USART1
    USART_Cmd(USART1, ENABLE);
}

// 重定义fputc函数，使printf函数可以通过USART1发送数据
int fputc(int ch, FILE *f)
{
    // 将字符ch通过USART1发送
    USART_SendData(USART1, ch);
    // 等待发送完成
    while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET)
        ;
    // 返回发送的字符
    return (ch);
}

int main(void)
{
    SystemInit(); // 配置系统时钟为72M
    delay_init(72);
    OLED_Init();
    ADC1_Init();

    // 初始化
    RCC_Configuration();
    GPIO_Configuration();
    USART1_Configuration();

    u8 i;

    char temperatureBuffer[32]; // 用于存储温度值的字符串表示

    while (1)
    {
        // 显示温度值
        u16 tempZS = TempZS_Get(); // 整数部分
        u16 tempXS = TempXS_Get(); // 小数部分

        // 将温度值显示到OLED上
        OLED_ShowNum(40, 32, tempZS, 2, 16); // 十位整数部分
        OLED_ShowChar(56, 32, '.', 16);      // 显示小数点
        OLED_ShowNum(64, 32, tempXS, 1, 16); // 小数部分

        // 发送温度值到串口
        sprintf(temperatureBuffer, "Temperature: %d.%d\n", tempZS, tempXS);
        for (i = 0; i < strlen(temperatureBuffer); i++) // 发送字符串中的每一个字符
        {
            USART_SendData(USART1, temperatureBuffer[i]);

            while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET)
                ;
        }

        Delay_MS(2000);
    }
}

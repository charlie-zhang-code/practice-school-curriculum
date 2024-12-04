// 采用通用定时器的库函数编程实现：通过TIM3产生4路周期为1秒，占空比为10 %、 20 %、 30 %、 40 % 的PWM波。将4路PWM波分别加到直流电机上，看直流电机转动速度的变化。
// 参考程序：

#include "stm32f10x.h"       // 包含标准外设库头文件
#include "misc.h"            // 包含NVIC相关函数
#include <stm32f10x_tim.h>   // 包含定时器相关函数
#include <stm32f10x_rcc.h>   // 包含RCC相关函数
#include <stm32f10x_flash.h> // 包含FLASH相关函数
#include <stm32f10x_gpio.h>  // 包含GPIO相关函数

/* 自定义函数声明 */
void RCC_Configuration(void);
void GPIO_Configuration(void);
void TIM_Configuration(void);

int main(void)
{
    RCC_Configuration();
    GPIO_Configuration();
    TIM_Configuration();
    while (1)
        ;
}

void RCC_Configuration(void)
{
    ErrorStatus HSEStartUpStatus;
    RCC_DeInit();
    RCC_HSEConfig(RCC_HSE_ON);
    HSEStartUpStatus = RCC_WaitForHSEStartUp();
    if (HSEStartUpStatus == SUCCESS)
    {
        RCC_HCLKConfig(RCC_SYSCLK_Div1);
        RCC_PCLK2Config(RCC_HCLK_Div1);
        RCC_PCLK1Config(RCC_HCLK_Div2);
        FLASH_SetLatency(FLASH_Latency_2);
        FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);
        RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);
        RCC_PLLCmd(ENABLE);
        while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)
            ;
        RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);
        while (RCC_GetSYSCLKSource() != 0x08)
            ;
    }
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM3, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE);
}

void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_6 | GPIO_Pin_7;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_0 | GPIO_Pin_1;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_Init(GPIOB, &GPIO_InitStructure);
}

void TIM_Configuration(void)
{
    TIM_TimeBaseInitTypeDef TIM_TimeBaseStructure;
    TIM_OCInitTypeDef TIM_OCInitStructure;
    TIM_TimeBaseStructure.TIM_Period = 20000;
    TIM_TimeBaseStructure.TIM_Prescaler = 3600 - 1;
    TIM_TimeBaseStructure.TIM_ClockDivision = 0;
    TIM_TimeBaseStructure.TIM_CounterMode = TIM_CounterMode_Up;
    TIM_TimeBaseInit(TIM3, &TIM_TimeBaseStructure);
    TIM_OCInitStructure.TIM_OCMode = TIM_OCMode_PWM1;
    TIM_OCInitStructure.TIM_OutputState = TIM_OutputState_Enable;
    TIM_OCInitStructure.TIM_OCPolarity = TIM_OCPolarity_High;
    TIM_OCInitStructure.TIM_Pulse = 2000;
    TIM_OC1Init(TIM3, &TIM_OCInitStructure);
    TIM_OCInitStructure.TIM_Pulse = 4000;
    TIM_OC2Init(TIM3, &TIM_OCInitStructure);
    TIM_OCInitStructure.TIM_Pulse = 6000;
    TIM_OC3Init(TIM3, &TIM_OCInitStructure);
    TIM_OCInitStructure.TIM_Pulse = 8000;
    TIM_OC4Init(TIM3, &TIM_OCInitStructure);
    /* 使能预装载寄存器 */
    TIM_OC1PreloadConfig(TIM3, TIM_OCPreload_Enable);
    TIM_OC2PreloadConfig(TIM3, TIM_OCPreload_Enable);
    TIM_OC3PreloadConfig(TIM3, TIM_OCPreload_Enable);
    TIM_OC4PreloadConfig(TIM3, TIM_OCPreload_Enable);
    TIM_ARRPreloadConfig(TIM3, ENABLE);
    /* 启动 TIM 计数 */
    TIM_Cmd(TIM3, ENABLE);
}

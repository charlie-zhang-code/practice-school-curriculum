// 采用通用定时器的库函数编程实现：通过TIM4产生4路周期为2秒，占空比为20 %、 40 %、 60 %、 80 % 的PWM波。

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
    ErrorStatus HSEStartUpStatus;               // HSE启动状态
    RCC_DeInit();                               // 复位所有时钟
    RCC_HSEConfig(RCC_HSE_ON);                  // 打开HSE
    HSEStartUpStatus = RCC_WaitForHSEStartUp(); // 等待HSE启动
    if (HSEStartUpStatus == SUCCESS)            // 如果HSE启动成功
    {
        RCC_HCLKConfig(RCC_SYSCLK_Div1);                      // 系统时钟不分频
        RCC_PCLK2Config(RCC_HCLK_Div1);                       // APB2时钟不分频
        RCC_PCLK1Config(RCC_HCLK_Div2);                       // APB1时钟2分频
        FLASH_SetLatency(FLASH_Latency_2);                    // 设置FLASH延时
        FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable); // 使能FLASH预取指
        RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);  // 设置PLL时钟源为HSE，倍频系数为9
        RCC_PLLCmd(ENABLE);                                   // 使能PLL
        while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)   // 等待PLL启动
            ;
        RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK); // 设置PLL为系统时钟源
        while (RCC_GetSYSCLKSource() != 0x08)      // 等待系统时钟源切换成功
            ;
    }
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM4, ENABLE);  // 定时器4
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE); // 定时器4的通道1和通道2
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE); // 定时器4的通道3和通道4
}

void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;                   // GPIO初始化结构体
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_6 | GPIO_Pin_7 | GPIO_Pin_8 | GPIO_Pin_9; // 定时器4的通道1、通道2、通道3和通道4
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;        // 复用推挽输出
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;      // 速度50MHz
    GPIO_Init(GPIOB, &GPIO_InitStructure);                 // 初始化GPIO
    GPIO_Init(GPIOA, &GPIO_InitStructure);                 // 初始化GPIO

    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_0 | GPIO_Pin_1; // 定时器4的通道3和通道4
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;        // 复用推挽输出
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;      // 速度50MHz
    GPIO_Init(GPIOB, &GPIO_InitStructure);                 // 初始化GPIO
}

void TIM_Configuration(void)
{
    TIM_TimeBaseInitTypeDef TIM_TimeBaseStructure;                // 定时器基本初始化结构体
    TIM_OCInitTypeDef TIM_OCInitStructure;                        // 定时器输出比较初始化结构体
    TIM_TimeBaseStructure.TIM_Period = 20000;                     // 定时器周期
    TIM_TimeBaseStructure.TIM_Prescaler = 3600 - 1;               // 定时器预分频
    TIM_TimeBaseStructure.TIM_ClockDivision = 0;                  // 定时器时钟分频
    TIM_TimeBaseStructure.TIM_CounterMode = TIM_CounterMode_Up;   // 定时器计数模式
    TIM_TimeBaseInit(TIM4, &TIM_TimeBaseStructure);               // 初始化定时器
    TIM_OCInitStructure.TIM_OCMode = TIM_OCMode_PWM1;             // 定时器输出比较模式
    TIM_OCInitStructure.TIM_OutputState = TIM_OutputState_Enable; // 定时器输出比较使能
    TIM_OCInitStructure.TIM_OCPolarity = TIM_OCPolarity_High;     // 定时器输出比较极性
    TIM_OCInitStructure.TIM_Pulse = 4000;                         // 定时器输出比较脉冲
    TIM_OC1Init(TIM4, &TIM_OCInitStructure);                      // 初始化定时器输出比较
    TIM_OCInitStructure.TIM_Pulse = 8000;                         // 定时器输出比较脉冲
    TIM_OC2Init(TIM4, &TIM_OCInitStructure);                      // 初始化定时器输出比较
    TIM_OCInitStructure.TIM_Pulse = 12000;                         // 定时器输出比较脉冲
    TIM_OC3Init(TIM4, &TIM_OCInitStructure);                      // 初始化定时器输出比较
    TIM_OCInitStructure.TIM_Pulse = 16000;                         // 定时器输出比较脉冲
    TIM_OC4Init(TIM4, &TIM_OCInitStructure);                      // 初始化定时器输出比较

    /* 使能预装载寄存器 */
    TIM_OC1PreloadConfig(TIM4, TIM_OCPreload_Enable); // 使能 TIM4 的通道 1 预装载寄存器
    TIM_OC2PreloadConfig(TIM4, TIM_OCPreload_Enable); // 使能 TIM4 的通道 2 预装载寄存器
    TIM_OC3PreloadConfig(TIM4, TIM_OCPreload_Enable); // 使能 TIM4 的通道 3 预装载寄存器
    TIM_OC4PreloadConfig(TIM4, TIM_OCPreload_Enable); // 使能 TIM4 的通道 4 预装载寄存器
    TIM_ARRPreloadConfig(TIM4, ENABLE);               // 使能 TIM4 的自动重装载寄存器

    /* 启动 TIM 计数 */
    TIM_Cmd(TIM4, ENABLE); // 启动 TIM4 定时器
}

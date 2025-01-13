#include "stm32f10x.h"
#include <stdio.h>

void Delay_MS(u16 dly);
void RCC_Configuration(void);
void GPIO_Configuration(void);
void TIM2_Configuration(void);
void NVIC_Configuration(void);

int main(void)
{
    RCC_Configuration();  // 调用系统时钟配置函数
    GPIO_Configuration(); // 调用GPIO配置函数
    TIM2_Configuration(); // 调用定时器配置函数
    NVIC_Configuration(); // 调用中断配置函数
    while (1)
        ;
}

void Delay_MS(u16 dly)
{
    u16 i, j;
    for (i = 0; i < dly; i++)
        for (j = 1000; j > 0; j--)
            ;
}

void RCC_Configuration(void)
{
    RCC_DeInit();
    RCC_HSEConfig(RCC_HSE_ON);
    while (RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET)
        ;

    FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable); // 使能FLASH预取指
    FLASH_SetLatency(FLASH_Latency_2);                    // 设置FLASH延时为2个周期

    RCC_HCLKConfig(RCC_SYSCLK_Div1);                     // 设置HCLK = SYSCLK
    RCC_PCLK2Config(RCC_HCLK_Div1);                      // 设置PCLK2 = HCLK
    RCC_PCLK1Config(RCC_HCLK_Div2);                      // 设置PCLK1 = HCLK/2
    RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9); // 设置PLL时钟源为HSE，倍频系数为9，PLLCLK = 8MHZ * 9 =72MHZ
    RCC_PLLCmd(ENABLE);                                  // 使能PLL

    while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)
        ;                                      // 等待PLL启动
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK); // 选择PLL作为系统时钟源
    while (RCC_GetSYSCLKSource() != 0x08)
        ; // 等待PLL作为系统时钟源

    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE); // 使能GPIOA时钟
    RCC_APB1PeriphClockCmd(RCC_APB1Periph_TIM2, ENABLE); // 使能定时器2时钟
}

void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;              // 定义GPIO初始化结构体
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_All;       // 设置GPIOA的所有引脚
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;  // 设置GPIOA的模式为推挽输出
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz; // 设置GPIOA的速度为50MHz
    GPIO_Init(GPIOA, &GPIO_InitStructure);            // 初始化GPIOA
}

void TIM2_Configuration(void)
{
    TIM_TimeBaseInitTypeDef TIM_TimeBaseStructure; // 定义定时器初始化结构体
    TIM_DeInit(TIM2); // 复位定时器2
    TIM_InternalClockConfig(TIM2); // 选择内部时钟作为定时器2的时钟源
    TIM_Cmd(TIM2, ENABLE); // 使能定时器2
    TIM_TimeBaseStructure.TIM_Period = 10000; // 设置定时器2的自动重装载寄存器的值
    TIM_TimeBaseStructure.TIM_Prescaler = 7200 - 1; // 设置定时器2的预分频器的值
    TIM_TimeBaseStructure.TIM_ClockDivision = 0x0; // 设置定时器2的时钟分频系数
    TIM_TimeBaseStructure.TIM_CounterMode = TIM_CounterMode_Up; // 设置定时器2的计数模式为向上计数
    TIM_TimeBaseInit(TIM2, &TIM_TimeBaseStructure); // 初始化定时器2
    TIM_ITConfig(TIM2, TIM_IT_Update, ENABLE); // 使能定时器2的更新中断
    TIM_ClearITPendingBit(TIM2, TIM_IT_Update); // 清除定时器2的更新中断标志位
}

void NVIC_Configuration(void)
{
    NVIC_InitTypeDef NVIC_InitStructure;                      // 定义NVIC初始化结构体
    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_0);           // 设置NVIC优先级分组为0
    NVIC_InitStructure.NVIC_IRQChannel = TIM2_IRQn;           // 设置中断通道为定时器2的中断
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0; // 设置抢占优先级为0
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;        // 设置子优先级为0
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;           // 使能中断通道
    NVIC_Init(&NVIC_InitStructure);                           // 初始化NVIC
}

// 定时器TIM2的中断服务程序：
extern int sec = 0;

void TIM2_IRQHandler(void)
{
    if (TIM_GetITStatus(TIM2, TIM_IT_Update) == SET) // 判断是否是定时器2的更新中断
    {
        sec++; // 每次中断，秒数加1
        GPIO_Write(GPIOA, sec); // 将秒数写入GPIOA的输出数据寄存器，以控制LED灯的亮灭
        if (sec >= 59) // 如果秒数大于等于59
        {
            sec = 0; // 将秒数清零
        }
        TIM_ClearITPendingBit(TIM2, TIM_IT_Update); // 清除定时器2的更新中断标志位
    }
}
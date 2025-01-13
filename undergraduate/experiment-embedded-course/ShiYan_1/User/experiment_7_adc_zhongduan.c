/*Include---------------------------*/
#include "stm32f10x.h" //包含所有的头文件
#include <stdio.h>
//----------------函数声明--------------------
void Delay_MS(u16 dly);
void RCC_Configuration(void);
void GPIO_Configuration(void);
void ADC1_Configuration(void);
void NVIC_Configuration(void);

int main(void)
{
    RCC_Configuration();
    GPIO_Configuration();
    ADC1_Configuration();
    NVIC_Configuration();
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
    //----------使用外部RC晶振-----------
    RCC_DeInit();              // 初始化为缺省值
    RCC_HSEConfig(RCC_HSE_ON); // 使能外部的高速时钟
    while (RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET)
        ;                                                 // 等待外部高速时钟使能就绪
    FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable); // Enable Prefetch Buffer
    FLASH_SetLatency(FLASH_Latency_2);                    // Flash 2 wait state
    RCC_HCLKConfig(RCC_SYSCLK_Div1);                      // HCLK = SYSCLK
    RCC_PCLK2Config(RCC_HCLK_Div1);                       // PCLK2 =  HCLK
    RCC_PCLK1Config(RCC_HCLK_Div2);                       // PCLK1 = HCLK/2
    RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);
    RCC_PLLCmd(ENABLE); // Enable PLLCLK
    while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)
        ;                                      // Wait till PLLCLK is ready
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK); // Select PLL as system clock
    while (RCC_GetSYSCLKSource() != 0x08)
        ; // Wait till PLL is used as system clock source
    //---------打开相应外设时钟--------------------
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE); // 使能GPIOA的时钟
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE); // 使能ADC1的时钟
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_AFIO, ENABLE); // 使能GPIOA的复用功能时钟
}
void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_1;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AIN;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_All;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;
    GPIO_Init(GPIOB, &GPIO_InitStructure);
}

void ADC1_Configuration(void)
{
    ADC_InitTypeDef ADC_InitStructure;
    ADC_DeInit(ADC1);
    ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;
    ADC_InitStructure.ADC_ScanConvMode = DISABLE;
    ADC_InitStructure.ADC_ContinuousConvMode = DISABLE;
    ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None;
    ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
    ADC_InitStructure.ADC_NbrOfChannel = 1;
    ADC_Init(ADC1, &ADC_InitStructure);
    RCC_ADCCLKConfig(RCC_PCLK2_Div8);                                          // 设置ADC_CLK
    ADC_RegularChannelConfig(ADC1, ADC_Channel_1, 1, ADC_SampleTime_7Cycles5); // 设置ADC转换通道、转换顺序、采样时间
    ADC_ResetCalibration(ADC1);                                                // ADC校准
    while (ADC_GetResetCalibrationStatus(ADC1))
        ;
    ADC_StartCalibration(ADC1);
    while (ADC_GetCalibrationStatus(ADC1))
        ;
    ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);
    ADC_SoftwareStartConvCmd(ADC1, ENABLE);
    ADC_Cmd(ADC1, ENABLE);
}

void NVIC_Configuration(void)
{
    NVIC_InitTypeDef NVIC_InitStructure;
    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_0);
    NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);
}

// 在中断服务函数中读取转换结果
void ADC1_2_IRQHandler(void)
{
    u16 ADCdate;
    if (ADC_GetITStatus(ADC1, ADC_IT_EOC) == SET)
    {
        ADCdate = ADC_GetConversionValue(ADC1); // 读取 ADC1 的转换值
        GPIO_Write(GPIOB, ADCdate);
        Delay_MS(1000);
    }
    ADC_ClearITPendingBit(ADC1, ADC_IT_EOC);
}

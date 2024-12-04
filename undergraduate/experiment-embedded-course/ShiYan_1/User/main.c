#include "stm32f10x_gpio.h"
#include "stm32f10x_exti.h"
#include <stdio.h>

void Delay_MS(u16 dly);
void GPIO_Configuration(void);
void EXTI_Configuration(void);
void NVIC_Configuration(void);

int main(void)
{
    u8 i;

    // ʹ��GPIOA��GPIOB�͸��ù���ʱ��
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_AFIO, ENABLE);

    GPIO_Configuration();
    EXTI_Configuration();
    NVIC_Configuration();

    // ��ѭ��
    while (1)
    {
        for (i = 0; i < 8; i++)
        {
            GPIO_Write(GPIOA, ~(0x01 << i)); 
            Delay_MS(500);
        }
    }
}

void Delay_MS(u16 dly)
{
    u16 i, j;
    for (i = 0; i < dly; i++)
        for (j = 1000; j > 0; j--)
            ;
}

void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;

    // ����PA0-PA7Ϊ�������ģʽ���ٶ�Ϊ50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_0 | GPIO_Pin_1 | GPIO_Pin_2 | GPIO_Pin_3 | GPIO_Pin_4 | GPIO_Pin_5 | GPIO_Pin_6 | GPIO_Pin_7;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_Out_PP;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    // ����PB8Ϊ��������ģʽ��PB0Ϊ��������ģʽ
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_8 | GPIO_Pin_0;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IPU;
    GPIO_Init(GPIOB, &GPIO_InitStructure);
}

void EXTI_Configuration(void)
{
    EXTI_InitTypeDef EXTI_InitStructure;

    // ����PB8Ϊ��������ģʽ��PB0Ϊ��������ģʽ
    GPIO_EXTILineConfig(GPIO_PortSourceGPIOB, GPIO_PinSource8);
    GPIO_EXTILineConfig(GPIO_PortSourceGPIOB, GPIO_PinSource0);

    // ����EXTI8��PB8��Ϊ�ж�ģʽ���½��ش�������ʹ��
    EXTI_InitStructure.EXTI_Line = EXTI_Line8;
    EXTI_InitStructure.EXTI_Mode = EXTI_Mode_Interrupt;
    EXTI_InitStructure.EXTI_Trigger = EXTI_Trigger_Falling;
    EXTI_InitStructure.EXTI_LineCmd = ENABLE;
    EXTI_Init(&EXTI_InitStructure);

    // ����EXTI0��PB0��Ϊ�ж�ģʽ
    EXTI_InitStructure.EXTI_Line = EXTI_Line0;
    EXTI_Init(&EXTI_InitStructure);
}

// �ж����ȼ����ú���
void NVIC_Configuration(void)
{
    NVIC_InitTypeDef NVIC_InitStructure;

    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);
    NVIC_InitStructure.NVIC_IRQChannel = EXTI9_5_IRQn;
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 1;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);

    NVIC_InitStructure.NVIC_IRQChannel = EXTI0_IRQn; // ����EXTI0�ж�ͨ��
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 1;
    NVIC_Init(&NVIC_InitStructure);
}

void EXTI9_5_IRQHandler(void)
{
    if (EXTI_GetFlagStatus(EXTI_Line8) != RESET)
    {
        // ��תGPIOA�ĵ���λ�͸���λ6��
        u8 i;
        for (i = 0; i < 6; i++)
        {
            GPIO_Write(GPIOA, ~(0x0F)); // ��ת����λ
            Delay_MS(500);
            GPIO_Write(GPIOA, ~(0xF0)); // ��ת����λ
            Delay_MS(500);
        }
    }
    EXTI_ClearFlag(EXTI_Line8); // ���EXTI8�жϱ�־
}

void EXTI0_IRQHandler(void)
{
    if (EXTI_GetFlagStatus(EXTI_Line0) != RESET)
    {
        // ��תGPIOA����λ4��
        u8 i;
        for (i = 0; i < 4; i++)
        {
            GPIO_Write(GPIOA, 0x00); // ����λ��
            Delay_MS(500);
            GPIO_Write(GPIOA, 0xFF); // ����λ��
            Delay_MS(500);
        }
    }
    EXTI_ClearFlag(EXTI_Line0); // ���EXTI0�жϱ�־
}

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

u16 V_SENSOR; // ����12λ����ı���
u16 Temp;     // ���嵱ǰ�¶�ֵ����
u16 TempZS, TempXS;

void ADC1_Init(void)
{
    ADC_InitTypeDef ADC_InitStructure;
    NVIC_InitTypeDef NVIC_InitStructure;
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE);
    RCC_ADCCLKConfig(RCC_PCLK2_Div6);
    ADC_DeInit(ADC1);

    // ���ADC1�ĳ�ʼ������
    /**************************************************************************/

    // ADC1�ĳ�ʼ������
    ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;                  // ����ģʽ����ʹ��DMA
    ADC_InitStructure.ADC_ScanConvMode = DISABLE;                       //  ��ͨ��ģʽ
    ADC_InitStructure.ADC_ContinuousConvMode = DISABLE;                 // ����ת��ģʽ
    ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; // ��ʹ���ⲿ����
    ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
    ADC_InitStructure.ADC_NbrOfChannel = 1;

    ADC_Init(ADC1, &ADC_InitStructure);
    ADC_RegularChannelConfig(ADC1, ADC_Channel_TempSensor, 1, ADC_SampleTime_55Cycles5);
    ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);

    /**************************************************************/
    NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);   // ADC1���ж����ȼ�����
    NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn; // ADC1���ж�ͨ��
    NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
    NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
    NVIC_Init(&NVIC_InitStructure);

    // ���ADC1�ĳ�ʼ������
    /**************************************************************/

    // todo

    // ʹ���¶ȴ������Ͳο���ѹ
    ADC_TempSensorVrefintCmd(ENABLE);
    ADC_Cmd(ADC1, ENABLE); // ʹ��ADC1������ADC

    /***************************************************************/
    ADC_ResetCalibration(ADC1);                 // ADC��У׼
    while (ADC_GetResetCalibrationStatus(ADC1)) // �ȴ�У׼���
        ;
    ADC_StartCalibration(ADC1);            // ��ʼУ׼
    while (ADC_GetCalibrationStatus(ADC1)) // �ȴ�У׼���
        ;
    ADC_SoftwareStartConvCmd(ADC1, ENABLE); // ����ADCת��
}

// ADC1���жϷ������
void ADC1_2_IRQHandler(void)
{
    /*************************************************************************/
    if (ADC_GetITStatus(ADC1, ADC_IT_EOC)) // ���ת������жϷ���
    {
        V_SENSOR = ADC_GetConversionValue(ADC1); // ��ȡת�����
        Temp = (25 + (1.43 - V_SENSOR * 3.3 / 4096) / 0.0043) * 10;
        ADC_ClearITPendingBit(ADC1, ADC_IT_EOC); // ����жϱ�־
    }

    /*************************************************************************/
}

u16 TempZS_Get(void)
{
    TempZS = Temp / 10; // Temp����10ȡ���õ��¶�ֵ��2λ����ֵ
    return TempZS;
}

u16 TempXS_Get(void)
{
    TempXS = Temp % 10; // Temp����õ��¶�ֵ��1λС��ֵ
    return TempXS;
}

/*****************************************************************************************************************/

// ʵ��һ���򵥵�΢�뼶��ʱ����
void Delay_MS(u16 dly)
{
    u16 i, j;
    for (i = 0; i < dly; i++)
        for (j = 1000; j > 0; j--)
            ;
}

// ����ϵͳʱ��
void RCC_Configuration(void)
{
    // ����RCC���õ�Ĭ��״̬
    RCC_DeInit();
    // ���������ⲿʱ��(HSE)
    RCC_HSEConfig(RCC_HSE_ON);
    // �ȴ�HSE�ȶ�
    while (RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET)
        ;
    // ����FlashԤȡ����
    FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);
    // ����Flash�ȴ�����Ϊ2������
    FLASH_SetLatency(FLASH_Latency_2);
    // ����AHBʱ��(HCLK)����SYSCLK
    RCC_HCLKConfig(RCC_SYSCLK_Div1);
    // ����AHBʱ��(HCLK)����SYSCLK
    RCC_PCLK2Config(RCC_HCLK_Div1);
    // ����APB1ʱ��(PCLK1)����HCLK/2
    RCC_PCLK1Config(RCC_HCLK_Div2);
    // ����PLLԴ��ʱ�ӱ�Ƶ��PLLCLK = HSE * 9 = 72MHz
    RCC_PLLConfig(RCC_PLLSource_HSE_Div1, RCC_PLLMul_9);
    // ʹ��PLL
    RCC_PLLCmd(ENABLE);
    // �ȴ�PLL�ȶ�
    while (RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET)
        ;
    // ����PLL��Ϊϵͳʱ��Դ
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);
    // �ȴ�ϵͳʱ��Դ�л���ɣ�ȷ��PLL��ѡ��ϵͳʱ��Դ
    while (RCC_GetSYSCLKSource() != 0x08)
        ;

    // ������Ҫ������ʱ��
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_USART1, ENABLE);
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_AFIO, ENABLE);
}

// GPIO
void GPIO_Configuration(void)
{
    GPIO_InitTypeDef GPIO_InitStructure;

    // ����USART1��TX����PA9Ϊ�����������ģʽ���ٶ�50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_9;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_AF_PP;
    GPIO_Init(GPIOA, &GPIO_InitStructure);

    // ����USART1��RX����PA10Ϊ��������ģʽ���ٶ�50MHz
    GPIO_InitStructure.GPIO_Pin = GPIO_Pin_10;
    GPIO_InitStructure.GPIO_Speed = GPIO_Speed_50MHz;
    GPIO_InitStructure.GPIO_Mode = GPIO_Mode_IN_FLOATING;
    GPIO_Init(GPIOA, &GPIO_InitStructure);
}

// USART1���ú��������ò����ʡ�����λ��ֹͣλ�Ȳ���
void USART1_Configuration(void)
{
    USART_InitTypeDef USART_InitStructure;

    // ��ʼ��USART1
    USART_DeInit(USART1);

    // ����USART1�Ĳ����ʡ�����λ��ֹͣλ�Ȳ��������ò�����Ϊ9600
    USART_InitStructure.USART_BaudRate = 9600;
    // ���ݳ���Ϊ8λ
    USART_InitStructure.USART_WordLength = USART_WordLength_8b;
    // ֹͣλΪ1λ
    USART_InitStructure.USART_StopBits = USART_StopBits_1;
    // ����żУ�飬��ʹ��У��λ
    USART_InitStructure.USART_Parity = USART_Parity_No;
    USART_InitStructure.USART_Mode = USART_Mode_Rx | USART_Mode_Tx; // �շ�ģʽ
    // ��ʹ��Ӳ��������
    // USART_InitStructure.USART_HardwareFlowControl = USART_HardwareFlowControl_None;

    // ��ʼ��USART1
    USART_Init(USART1, &USART_InitStructure);
    // USART_ITConfig(USART1, USART_IT_RXNE, ENABLE); // ʹ�ܽ����ж�
    // ʹ��USART1
    USART_Cmd(USART1, ENABLE);
}

// �ض���fputc������ʹprintf��������ͨ��USART1��������
int fputc(int ch, FILE *f)
{
    // ���ַ�chͨ��USART1����
    USART_SendData(USART1, ch);
    // �ȴ��������
    while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET)
        ;
    // ���ط��͵��ַ�
    return (ch);
}

int main(void)
{
    SystemInit(); // ����ϵͳʱ��Ϊ72M
    delay_init(72);
    OLED_Init();
    ADC1_Init();

    // ��ʼ��
    RCC_Configuration();
    GPIO_Configuration();
    USART1_Configuration();

    u8 i;

    char temperatureBuffer[32]; // ���ڴ洢�¶�ֵ���ַ�����ʾ

    while (1)
    {
        // ��ʾ�¶�ֵ
        u16 tempZS = TempZS_Get(); // ��������
        u16 tempXS = TempXS_Get(); // С������

        // ���¶�ֵ��ʾ��OLED��
        OLED_ShowNum(40, 32, tempZS, 2, 16); // ʮλ��������
        OLED_ShowChar(56, 32, '.', 16);      // ��ʾС����
        OLED_ShowNum(64, 32, tempXS, 1, 16); // С������

        // �����¶�ֵ������
        sprintf(temperatureBuffer, "Temperature: %d.%d\n", tempZS, tempXS);
        for (i = 0; i < strlen(temperatureBuffer); i++) // �����ַ����е�ÿһ���ַ�
        {
            USART_SendData(USART1, temperatureBuffer[i]);

            while (USART_GetFlagStatus(USART1, USART_FLAG_TC) == RESET)
                ;
        }

        Delay_MS(2000);
    }
}

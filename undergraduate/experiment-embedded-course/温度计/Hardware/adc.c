// #include "stm32f10x.h"
// #include <stm32f10x_adc.h>
// #include <stm32f10x_rcc.h>
// #include <misc.h>
// #include <stdio.h>

// u16 V_SENSOR; // ����12λ����ı���
// u16 Temp;     // ���嵱ǰ�¶�ֵ����
// u16 TempZS, TempXS;

// void ADC1_Init(void)
// {
//   ADC_InitTypeDef ADC_InitStructure;
//   NVIC_InitTypeDef NVIC_InitStructure;
//   RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE);
//   RCC_ADCCLKConfig(RCC_PCLK2_Div6);
//   ADC_DeInit(ADC1);

//   // ���ADC1�ĳ�ʼ������
//   /**************************************************************************/

//   // ADC1�ĳ�ʼ������
//   ADC_InitStructure.ADC_Mode = ADC_Mode_Independent; // ����ģʽ����ʹ��DMA
//   ADC_InitStructure.ADC_ScanConvMode = DISABLE; //  ��ͨ��ģʽ
//   ADC_InitStructure.ADC_ContinuousConvMode = DISABLE; // ����ת��ģʽ
//   ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; // ��ʹ���ⲿ����
//   ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
//   ADC_InitStructure.ADC_NbrOfChannel = 1;

//   ADC_Init(ADC1, &ADC_InitStructure);
//   ADC_RegularChannelConfig(ADC1, ADC_Channel_TempSensor, 1, ADC_SampleTime_55Cycles5);
//   ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);

//   /**************************************************************/
//   NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);   // ADC1���ж����ȼ�����
//   NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn; // ADC1���ж�ͨ��
//   NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
//   NVIC_Init(&NVIC_InitStructure);

//   // ���ADC1�ĳ�ʼ������
//   /**************************************************************/

//   // todo

//   // ʹ���¶ȴ������Ͳο���ѹ
//   ADC_TempSensorVrefintCmd(ENABLE);
//   ADC_Cmd(ADC1, ENABLE); // ʹ��ADC1������ADC

//   /***************************************************************/
//   ADC_ResetCalibration(ADC1); // ADC��У׼
//   while (ADC_GetResetCalibrationStatus(ADC1)) // �ȴ�У׼���
//     ;
//   ADC_StartCalibration(ADC1); // ��ʼУ׼
//   while (ADC_GetCalibrationStatus(ADC1)) // �ȴ�У׼���
//     ;
//   ADC_SoftwareStartConvCmd(ADC1, ENABLE); // ����ADCת��
// }

// // ADC1���жϷ������
// void ADC1_2_IRQHandler(void)
// {
//   /*************************************************************************/
//   if (ADC_GetITStatus(ADC1, ADC_IT_EOC)) // ���ת������жϷ���
//   {
//     V_SENSOR = ADC_GetConversionValue(ADC1); // ��ȡת�����
//     Temp = (25 + (1.43 - V_SENSOR * 3.3 / 4096) / 0.0043) * 10;
//     ADC_ClearITPendingBit(ADC1, ADC_IT_EOC); // ����жϱ�־
//   }

//   /*************************************************************************/
// }

// u16 TempZS_Get(void)
// {
//   TempZS = Temp / 10; // Temp����10ȡ���õ��¶�ֵ��2λ����ֵ
//   return TempZS;
// }

// u16 TempXS_Get(void)
// {
//   TempXS = Temp % 10; // Temp����õ��¶�ֵ��1λС��ֵ
//   return TempXS;
// }

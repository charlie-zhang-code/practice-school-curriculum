// #include "stm32f10x.h"
// #include <stm32f10x_adc.h>
// #include <stm32f10x_rcc.h>
// #include <misc.h>
// #include <stdio.h>

// u16 V_SENSOR; // 定义12位编码的变量
// u16 Temp;     // 定义当前温度值变量
// u16 TempZS, TempXS;

// void ADC1_Init(void)
// {
//   ADC_InitTypeDef ADC_InitStructure;
//   NVIC_InitTypeDef NVIC_InitStructure;
//   RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE);
//   RCC_ADCCLKConfig(RCC_PCLK2_Div6);
//   ADC_DeInit(ADC1);

//   // 添加ADC1的初始化程序
//   /**************************************************************************/

//   // ADC1的初始化程序
//   ADC_InitStructure.ADC_Mode = ADC_Mode_Independent; // 独立模式，不使用DMA
//   ADC_InitStructure.ADC_ScanConvMode = DISABLE; //  单通道模式
//   ADC_InitStructure.ADC_ContinuousConvMode = DISABLE; // 单次转换模式
//   ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None; // 不使用外部触发
//   ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
//   ADC_InitStructure.ADC_NbrOfChannel = 1;

//   ADC_Init(ADC1, &ADC_InitStructure);
//   ADC_RegularChannelConfig(ADC1, ADC_Channel_TempSensor, 1, ADC_SampleTime_55Cycles5);
//   ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);

//   /**************************************************************/
//   NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);   // ADC1的中断优先级设置
//   NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn; // ADC1的中断通道
//   NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
//   NVIC_Init(&NVIC_InitStructure);

//   // 添加ADC1的初始化程序
//   /**************************************************************/

//   // todo

//   // 使能温度传感器和参考电压
//   ADC_TempSensorVrefintCmd(ENABLE);
//   ADC_Cmd(ADC1, ENABLE); // 使能ADC1，启动ADC

//   /***************************************************************/
//   ADC_ResetCalibration(ADC1); // ADC自校准
//   while (ADC_GetResetCalibrationStatus(ADC1)) // 等待校准完成
//     ;
//   ADC_StartCalibration(ADC1); // 开始校准
//   while (ADC_GetCalibrationStatus(ADC1)) // 等待校准完成
//     ;
//   ADC_SoftwareStartConvCmd(ADC1, ENABLE); // 启动ADC转换
// }

// // ADC1的中断服务程序
// void ADC1_2_IRQHandler(void)
// {
//   /*************************************************************************/
//   if (ADC_GetITStatus(ADC1, ADC_IT_EOC)) // 检查转换完成中断发生
//   {
//     V_SENSOR = ADC_GetConversionValue(ADC1); // 读取转换结果
//     Temp = (25 + (1.43 - V_SENSOR * 3.3 / 4096) / 0.0043) * 10;
//     ADC_ClearITPendingBit(ADC1, ADC_IT_EOC); // 清除中断标志
//   }

//   /*************************************************************************/
// }

// u16 TempZS_Get(void)
// {
//   TempZS = Temp / 10; // Temp除以10取整得到温度值的2位整数值
//   return TempZS;
// }

// u16 TempXS_Get(void)
// {
//   TempXS = Temp % 10; // Temp求余得到温度值的1位小数值
//   return TempXS;
// }

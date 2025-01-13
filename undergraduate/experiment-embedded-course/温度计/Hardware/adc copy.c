// #include "stm32f10x.h"

// u16 V_SENSOR; // ????12¶À????????
// u16 Temp;     // ???ç”?????????
// u16 TempZS, TempXS;

// void ADC1_Init(void)
// {
//   ADC_InitTypeDef ADC_InitStructure;
//   NVIC_InitTypeDef NVIC_InitStructure;
//   RCC_APB2PeriphClockCmd(RCC_APB2Periph_ADC1, ENABLE);
//   RCC_ADCCLKConfig(RCC_PCLK2_Div6); // APB2?????6??????ADC_CLK
//   ADC_DeInit(ADC1);

//   // ????ADC1??????????
//   /**************************************************************************/

//   ADC_InitStructure.ADC_Mode = ADC_Mode_Independent;
//   ADC_InitStructure.ADC_ScanConvMode = ENABLE;       // ???????
//   ADC_InitStructure.ADC_ContinuousConvMode = ENABLE; // ?????????
//   ADC_InitStructure.ADC_ExternalTrigConv = ADC_ExternalTrigConv_None;
//   ADC_InitStructure.ADC_DataAlign = ADC_DataAlign_Right;
//   ADC_InitStructure.ADC_NbrOfChannel = 1;
//   ADC_Init(ADC1, &ADC_InitStructure);
//   ADC_RegularChannelConfig(ADC1, ADC_Channel_TempSensor, 1, ADC_SampleTime_55Cycles5);
//   ADC_ITConfig(ADC1, ADC_IT_EOC, ENABLE);

//   NVIC_PriorityGroupConfig(NVIC_PriorityGroup_1);   // ADC1??????????1??
//   NVIC_InitStructure.NVIC_IRQChannel = ADC1_2_IRQn; // ADC1?ßÿ??????
//   NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
//   NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
//   NVIC_Init(&NVIC_InitStructure);
//   // ????ADC1??????????

//   ADC_TempSensorVrefintCmd(ENABLE);
//   // ADC_SoftwareStartConvCmd(ADC1, ENABLE);
//   ADC_Cmd(ADC1, ENABLE);

//   /**************************************************************/
//   ADC_ResetCalibration(ADC1); // ADC??ßµ?
//   while (ADC_GetResetCalibrationStatus(ADC1))
//     ;
//   ADC_StartCalibration(ADC1);
//   while (ADC_GetCalibrationStatus(ADC1))
//     ;
//   ADC_SoftwareStartConvCmd(ADC1, ENABLE);
// }

// // ADC1???ßÿ???????
// void ADC1_2_IRQHandler(void)
// {
//   /*************************************************************************/
//   if (ADC_GetITStatus(ADC1, ADC_IT_EOC)) // ??????????ßÿ????
//   {
//     V_SENSOR = ADC_GetConversionValue(ADC1); // ?????????
//     Temp = (25 + (1.43 - V_SENSOR * 3.3 / 4096) / 0.0043) * 10;
//     ADC_ClearITPendingBit(ADC1, ADC_IT_EOC);
//     // ADC_SoftwareStartConvCmd(ADC1, ENABLE);
//   }
//   /*************************************************************************/
// }

// u16 TempZS_Get(void)
// {
//   TempZS = Temp / 10; // Temp????10????????????2¶À?????
//   return TempZS;
// }

// u16 TempXS_Get(void)
// {
//   TempXS = Temp % 10; // Temp????????????1¶Àß≥???
//   return TempXS;
// }

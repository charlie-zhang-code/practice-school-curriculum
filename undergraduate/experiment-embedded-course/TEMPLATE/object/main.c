/*Include---------------------------*/
#include"stm32f10x.h"		//�������е�ͷ�ļ�
#include<stdio.h>

//----------------��������--------------------
void Delay_MS(u16 dly);
void RCC_Configuration(void);
void GPIO_Configuration(void);


/*******************************************************************************
* Function Name  : main
* Description    : Main program.
* Input          : None
* Output         : None
* Return         : None
*******************************************************************************/ 

  int  main(void)
{ 
  u8 K8, K9; 

//  GPIOA->CRL=0x01;	//A0Ϊ�������ģʽ������ٶ�Ϊ10MHz 
//  GPIOA->CRH=0x04;  //A8Ϊ��������ģʽ
//  while(1)
//  {
//    if((GPIOA->IDR&0x0100)==0x0100)
//    GPIOA->ODR=0x01;
//	else
//	GPIOA->ODR=0x00;
//}
 RCC_Configuration();
 GPIO_Configuration();
 while(1)
 { 
   K8 = GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_8);
   K9 = GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_9);

   if ((K9==0)&(K8==0)) GPIO_ResetBits(GPIOA , GPIO_Pin_0);         
   else                GPIO_SetBits(GPIOA, GPIO_Pin_0);	
   if ((K9==0)&(K8==1)) GPIO_ResetBits(GPIOA , GPIO_Pin_1);         
   else                GPIO_SetBits(GPIOA, GPIO_Pin_1);
   if ((K9==1)&(K8==0)) GPIO_ResetBits(GPIOA , GPIO_Pin_2);         
   else                GPIO_SetBits(GPIOA, GPIO_Pin_2);
   if ((K9==1)&(K8==1))GPIO_ResetBits(GPIOA , GPIO_Pin_3);         
   else                GPIO_SetBits(GPIOA, GPIO_Pin_3);
 }

}

/*******************************************************************************
* Function Name  : Delay_Ms
* Description    : delay 1 ms.
* Input          : dly (ms)
* Output         : None
* Return         : None
*******************************************************************************/
void Delay_MS(u16 dly)
{
	u16 i,j;
	for(i=0;i<dly;i++)
		for(j=1000;j>0;j--);
}

/*******************************************************************************
* Function Name  : RCC_Configuration
* Description    : Configures the different system clocks.
* Input          : None
* Output         : None
* Return         : None
*******************************************************************************/
void RCC_Configuration(void)
{
	//----------ʹ���ⲿRC����-----------
	RCC_DeInit();			//��ʼ��Ϊȱʡֵ
	RCC_HSEConfig(RCC_HSE_ON);	//ʹ���ⲿ�ĸ���ʱ�� 
	while(RCC_GetFlagStatus(RCC_FLAG_HSERDY) == RESET);	//�ȴ��ⲿ����ʱ��ʹ�ܾ���
	
	FLASH_PrefetchBufferCmd(FLASH_PrefetchBuffer_Enable);	//Enable Prefetch Buffer
	FLASH_SetLatency(FLASH_Latency_2);		//Flash 2 wait state
	
	RCC_HCLKConfig(RCC_SYSCLK_Div1);		//HCLK = SYSCLK
	RCC_PCLK2Config(RCC_HCLK_Div1);			//PCLK2 =  HCLK
	RCC_PCLK1Config(RCC_HCLK_Div2);			//PCLK1 = HCLK/2
	RCC_PLLConfig(RCC_PLLSource_HSE_Div1,RCC_PLLMul_9);	//PLLCLK = 8MHZ * 9 =72MHZ
	RCC_PLLCmd(ENABLE);			//Enable PLLCLK

	while(RCC_GetFlagStatus(RCC_FLAG_PLLRDY) == RESET);	//Wait till PLLCLK is ready
    RCC_SYSCLKConfig(RCC_SYSCLKSource_PLLCLK);	//Select PLL as system clock
	while(RCC_GetSYSCLKSource()!=0x08);		//Wait till PLL is used as system clock source
	
	//---------����Ӧ����ʱ��--------------------
	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOA,ENABLE);	//ʹ��APB2�����GPIOA��ʱ��	
	//RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOC,ENABLE);	//ʹ��APB2�����GPIOC��ʱ��		 
}

/*******************************************************************************
* Function Name  : GPIO_Configuration
* Description    : ��ʼ��GPIO����
* Input          : None
* Output         : None
* Return         : None
*******************************************************************************/
//void GPIO_Configuration(void)
//{
//	GPIO_InitTypeDef	GPIO;		//����һ���ṹ�����
//	GPIO.GPIO_Pin = GPIO_Pin_0; 	//ѡ��PX.3
//	GPIO.GPIO_Speed = GPIO_Speed_10MHz;	 //�ܽ�Ƶ��Ϊ50MHZ
//	GPIO.GPIO_Mode = GPIO_Mode_Out_PP;	 //���ģʽΪ�������
//	GPIO_Init(GPIOA,&GPIO);				 //��ʼ��GPIOA�Ĵ���
//
//	GPIO.GPIO_Pin = GPIO_Pin_8;
//	GPIO.GPIO_Mode = GPIO_Mode_In_F;	 //���ģʽΪ�������
//	GPIO_Init(GPIOA,&GPIO);				 //��ʼ��GPIOA�Ĵ���	
//} 

  void GPIO_Configuration(void)
{
  	GPIO_InitTypeDef GPIO;
 
  	/* ����PA0��Ϊ������������תƵ��Ϊ50MHz*/
  	GPIO.GPIO_Pin = GPIO_Pin_0|GPIO_Pin_1|GPIO_Pin_2|GPIO_Pin_3;
  	GPIO.GPIO_Speed = GPIO_Speed_50MHz;
  	GPIO.GPIO_Mode = GPIO_Mode_Out_PP;
  	GPIO_Init(GPIOA , &GPIO);
 
  	/* ����PA8Ϊ��������*/
  	GPIO.GPIO_Pin = GPIO_Pin_8|GPIO_Pin_9;
  	GPIO.GPIO_Mode = GPIO_Mode_IN_FLOATING; 
  	GPIO_Init(GPIOA , &GPIO);
    
}

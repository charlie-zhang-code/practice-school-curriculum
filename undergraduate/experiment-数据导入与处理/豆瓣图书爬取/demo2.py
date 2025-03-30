import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from pandas import read_csv

df = pd.read_csv(r"book_douban.csv", index_col=0, encoding="utf-8")
df.head(10)

df.info()

df = df.rename(columns={'数': '页数'})
df.reset_index(drop=True, inplace=True)
df.shape
df.describe()

df.replace('None', np.nan, inplace=True)
df.isnull().sum()

del df['ISBM']
df.dropna(axis=0, subset=['作者', '出版社', '出版时间', '页数', '价格', '评分', '评论数量'], how='any', inplace=True)

df.reset_index(drop=True, inplace=True)
df.isna().sum()

import re

df['出版时间'] = df['出版时间'].str.replace(' ', '')

for index, row in df.iterrows():
    num = re.findall(r'\d+', row.iloc[3])
    num = ''.join(num)[0:4]
    df.iloc[index, 3] = num

df.drop(df[df['出版时间'].str.len() != 4].index, axis=0, inplace=True)
df['出版时间'] = df['出版时间'].astype(np.int32)

df.drop(df[df['出版时间'] > 2019].index, inplace=True)

df['评分'] = df['评分'].astype(float)
df['评论数量'] = df['评论数量'].astype(np.int32)

df['页数'].str.contains('.').value_counts()

df['页数'] = df['页数'].astype(np.int32)
df.drop(df[df['页数'] == 0].index, inplace=True)

df['价格'] = df['价格'].apply(lambda x: x.replace(',', '').replace(' ', ''))
df.drop(df[~(df['页数'].str.isdecimal())].index, axis=0, inplace=True)

df['页数'] = df['页数'].astype(np.int32)
df.drop(df[df['页数'] == 0].index, inplace=True)

df['价格'] = df['价格'].apply(lambda x: x.replace(',', '').replace(' ', ''))
for r_index, row in df.iterrows():
    if row[5].replace('.', '').isdecimal() == False:
        df.drop(r_index, axis=0, inplace=True)
    elif row[5][-1].isdecimal() == False:
        df.drop(r_index, axis=0, inplace=True)

df['价格'] = df['价格'].astype(float)
df.drop(df[df['价格'] < 1].index, inplace=True)

df['书名'].value_counts()
df['书名'].duplicated().value_counts()

df = df.sort_values(by='评论数量', ascending=False)
df.reset_index(drop=True, inplace=True)

df = df.sort_values(by='评论数量', ascending=False)
df.reset_index(drop=True, inplace=True)

df.to_excel(r'book_douban_res.xlsx', index=False)
df
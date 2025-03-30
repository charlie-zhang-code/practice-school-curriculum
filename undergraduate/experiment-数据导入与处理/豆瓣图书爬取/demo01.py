import re
import time
import requests, openpyxl
from bs4 import BeautifulSoup
import pandas as pd

wb = openpyxl.Workbook()
sheet = wb.active
sheet.title = '图书信息表'

sheet['A1'] = '书名'
sheet['B1'] = '作者'
sheet['C1'] = '出版社'
sheet['D1'] = '出版时间'
sheet['E1'] = '价格'
sheet['F1'] = '评分'

headers = {
    'origin': 'https://book.douban.com',
    'referer': 'https://book.douban.com/top250',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
}

url = 'https://book.douban.com/top250'

for x in range(10):
    params = {
        'start': str(x * 25),
        'filter': ''
    }

    res = requests.get(url, headers=headers, params=params)

    bs_book = BeautifulSoup(res.text, 'html.parser')
    list_book = bs_book.find_all('div', class_='item')
    book_name_tags = bs_book.select('div.pl2 a')
    book_info_tags = bs_book.select('p.pl')
    marks = bs_book.select('span.rating_nums')

    for i in range(25):
        book_name = book_name_tags[i]['title']
        info = book_info_tags[i].text
        info_list = info.split('/')
        author = info_list[0]
        publisher = info_list[-3]
        data = info_list[-2]
        jge = info_list[-1]
        pf = marks[i].text
        print(book_name, author, publisher, data, jge, pf)
        sheet.append([book_name, author, publisher, data, jge, pf])

wb.save('豆瓣图书.xlsx')
print('爬取完成！')
wb.close()

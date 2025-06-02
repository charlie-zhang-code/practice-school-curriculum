from django.core.management.base import BaseCommand
from faker import Faker
import random

from snippets.models import SnippetCategory, Snippet


class Command(BaseCommand):
    help = 'Generates test data for code snippets and categories'

    def add_arguments(self, parser):
        parser.add_argument(
            '--categories',
            type=int,
            default=6,
            help='Number of snippet categories to create (default: 6)',
        )
        parser.add_argument(
            '--snippets',
            type=int,
            default=30,
            help='Number of code snippets to create (default: 30)',
        )

    def handle(self, *args, **options):
        fake = Faker()
        category_count = options['categories']
        snippet_count = options['snippets']

        self.stdout.write(self.style.SUCCESS('开始生成代码片段测试数据...'))

        # 预定义编程语言列表
        languages = [
            'Python', 'JavaScript', 'Java', 'C++', 'Go', 'Ruby',
            'PHP', 'Swift', 'Kotlin', 'TypeScript', 'HTML', 'CSS',
            'SQL', 'Bash', 'Rust', 'Dart', 'C#', 'Scala'
        ]

        # 预定义代码片段分类
        predefined_categories = [
            '算法实现', '数据处理', '网络请求', '文件操作',
            '数据库操作', '用户认证', '测试代码', '工具函数',
            '正则表达式', '性能优化', '安全相关', '并发编程'
        ]

        # 创建分类
        categories = []
        for i, cat_name in enumerate(predefined_categories[:category_count]):
            category, created = SnippetCategory.objects.get_or_create(name=cat_name)
            categories.append(category)
            self.stdout.write(f'已创建分类: {cat_name}')

        # 预定义一些常见的代码片段模板
        snippet_templates = [
            {
                'title': '快速排序实现',
                'language': 'Python',
                'description': 'Python实现的快速排序算法',
                'code': '''def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)'''
            },
            {
                'title': 'HTTP请求示例',
                'language': 'JavaScript',
                'description': '使用Fetch API发起GET请求',
                'code': '''fetch('https://api.example.com/data')
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));'''
            },
            {
                'title': '文件读取',
                'language': 'Python',
                'description': '读取文本文件内容',
                'code': '''with open('file.txt', 'r') as file:
    content = file.read()
    print(content)'''
            },
            {
                'title': '数据库连接',
                'language': 'Python',
                'description': '使用SQLAlchemy连接数据库',
                'code': '''from sqlalchemy import create_engine
engine = create_engine('postgresql://user:password@localhost:5432/mydatabase')
conn = engine.connect()'''
            },
            {
                'title': '数组去重',
                'language': 'JavaScript',
                'description': 'ES6方法实现数组去重',
                'code': '''const uniqueArray = [...new Set(array)];'''
            }
        ]

        # 创建代码片段
        for i in range(snippet_count):
            # 如果预定义代码片段模板用完，使用随机生成的内容
            if i < len(snippet_templates):
                snippet_data = snippet_templates[i]
            else:
                # 随机生成代码片段
                language = random.choice(languages)
                snippet_data = {
                    'title': f'{fake.word().capitalize()} {fake.word()}示例',
                    'language': language,
                    'description': fake.sentence(),
                    'code': self.generate_random_code(language)
                }

            # 随机选择一个分类
            category = random.choice(categories)

            # 创建代码片段
            snippet = Snippet.objects.create(
                category=category,
                language=snippet_data['language'],
                title=snippet_data['title'],
                description=snippet_data['description'],
                code=snippet_data['code'],
                author=fake.name()
            )

            self.stdout.write(f'已创建代码片段 {i + 1}/{snippet_count}: {snippet.title}', ending='\r')

        self.stdout.write(self.style.SUCCESS(f'\n成功创建 {len(categories)} 个分类和 {snippet_count} 个代码片段!'))

    def generate_random_code(self, language):
        fake = Faker()
        """根据语言生成随机代码片段"""
        code_snippets = {
            'Python': [
                '''for i in range(10):
    print(i)''',
                '''def {function_name}():
    return "{result}"'''.format(
                    function_name=fake.word(),
                    result=fake.word()
                ),
                '''class {class_name}:
    def __init__(self):
        self.{attribute} = "{value}"'''.format(
                    class_name=fake.word().capitalize(),
                    attribute=fake.word(),
                    value=fake.word()
                )
            ],
            'JavaScript': [
                '''const {varName} = () => {{
    console.log("{message}")
}}'''.format(
                    varName=fake.word(),
                    message=fake.sentence()
                ),
                '''document.querySelector("{selector}")
    .addEventListener("{event}", () => {{
        // {comment}
    }});'''.format(
                    selector=fake.word(),
                    event=random.choice(['click', 'hover', 'submit']),
                    comment=fake.sentence()
                )
            ],
            'SQL': [
                '''SELECT {columns} 
FROM {table}
WHERE {condition}'''.format(
                    columns=', '.join([fake.word() for _ in range(3)]),
                    table=fake.word(),
                    condition=f"{fake.word()} = '{fake.word()}'"
                )
            ],
            'Bash': [
                '''#!/bin/bash
for file in *.txt; do
    echo "Processing $file"
done'''
            ]
        }

        # 如果找不到特定语言的代码，返回通用代码
        if language not in code_snippets:
            return f"// {fake.sentence()}\n{language} code example here"

        return random.choice(code_snippets[language])
from django.core.management.base import BaseCommand
from faker import Faker
import random

import commands
from commands.models import CommandCategory


class Command(BaseCommand):
    help = 'Generates test data for commands and command categories'

    def add_arguments(self, parser):
        parser.add_argument(
            '--categories',
            type=int,
            default=5,
            help='Number of command categories to create (default: 5)',
        )
        parser.add_argument(
            '--commands',
            type=int,
            default=20,
            help='Number of commands to create (default: 20)',
        )

    def handle(self, *args, **options):
        fake = Faker()
        category_count = options['categories']
        command_count = options['commands']

        self.stdout.write(self.style.SUCCESS('开始生成命令速查测试数据...'))

        # 预定义一些常见的命令分类
        predefined_categories = [
            'Git命令',
            'Linux命令',
            'Docker命令',
            'Python命令',
            '数据库命令',
            '网络命令',
            '系统管理',
            '文件操作',
            '权限管理',
            '进程管理'
        ]

        # 创建分类
        categories = []
        for i, cat_name in enumerate(predefined_categories[:category_count]):
            category, created = CommandCategory.objects.get_or_create(name=cat_name)
            categories.append(category)
            self.stdout.write(f'已创建分类: {cat_name}')

        # 预定义一些常见的命令模板
        command_templates = [
            {
                'title': '查看当前目录内容',
                'description': '列出当前目录下的文件和文件夹',
                'command': 'ls'
            },
            {
                'title': '查看网络连接',
                'description': '显示网络连接、路由表和网络接口信息',
                'command': 'netstat -tuln'
            },
            {
                'title': '查找文件',
                'description': '在指定目录下查找文件',
                'command': 'find /path/to/search -name "*.txt"'
            },
            {
                'title': '查看进程',
                'description': '显示当前运行的进程',
                'command': 'ps aux'
            },
            {
                'title': '克隆仓库',
                'description': '克隆远程Git仓库到本地',
                'command': 'git clone https://github.com/user/repo.git'
            }
        ]

        # 创建命令
        for i in range(command_count):
            # 如果预定义命令模板用完，使用随机生成的内容
            if i < len(command_templates):
                cmd_data = command_templates[i]
            else:
                actions = ['查看', '列出', '显示', '创建', '删除', '修改', '移动', '复制', '搜索', '安装']
                targets = ['文件', '目录', '进程', '网络', '用户', '系统', '服务', '数据库', '容器', '软件包']

                cmd_data = {
                    'title': f'{random.choice(actions)}{random.choice(targets)}',
                    'description': fake.sentence(),
                    'command': fake.word() + ' ' + ' '.join(fake.words(nb=random.randint(1, 3)))
                }

            # 随机选择一个分类
            category = random.choice(categories)

            # 创建命令
            command = commands.models.Command.objects.create(
                category=category,
                title=cmd_data['title'],
                description=cmd_data['description'],
                command=cmd_data['command']
            )

            self.stdout.write(f'已创建命令 {i + 1}/{command_count}: {command.title}', ending='\r')

        self.stdout.write(self.style.SUCCESS(f'\n成功创建 {len(categories)} 个分类和 {command_count} 条命令!'))

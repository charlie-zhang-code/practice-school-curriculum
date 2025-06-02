from django.core.management.base import BaseCommand
from faker import Faker
import random
import os
from django.conf import settings
from django.core.files import File

from tools.models import ToolCategory, Tool


class Command(BaseCommand):
    help = 'Generates test data for tools and tool categories'

    def add_arguments(self, parser):
        parser.add_argument(
            '--categories',
            type=int,
            default=8,
            help='Number of tool categories to create (default: 8)',
        )
        parser.add_argument(
            '--tools',
            type=int,
            default=25,
            help='Number of tools to create (default: 25)',
        )

    def handle(self, *args, **options):
        fake = Faker()
        category_count = options['categories']
        tool_count = options['tools']

        self.stdout.write(self.style.SUCCESS('开始生成工具测试数据...'))

        # 确保媒体目录存在
        self.prepare_media_dirs()

        # 预定义工具分类
        predefined_categories = [
            '开发工具', '设计工具', '效率工具', '学习资源',
            'API工具', '数据库工具', '测试工具', '部署工具',
            '代码分析', '文档工具', '安全工具', 'AI工具'
        ]

        # 创建分类
        categories = []
        for i, cat_name in enumerate(predefined_categories[:category_count]):
            icon_path = self.get_random_icon('tool_categories')
            with open(icon_path, 'rb') as icon_file:
                category = ToolCategory.objects.create(
                    name=cat_name,
                    icon=File(icon_file, name=os.path.basename(icon_path))
                )
                categories.append(category)
                self.stdout.write(f'已创建分类: {cat_name}')

        # 预定义一些常见工具
        tool_templates = [
            {
                'name': 'Postman',
                'description': 'API开发和测试工具',
                'link_type': 'page',
                'link_target': '/pages/api-tools/postman'
            },
            {
                'name': 'GitHub',
                'description': '代码托管平台',
                'link_type': 'page',
                'link_target': '/pages/dev-tools/github'
            },
            {
                'name': 'Figma',
                'description': '在线设计协作工具',
                'link_type': 'page',
                'link_target': '/pages/design-tools/figma'
            },
            {
                'name': 'JSON格式化',
                'description': '在线JSON格式化工具',
                'link_type': 'miniprogram',
                'link_target': '/pages/json-formatter'
            },
            {
                'name': '正则测试',
                'description': '正则表达式测试工具',
                'link_type': 'miniprogram',
                'link_target': '/pages/regex-tester'
            }
        ]

        # 创建工具
        for i in range(tool_count):
            # 如果预定义工具模板用完，使用随机生成的内容
            if i < len(tool_templates):
                tool_data = tool_templates[i]
            else:
                tool_types = ['编辑器', '转换器', '生成器', '分析器', '检查器', '优化器']
                tool_domains = ['代码', '图片', '视频', '文本', '数据', '网络']

                tool_data = {
                    'name': f'{fake.word().capitalize()}{random.choice(tool_types)}',
                    'description': f'{random.choice(["专业的", "高效的", "智能的", "在线的"])}{random.choice(tool_domains)}{random.choice(tool_types)}',
                    'link_type': random.choice(['miniprogram', 'page']),
                    'link_target': f'/pages/{fake.uri_path()}'
                }

            # 随机选择一个分类
            category = random.choice(categories)

            # 获取随机图标
            icon_path = self.get_random_icon('tools')

            try:
                with open(icon_path, 'rb') as icon_file:
                    tool = Tool.objects.create(
                        category=category,
                        name=tool_data['name'],
                        link_type=tool_data['link_type'],
                        link_target=tool_data['link_target'],
                        icon=File(icon_file, name=os.path.basename(icon_path)),
                        description=tool_data['description']
                    )

                self.stdout.write(f'已创建工具 {i + 1}/{tool_count}: {tool.name}')
            except Exception as e:
                self.stdout.write(self.style.ERROR(f'创建工具失败: {str(e)}'))
                continue

        self.stdout.write(self.style.SUCCESS(f'\n成功创建 {len(categories)} 个分类和 {tool_count} 个工具!'))

    def prepare_media_dirs(self):
        """准备媒体文件目录结构"""
        dirs = [
            os.path.join(settings.MEDIA_ROOT, 'tool_categories'),
            os.path.join(settings.MEDIA_ROOT, 'tools')
        ]

        for dir_path in dirs:
            os.makedirs(dir_path, exist_ok=True)

            # 如果没有图标文件，创建一些虚拟文件
            if not os.listdir(dir_path):
                for i in range(1, 6):
                    icon_path = os.path.join(dir_path, f'icon{i}.png')
                    with open(icon_path, 'wb') as f:
                        f.write(b'')  # 创建空文件
                self.stdout.write(f'在 {dir_path} 创建了虚拟图标文件')

    def get_random_icon(self, dir_name):
        """获取随机图标文件路径"""
        dir_path = os.path.join(settings.MEDIA_ROOT, dir_name)
        icons = [f for f in os.listdir(dir_path) if f.endswith(('.png', '.jpg', '.jpeg'))]

        if not icons:
            # 如果没有图标，创建一个虚拟文件
            icon_path = os.path.join(dir_path, 'default_icon.png')
            with open(icon_path, 'wb') as f:
                f.write(b'')
            return icon_path

        return os.path.join(dir_path, random.choice(icons))
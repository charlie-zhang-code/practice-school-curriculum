from django.core.management.base import BaseCommand
from faker import Faker
import random
from datetime import datetime, timedelta

from articles.models import ArticleCategory, Article


class Command(BaseCommand):
    help = 'Generates test data for articles and categories'

    def add_arguments(self, parser):
        parser.add_argument(
            '--count',
            type=int,
            default=10,
            help='Number of articles to create (default: 10)',
        )

    def handle(self, *args, **options):
        fake = Faker()
        count = options['count']

        self.stdout.write(self.style.SUCCESS('开始生成测试数据...'))

        # 创建一些分类
        categories = ['Python', 'Django', 'JavaScript', 'Vue', 'React', '数据库', '算法', 'Linux']
        for cat in categories:
            ArticleCategory.objects.get_or_create(name=cat)

        all_categories = ArticleCategory.objects.all()

        # 创建文章
        for i in range(count):
            # 随机选择分类
            category = random.choice(all_categories)

            # 随机发布日期（最近365天内）
            publish_time = fake.date_time_between(
                start_date='-1y',
                end_date='now'
            )

            # 创建文章
            Article.objects.create(
                category=category,
                author=fake.name(),
                title=fake.sentence(),
                content='\n\n'.join(fake.paragraphs(nb=random.randint(3, 10))),
                publish_time=publish_time
            )

            self.stdout.write(f'已创建文章 {i + 1}/{count}', ending='\r')

        self.stdout.write(self.style.SUCCESS(f'\n成功创建 {count} 篇测试文章和 {len(categories)} 个分类!'))

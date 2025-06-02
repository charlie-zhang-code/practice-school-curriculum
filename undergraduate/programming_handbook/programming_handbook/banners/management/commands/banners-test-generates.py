from django.core.management.base import BaseCommand
from faker import Faker
import random
import os
from django.conf import settings
from django.core.files import File
from banners.models import Banner  # 确保正确导入Banner模型
from articles.models import Article  # 导入Article模型（如果链接类型需要）


class Command(BaseCommand):
    help = 'Generates test data for banners'

    def add_arguments(self, parser):
        parser.add_argument(
            '--count',
            type=int,
            default=5,
            help='Number of banners to create (default: 5)',
        )

    def handle(self, *args, **options):
        fake = Faker()
        count = options['count']

        self.stdout.write(self.style.SUCCESS('开始生成轮播图测试数据...'))

        # 确保media/banners目录存在
        banners_dir = os.path.join(settings.MEDIA_ROOT, 'banners')
        os.makedirs(banners_dir, exist_ok=True)

        # 创建一些虚拟图片文件
        sample_images = [
            'banner1.png',
            'banner2.png',
            'banner3.jpg',
            'banner4.jpg',
        ]
        # for i in range(1, 6):
        #     img_name = f'banner{i}.jpg'
        #     img_path = os.path.join(banners_dir, img_name)
        #
        #     # 如果图片不存在，创建一个空文件
        #     if not os.path.exists(img_path):
        #         open(img_path, 'wb').close()
        #         self.stdout.write(f'创建虚拟图片: {img_path}')
        #
        #     sample_images.append(img_name)

        # 获取所有文章ID（如果链接类型选择文章）
        article_ids = list(Article.objects.values_list('id', flat=True)) if Article.objects.exists() else []

        for i in range(count):
            # 随机选择链接类型
            link_type = random.choice(['miniprogram', 'article', 'page'])

            # 根据链接类型设置目标
            if link_type == 'article' and article_ids:
                link_target = str(random.choice(article_ids))
            elif link_type == 'miniprogram':
                link_target = f'/pages/{fake.uri_path()}'
            else:  # page
                link_target = f'/pages/{fake.uri_path()}'

            # 随机选择图片
            img_name = random.choice(sample_images)
            img_path = os.path.join(banners_dir, img_name)

            try:
                # 使用正确的文件路径创建轮播图
                with open(img_path, 'rb') as img_file:
                    banner = Banner.objects.create(
                        title=fake.sentence(nb_words=4),
                        image=File(img_file, name=img_name),
                        link_type=link_type,
                        link_target=link_target,
                        sort_order=i
                    )

                self.stdout.write(f'已创建轮播图 {i + 1}/{count}: {banner.title}')
            except Exception as e:
                self.stdout.write(self.style.ERROR(f'创建轮播图失败: {str(e)}'))
                continue

        self.stdout.write(self.style.SUCCESS(f'\n成功创建 {count} 个轮播图!'))
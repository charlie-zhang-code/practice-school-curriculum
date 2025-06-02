from django.db import models

# Create your models here.

from django.db import models
from common.models import BaseModel


class Banner(BaseModel):
    title = models.CharField(max_length=100, verbose_name="标题")
    image = models.ImageField(upload_to='banners/', verbose_name="轮播图图片")
    link_type = models.CharField(max_length=20, choices=(
        ('miniprogram', '小程序'),
        ('article', '技术文章'),
        ('page', '本小程序页面')
    ), verbose_name="链接类型")
    link_target = models.CharField(max_length=200, verbose_name="链接目标")
    sort_order = models.PositiveIntegerField(default=0, verbose_name="排序")

    class Meta:
        verbose_name = "轮播图"
        verbose_name_plural = verbose_name
        ordering = ['sort_order']

    def __str__(self):
        return self.title
from django.db import models

# Create your models here.

from django.db import models
from common.models import BaseModel


class ArticleCategory(BaseModel):
    name = models.CharField(max_length=50, verbose_name="分类名称")

    class Meta:
        verbose_name = "文章分类"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.name


class Article(BaseModel):
    category = models.ForeignKey(ArticleCategory, on_delete=models.CASCADE, verbose_name="分类")
    author = models.CharField(max_length=50, verbose_name="作者")
    title = models.CharField(max_length=200, verbose_name="标题")
    content = models.TextField(verbose_name="内容")
    publish_time = models.DateTimeField(verbose_name="发布时间")

    class Meta:
        verbose_name = "技术文章"
        verbose_name_plural = verbose_name
        ordering = ['-publish_time']

    def __str__(self):
        return self.title

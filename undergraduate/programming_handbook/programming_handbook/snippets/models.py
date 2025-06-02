from django.db import models

# Create your models here.

from django.db import models
from common.models import BaseModel


class SnippetCategory(BaseModel):
    name = models.CharField(max_length=50, verbose_name="分类名称")

    class Meta:
        verbose_name = "代码片段分类"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.name


class Snippet(BaseModel):
    category = models.ForeignKey(SnippetCategory, on_delete=models.CASCADE, verbose_name="分类")
    language = models.CharField(max_length=50, verbose_name="语言")
    title = models.CharField(max_length=100, verbose_name="代码片段标题")
    description = models.TextField(verbose_name="代码片段描述")
    code = models.TextField(verbose_name="代码片段")
    author = models.CharField(max_length=50, verbose_name="作者")

    class Meta:
        verbose_name = "代码片段"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.title
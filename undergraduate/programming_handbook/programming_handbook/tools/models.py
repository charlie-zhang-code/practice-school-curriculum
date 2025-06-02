from django.db import models

# Create your models here.

from django.db import models
from common.models import BaseModel


class ToolCategory(BaseModel):
    name = models.CharField(max_length=50, verbose_name="分类名称")
    icon = models.CharField(max_length=100, verbose_name="分类图标名称", blank=True, null=True)

    class Meta:
        verbose_name = "工具分类"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.name


class Tool(BaseModel):
    category = models.ForeignKey(ToolCategory, on_delete=models.CASCADE, verbose_name="分类")
    name = models.CharField(max_length=50, verbose_name="工具名称")
    link_type = models.CharField(max_length=20, choices=(
        ('miniprogram', '小程序'),
        ('page', '本小程序页面')
    ), verbose_name="链接类型")
    link_target = models.CharField(max_length=200, verbose_name="链接目标")
    icon = models.CharField(max_length=100, verbose_name="工具图标名称")
    description = models.TextField(verbose_name="工具描述", blank=True)

    class Meta:
        verbose_name = "工具"
        verbose_name_plural = verbose_name
        ordering = ['category', 'name']

    def __str__(self):
        return self.name

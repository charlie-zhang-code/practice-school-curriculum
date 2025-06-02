from django.db import models

# Create your models here.

from django.db import models
from common.models import BaseModel


class CommandCategory(BaseModel):
    name = models.CharField(max_length=50, verbose_name="分类名称")

    class Meta:
        verbose_name = "命令分类"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.name


class Command(BaseModel):
    category = models.ForeignKey(CommandCategory, on_delete=models.CASCADE, verbose_name="分类")
    title = models.CharField(max_length=100, verbose_name="命令标题")
    description = models.TextField(verbose_name="命令描述")
    command = models.TextField(verbose_name="命令")

    class Meta:
        verbose_name = "命令速查"
        verbose_name_plural = verbose_name

    def __str__(self):
        return self.title
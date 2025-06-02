from django.db import models

# Create your models here.

from django.db import models


class BaseModel(models.Model):
    created_time = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    updated_time = models.DateTimeField(auto_now=True, verbose_name="更新时间")
    is_active = models.BooleanField(default=True, verbose_name="是否启用")

    class Meta:
        abstract = True
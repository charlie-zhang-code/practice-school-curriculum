from rest_framework import serializers
from .models import Article, ArticleCategory
from datetime import datetime
from django.utils.timezone import make_aware


class ArticleCategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = ArticleCategory
        fields = '__all__'


class ArticleSerializer(serializers.ModelSerializer):
    category = ArticleCategorySerializer(read_only=True)
    time_since_published = serializers.SerializerMethodField()

    class Meta:
        model = Article
        fields = '__all__'

    def get_time_since_published(self, obj):
        now = make_aware(datetime.now())
        delta = now - obj.publish_time

        if delta.days >= 365:
            years = delta.days // 365
            return f"{years}年前"
        elif delta.days >= 30:
            months = delta.days // 30
            return f"{months}个月前"
        elif delta.days >= 7:
            weeks = delta.days // 7
            return f"{weeks}周前"
        elif delta.days > 0:
            return f"{delta.days}天前"
        elif delta.seconds >= 3600:
            hours = delta.seconds // 3600
            return f"{hours}小时前"
        elif delta.seconds >= 60:
            minutes = delta.seconds // 60
            return f"{minutes}分钟前"
        else:
            return "刚刚"

from django.shortcuts import render
from rest_framework.decorators import action

from common.pagination import OptionalPageNumberPagination
from common.response import APIResponse

from common.views import BaseViewSet
from articles.models import Article, ArticleCategory
from articles.serializers import ArticleSerializer, ArticleCategorySerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import SearchFilter, OrderingFilter


class ArticleCategoryViewSet(BaseViewSet):
    queryset = ArticleCategory.objects.filter(is_active=True)
    serializer_class = ArticleCategorySerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['name']


class ArticleViewSet(BaseViewSet):
    queryset = Article.objects.filter(is_active=True)
    serializer_class = ArticleSerializer
    filter_backends = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_fields = ['author', 'category']
    search_fields = ['title', 'content', 'author']
    ordering_fields = ['publish_time']
    ordering = ['-publish_time']

    pagination_class = OptionalPageNumberPagination  # 添加这行

    @action(detail=False, methods=['get'])
    def recent(self, request):
        """获取最近10篇文章"""
        queryset = self.filter_queryset(self.get_queryset())
        recent_articles = queryset[:10]  # 获取前10条
        serializer = self.get_serializer(recent_articles, many=True)
        return APIResponse(data=serializer.data)

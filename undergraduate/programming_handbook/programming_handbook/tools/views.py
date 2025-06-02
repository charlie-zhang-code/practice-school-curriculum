from django.db.models import Count
from django.shortcuts import render
from rest_framework.decorators import action

from common.response import APIResponse
# Create your views here.

from common.views import BaseViewSet
from tools.models import Tool, ToolCategory
from tools.serializers import ToolSerializer, ToolCategorySerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import SearchFilter, OrderingFilter


class ToolCategoryViewSet(BaseViewSet):
    queryset = ToolCategory.objects.filter(is_active=True)
    serializer_class = ToolCategorySerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['name']


class ToolViewSet(BaseViewSet):
    queryset = Tool.objects.filter(is_active=True)
    serializer_class = ToolSerializer
    filter_backends = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_fields = ['category']
    search_fields = ['name']
    ordering_fields = ['created_time']
    ordering = ['-created_time']

    @action(detail=False, methods=['get'])
    def by_category(self, request):
        # 获取所有活跃分类
        categories = ToolCategory.objects.filter(is_active=True)

        # 预取关联的工具数据
        categories = categories.prefetch_related('tool_set')

        # 统计每个分类下的工具数量
        categories = categories.annotate(tool_count=Count('tool'))

        # 过滤掉没有工具的分类（可选）
        categories = categories.filter(tool_count__gt=0)

        # 构建响应数据
        data = []
        for category in categories:
            tools = category.tool_set.filter(is_active=True)
            serializer = self.get_serializer(tools, many=True)
            data.append({
                "category": ToolCategorySerializer(category).data,
                "tools": serializer.data,
                "count": category.tool_count
            })

        return APIResponse(data=data)

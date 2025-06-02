from django.shortcuts import render

# Create your views here.

from rest_framework import viewsets
from common.response import APIResponse


class BaseViewSet(viewsets.ModelViewSet):
    def list(self, request, *args, **kwargs):
        queryset = self.filter_queryset(self.get_queryset())

        # 检查是否应用了分页
        page = self.paginate_queryset(queryset)
        if page is not None:
            serializer = self.get_serializer(page, many=True)
            return self.get_paginated_response(serializer.data)

        # 无分页情况
        serializer = self.get_serializer(queryset, many=True)
        return APIResponse(data=serializer.data)

    def retrieve(self, request, *args, **kwargs):
        response = super().retrieve(request, *args, **kwargs)
        return APIResponse(data=response.data)

    def create(self, request, *args, **kwargs):
        response = super().create(request, *args, **kwargs)
        return APIResponse(data=response.data, message='创建成功')

    def update(self, request, *args, **kwargs):
        response = super().update(request, *args, **kwargs)
        return APIResponse(data=response.data, message='更新成功')

    def partial_update(self, request, *args, **kwargs):
        response = super().partial_update(request, *args, **kwargs)
        return APIResponse(data=response.data, message='部分更新成功')

    def destroy(self, request, *args, **kwargs):
        super().destroy(request, *args, **kwargs)
        return APIResponse(message='删除成功')

    def handle_exception(self, exc):
        response = super().handle_exception(exc)
        return APIResponse(
            code=response.status_code,
            data=None,
            message=str(exc),
            success=False,
            status=response.status_code
        )

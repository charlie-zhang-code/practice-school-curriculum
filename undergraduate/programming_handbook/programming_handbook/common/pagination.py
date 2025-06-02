from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response

from common.response import APIResponse


class OptionalPageNumberPagination(PageNumberPagination):
    """
    自定义分页类，请求中没有page参数时不分页，有page参数时分页
    """
    page_size_query_param = 'page_size'
    max_page_size = 1000

    def paginate_queryset(self, queryset, request, view=None):
        if 'page' not in request.query_params:
            return None
        return super().paginate_queryset(queryset, request, view)

    def get_paginated_response(self, data):
        return APIResponse(
            code=200,
            data={
                'total': self.page.paginator.count,
                'page': self.page.number,
                'size': self.get_page_size(self.request),
                'pages': self.page.paginator.num_pages,
                'record': data,
            },
            message='success',
            success=True
        )
"""
URL configuration for programming_handbook project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static

from rest_framework.exceptions import APIException
from common.response import APIResponse
from django.http import JsonResponse


def custom_exception_handler(exc, context):
    """
    自定义异常处理
    """
    if isinstance(exc, APIException):
        return APIResponse(
            code=exc.status_code,
            message=str(exc.detail),
            success=False,
            status=exc.status_code
        )

    # 处理其他类型的异常
    return APIResponse(
        code=500,
        message=str(exc),
        success=False,
        status=500
    )


handler500 = lambda request, *args, **kwargs: JsonResponse({
    'code': 500,
    'data': None,
    'message': '服务器内部错误',
    'success': False
}, status=500)

handler404 = lambda request, *args, **kwargs: JsonResponse({
    'code': 404,
    'data': None,
    'message': '未找到资源',
    'success': False
}, status=404)

handler400 = lambda request, *args, **kwargs: JsonResponse({
    'code': 400,
    'data': None,
    'message': '请求参数错误',
    'success': False
}, status=400)

urlpatterns = [
                  path('admin/', admin.site.urls),
                  path('api/banners/', include('banners.urls')),
                  path('api/articles/', include('articles.urls')),
                  path('api/commands/', include('commands.urls')),
                  path('api/tools/', include('tools.urls')),
                  path('api/snippets/', include('snippets.urls')),
              ] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)

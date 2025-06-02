from django.shortcuts import render

# Create your views here.

from common.views import BaseViewSet
from banners.models import Banner
from banners.serializers import BannerSerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import OrderingFilter

class BannerViewSet(BaseViewSet):
    queryset = Banner.objects.filter(is_active=True)
    serializer_class = BannerSerializer
    filter_backends = [DjangoFilterBackend, OrderingFilter]
    filterset_fields = ['link_type']
    ordering_fields = ['sort_order']
    ordering = ['sort_order']
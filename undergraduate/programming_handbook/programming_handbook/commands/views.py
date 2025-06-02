from django.shortcuts import render

# Create your views here.

from common.views import BaseViewSet
from commands.models import CommandCategory, Command
from commands.serializers import CommandCategorySerializer, CommandSerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import SearchFilter, OrderingFilter


class CommandCategoryViewSet(BaseViewSet):
    queryset = CommandCategory.objects.filter(is_active=True)
    serializer_class = CommandCategorySerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['name']


class CommandViewSet(BaseViewSet):
    queryset = Command.objects.filter(is_active=True)
    serializer_class = CommandSerializer
    filter_backends = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_fields = ['category']
    search_fields = ['title', 'description', 'command']
    ordering_fields = ['created_time']
    ordering = ['-created_time']

from django.shortcuts import render

# Create your views here.

from common.views import BaseViewSet
from snippets.models import SnippetCategory, Snippet
from snippets.serializers import SnippetCategorySerializer, SnippetSerializer
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.filters import SearchFilter, OrderingFilter

class SnippetCategoryViewSet(BaseViewSet):
    queryset = SnippetCategory.objects.filter(is_active=True)
    serializer_class = SnippetCategorySerializer
    filter_backends = [DjangoFilterBackend]
    filterset_fields = ['name']

class SnippetViewSet(BaseViewSet):
    queryset = Snippet.objects.filter(is_active=True)
    serializer_class = SnippetSerializer
    filter_backends = [DjangoFilterBackend, SearchFilter, OrderingFilter]
    filterset_fields = ['category', 'language']
    search_fields = ['title', 'description', 'author']
    ordering_fields = ['created_time']
    ordering = ['-created_time']
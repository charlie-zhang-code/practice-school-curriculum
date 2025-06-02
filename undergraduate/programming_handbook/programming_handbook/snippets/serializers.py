from rest_framework import serializers
from .models import SnippetCategory, Snippet


class SnippetCategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = SnippetCategory
        fields = '__all__'


class SnippetSerializer(serializers.ModelSerializer):
    category = SnippetCategorySerializer(read_only=True)

    class Meta:
        model = Snippet
        fields = '__all__'
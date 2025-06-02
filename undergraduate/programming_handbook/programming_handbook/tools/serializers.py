from rest_framework import serializers
from .models import Tool, ToolCategory


class ToolCategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = ToolCategory
        fields = '__all__'

class ToolSerializer(serializers.ModelSerializer):
    category = ToolCategorySerializer(read_only=True)

    class Meta:
        model = Tool
        fields = '__all__'
from rest_framework import serializers
from .models import CommandCategory, Command


class CommandCategorySerializer(serializers.ModelSerializer):
    class Meta:
        model = CommandCategory
        fields = '__all__'


class CommandSerializer(serializers.ModelSerializer):
    category = CommandCategorySerializer(read_only=True)

    class Meta:
        model = Command
        fields = '__all__'
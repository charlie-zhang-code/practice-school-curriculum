from rest_framework import serializers
from .models import Banner


class BannerSerializer(serializers.ModelSerializer):
    image = serializers.ImageField(use_url=True)

    class Meta:
        model = Banner
        fields = '__all__'
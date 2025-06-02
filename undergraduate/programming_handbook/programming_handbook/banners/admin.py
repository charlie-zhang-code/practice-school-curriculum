from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models import Banner
from django.utils.html import format_html


@admin.register(Banner)
class BannerAdmin(admin.ModelAdmin):
    list_display = ('title', 'image_preview', 'link_type', 'link_target', 'sort_order', 'is_active')
    list_editable = ('sort_order', 'is_active')
    list_filter = ('is_active', 'link_type')
    search_fields = ('title', 'link_target')
    ordering = ('sort_order',)
    list_per_page = 20

    fieldsets = (
        ('基本信息', {
            'fields': ('title', 'image', 'is_active', 'sort_order')
        }),
        ('链接设置', {
            'fields': ('link_type', 'link_target')
        }),
    )

    def image_preview(self, obj):
        return format_html('<img src="{}" style="max-height:50px;max-width:50px;" />',
                    obj.image.url) if obj.image else ''

    image_preview.short_description = '图片预览'


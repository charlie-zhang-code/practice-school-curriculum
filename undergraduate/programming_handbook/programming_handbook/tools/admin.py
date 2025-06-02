from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models import Tool
from django.utils.html import format_html

from django.contrib import admin
from .models import ToolCategory, Tool


class ToolInline(admin.TabularInline):
    model = Tool
    extra = 1
    fields = ('name', 'link_type', 'is_active')


@admin.register(ToolCategory)
class ToolCategoryAdmin(admin.ModelAdmin):
    list_display = ('name', 'icon', 'tool_count', 'is_active')
    list_editable = ('is_active',)
    inlines = [ToolInline]
    fields = ('name', 'icon', 'is_active')

    def tool_count(self, obj):
        return obj.tool_set.count()

    tool_count.short_description = '工具数量'


@admin.register(Tool)
class ToolAdmin(admin.ModelAdmin):
    list_display = ('name', 'category', 'icon', 'link_type', 'link_target', 'is_active')
    list_editable = ('is_active',)
    list_filter = ('category', 'link_type', 'is_active')
    search_fields = ('name', 'description', 'link_target')
    list_per_page = 20

    fieldsets = (
        ('基本信息', {
            'fields': ('category', 'name', 'is_active')
        }),
        ('链接设置', {
            'fields': ('link_type', 'link_target')
        }),
        ('图标和描述', {
            'fields': ('icon', 'description')
        }),
    )


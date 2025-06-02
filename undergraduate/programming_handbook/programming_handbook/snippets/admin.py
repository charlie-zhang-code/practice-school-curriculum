from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models import SnippetCategory, Snippet
from django.utils.html import format_html


class SnippetInline(admin.TabularInline):
    model = Snippet
    extra = 1
    fields = ('title', 'language', 'is_active')
    classes = ('collapse',)


@admin.register(SnippetCategory)
class SnippetCategoryAdmin(admin.ModelAdmin):
    list_display = ('name', 'snippet_count', 'is_active')
    list_editable = ('is_active',)
    inlines = [SnippetInline]

    def snippet_count(self, obj):
        return obj.snippet_set.count()

    snippet_count.short_description = '代码片段数量'


@admin.register(Snippet)
class SnippetAdmin(admin.ModelAdmin):
    list_display = ('title', 'category', 'language', 'author', 'created_time', 'is_active')
    list_editable = ('is_active',)
    list_filter = ('category', 'language', 'is_active')
    search_fields = ('title', 'description', 'code', 'author')
    date_hierarchy = 'created_time'
    list_per_page = 20

    fieldsets = (
        ('基本信息', {
            'fields': ('category', 'title', 'language', 'author', 'is_active')
        }),
        ('代码内容', {
            'fields': ('description', 'code')
        }),
    )

    def save_model(self, request, obj, form, change):
        if not obj.author:
            obj.author = request.user.username
        super().save_model(request, obj, form, change)
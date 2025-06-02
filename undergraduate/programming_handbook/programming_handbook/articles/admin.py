from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models import Article, ArticleCategory
from django.utils.html import format_html

class ArticleInline(admin.TabularInline):
    model = Article
    extra = 1
    fields = ('title', 'author', 'publish_time', 'is_active')
    classes = ('collapse',)


@admin.register(ArticleCategory)
class ArticleCategoryAdmin(admin.ModelAdmin):
    list_display = ('name','article_count', 'is_active')
    list_editable = ('is_active',)
    inlines = [ArticleInline]

    def article_count(self, obj):
        return obj.article_set.count()

    article_count.short_description = '文章数量'

@admin.register(Article)
class ArticleAdmin(admin.ModelAdmin):
    list_display = ('title', 'category', 'author', 'publish_time', 'is_active', 'content_preview')
    list_editable = ('is_active',)
    list_filter = ('category', 'is_active', 'publish_time')
    search_fields = ('title', 'content', 'author')
    date_hierarchy = 'publish_time'
    list_per_page = 15

    fieldsets = (
        ('基本信息', {
            'fields': ('category', 'title', 'author', 'is_active')
        }),
        ('内容', {
            'fields': ('content',)
        }),
        ('时间', {
            'fields': ('publish_time',)
        }),
    )

    def content_preview(self, obj):
        return format_html('<span title="{}">{}...</span>',
                           obj.content,
                           obj.content[:50] if obj.content else '')

    content_preview.short_description = '内容预览'
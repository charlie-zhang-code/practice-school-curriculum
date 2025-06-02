from django.contrib import admin

# Register your models here.

from django.contrib import admin
from .models import CommandCategory, Command


class CommandInline(admin.TabularInline):
    model = Command
    extra = 1
    fields = ('title', 'command', 'description', 'is_active')
    classes = ('collapse',)


@admin.register(CommandCategory)
class CommandCategoryAdmin(admin.ModelAdmin):
    list_display = ('name', 'command_count', 'is_active')
    list_editable = ('is_active',)
    inlines = [CommandInline]

    def command_count(self, obj):
        return obj.command_set.count()

    command_count.short_description = '命令数量'


@admin.register(Command)
class CommandAdmin(admin.ModelAdmin):
    list_display = ('title', 'category', 'command_short', 'description_short', 'is_active')
    list_editable = ('is_active',)
    list_filter = ('category', 'is_active')
    search_fields = ('title', 'command', 'description')
    list_per_page = 20

    fieldsets = (
        ('基本信息', {
            'fields': ('category', 'title', 'is_active')
        }),
        ('命令详情', {
            'fields': ('command', 'description')
        }),
    )

    def command_short(self, obj):
        return obj.command[:50] + '...' if len(obj.command) > 50 else obj.command

    command_short.short_description = '命令'

    def description_short(self, obj):
        return obj.description[:50] + '...' if obj.description and len(obj.description) > 50 else obj.description

    description_short.short_description = '描述'
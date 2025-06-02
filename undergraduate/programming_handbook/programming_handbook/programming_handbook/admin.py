from django.contrib import admin

admin.site.site_header = '编程宝典管理系统'
admin.site.site_title = '编程宝典后台管理'
admin.site.index_title = '欢迎使用编程宝典管理系统'


# 自定义应用分组
def get_app_list(self, request):
    app_list = super(admin.AdminSite, self).get_app_list(request)

    # 重新组织应用
    custom_app_list = [
        {
            'name': '内容管理',
            'app_label': 'content_group',
            'models': [
                model for app in app_list
                for model in app['models']
                if app['name'] in ['Banners', 'Articles']
            ]
        },
        {
            'name': '代码资源',
            'app_label': 'code_group',
            'models': [
                model for app in app_list
                for model in app['models']
                if app['name'] in ['Snippets', 'Commands']
            ]
        },
        {
            'name': '实用工具',
            'app_label': 'tools_group',
            'models': [
                model for app in app_list
                for model in app['models']
                if app['name'] in ['Tools']
            ]
        }
    ]

    # 添加剩余的其他应用
    other_apps = [app for app in app_list if
                  app['name'] not in ['Banners', 'Articles', 'Snippets', 'Commands', 'Tools']]
    return custom_app_list + other_apps


admin.AdminSite.get_app_list = get_app_list

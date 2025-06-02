from django.urls import path
from rest_framework.routers import DefaultRouter
from .views import ArticleViewSet, ArticleCategoryViewSet

router = DefaultRouter()
router.register(r'categories', ArticleCategoryViewSet, basename='article-categories')
router.register(r'', ArticleViewSet, basename='articles')

urlpatterns = router.urls
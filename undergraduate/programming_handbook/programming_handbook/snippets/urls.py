from django.urls import path
from rest_framework.routers import DefaultRouter
from .views import SnippetCategoryViewSet, SnippetViewSet

router = DefaultRouter()
router.register(r'categories', SnippetCategoryViewSet, basename='snippet-categories')
router.register(r'', SnippetViewSet, basename='snippets')

urlpatterns = router.urls
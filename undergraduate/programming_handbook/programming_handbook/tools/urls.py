from django.urls import path
from rest_framework.routers import DefaultRouter
from .views import ToolViewSet, ToolCategoryViewSet

router = DefaultRouter()
router.register(r'categories', ToolCategoryViewSet, basename='tool-categories')
router.register(r'', ToolViewSet, basename='tools')

urlpatterns = router.urls
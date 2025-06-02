from django.urls import path
from rest_framework.routers import DefaultRouter
from .views import CommandCategoryViewSet, CommandViewSet

router = DefaultRouter()
router.register(r'categories', CommandCategoryViewSet, basename='command-categories')
router.register(r'', CommandViewSet, basename='commands')

urlpatterns = router.urls
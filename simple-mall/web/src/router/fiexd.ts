import Admin from "@/layouts/Admin.vue";
import Web from "@/layouts/Web.vue";
import { RouteRecordRaw } from "vue-router";

export const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "root",
    redirect: "/web",
    children: [
      {
        path: "/web",
        name: "web",
        component: Web,
        redirect: "/home",
        children: [
          {
            path: "/home",
            name: "home",
            component: () => import("../views/web/index.vue"),
          },
          {
            path: "/search",
            name: "search",
            component: () => import("../views/web/search/index.vue"),
          },
          {
            path: "/product/:id",
            name: "product",
            component: () => import("../views/web/product/index.vue"),
          },
          {
            path: "/order/:id",
            name: "order",
            component: () => import("../views/web/order/index.vue"),
          },
          {
            path: "/comment/:id",
            name: "comment",
            component: () => import("../views/web/comment/index.vue"),
          },
          {
            path: "/problem/:id",
            name: "problem",
            component: () => import("../views/web/problem/index.vue"),
          },
          {
            path: "/user/:id",
            name: "user",
            component: () => import("../views/web/user/index.vue"),
          },
          {
            path: "/trolley",
            name: "trolley",
            component: () => import("../views/web/trolley/index.vue"),
          },
          {
            path: "/settings",
            name: "settings",
            component: () => import("../views/web/settings/index.vue"),
          },
          {
            path: "/test",
            name: "test",
            component: () => import("../views/web/test/index.vue"),
          },
        ],
      },
      {
        path: "/admin",
        name: "admin",
        component: Admin,
        redirect: "/admin/index",
        children: [
          {
            path: "/admin/index",
            name: "admin/index",
            component: () => import("../views/admin/index.vue"),
          },
          {
            path: "/admin/user",
            name: "admin/user",
            component: () => import("../views/admin/user/index.vue"),
          },
          {
            path: "/admin/product",
            name: "admin/product",
            component: () => import("../views/admin/product/index.vue"),
          },
          {
            path: "/admin/category",
            name: "admin/category",
            component: () => import("../views/admin/category/index.vue"),
          },
          {
            path: "/admin/specification",
            name: "admin/specification",
            component: () => import("../views/admin/specification/index.vue"),
          },
          {
            path: "/admin/comment",
            name: "admin/comment",
            component: () => import("../views/admin/comment/index.vue"),
          },
          {
            path: "/admin/problem",
            name: "admin/problem",
            component: () => import("../views/admin/problem/index.vue"),
          },
          {
            path: "/admin/order",
            name: "admin/order",
            component: () => import("../views/admin/order/index.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/sign/login/index.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("../views/sign/register/index.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    name: "NotFound",
    component: () => import("../views/web/error/404/index.vue"),
  },
];

/* eslint-disable */
/* prettier-ignore */
// @ts-nocheck
// Generated by vite-plugin-uni-pages

interface NavigateToOptions {
  url: "/pages/index/index" |
       "/pages/account/index" |
       "/pages/article/index" |
       "/pages/articles/index" |
       "/pages/code/index" |
       "/pages/command/index" |
       "/pages/example/index" |
       "/pages/tools/index" |
       "/pages/tools/collection/coutdown" |
       "/pages/tools/collection/dice" |
       "/pages/tools/collection/hotAnalyzer" |
       "/pages/tools/collection/jsonFormate";
}
interface RedirectToOptions extends NavigateToOptions {}

interface SwitchTabOptions {
  url: "/pages/index/index" | "/pages/command/index" | "/pages/tools/index" | "/pages/code/index"
}

type ReLaunchOptions = NavigateToOptions | SwitchTabOptions;

declare interface Uni {
  navigateTo(options: UniNamespace.NavigateToOptions & NavigateToOptions): void;
  redirectTo(options: UniNamespace.RedirectToOptions & RedirectToOptions): void;
  switchTab(options: UniNamespace.SwitchTabOptions & SwitchTabOptions): void;
  reLaunch(options: UniNamespace.ReLaunchOptions & ReLaunchOptions): void;
}

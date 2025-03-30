import { createApp } from "vue";
import "@/assets/style.css";
import App from "@/App.vue";
import { setupRouter } from "./router";
import { setupPinia } from "./stores";
import 'virtual:uno.css'
import 'element-plus/dist/index.css'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

async function setupApp() {
  const app = createApp(App);

  // 引入Pinia
  await setupPinia(app);

  // 引入路由
  await setupRouter(app);

  for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

  // 挂载Vue
  app.mount("#app");
}

setupApp();

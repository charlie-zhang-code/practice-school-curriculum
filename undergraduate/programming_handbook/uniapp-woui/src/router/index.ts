/// <reference types="@uni-helper/vite-plugin-uni-pages/client" />
import { pages, subPackages } from 'virtual:uni-pages'

function generateRoutes() {
  const routes = pages.map((page) => {
    const newPath = `/${page.path}`
    return { ...page, path: newPath }
  })
  if (subPackages && subPackages.length > 0) {
    subPackages.forEach((subPackage) => {
      const subRoutes = subPackage.pages.map((page: any) => {
        const newPath = `/${subPackage.root}/${page.path}`
        return { ...page, path: newPath }
      })
      routes.push(...subRoutes)
    })
  }
  return routes
}

export const router = createRouter({
  routes: generateRoutes(),
})
router.beforeEach((to, from, next) => {
  // console.log(to)
  // console.log(from)
  next()
})
router.afterEach((to, from) => {
  // console.log(to)
  // console.log(from)
})

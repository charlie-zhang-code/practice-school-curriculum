import { createAlova } from 'alova'
import VueHook from 'alova/vue'
import adapterFetch from 'alova/fetch'

const baseURL = 'http://127.0.0.1:8000'

export const request = createAlova({
    statesHook: VueHook,
    requestAdapter: adapterFetch(),
    cacheFor: null, // 不缓存数据
    baseURL: baseURL,
    responded: response => response.json()
})

export function fetchConfigUserNoToken(username: string) {
    return request.Post(`/config/user/username`, { username })
}

export function fetchConfigUser(username: string, token: string) {
    return request.Post(`/config/user`, { username, token })
}

export function fetchUserInfo() {
    return request.Get(`/config/user`)
}

export function fetchReposLanguages() {
    return request.Get(`/user/languages`)
}

export function fetchReposAction() {
    return request.Get(`/user/action`)
}

export function fetchRepos() {
    return request.Get(`/user/repos`)
}

export function fetchReposDetailData() {
    return request.Get(`/user/repos/data`)
}

export function fetchUserAction() {
    return request.Get(`/user/commits`)
}
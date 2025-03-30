const base = {
    get() {
                return {
            url : "http://localhost:8080/mall/",
            name: "mall",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/mall/front/index.html'
        };
            },
    getProjectName(){
        return {
            projectName: "Simple Mall"
        } 
    }
}
export default base

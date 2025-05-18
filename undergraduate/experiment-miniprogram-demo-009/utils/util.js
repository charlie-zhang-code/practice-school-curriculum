// 获取当前日期，格式为YYYY-MM-DD
function getCurrentDate() {
    const date = new Date()
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    
    return `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`
  }
  
  module.exports = {
    getCurrentDate
  }
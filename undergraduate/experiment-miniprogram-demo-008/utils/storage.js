const STORAGE_KEY = 'photo_records';

// 获取所有记录
function getAllRecords() {
  return wx.getStorageSync(STORAGE_KEY) || [];
}

// 保存所有记录
function saveAllRecords(records) {
  wx.setStorageSync(STORAGE_KEY, records);
}

// 添加新记录
function addRecord(record) {
  const records = getAllRecords();
  records.unshift(record); // 新记录添加到前面
  saveAllRecords(records);
}

// 更新记录
function updateRecord(id, updates) {
  const records = getAllRecords();
  const index = records.findIndex(r => r.id === id);
  if (index !== -1) {
    records[index] = {...records[index], ...updates};
    saveAllRecords(records);
  }
}

// 获取收藏的记录
function getFavorites() {
  const records = getAllRecords();
  return records.filter(r => r.isFavorite);
}

module.exports = {
  getAllRecords,
  addRecord,
  updateRecord,
  getFavorites,
  saveAllRecords // 添加这一行
};



//key是status
function get(key){
  var value = wx.getStorageSync(key)

  //没有在内存中取到状态
  if (!value) {
    var value = getApp().globalData[key];
  }
  return value
}

module.exports = {
  get
}
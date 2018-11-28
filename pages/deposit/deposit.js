// pages/deposit/deposit.js

var mydefs = require("../../utils/mydefs.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  deposit: function() {
    var that = this;
    
    //获取用户的手机号
    var phoneNum = mydefs.get('phoneNum') 

    wx.showModal({
      title: '提示',
      content: '是否选择充值押金',
      success: function(res){
        if (res.confirm){
          console.log('去充值')
          //先调用微信小程序的支付接口，如果成功，向后台发送请求，更新用户押金
          wx.showLoading({
            title: '充值中',
            mask:true
          })
          //如果成功，调用模拟的微信支付接口
          //向后台发送请求，更新押金
          wx.request({
            url: 'http://localhost:8080/user/deposit ',
            method:'POST',
            data:{
              phoneNum : phoneNum,
              deposit : 299
            },
            success:function(){
              //关闭加载对话框 
              wx.hideLoading()
              wx.navigateTo({
                url: '../identify/identify',
              })
           }
          })
        }
      }
    })
  
  }

})
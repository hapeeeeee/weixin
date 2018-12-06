// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    countryCodes: ["86", "80", "84", "87"],
    countryCodeIndex: 0,
    phoneNum: ""
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

  bindCountryCodeChange: function (e) {
    //console.log('picker country code 发生选择改变，携带值为', e.detail.value);
    this.setData({
      countryCodeIndex: e.detail.value
    })
  },

  inputPhoneNum: function (e) {
    this.setData({
      phoneNum: e.detail.value
    })
  },

  genVerifyCode: function () {
    var index = this.data.countryCodeIndex;
    var countryCode = this.data.countryCodes[index];
    var phoneNum = this.data.phoneNum;
    console.log(phoneNum)
    wx.request({
      //小程序访问的网络请求协议必须是https，url里面不能有端口号
      url: "http://localhost:8080/user/genCode",
      method:'GET',//传递参数

      data: {
         "countryCode":countryCode,
         "phoneNum":phoneNum 
      },
      success:function(res){
        wx.showToast({
          title: '已发送，请注意查收',
        })

      }

    })
  },
  

  //提交表单
  formSubmit: function (e) {
    //获取用户输入的手机号和验证码
    var phoneNum = e.detail.value.phoneNum
    var code  = e.detail.value.verifyCode
    console.log(phoneNum,code)
    wx.request({
      url: 'http://localhost:8080/user/verify',
      
      //POST请求是json，后台接收的是字符串。所以收不到

      method: 'GET',
      data:{
        phoneNum: phoneNum,
        code : code 
      },
      success: function(res){
        //res.data是后台的返回值
        console.log(res)
        //校验成功
        if (res.data){
          wx.request({
            url: 'http://localhost:8080/user/register',
            method:'POST',
            
            data:{
              phoneNum : phoneNum,
              regDate : new Date(),
              status : 1
            },
            //用户信息保存成功，跳转页面
            success:function(res){
              if(res.data){
                wx.navigateTo({
                  url: '../deposit/deposit',
                })
                //记录用户状态，0表示未注册，1表示绑定完手机号 2表示已经实名认证 
                
                //更新getApp().global中的数据，是更新内存中的数据
                getApp().globalData.status = 1
                getApp().globalData.phoneNum = phoneNum

                //将用户信息保存到手机的手机的存储卡中
                wx.setStorageSync('status', 1)
                wx.setStorageSync('phoneNum', phoneNum)

              
              }else{//保存失败
                wx.showModal({
                  title: '错误',
                  content: '对不起，服务器错误，请稍后再试！',
                })
              }
            }

          })
          

        }else{ //校验失败
          wx.showModal({
            title: '提示',
            content: '您的验证码有误，请重新核对',
            showCancel:false
          })
        }

      }
    })
  }



})
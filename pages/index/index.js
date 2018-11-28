var mydefs = require('../../utils/mydefs') 

Page({
  data: {
    longtiude: 0,
    latitude: 0,
    controls:[],
    markers:[]
    
  },
 
  onLoad: function () {
    var that = this;
    wx.getLocation({
      success: function(res) {
        var longtiude = res.longitude
        var latitude  = res.latitude
        that.setData({
          longtiude: longtiude,
          latitude:latitude
        })
      },
    })
  
    wx.getSystemInfo({
      success: function(res) {
        var windowwidth  = res.windowWidth;
        var windowheight = res.windowHeight;
        that.setData({
          controls: [
            
            {  //第一个控件,扫码开锁
              id: 1,
              iconPath: '/images/saoma.png',//控件的背景图片
              position: {                     //图片相对于页面的位置
                width: 180,
                height: 60,
                left: windowwidth / 2 - 90,
                top: windowheight -80
              },
              clickable:true
            },

            { //第二个控件，充值按钮
              id : 2,
              iconPath : '/images/chongzhi.png',
              position: {
                width  : 30,
                height : 30,
                left : windowwidth -30,
                top  : windowheight - 80
              },
              clickable: true
            },

            { //第三个控件，保修按钮
              id : 3,
              iconPath :'/images/baoxiu.png',
              position:{
                width  : 30,
                height : 30,
                left : windowwidth - 30,
                top  : windowheight- 50
              },
              clickable: true
            },

            { //第四个控件重新定位
              id : 4,
              iconPath : '/images/chongxindingwei.png',
              position:{
                width  : 40,
                height : 40,
                left : 20,
                top : windowheight - 60
              },
              clickable:true
            },

            { //第五个控件，自己的位置
              id : 5,
              iconPath : '/images/danche.png',
              position:{
                width: 40,
                height :60,
                left: windowwidth / 2 -20,
                top : windowheight /2 -50
              },
              clickable:true
            },

            { //第六个控件，添加单车
              id: 6,
              iconPath: '/images/add.jpeg',
              position: {
                width: 40,
                height: 40,
              },
              clickable: true
            }


          ]
        })
      },
    })
  },

  //控件被点击
  controltap:function(e){
    var that = this;
    var clickid = e.controlId;
    
    switch(clickid){
      //点击扫码开锁
      case 1:{

       var status = mydefs.get('status')        
      //根据用户状态，跳转页面  
        
        //如果status为0 ，用户未登录，跳转到手机注册登录页面
        if (status == 0){
          wx.navigateTo({
            url: '../register/register',
          })
        }else if(status == 1){
          wx.navigateTo({
            url: '../deposit/deposit',
          })
        }
        
        break
      }

      case 4:{
        this.mapCtx.moveToLocation();
        break
      }
      
      case 6:{
        //var bikes = that.data.markers;
        
        //获取当前位置，此处有个bug，必须定义临时变量，不然电脑卡死
        this.mapCtx.getCenterLocation({
          success:function(res){
            var log = res.longitude;
            var lat = res.latitude;
            
            // bikes.push(
            //   {
            //     iconPath: "/images/bike.jpeg",
            //     latitude: lat,
            //     longitude: log,
            //     width: 50,
            //     height: 50
            //   }
             
            // )
            // that.setData({
            //   markers: bikes
            // })

            //将添加的数据发送到后台
            wx.request({
              url: 'http://localhost:8080/bike/add',
              data:{
                latitude: lat,
                longitude: log,
                status:0
              },
              method:"post",
              success:function(res){
                console.log(res)
              }
            })
          }
          
        })
        
        break
      }
  
    }

  
  },

  //地图视野发生变化，移动后地图视触发的事件

  regionchange :function(e){
    // var that = this
    // console.log(e)
    // var etype = e.type;
    // if (etype == 'end'){
    //   this.mapCtx.getCenterLocation({
    //     success: function (res) {
    //       console.log(res.longitude)
    //       console.log(res.latitude)
    //     }
    //   })
    // }
  }
  ,

  onReady:function(){
    //创建map上下文
    this.mapCtx = wx.createMapContext('myMap')
  }     
})

function load() {
  $(".head").load("zxqy/head.html");
  $(".foot").load("zxqy/foot.html");
}

// 微信显示
function wx() {
  $(".wx").on("mousemove", function () {
    $(".wximg").css("display", "block");
  })
  $(".wx").on("mouseout", function () {
    $(".wximg").css("display", "none");
  })
  $(".wximg").on("mousemove", function () {
    $(".wximg").css("display", "block");
  })
  $(".wximg").on("mouseout", function () {
    $(".wximg").css("display", "none");
  })
}

// 导航显示
function columsDis() {
  var colums = $(".colums").css("width");
  $(".index-but span").on("click", function () {
    var widths = $(".index-right").css("right");
    if (widths < colums) {
      $(".index-right").animate({ right: '-156px' });
    }
    if (widths == "-156px") {
      $(".index-right").animate({ right: '-1px' });
    }
  })
}

function echartsMap() {
  var myChart1 = echarts.init(document.getElementById("map2"));
  var myChart2 = echarts.init(document.getElementById("map3"));
  var myChart3 = echarts.init(document.getElementById("map4"));

  var ROOT_PATH = "http://127.0.0.1:5500/2020%E4%B8%AD%E5%B0%8F%E4%BC%81%E4%B8%9A/"

  // 地图2
  window.onresize = myChart1.resize;
  myChart1.showLoading();

  $.get(ROOT_PATH + '/js/150600.json', function (geoJson) {

    myChart1.hideLoading();
    echarts.registerMap('Ordos', geoJson);

    myChart1.setOption(option = {
      grid: {
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
      },
      series: [
        {
          name: '',
          type: 'map',
          mapType: 'Ordos', // 自定义扩展图表类型
          itemStyle: {
            normal: {
              label: {
                show: true,//默认是否显示省份名称   
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#4bc3ff",
              borderWidth: 2,
              borderColor: '#FFFFFF'
            },
            emphasis: {
              label: {
                show: true,//选中状态是否显示省份名称
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#24dfbe",
            },
          },
        }
      ]
    });
    myChart1.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: 7 });

    $(".index-sixth-right .second-msg ul").eq(7).css("display", "block");
    myChart1.on('mouseover', function (e) {
      $(".index-sixth-right .second-msg ul").css("display", "none");
      $(".index-sixth-right .second-msg ul").eq(e.dataIndex).css("display", "block");
      for (let i = 0; i < 8; i++) {
        if (e.dataIndex != i) {
          myChart1.dispatchAction({ type: 'downplay', seriesIndex: 0, dataIndex: i });
        }
      }
    });

    myChart1.on('mouseout', function (e) {
      index = e.dataIndex;
      myChart1.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: e.dataIndex });
    });
  });

  // 地图3
  window.onresize = myChart2.resize;
  myChart2.showLoading();

  $.get(ROOT_PATH + '/js/150600.json', function (geoJson) {

    myChart2.hideLoading();
    echarts.registerMap('Ordos', geoJson);

    myChart2.setOption(option = {
      grid: {
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
      },
      series: [
        {
          name: '',
          type: 'map',
          mapType: 'Ordos', // 自定义扩展图表类型
          itemStyle: {
            normal: {
              label: {
                show: true,//默认是否显示省份名称   
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#4bc3ff",
              borderWidth: 2,
              borderColor: '#FFFFFF'
            },
            emphasis: {
              label: {
                show: true,//选中状态是否显示省份名称
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#24dfbe",
            },
          },
        }
      ]
    });
    myChart2.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: 7 });

    $(".index-sixth-right .third-msg ul").eq(7).css("display", "block");
    myChart2.on('mouseover', function (e) {
      $(".index-sixth-right .third-msg ul").css("display", "none");
      $(".index-sixth-right .third-msg ul").eq(e.dataIndex).css("display", "block");
      for (let i = 0; i < 8; i++) {
        if (e.dataIndex != i) {
          myChart2.dispatchAction({ type: 'downplay', seriesIndex: 0, dataIndex: i });
        }
      }
    });

    myChart2.on('mouseout', function (e) {
      index = e.dataIndex;
      myChart2.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: e.dataIndex });
    });
  });

  // 地图4
  window.onresize = myChart3.resize;
  myChart3.showLoading();

  $.get(ROOT_PATH + '/js/150600.json', function (geoJson) {

    myChart3.hideLoading();
    echarts.registerMap('Ordos', geoJson);

    myChart3.setOption(option = {
      grid: {
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
      },
      series: [
        {
          name: '',
          type: 'map',
          mapType: 'Ordos', // 自定义扩展图表类型
          itemStyle: {
            normal: {
              label: {
                show: true,//默认是否显示省份名称   
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#4bc3ff",
              borderWidth: 2,
              borderColor: '#FFFFFF'
            },
            emphasis: {
              label: {
                show: true,//选中状态是否显示省份名称
                textStyle: {
                  fontSize: 14,
                  color: '#FFFFFF'
                }
              },
              areaColor: "#24dfbe",
            },
          },
        }
      ]
    });
    myChart3.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: 7 });

    $(".index-sixth-right .fouth-msg ul").eq(7).css("display", "block");
    myChart3.on('mouseover', function (e) {
      $(".index-sixth-right .fouth-msg ul").css("display", "none");
      $(".index-sixth-right .fouth-msg ul").eq(e.dataIndex).css("display", "block");
      for (let i = 0; i < 8; i++) {
        if (e.dataIndex != i) {
          myChart3.dispatchAction({ type: 'downplay', seriesIndex: 0, dataIndex: i });
        }
      }
    });

    myChart3.on('mouseout', function (e) {
      index = e.dataIndex;
      myChart3.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: e.dataIndex });
    });
  });
}

// 政策切换
function zcClick() {
  $(".zcList ul").eq(0).css("display", "block");
  $(".zc i").on("click", function () {
    var ind = $(this).parent("span").index();
    $(this).addClass("icon").parent("span").siblings().children("i").removeClass();
    $(".zcList ul").css("display", "none");
    $(".zcList ul").eq(ind).css("display", "block");
  })
}

// 尾部显示
function footDis() {
  var footHeight = $(".foot").outerHeight();
  var bootoms = $(".foot").css("bottom");
  $(".footbut a").on("click", function () {
    var widths = $(".foot").css("bottom");
    if (widths == "-" + footHeight + "px") {
      $(".foot").animate({ bottom: "0" }, 500);
    }
  })

  $(document.body).find(":not(.foot,.foot a)").on("click", function () {
    if ($(".foot").css("bottom") == "0px") {
      $(".foot").animate({ bottom: "-" + footHeight + "px" }, 500);
    }
  })

  $(".foot a").on("click", function (event) {
    event.stopPropagation();
    $(".foot").stop();
  })

  $(".linksMore ul li").on("click", function (event) {
    event.stopPropagation();
    $(".foot").stop();
  })
}

// 地图切换
function maps() {
  $(".index-sixth-center ul li").eq(0).css("display", "block");
  $(".index-sixth-right>div").eq(0).css("display", "block");
  $(".index-sixth-left ul li").on("click", function () {
    var ind = $(this).index();
    $(this).addClass("bks").siblings().removeClass();
    $(".index-sixth-center ul li").css("display", "none");
    $(".index-sixth-center ul li").eq(ind).css("display", "block");
    $(".index-sixth-right>div").css("display", "none");
    $(".index-sixth-right>div").eq(ind).css("display", "block");
  })
}

// 课程切换
function classShow() {
  $(".index-fifth-left-list>ul").eq(0).css("display", "block");
  $(".classList span").on("mousemove", function () {
    var index = $(this).index();
    $(this).addClass("fonts").siblings().removeClass();
    $(".index-fifth-left-list>ul").css("display", "none");
    $(".index-fifth-left-list>ul").eq(index).css("display", "block");
  })
}

// 列表页返回
function columses() {
  $(".index-rights>div").click(function () {
    window.location = "/";
  });
}

// 友情链接切换
function footMove() {
  $(".linkList>table").eq(0).css("display", "block");
  $(".linksMore ul li").on("click", function (event) {
    event.stopPropagation();
    $(".foot").stop();
    var index = $(this).index();
    $(this).addClass("borders").siblings().removeClass();
    $(".linkList>table").css("display", "none");
    $(".linkList>table").eq(index).css("display", "block");
  })
}

function doSomething() {
  var menuItemNum = $(".menu-item").length;
  var heights = $("body").height();
  console.log(heights);
  var angle = 160;
  var distance = heights / 1; //控制小球与大球的距离
  var startingAngle = 180 + (-angle / 2);
  var slice = angle / (menuItemNum - 1);
  TweenMax.globalTimeScale(0.8); //动画时间
  $(".menu-item").each(function (i) {
    var angle = startingAngle - (slice * i);
    $(this).css({
      transform: "rotate(" + (348.7 + angle) + "deg)" //控制小球的角度
    })
  })

  $(window).load(function () {
    pressHandler()
  })

  function pressHandler(event) {
    openMenu()
  }
  function openMenu() {
    $(".menu-item").each(function (i) {
      var delay = i * 0.09; //小球出来的时间
      var $bounce = $(this).children(".menu-item-bounce");
      TweenMax.fromTo($bounce, 0.1, {
        transformOrigin: "50% 50%"
      }, {
        delay: delay,
        scaleX: 0.8,
        scaleY: 1.2,
        force3D: true,
        ease: Quad.easeInOut,
        onComplete: function () {
          TweenMax.to($bounce, 0.15, {
            // scaleX:1.2,
            scaleY: 0.7,
            force3D: true,
            ease: Quad.easeInOut,
            onComplete: function () {
              TweenMax.to($bounce, 3, {
                scaleX: 1,
                scaleY: 1,
                force3D: true,
                ease: Elastic.easeOut,
                easeParams: [1.1, 0.12]
              })
            }
          })
        }
      });
      TweenMax.to($(this).children(".menu-item-button"), 0.5, {
        delay: delay,
        y: distance,
        force3D: true,
        ease: Quint.easeInOut
      });
    })
  }
}

function getUrlParam(name) {
  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg);  //匹配目标参数
  if (r != null) return unescape(r[2]); return null; //返回参数值
}

// 赋值
function setItem() {
  var itemcode = getUrlParam("itemcode");
  console.log(itemcode);
  var hrefs = $(".menu-items").find("a");
  for (var i = 0; i < hrefs.length; i++) {
    console.log(hrefs.eq(i).attr("href"));
    hrefs.eq(i).attr("href", hrefs.eq(i).attr("href") + "?itemcode=" + itemcode);
  }
  console.log(hrefs);
}

function getData() {
  $.ajax({
    url: 'http://ordoszxqy.org.cn/hr/getdata',
    type: 'get',
    success: function (data) {
      var res = $.parseJSON(data);
      console.log(res);
      $(".org").text(res[0].orgsize);
      $(".serviceActive").text(res[2].serviceActive);
      $(".serviceorgsize").text(res[1].serviceorgsize);
      $(".ServiceItem").text(res[3].ServiceItem);
      $(".Appeal").text(res[4].Appeal);
    },
    error: function (err) {
      console.log(err)
    }
  })
}

// 切换
function serviceMove() {
  $(".service-content-content > div").eq(0).css("display", "block");
  $(".service-content-msg .row > ul li").on("mousemove", function () {
    var ind = $(this).index();
    $(this).addClass("fontes").siblings().removeClass("fontes");
    $(".service-content-content > div").css("display", "none");
    $(".service-content-content > div").eq(ind).css("display", "block");
  })
}

// button切换
function buttosMove() {
  $(".service-button button").on("mousemove", function () {
    var ind = $(this).index();
    $(this).addClass("butbag").siblings().removeClass("butbag");
    $(this).children("img").eq(0).addClass("butimg");
    $(this).children("img").eq(1).removeClass("butimg");
    $(this).siblings().children("img").eq(0).removeClass("butimg");
    $(this).siblings().children("img").eq(1).addClass("butimg");
  })
}
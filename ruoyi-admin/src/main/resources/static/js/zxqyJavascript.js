function load() {
  $(".head").load("head.html");
  $(".foot").load("foot.html");
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
  $(".index-but span").eq(0).css("display", "block");
  var colums = $(".colums").css("width");
  $(".index-but span").on("click", function () {
    var widths = $(".index-right").css("right");
    if (widths < colums) {
      $(".index-right").animate({ right: '-156px' });
      $(".index-but span").eq(0).css("display", "none");
      $(".index-but span").eq(1).css("display", "block");
    }
    if (widths == "-156px") {
      $(".index-right").animate({ right: '-1px' });
      $(".index-but span").eq(0).css("display", "block");
      $(".index-but span").eq(1).css("display", "none");
    }
  })
}

function echartsMap() {
  var myChart = echarts.init(document.getElementById("map1"));
  var myChart1 = echarts.init(document.getElementById("map2"));
  var myChart2 = echarts.init(document.getElementById("map3"));
  var myChart3 = echarts.init(document.getElementById("map4"));

  // 地图1
  window.onresize = myChart.resize;
  myChart.showLoading();

  var ROOT_PATH = "http://127.0.0.1:5500/2020%E4%B8%AD%E5%B0%8F%E4%BC%81%E4%B8%9A"
  $.get(ROOT_PATH + '/js/150600.json', function (geoJson) {

    myChart.hideLoading();
    echarts.registerMap('Ordos', geoJson);

    myChart.setOption(option = {
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
    myChart.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: 7 });

    $(".index-sixth-right .first-msg ul").eq(7).css("display", "block");
    myChart.on('mouseover', function (e) {
      $(".index-sixth-right .first-msg ul").css("display", "none");
      $(".index-sixth-right .first-msg ul").eq(e.dataIndex).css("display", "block");
      for (let i = 0; i < 8; i++) {
        if (e.dataIndex != i) {
          myChart.dispatchAction({ type: 'downplay', seriesIndex: 0, dataIndex: i });
        } 
      }
    });

    myChart.on('mouseout', function (e) {
      index = e.dataIndex;
      myChart.dispatchAction({ type: 'highlight', seriesIndex: 0, dataIndex: e.dataIndex });
    });
  });

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

// 整体移动
function colums() {
  $(".colums ul li a").click(function () {
    $("html, body").animate({ scrollTop: $($(this).attr("href")).offset().top }, 500);
    $(this).addClass("aLine").parent("li").siblings().children("a").removeClass();
    return false;
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
  console.log(footHeight);
  var bootoms = $(".foot").css("bottom");
  $(".footbut a").on("click", function () {
    var widths = $(".foot").css("bottom");
    if (widths == "-" + footHeight + "px") {
      $(".foot").animate({ bottom: "0" }, 500);
      $(".index-but span").eq(0).css("display", "none");
      $(".index-but span").eq(1).css("display", "block");
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

// 助保代切换
function zbdShow() {
  $(".content-bottom-list>div").eq(0).css("display", "block");
  $(".list-inline li").on("mousemove", function () {
    var index = $(this).index();
    $(this).addClass("libks").siblings().removeClass("libks");
    $(".content-bottom-list>div").css("display", "none");
    $(".content-bottom-list>div").eq(index).css("display", "block");
  })
}

// 列表页返回
function columses() {
  $(".colums ul li a").click(function () {
    window.location = "http://ordoszxqy.org.cn/" + $(this).attr("href");
    $("html, body").animate({ scrollTop: $($(this).attr("href")).offset().top }, 500);
    $(this).addClass("aLine").parent("li").siblings().children("a").removeClass();
    return false;
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

// function mouseMoves() {
//   $(document).on("mousewheel DOMMouseScroll", function (event) {

//     // var heghts = $(document.body).height();
//     var heghts = $(document).height();
//     var delta = (event.originalEvent.wheelDelta && (event.originalEvent.wheelDelta > 0 ? 1 : -1)) ||  // chrome & ie
//       (event.originalEvent.detail && (event.originalEvent.detail > 0 ? -1 : 1));              // firefox

//     if (delta > 0) {
//       // 向上滚
//       $("html, body").animate({scrollTop: '-' + heghts}, 1000);
//       return false;
//     } else if (delta < 0) {
//       $("html, body").animate({scrollTop: heghts}, 1000);
//       return false;
//     }
//   });
// }
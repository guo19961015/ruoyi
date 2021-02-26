$(document).ready(function () {
    $.ajax({
        type: "get",
        url: "https://www.ordoszxqy.org.cn/ry/homeAjax",
        dataType: "json",
        success: function (data) {
            var plBanksLists = data.plBanksList;
            var content = ""
            for (var i = 0; i < plBanksLists.length; i++) {
                content = content + "<li class=\"col-sm-4\">\n" +
                    "<div>\n" +
                    "<img class=\"img-responsive\" src=\"" + plBanksLists[i].bankImageUrl + "\" alt=\"\">\n" +
                    "<div>\n" +
                    "<span><a href=\"http://ordoszxqy.org.cn/ry/details?bankId=" + plBanksLists[i].id + "\">查看详情</a></span><a href=\"http://ordoszxqy.org.cn/ry/index?bankId=" + plBanksLists[i].id + "\" class=\"time\">立即申请</a>\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "</li>";
            }
            $('#capitalContent').html(content);

            var statisticsLists = data.statisticsList;

            var contentDouble = "";
            $.each(statisticsLists, function(key, val) {
                if(key == "已注册企业数"){
                    var oneHtml = "<span>"+key+"</span><i>"+statisticsLists[key]+"</i>";
                    $('.statistics').find("#one").html(oneHtml);
                }else if(key == "服务机构入驻数量"){
                    var twoHtml = "<span>"+key+"</span><i>"+statisticsLists[key]+"</i>";
                    $('.statistics').find("#two").html(twoHtml);
                }else if(key == "金融服务申请数量"){
                    var threeHtml = "<span>"+key+"</span><i>"+statisticsLists[key]+"</i>";
                    $('.statistics').find("#three").html(threeHtml);
                }else if(key == "完成申请事项数"){
                    var fourHtml = "<span>"+key+"</span><i>"+statisticsLists[key]+"</i>";
                    $('.statistics').find("#four").html(fourHtml);
                }else if(key == "企业需求数"){
                    var fiveHtml ="<span>"+key+"</span><i>"+statisticsLists[key]+"</i>";
                    $('.statistics').find("#five").html(fiveHtml);
                }

            });

        }
    }).done(function (data) {
        // 请求成功
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // net::ERR_CONNECTION_REFUSED 发生时，也能进入
        console.info("网络出错");
    });
});

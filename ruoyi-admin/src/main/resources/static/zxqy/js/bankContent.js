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
                contentDouble = contentDouble + "<li class=\"col-sm-6\">\n" +
                    "<span>"+key+"</span><i>"+statisticsLists[key]+"</i>\n" +
                    "</li>";
            });
            $('.statistics').find("ul").html(contentDouble);

        }
    }).done(function (data) {
        // 请求成功
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // net::ERR_CONNECTION_REFUSED 发生时，也能进入
        console.info("网络出错");
    });
});

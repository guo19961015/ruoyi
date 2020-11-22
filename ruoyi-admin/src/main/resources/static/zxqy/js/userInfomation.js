$(document).ready(function () {
    /*url: "https://www.ordoszxqy.org.cn/ry/userInformation",*/
    $.ajax({
        type: "get",
        url: "https://www.ordoszxqy.org.cn/ry/userInformation",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var content = ""
            if(data.currentUser != null && data.currentUser != ""){
                content =  "<span style='color: #FFFFFF;'>\n" +
                    "      <img src=\"images/index-first_07.png\" alt=\"\"><i style='margin-right: 15px'>欢迎您："+data.currentUser.userName+"</i>\n" +
                    "  |<a href=\"https://www.ordoszxqy.org.cn/ry/index\" class=\'\'><i style='margin-left: 15px;margin-right: 15px'>个人中心</i></a>|<a  href=\"http://ordoszxqy.org.cn/ry/logout\" class=\'\' onClick=\"return confirm('确定退出?');\"><i style='margin-left: 15px;'>退出</i></a>\n" +
                    "</span>";
            }else {
                content = "<span>\n" +
                    "                        <a href=\"http://ordoszxqy.org.cn/ry/login\">\n" +
                    "                            <img src=\"images/index-first_05.png\" alt=\"\"><i>登录</i>\n" +
                    "                        </a>\n" +
                    "                    </span>\n" +
                    "                    <span>\n" +
                    "                      <a href=\"http://ordoszxqy.org.cn/ry/register\">\n" +
                    "                        <img src=\"images/index-first_07.png\" alt=\"\"><i>注册</i>\n" +
                    "                      </a>\n" +
                    "                    </span>\n" +
                    "                    <span>\n" +
                    "                      <a href=\"http://ordoszxqy.org.cn/contents/723/46381.html\">\n" +
                    "                        <img src=\"images/index-first_09.png\" alt=\"\"><i>联系我们</i>\n" +
                    "                      </a>\n" +
                    "                    </span>";
            }
            $('.login').html(content);
        }
    }).done(function (data) {
        // 请求成功
    }).fail(function (jqXHR, textStatus, errorThrown) {
        // net::ERR_CONNECTION_REFUSED 发生时，也能进入
        console.info("网络出错");
    });
});

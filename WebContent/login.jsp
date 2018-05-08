<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    ss
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

 <title>登录</title>

  <!-- 页面基本样式 -->
  <link href="./css/login.css" rel="stylesheet">
  <!-- 滑动解锁样式  -->
  <link rel="stylesheet" href="./css/slide-unlock.css">
  <!-- Bootstrap CSS -->
  <link href="./css/bootstrap.min.css" rel="stylesheet">

  <!-- jQuery -->
  <script src="./js/jquery-3.2.1.min.js"></script>
  <!-- 滑动解锁 -->
  <script src="./js/jquery.slideunlock.js"></script>
  <!-- 表单验证 -->
  <script src="./js/jquery.validate.js"></script>
  <!-- Bootstrap -->
  <script src="./js/bootstrap.min.js"></script>
  <!-- 弹出框 -->
  <script src="./js/bootbox.min.js"></script>
</head>

<body class="text-center">
	<!-- 登录表单 -->
  <form class="form-signin" action="LoginServlet" method="post">
    <h1 class="title h3 mb-3 font-weight-normal">公司期刊管理系统</h1>
    <br>
    <br>
    <label for="inputUsername" class="sr-only">用户名</label>
    <!-- 获取之前输入到 -->
    <%
    	String username = (String) request.getAttribute("username");
    	if(username==null){
    		username="";
    	}
    %>
    <input type="text" id="inputUsername" name="username" class="form-control" placeholder="用户名" value="<%=username %>" autofocus>
    <br>
    <label for="inputPassword" class="sr-only">密码</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码">

    <!-- 滑动解锁 -->
    <div id="slider">
      <div id="slider_bg"></div>
      <span id="label">>></span>
      <span id="labelTip">滑动解锁</span>
    </div>

    <button id="btn_login" class="btn btn-lg btn-primary btn-block hidden" name="btn" type="submit">登录</button>
    <%
    	String message = (String) request.getAttribute("message");
    	if (message==null){
    		message="";
    	}
    %>
    <input id="message_text" class="hidden" value="<%=message %>"> 
    <p class="mt-5 mb-3 text-muted">&copy; 软件工程第9组</p>
  </form>

  <script>
  $(function () {
    	
      // 显示登录按钮
      let slider = new SliderUnlock("#slider", {}, function () {
        $("#btn_login").removeClass("hidden");
      });
      slider.init();
      var message = $("#message_text").val();
      console.log(2);
  	  console.log(message);
      if(message.length != 0){
      	bootbox.alert(message);
      	return false;
      }
      // 弹出框提醒
      $("#btn_login").click(function () {
        let username = $("#inputUsername").val();
        let password = $("#inputPassword").val();

        if (username.length === 0) {
          bootbox.alert("请输入用户名！");
          return false;
        }

        if (password.length === 0) {
          bootbox.alert("请输入密码！");
          return false;
        }
        
      });

    }); // end $(function);
  </script>
</body>

</html>

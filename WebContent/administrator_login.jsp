<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<jsp:include page="/meta.jsp" />
	<title>登录</title>
	<jsp:include page="/link.jsp" />
</head>
<body>
	<jsp:include page="/top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 500px;">
		<div class="panel" style="margin-top: 20px">
			<div class="panel-heading" style="font-size: 18px">
				<strong>管理员登录</strong>
			</div>
			<div class="panel-body row">
				<div class="col-xs-3"></div>

				<div class="col-xs-6" style="padding: 30px 60px">
					<div class="panel" style="border: none;">
						<div class="panel-heading" style="background: none;">
							<strong>已有帐号</strong>
						</div>
						<div class="panel-body">
							<form action="${ctx}/administrator_login" method="post">
								<c:if test="${!empty msg}">
									<div class="form-group">
										<div id="formError" class="alert alert-danger">${msg}</div>
									</div>
									<c:remove var="msg" />
								</c:if>
								<div class="form-group">
									<input type="text" name="email" id="email" value=""
										placeholder="请输入邮箱" class="form-control input-lg">
								</div>
								<div class="form-group">
									<input type="password" name="pwd" id="pwd" value=""
										placeholder="请输入密码" class="form-control input-lg">
								</div>
								<button type="submit" class="btn btn-primary btn-wider btn-lg">登录</button>
								&nbsp; &nbsp; <a href="${ctx}/administrator_register.jsp">注册</a>
							</form>
						</div>
					</div>
					<a href="saler_login.jsp">点此切换至销售员入口</a>
				</div>

				<div class="col-xs-3"></div>

			</div>
		</div>
	</div>
	<!-- /主内容 -->

	<jsp:include page="/bottom.jsp"></jsp:include>

	<script src="${ctx}/zui/lib/jquery/jquery.js"></script>
	<script src="${ctx}/js/jquery.scrollUp.min.js"></script>
	<script src="${ctx}/zui/js/zui.js"></script>
	<script src="${ctx}/js/my.js"></script>
</body>
</html>
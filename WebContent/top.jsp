<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!-- 本来jstl报黄线，但是加了isELIgnored="false"就好了，原因是false之后就不禁用el表达式，不知道会不会造成什么影响 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 顶部 -->
<div id="top">
	<!-- 工具条 -->
	<div id="toolbar">
		<div class="wrapper row">
			<c:if test="${!empty sessionScope.curr_mbr and sessionScope.curr_mbr_cate==0}" var="flag">
				<div id="welcome" class="col-xs-5"><a>欢迎用户</a>${sessionScope.curr_mbr.id}</div>
				<div class="col-xs-4"></div>
				<div class="col-xs-3" style="text-align: right;">
					<a href="${ctx}/member/orders">我的商城</a> <a
						href="${ctx}/member/logout">退出</a>
				</div>
			</c:if>
			<c:if test="${!empty sessionScope.curr_mbr and sessionScope.curr_mbr_cate==1}" var="flag">
				<div id="welcome" class="col-xs-5"><a>你好，管理员</a>${sessionScope.curr_mbr.id}</div>
				<div class="col-xs-4"></div>
				<div class="col-xs-3" style="text-align: right;">
					<a href="${ctx}/administrator/products">后台管理</a> <a
						href="${ctx}/administrator/logout">退出</a>
				</div>
			</c:if>
			<c:if test="${!empty sessionScope.curr_mbr and sessionScope.curr_mbr_cate==2}" var="flag">
				<div id="welcome" class="col-xs-5"><a>你好，销售员</a>${sessionScope.curr_mbr.id}</div>
				<div class="col-xs-4"></div>
				<div class="col-xs-3" style="text-align: right;">
					<a href="${ctx}/saler/products">后台管理</a> <a
						href="${ctx}/saler/logout">退出</a>
				</div>
			</c:if>
			<c:if test="${empty sessionScope.curr_mbr}">
				<div class="col-xs-5">演示系统</div>
				<div class="col-xs-4"></div>
				<div class="col-xs-3" style="text-align: right;">
					<a href="${ctx}/member_login.jsp">登录</a> <a
						href="${ctx}/member_register.jsp">注册</a>
					<a href="${ctx}/administrator_login.jsp">后台管理</a>
				</div>
			</c:if>
		</div>
	</div>
	<!-- /工具条 -->

	<!-- LOGO栏 -->
	<div id="subject">
		<div class="wrapper row">
			<div class="col-xs-2" id="logo">
				<a href="${ctx}/main "> <img src="${ctx}/images/logo-zx.jpg"
					style="width: 160px;" />
				</a>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-4" id="search">
				<form action="${ctx}/search" method="post">
					<div class="input-group">
						<input type="text" name="keyword" id="keyword"
							class="form-control" placeholder="商品搜索" />
						<div class="input-group-btn">
							<button class="btn btn-primary" type="submit">
								<i class="icon icon-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-2" id="cart">
				<a href="${ctx}/view_cart.jsp" class="btn btn-primary"
					style="width: 120px; height: 40px; line-height: 30px;"> <i
					class="icon icon-shopping-cart"></i> 购物车 <!-- <span class="label label-badge" style="background-color: #eba5a3;">4</span> -->
				</a>
			</div>
		</div>
	</div>
	<!-- /LOGO栏 -->

	<!-- 导航栏 -->
	<div id="navbar" class="navbar navbar-inverse" style="background-color:#ff9000">
		<div class="wrapper row">
			<div class="navbar-header col-xs-3 categories" style="background-color:#ff8000">
				<div id="title">
					<i class="icon icon-th-large"></i> 所有商品分类
				</div>
				<div id="menu"
					${param.tag == 'index' ? "" : "style='display: none'"}>
					<!--  -->
					<ul class="cate-list"
						${param.tag == 'index' ? "" : "id='categories'"}>
						<c:forEach items="${cates}" var="top">
							<li><a href="${ctx}/product/list?level=top&id=${top.id}"
								class="text-ellipsis"> ${top.name}<i
									class="icon-angle-right director"></i></a>
								<ul class="sub-list">
									<c:forEach items="${top.childs}" var="second">
										<li><a target="_blank"
											href="${ctx}/product/list?level=second&id=${second.id}">${second.name}</a></li>
									</c:forEach>
								</ul></li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<ul class="nav navbar-nav col-xs-9" style="color:#ffffff">
				<li ${param.tag == 'index' ? "class='active'" : ""}><a
					href="${ctx}/main">首页</a></li>
				<li><a href="about.jsp">关于我们</a></li>
			</ul>
		</div>
	</div>
	<!-- /导航栏 -->
</div>
<!-- /顶部 -->
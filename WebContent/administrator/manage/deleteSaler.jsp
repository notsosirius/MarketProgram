<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>删除销售员</title>
<jsp:include page="/link.jsp" />
</head>
<body>
	<jsp:include page="/top.jsp"></jsp:include>

	<!-- 主内容 -->
	<div class="wrapper" style="min-height: 530px">

		<div class="row" style="padding: 20px 0px;">
			<!-- /左边 -->
			<div class="col-xs-2">
				<%-- JSP中的include中page路径里/代表的是项目的根目录 --%>
				<jsp:include page="/administrator/menu.jsp">
					<jsp:param value="deleteSaler" name="tag" />
				</jsp:include>
			</div>
			<!-- /左边 -->

			<!-- 右边 -->
			<div class="col-xs-10">
				<div class="panel">
					<div class="panel-heading">
						<strong> 人员管理</strong>
					</div>
					<div class="col-xs-4" id="search">
						<form action="${ctx}/DeleteSalerSearchServlet" method="post">
							<div class="input-group">
								<input type="text" name="salerkeyword" id="salerkeyword" class="form-control" placeholder="销售员搜索" />
								<div class="input-group-btn">
									<button class="btn btn-primary" type="submit">
										<i class="icon icon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
					<table class="table table-hover table-striped tablesorter">
						<thead>
							<tr class="text-center">
								<td style="width: 60px">销售员ID</td>
								<td style="width: 60px">名字</td>
								<td style="width: 100px">邮箱</td>
								<td style="width: 100px">手机号码</td>
								<td style="width: 80px">注册时间</td>
								<td style="width: 100px">密码</td>
								<td style="width: 80px">销售类别</td>
								<td style="width: 80px">操作</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.items}" var="pro">
								<tr>
									<td class="text-center">${pro.id}</td>
									<td class="text-center">${pro.real_name}</td>
									<td class="text-center">${pro.email}</td>
									<td class="text-center">${pro.mobile}</td>
									<td class="text-center">${pro.register_time}</td>
									<td class="text-center">${pro.pwd}</td>
									<td class="text-center">${pro.category}</td>
									<td class="text-center"><a href="${ctx}/DeleteSalerServlet?id=${pro.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
 						<tfoot>
							<tr>
								<td colspan="8"><q:pager
										totalElements="${page.totalElements}" number="${page.number}" />
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- /右边 -->
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
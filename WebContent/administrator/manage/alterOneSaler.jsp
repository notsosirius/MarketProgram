<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/pager.tld" prefix="q"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<jsp:include page="/meta.jsp" />
<title>修改销售员</title>
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
					<jsp:param value="alterSaler" name="tag" />
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
						<form id="userAction_save_do" action="${ctx}/AlterOneSalerServelt?id=${product.id}" method="post" enctype="multipart/form-data" name="Form1">
							&nbsp;
							<table cellSpacing="1" cellPadding="5" width="100%" align="center">
								<tr>
									<td width="30%" align="center">名字</td>
									<td>
										<input type="text" name="name" value="${product.real_name}" id="userAction_save_do_name"/>
									</td>
									<td width="30%" align="center">管理类别</td>
									<td>
										<input type="text" name="category" value="${product.category}" id="userAction_save_do_cate"/>
									</td>
								</tr>
								<tr>
									<td width="30%" align="center">注册邮箱</td>
									<td>
										<input type="text" name="email" value="${product.email}" id="userAction_save_do_email"/>
									</td>
									<td width="30%" align="center">手机号码</td>
									<td>
										<input type="text" name="mobile" value="${product.mobile}" id="userAction_save_do_mobile"/>
									</td>
								</tr>
								<tr>
									<td width="30%" align="center">密码</td>
									<td>
										<input type="text" name="pwd" value="${product.pwd}" id="userAction_save_do_pwd"/>
									</td>
								</tr>
								<tr>
									<td style="width:100%" align="center" colspan="4">
										<button type="submit" is="userActoin_save_do_submit" value="确定" class="btn btn_primary">
											&#30830;&#30830;
										</button>
										<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
										<button type="reset" value="重置" class="btn btn_primary">
											&#37325;&#32622;
										</button>
									</td>
								</tr>
							</table>
							
						</form>
					</div>
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
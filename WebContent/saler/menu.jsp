<%@ page pageEncoding="UTF-8"%>
<nav class="menu" id="mymenu">
	<ul class="nav nav-primary">
		<li class="nav-parent show"><a href="javascript:;"> 商品管理</a>
			<ul class="nav">
				<li ${param.tag == 'ad_index' ? "class='active'" : ""}><a
					href="${ctx}/saler/products"><i class="icon-shopping-cart"></i>
						商品列表<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'add' ? "class='active'" : ""}><a
					href="${ctx}/saler/product/add.jsp"><i class="icon-plus-sign"></i>
						添加商品<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'delete' ? "class='active'" : ""}><a
					href="${ctx}/saler/product/delete.jsp"><i class="icon-minus-sign"></i>
						删除商品<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'alter' ? "class='active'" : ""}><a
					href="${ctx}/saler/product/alter.jsp"><i class="icon-edit"></i>
						修改商品<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
		<li class="nav-parent show"><a href="javascript:;"> 销售管理</a>
			<ul class="nav">
				<li ${param.tag == 'report' ? "class='active' " : ""}><a
					href="${ctx}/SalerReportFormServlet?action=showall"><i class="icon-table"></i>
					 统计报表<i class="icon-chevron-right"></i></a></li>
				<li ${param.tag == 'order' ? "class='active' " : ""}><a
					href="${ctx}/saler/sale/order"><i class="icon-calculator"></i>
						 订单管理<i class="icon-chevron-right"></i></a></li>
			</ul>
		</li>
	</ul>
</nav>
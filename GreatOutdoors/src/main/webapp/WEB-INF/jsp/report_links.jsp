<%@ include file="common/header.jspf" %>
<%@ include file="common/admin_navigation.jspf" %>
<div class="container">
		<table class="table table-striped">
			<caption>Generate Reports:</caption>
			<thead>
				<tr>
					<td><a class="button" href="/admin/order_report">List Orders</a></td>
                    <td><a class="button" href="/admin/refund_report">List Refunds</a></td>
                    <td><a class="button" href="/admin/best_customer">Top Customers</a></td>
                    <td><a class="button" href="/admin/hot_product">Hot Products</a></td>
				</tr>
			</thead>

		</table>
	</div>
<%@ include file="common/footer.jspf" %>
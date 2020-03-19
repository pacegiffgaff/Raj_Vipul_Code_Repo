<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<table class="table">
			<caption>My Orders:</caption>
			<thead>
				<tr class="active">
					<th>Order No</th>
					<th>Order Date</th>
					<th>Quantity</th>
					<th>Total Amount</th>
					<th>Customer Name</th>
					<th>Shipping Address</th>
					<th>Shipping Email</th>
					<th>Shipping Phone</th>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Status</th>
					<th>Gift</th>
				    <th>Action</th>
				</tr>
			</thead>
            <tbody>
                <font color = "red">${message}</font>
				<c:forEach items="${orderHistory}" var="order">
					<tr class="active">
						<td>${order.id}</td>
    					<td>${order.orderDate}</td>
    					<td>${order.quantity}</td>
    					<td>${order.bill}</td>
    					<td>${order.customerName}</td>
    					<td>${order.customerAddress}</td>
    					<td>${order.customerEmail}</td>
    					<td>${order.customerPhone}</td>
    					<td>${order.product.name}</td>
    					<td>${order.product.price}</td>
    					<td>${order.status}</td>
    					<td>${order.giftPack}</td>
    					<td><a type="button" class="btn btn-success" href="/refund_order?id=${order.id}">Refund</a></td>

						</tr>
				</c:forEach>
			</tbody>
		</table>

</div>


<%@ include file="common/footer.jspf" %>
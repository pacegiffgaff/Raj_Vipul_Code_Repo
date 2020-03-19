<%@ include file="common/header.jspf" %>
<%@ include file="common/admin_navigation.jspf" %>
<div class = "container">

<table class="table table-striped">
			<caption>Admin Refund report:</caption>
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Date of Purchase</th>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Order Quantity</th>
					<th>Order Amount</th>
					<th>Customer Name</th>
					<th>Customer Address</th>
					<th>Order Status</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${orderRefundList}" var="order">
            <tr>
			        <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.product.name}</td>
                    <td>${order.product.price}</td>
                    <td>${order.quantity}</td>
                    <td>${order.bill}</td>
                    <td>${order.customerName}</td>
                    <td>${order.customerAddress}</td>
                    <td>${order.status}</td>
                    <td><a type="button" class="btn btn-warning" href="/update_product?id=${order.id}">Process Refund</a></td>
             </tr>
            </c:forEach>
            </tbody>
            </table>


</div>
<%@ include file="common/footer.jspf" %>
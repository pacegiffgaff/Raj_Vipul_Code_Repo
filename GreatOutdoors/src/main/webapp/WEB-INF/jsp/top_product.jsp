<%@ include file="common/header.jspf" %>
<%@ include file="common/admin_navigation.jspf" %>
<div class = "container">

<table class="table table-striped">
			<caption>Top Selling Product:</caption>
			<thead>
				<tr>
					<th>Product Code</th>
					<th>Product Name</th>
					<th>Product Price</th>
					<th>Inventory</th>
					<th>Sold</th>
				</tr>
			</thead>
			<tbody>
			<tr>
                <c:forEach items="${productSet}" var="product">
                    <td>${product.code}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${count}</td>
                </c:forEach>
             </tr>
            </tbody>
            </table>


</div>
<%@ include file="common/footer.jspf" %>
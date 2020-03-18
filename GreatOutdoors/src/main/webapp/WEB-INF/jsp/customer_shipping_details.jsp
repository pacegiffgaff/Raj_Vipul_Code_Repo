<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<form:form method="post"  action = "/customer/place_order" modelAttribute="order">
		<table class="table table-striped">
			<caption>Available Products:</caption>
			<thead>
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Amount</th>
					<th>Gift Pack</th>
				</tr>
			</thead>
			<tbody>

					<tr>
						<td>${order.product.code}</td>
    					<td>${order.product.name}</td>
    					<td>${order.product.price}</td>
    					<td>${order.quantity}</td>
    					<td>${order.bill}</td>
    					<td>${order.giftPack}</td>
    					<td></td>

					</tr>

			</tbody>
		</table>
		<div class="container">




        		<fieldset class="form-group">
                				<form:label path="customerName">Customer Name</form:label>
                				<form:input path="customerName" type="text"
                					class="form-control" required="required"/>
                				<form:errors path="customerName" cssClass="text-warning"/>
                			</fieldset>
                <fieldset class="form-group">
                        				<form:label path="customerAddress">Customer Address</form:label>
                        				<form:input path="customerAddress" type="text"
                        					class="form-control" required="required"/>
                        				<form:errors path="customerAddress" cssClass="text-warning"/>
                        			</fieldset>
                <fieldset class="form-group">
                        				<form:label path="customerEmail">Customer Email</form:label>
                        				<form:input path="customerEmail" type="text"
                        					class="form-control" required="required"/>
                        				<form:errors path="customerEmail" cssClass="text-warning"/>
                        			</fieldset>
                <fieldset class="form-group">
                        				<form:label path="customerPhone">Customer Phone</form:label>
                        				<form:input path="customerPhone" type="text"
                        					class="form-control" required="required"/>
                        				<form:errors path="customerPhone" cssClass="text-warning"/>
                        			</fieldset>

              <input type="hidden" name="code" value = ${order.product.code}>
              <input type="hidden" name="quantity" value = ${order.quantity}>
              <input type="hidden" name="bill" value = ${order.bill}>
              <input type="hidden" name="id" value = ${order.id}>
              <input type="hidden" name="giftPack" value = ${order.giftPack}>



        			<button type="submit" class="btn btn-success">Place Order</button>

        	</div>
</form:form>
	</div>
<%@ include file="common/footer.jspf" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">

		<table class="table table-striped">
			<caption>Available Products:</caption>
			<thead>
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Price</th>
					<th>Image</th>
					<th>Select</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<form:form method="post" action= "/customer/buy_now" modelAttribute="order" >
					<tr>
						<td>${product.code}</td>
    					<td>${product.name}</td>
    					<td>${product.price}</td>
    					 <td><img class="img-thumbnail" width="100" height="80" src="/get_image?code=${product.code}" /></td>
    					 <td>
    					 <div class="form-group">
                           <label for="quantity">Select:</label>
                           <select class="form-control" id="quantity" name = "quantity">
                             <option value="1">1</option>
                             <option value="2">2</option>
                             <option value="3">3</option>
                             <option value="4">4</option>
                           </select>
                         </div>
    					 <div class="form-group">
                            <label for="comment">Comment:</label>
                            <textarea class="form-control" rows="2" id="comment" name = "comment"></textarea>
                         </div>

                         </td>
                            <input type="hidden" name="pcode" value = ${product.code}>
                         <td>
                         <div class="checkbox">
                               <label><input type="checkbox" name = "giftPack" value="Y">Gift Pack</label>
                         </div>
                          <div>
                         <button type="submit" class="btn btn-success">Buy Now</button>
                         </div>
                         </td>

					</tr>
					</form:form>
				</c:forEach>
			</tbody>
		</table>


	</div>


<%@ include file="common/footer.jspf" %>
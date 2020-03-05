<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
		<table class="table table-striped">
			<caption>
				
  				
  	<div class="dropdown">
    	<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Choose Brand
    		<span class="caret"></span></button>
    			<ul class="dropdown-menu">
    				<c:forEach items="${mobiles}" var="mobile">
      					<li><a href="/list-mobiles?brand=${mobile.brand}">${mobile.brand}</a></li>
     				</c:forEach> 
    			</ul>
  	</div>	
  				
  				
			</caption>
			<thead>
				<tr>
					<th>Mobile</th>
					<th>Brand</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mobiles}" var="mobile">
					<tr>
						<td>${mobile.model}</td>
    					<td>${mobile.brand}</td>
						<td>${mobile.price}</td>
						<td><a type="button" class="btn btn-success"
							href="/addToCart?id=${mobile.id}">Place Order</a></td>
						<td><a type="button" class="btn btn-warning" href="/delete-mobile?id=${mobile.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/add-mobile">Add Mobile</a></div>
	</div>
<%@ include file="common/footer.jspf" %>
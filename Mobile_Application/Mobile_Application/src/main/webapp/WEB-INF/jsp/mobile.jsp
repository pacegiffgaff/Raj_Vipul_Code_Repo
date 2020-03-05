<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="mobile">
			
			<fieldset class="form-group">
				<form:label path="model">Model</form:label> 
				<form:input path="model" type="text"
					class="form-control" required="required"/>
				<form:errors path="model" cssClass="text-warning"/>
			</fieldset>

			<fieldset class="form-group">
            				<form:label path="brand">Brand</form:label>
            				<form:input path="brand" type="text"
            					class="form-control" required="required"/>
            				<form:errors path="brand" cssClass="text-warning"/>
            </fieldset>
            
            <fieldset class="form-group">
            				<form:label path="price">Price</form:label>
            				<form:input path="price" type="text"
            					class="form-control" required="required"/>
            				<form:errors path="price" cssClass="text-warning"/>
            </fieldset>

			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>
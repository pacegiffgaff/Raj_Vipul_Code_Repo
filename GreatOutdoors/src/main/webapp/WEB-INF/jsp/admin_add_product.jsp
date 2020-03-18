<%@ include file="common/header.jspf" %>
<%@ include file="common/admin_navigation.jspf" %>
	<div class="container">
		<form:form method="post"  modelAttribute="productForm" enctype="multipart/form-data">

		<fieldset class="form-group">
        				<form:label path="code">Code</form:label>
        				<form:input path="code" type="text"
        					class="form-control" required="required"/>
        				<form:errors path="code" cssClass="text-warning"/>
        			</fieldset>

			<fieldset class="form-group">
				<form:label path="name">Name</form:label>
				<form:input path="name" type="text"
					class="form-control" required="required"/>
				<form:errors path="name" cssClass="text-warning"/>
			</fieldset>

			<fieldset class="form-group">
            				<form:label path="price">Price</form:label>
            				<form:input path="price" type="text"
            					class="form-control" required="required"/>
            				<form:errors path="price" cssClass="text-warning"/>
            </fieldset>

            <fieldset class="form-group">
                        				<form:label path="quantity">Quantity</form:label>
                        				<form:input path="quantity" type="text"
                        					class="form-control" required="required"/>
                        				<form:errors path="quantity" cssClass="text-warning"/>
                        </fieldset>

            <fieldset class="form-group">
                        				<form:label path="fileData">Image</form:label>
                        				<form:input path="fileData" type="file"
                        					class="form-control" required="required"/>
                        				<form:errors path="fileData" cssClass="text-warning"/>
                        </fieldset>

			<button type="submit" class="btn btn-success">Save</button>
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>
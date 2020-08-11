<%@ page isELIgnored="false" %>
<%@include file="../../source/includes/header/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="register_account">
		<div class="wrap">
				<h4 class="title">Create new PRODUCT</h4>
				<form action="/admin" method="post">


						<div class="col_1_of_2 span_1_of_2">
								<div><input type="text" value="Name" name="name" onfocus="this.value = '';"
														onblur="if (this.value == '') {this.value = 'Name';}"></div>

								<div><input type="text" value="Prise" name="price" onfocus="this.value = '';"
														onblur="if (this.value == '') {this.value = 'Prise';}"></div>

								<div><input type="text" value="Discount" name="discount" onfocus="this.value = '';"
														onblur="if (this.value == '') {this.value = 'Discount';}"></div>
								<div>
										<select id="Category" name="categoryID" class="frm-field required">
												<option value="null">Select a category</option>
												<option value="1">Men</option>
												<option value="2">Woman</option>
												<option value="3">Kids</option>
										</select>
								</div>
								<div>
										<select id="Rating" name="rating" class="frm-field required">
												<option value="null">Select a rating</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
										</select>
								</div>
						</div>
						<div class="col_1_of_2 span_1_of_2">
								<div>
										<input type="text" value="Short Description" name="shortDescription" onfocus="this.value = '';"
													 onblur="if (this.value == '') {this.value = 'Short Description';}">
								</div>

								<div>
										<input type="text" value="Description" name="description" onfocus="this.value = '';"
													 onblur="if (this.value == '') {this.value = 'Description';}">
								</div>


						</div>

						<button class="grey" value="send" type="submit">Submit</button>
						<p class="terms">By clicking 'Create Account' you agree to the <a href="#">Terms &amp; Conditions</a>.</p>
						<div class="clear"></div>
						${ERROR}
				</form>
		</div>
</div>
<%@include file="../../source/includes/footer/footer.jsp" %>

<%@ page isELIgnored="false" %>
<%@include file="../../source/includes/header/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="login">
		<div class="wrap">
				<%--				це фільтра 						--%>
				<div class="rsidebar span_1_of_left">
						<section class="sky-form">

								<h4>Category</h4>
								<div class="row row1 scroll-pane">
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Flats
														(70)</label>
										</div>
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Athletic Shoes
														(48)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Athletic Shoes
														(48)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Heels (38)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Other (1)</label>
										</div>
								</div>
						</section>

						<section class="sky-form">
								<h4>Price</h4>
								<div class="row row1 scroll-pane">
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>$50.00 and
														Under (30)</label>
										</div>
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$100.00 and Under
														(30)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$200.00 and Under
														(30)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$300.00 and Under
														(30)</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$400.00 and Under
														(30)</label>
										</div>
								</div>
						</section>
						<section class="sky-form">
								<h4>Colors</h4>
								<div class="row row1 scroll-pane">
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Red</label>
										</div>
										<div class="col col-4">
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Green</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Black</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Yellow</label>
												<label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Orange</label>
										</div>
								</div>
						</section>
				</div>
				<div class="cont span_2_of_3">
						<%--						це верхный бар		--%>
						<div class="mens-toolbar">
								<div class="sort">
										<div class="sort-by">
												<label>Sort By</label>
												<select>
														<option value="">
																Popularity
														</option>
														<option value="">
																Price : High to Low
														</option>
														<option value="">
																Price : Low to High
														</option>
												</select>
												<a href=""><img src="../../source/images/picEnought/arrow2.gif" alt="" class="v-middle"></a>
										</div>
								</div>
								<div class="pager">
										<div class="limiter visible-desktop">
												<label>Show</label>
												<select>
														<option value="" selected="selected">
																9
														</option>
														<option value="">
																15
														</option>
														<option value="">
																30
														</option>
												</select> per page
										</div>
										<ul class="dc_pagination dc_paginationA dc_paginationA06">
												<li><a href="#" class="previous">Pages</a></li>
												<li><a href="#">1</a></li>
												<li><a href="#">2</a></li>
										</ul>
										<div class="clear"></div>
								</div>
								<div class="clear"></div>
						</div>


						<c:forEach var="warishee" items="${prodList}" varStatus="сounter">

						<c:if test="${сounter.index == '0'}">
						<div class="box1">
								</c:if>

								<div class="col_1_of_single1 span_1_of_single1">

										<div class="view1 view-fifth1">

												<div class="top_box">
														<h3 class="m_1">${warishee.name}</h3>
														<p class="m_2">${warishee.description}</p>
														<div class="grid_img">
																<div class="css3"><img src="${warishee.photoUrl}" alt=""/></div>
																<div class="mask1">
																		<form action="/singleproduct" method="get">
																				<input type="hidden" name="idProduct" value="${warishee.id}"/>
																				<div class="info"><input type="submit" value="Send"/></div>
																		</form>
																</div>
														</div>
														<div class="price">$ ${warishee.price}</div>
												</div>

										</div>
										<span class="rating1">

																<c:if test="${warishee.rating == '1'}">
																		<input type="radio" class="rating-input" id="rating-input-1-1"
																					 name="rating-input-1">
																		<label for="rating-input-1-1" class="rating-star"></label>
																</c:if>
																<c:if test="${warishee.rating == '2'}">
																		<input type="radio" class="rating-input" id="rating-input-1-2"
																					 name="rating-input-1">
																		<label for="rating-input-1-2" class="rating-star"></label>
																</c:if>
																<c:if test="${warishee.rating == '3'}">
																		<input type="radio" class="rating-input" id="rating-input-1-3"
																					 name="rating-input-1">
																		<label for="rating-input-1-3" class="rating-star1"></label>
																</c:if>
																<c:if test="${warishee.rating == '4'}">
																		<input type="radio" class="rating-input" id="rating-input-1-4"
																					 name="rating-input-1">
																		<label for="rating-input-1-4" class="rating-star1"></label>
																</c:if>
																<c:if test="${warishee.rating == '5'}">
																		<input type="radio" class="rating-input" id="rating-input-1-5"
																					 name="rating-input-1">
																		<label for="rating-input-1-5" class="rating-star1"></label>
																</c:if>



										        	  (45)
										    	      </span>
										<ul class="list2">
												<li>
														<img src="../../source/images/picEnought/plus.png" alt=""/>
														<ul class="icon1 sub-icon1 profile_img">
																<li><a class="active-icon c1" href="#">Add To Bag </a>
																		<ul class="sub-icon1 list">
																				<li><h3>${warishee.description}</h3><a href=""></a></li>
																				<li><p>${warishee.shortDescription} <a href="">adipiscing elit, sed diam</a>
																				</p></li>
																		</ul>
																</li>
														</ul>
												</li>
										</ul>
										<div class="clear"></div>
										</a></div>

								<c:if test="${сounter.index == '4'}">
										<%--										СЮДА --%>
								</c:if>

								</c:forEach>
								<%--										оці два рядка знизу потім перекинуть в іф зверху--%>
								<div class="clear"></div>
						</div>

				</div>
				<div class="clear"></div>
		</div>
</div>
<%@include file="../../source/includes/footer/footer.jsp" %>

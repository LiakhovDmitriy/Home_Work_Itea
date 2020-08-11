<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
		<title>Adidas | Home</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link href="../../source/css/style.css" rel="stylesheet" type="text/css" media="all"/>
		<link href="../../source/css/form.css" rel="stylesheet" type="text/css" media="all"/>
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<script type="text/javascript" src="../../source/js/jquery.min.js"></script>
		<script src="../../source/js/jquery.easydropdown.js"></script>
		<script type="text/javascript">
        $(document).ready(function () {
            $(".dropdown img.flag").addClass("flagvisibility");

            $(".dropdown dt a").click(function () {
                $(".dropdown dd ul").toggle();
            });

            $(".dropdown dd ul li a").click(function () {
                var text = $(this).html();
                $(".dropdown dt a span").html(text);
                $(".dropdown dd ul").hide();
                $("#result").html("Selected value is: " + getSelectedValue("sample"));
            });

            function getSelectedValue(id) {
                return $("#" + id).find("dt a span.value").html();
            }

            $(document).bind('click', function (e) {
                var $clicked = $(e.target);
                if (!$clicked.parents().hasClass("dropdown"))
                    $(".dropdown dd ul").hide();
            });


            $("#flagSwitcher").click(function () {
                $(".dropdown img.flag").toggleClass("flagvisibility");
            });
        });
		</script>
		<!-- start menu -->
		<link href="../../source/css/megamenu.css" rel="stylesheet" type="text/css" media="all"/>
		<script type="text/javascript" src="../../source/js/megamenu.js"></script>
		<script>$(document).ready(function () {
        $(".megamenu").megamenu();
    });</script>
		<!-- end menu -->
		<!-- top scrolling -->
		<script type="text/javascript" src="../../source/js/move-top.js"></script>
		<script type="text/javascript" src="../../source/js/easing.js"></script>
		<script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
            });
        });
		</script>
</head>
<body>
<div class="header-top">
		<div class="wrap">
				<div class="logo">
						<a href="/home"><img src="../../source/images/picEnought/logo.png" alt=""/></a>
				</div>
				<div class="cssmenu">
						<ul>
								<c:if test="${login == null}">
										<li class="active"><a href="/registration">Sign up & Save</a></li>
								</c:if>

								<c:if test="${login != null}">
										<li class="active">
												<form action="/login" method="post" >
														<input class="cssmenuLogout" action="/login" method="post" value="Logout" type="submit"
																	 name="logout" style="">
												</form>
										</li>
								</c:if>
								<li><a href="/login">My Account</a></li>

								<c:if test="${login != null}">

										<li class="active">
														<input class="cssmenuLogout" value="${login}" style="width: 130px">

										</li>
										<li class="active">
												<form action="/admin" method="get" >
														<input class="cssmenuLogout" action="/admin" method="get" type="submit"
																	 value="Admin" style="">
												</form>

										</li>
								</c:if>



						</ul>
				</div>
				<ul class="icon2 sub-icon2 profile_img">
						<li><a class="active-icon c2" href="#"> </a>
								<ul class="sub-icon2 list">
										<li><h3>Products</h3><a href=""></a></li>
										<li><p>Lorem ipsum dolor sit amet, consectetuer <a href="">adipiscing elit, sed diam</a></p></li>
								</ul>
						</li>
				</ul>
				<div class="clear"></div>
		</div>
</div>
<div class="header-bottom">
		<div class="wrap">
				<!-- start header menu -->
				<ul class="megamenu skyblue">
						<li><a class="color1" href="/home">Home</a></li>
						<li><a class="color1" href="/home">Men</a></li>
						<li><a class="color1" href="/home">Women</a></li>
						<li><a class="color1" href="/home">Kids</a></li>
						<li><a class="color1" href="/shop">All product</a></li>
				</ul>
				<div class="clear"></div>
		</div>
</div>

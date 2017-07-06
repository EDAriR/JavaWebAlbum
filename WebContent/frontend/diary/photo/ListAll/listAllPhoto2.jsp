<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.PhotoService" %>
<%@ page import="com.diary.model.PhotoVO" %>
<%@ page import="java.util.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    PhotoService photoSvc = new PhotoService();
    List<PhotoVO> list = photoSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		    <script src="https://code.jquery.com/jquery.js"></script>
   			 <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style>
		    * { box-sizing: border-box; }
		    /* force scrollbar */
		    html { overflow-y: scroll; }
		    body { font-family: sans-serif; }
		    /* ---- grid ---- */
		    .grid {
		        background: #DDD;
		    }
		    /* clear fix */
		    .grid:after {
		        content: '';
		        display: block;
		        clear: both;
		    }
		    /* ---- .grid-item ---- */
		    .grid-sizer,
		    .grid-item {
		        width: 33.333%;
		    }
		    .grid-item {
		        float: left;
		    }
		    .grid-item img {
		        display: block;
		        max-width: 100%;
		    }
		</style>
	</head>
	<body>
		
		<div class="container">
			<div class="row">
			
				<h1>Masonry - imagesLoaded progress</h1>
	
				<div class="grid">
				    <div class="grid-sizer"></div>
				    <c:forEach var="PhotoVO" items="${list}">
					    <div class="grid-item">
					       <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
					    </div>
					</c:forEach>
				</div>

			<script>
			    $('.box').masonry({
			        itemSelector: 'item',
			    });
			</script>

			</div>
		</div>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

	</body>

	<script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/3.0.4/jquery.imagesloaded.js"></script>
	<script>
	    // external js: masonry.pkgd.js, imagesloaded.pkgd.js
	    // init Masonry
	    var $grid = $('.grid').masonry({
	        itemSelector: '.grid-item',
	        percentPosition: true,
	        columnWidth: '.grid-sizer'
	    });
	    // layout Masonry after each image loads
	    $grid.imagesLoaded().progress( function() {
	        $grid.masonry();
	    });
	</script>
</html>
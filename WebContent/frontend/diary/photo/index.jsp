<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diary.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/css/bootstrap.css">
<!-- bootstrap -->

<link href="../../assets/js/fancybox/jquery.fancybox.css" rel="stylesheet" />
<script src="//cdn.ckeditor.com/4.7.0/standard/ckeditor.js">

    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->


</head>
<body>
	<div class="page-header">
		<h1>
			頁面標題 <small>真是好標題啊</small>
		</h1>
	</div>
	<div class="row">
		<div class="col-sm-8 offset-sm-2">
			<div id="dropDIV" ondragover="dragoverHandler(event)"
				ondrop="dropHandler(event)">
				拖曳圖片到此處上傳
				<div id="up_progress"></div>
				<div id="container"></div>

				<div class="card-columns" id="imgDIV">

					<div class="card">
						<a href="1497013571228.gif" data-fancybox="images"
							data-caption="My caption"> <img src="1497013571228.gif"
							alt="" />
						</a>
					</div>
					<div class="card">
						<a class="fancybox" href="1497013571228.gif"> <img
							class="card-img-top img-fluid" src="x1BWb8g5L7.jpeg"
							alt="Card image cap">
						</a>
					</div>
					<div class="card">
						<a class="fancybox" href="assets/img/portfolio/port06.jpg"> <img
							class="card-img-top img-fluid"
							src="assets/img/portfolio/port06.jpg" alt="">
						</a>
					</div>
					<div class="card">
						<img class="card-img-top img-fluid" src="x1AB7ePxEn.jpeg"
							alt="Card image cap">
					</div>
					
				</div>
			</div>
			<form>
				<textarea name="editor1" id="editor1" rows="10" cols="80">
                This is my textarea to be replaced with CKEditor.
            </textarea>
				<script>
					// Replace the <textarea id="editor1"> with a CKEditor
					// instance, using default configuration.
					CKEDITOR.replace('editor1');
				</script>
			</form>
		</div>


	</div>

	</div>

</body>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
<script src="https://cdn.rawgit.com/twbs/bootstrap/v4-dev/dist/js/bootstrap.js"></script>

<script src="../../assets/js/fancybox/jquery.fancybox.js"></script>

<%--main--%>
<script src="../../assets/main.js"></script>

</html>
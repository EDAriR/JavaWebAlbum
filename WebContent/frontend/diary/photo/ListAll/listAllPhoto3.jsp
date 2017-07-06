<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.PhotoService" %>
<%@ page import="com.diary.model.PhotoVO" %>
<%@ page import="java.util.*" %>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

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
		    
		    #dropDIV {
            text-align: center;
            width: 100%;
            height: 100%;
            margin: auto;
            background-color: #f0f;
            /*border: dashed 2px gray;*/
        	}
		</style>
	</head>
	<body>
		
		<div class="container">
			<div class="row">
			
				<h1>Masonry - imagesLoaded progress</h1>
	
				<div class="grid">
					<div id="dropDIV" ondragover="dragoverHandler(event)" ondrop="dropHandler(event)">
					    <div class="grid-sizer"></div>
					    <div id="imgDIV">
						    <c:forEach var="PhotoVO" items="${list}">
							    <div class="grid-item">
							       <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
							    </div>
							</c:forEach>
						 </div>
					 </div>	 
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
	
	
	
<!-- AJAX PHOTO UPLOAD-->
<script>

    function dragoverHandler(evt) {
        evt.preventDefault();
    }
    function dropHandler(evt) {//evt �� DragEvent ����
        evt.preventDefault();
        var files = evt.dataTransfer.files;//��DataTransfer����files�ݩʨ��o�ɮת���
        var fd = new FormData();
        var xhr = new XMLHttpRequest();
        var up_progress = document.getElementById('up_progress');
        xhr.open('POST', '<%=request.getContextPath()%>/frontend/PhotoUpload');//�W�Ǩ�PhotoUpload.php
        xhr.onload = function () {
            //�W�ǧ���
            up_progress.innerHTML = '100 %, �W�ǧ���';
        };
        xhr.upload.onprogress = function (evt) {
            //�W�Ƕi��
            if (evt.lengthComputable) {
                var complete = (evt.loaded / evt.total * 100 | 0);
                if (100 == complete) {
                    complete = 99.9;
                }
                up_progress.innerHTML = complete + ' %';
            }
        }


        for (var i in files) {
            if (files[i].type == 'image/jpeg' || files[i].type == 'image/png' || files[i].type == 'image/gif') {
                //�N�Ϥ��b�����w��
                var fr = new FileReader();
                fr.onload = openfile;
                fr.readAsDataURL(files[i]);

                //�s�W�W���ɮסA�W�ǫ�W�٬� ff ���}�C
                fd.append('ff[]', files[i]);
            }
        }
        xhr.send(fd);//�}�l�W��
    }
    function openfile(evt) {
        var img = evt.target.result;
        var imgx = document.createElement('img');
        var card = document.createElement('div');
        imgx.src = img;
        imgx.className = "card-img-top img-fluid";
        card.className = "card";
        document.getElementById('imgDIV').appendChild(card).appendChild(imgx);
    }

</script>
<!-- AJAX PHOTO UPLOAD-->
</html>
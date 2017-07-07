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

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Add mousewheel plugin (this is optional) -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/frontend/assets/fancybox/lib/jquery.mousewheel.pack.js?v=3.1.3"></script>

    <!-- Add fancyBox main JS and CSS files -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/frontend/assets/fancybox/source/jquery.fancybox.pack.js?v=2.1.5"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/assets/fancybox/source/jquery.fancybox.css?v=2.1.5"
          media="screen"/>
    <!-- Add Thumbnail helper (this is optional) -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/frontend/assets/fancybox/source/helpers/jquery.fancybox-thumbs.css?v=1.0.7"/>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/frontend/assets/fancybox/source/helpers/jquery.fancybox-thumbs.js?v=1.0.7"></script>

	<%------------Masonry------------------%>
	<script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/3.0.4/jquery.imagesloaded.js"></script>
	

    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        /*----------Masonry----------------*/
        * {
            box-sizing: border-box;
        }

        /* force scrollbar */
        html {
            overflow-y: scroll;
        }

        body {
            font-family: sans-serif;
        }

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

        /*----------Masonry----------------*/

        /*ajax*/
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
<%--         <h1>photoList=> ${photoList}</h1> --%>
<%--         <h1>list=> ${list}</h1> --%>
        
        <div id="up_progress"></div>
        <%--進度條--%>

        <%--end grid--%>
        <div class="grid">
            <%--end dropDIV--%>
            <div id="dropDIV" ondragover="dragoverHandler(event)" ondrop="dropHandler(event)">

                <div class="grid-sizer"></div>
                <div id="imgDIV">
                    <c:forEach var="photoVO" items="${photoList}">
                        <div class="grid-item"> 
                            <a class="fancybox-thumbs" data-fancybox-group="thumb" href="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${photoVO.pho_no}">
                                <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${photoVO.pho_no}">
                            </a>
                            <p>
                            ${photoVO.pho_title}
                            <button class="btn btn-inverse btn-large"><i class="icon-remove"></i> Delete</button>
                            </p>
                        </div>
                    </c:forEach>
                </div>
                <%--end imgDIV--%>
            </div>
            <%--end dropDIV--%>
        </div>
        <%--end grid--%>
    </div>
</div>


</body>
<script>
<%------------Masonry------------------%>
$(document).ready(function () {
    $('.box').masonry({
        itemSelector: 'item',
    });
    // external js: masonry.pkgd.js, imagesloaded.pkgd.js
    // init Masonry
    var $grid = $('.grid').masonry({
        itemSelector: '.grid-item',
        percentPosition: true,
        columnWidth: '.grid-sizer'
    });
    // layout Masonry after each image loads
    $grid.imagesLoaded().progress(function () {
        $grid.masonry();
    });
    </script>

<%------------Masonry------------------%>

<%--Masonry--%>
<script>
    $('.box').masonry({
        itemSelector: 'item',
    });

    // external js: masonry.pkgd.js, imagesloaded.pkgd.js

    // init Masonry
    var $grid = $('.grid').masonry({
        itemSelector: '.grid-item',
        percentPosition: true,
        columnWidth: '.grid-sizer'
    });
    // layout Masonry after each image loads
    $grid.imagesLoaded().progress(function () {
        $grid.masonry();
    });

</script>
<%--Masonry--%>

<!-- AJAX PHOTO UPLOAD-->
<script>

    function dragoverHandler(evt) {
        evt.preventDefault();
    }
    function dropHandler(evt) {//evt 為 DragEvent 物件
        evt.preventDefault();
        var files = evt.dataTransfer.files;//由DataTransfer物件的files屬性取得檔案物件
        var fd = new FormData();
        var xhr = new XMLHttpRequest();
        var up_progress = document.getElementById('up_progress');
        xhr.open('POST', '<%=request.getContextPath()%>/frontend/PhotoUpload',true);//上傳到PhotoUpload.php
        xhr.onload = function () {
            //上傳完成
            up_progress.innerHTML = '100 %, 上傳完成';
            
        };
        	
        	 /* function(){
        	alert(1);
            location.reload();
        }*/
        xhr.upload.onprogress = function (evt) {
            //上傳進度
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
                //將圖片在頁面預覽
                var fr = new FileReader();
                fr.onload = openfile;
                fr.readAsDataURL(files[i]);
                //新增上傳檔案，上傳後名稱為 ff 的陣列
                fd.append('ff[]', files[i]);
            }
        }
        xhr.send(fd);//開始上傳
    }
    
    function pageReload() {
        location.reload();
    }
    function openfile(evt) {
       
    }

</script>
<!-- AJAX PHOTO UPLOAD-->

<%--fancybox--%>



</script>
<%--fancybox--%>
</html>
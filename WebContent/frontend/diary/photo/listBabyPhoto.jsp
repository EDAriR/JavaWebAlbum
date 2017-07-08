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

    <!-- babeq -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/babeq.css">

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Add fancyBox -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.css">
    <script src="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.min.js"></script>

    <%------------Masonry------------------%>
    <script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/3.0.4/jquery.imagesloaded.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/fileinput.min.css">
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/piexif.min.js"
            type="text/javascript"></script>
    <!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
         This must be loaded before fileinput.min.js -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/sortable.min.js"
            type="text/javascript"></script>
    <!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
         This must be loaded before fileinput.min.js -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/purify.min.js"
            type="text/javascript"></script>
    <!-- the main fileinput plugin file -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/fileinput.min.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/zh-TW.js"></script>


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

        <div class="col-lg-12">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="sr-only" for="xxxx">Email address</label>
                    <input type="text" class="form-control" id="xxxx" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="xxx">Password</label>
                    <input type="text" class="form-control" id="xxx" placeholder="Password">
                </div>
                <div class="form-group">
                    <label for="sel1">小孩選擇:</label>
                    <select class="form-control" id="sel1">
                        <option>嬰兒1babyVO.no}</option>
                        <option>嬰兒2babyVO.no</option>
                        <option>嬰兒3babyVO.no</option>
                        <option>嬰兒4babyVO.no</option>
                    </select></div>
                <button type="submit" class="btn btn-theme">確認</button>
            </form>
        </div>

        <div class="col-lg-12">
            <label class="control-label">Select File</label>
            <input id="input-44" name="input44[]" type="file" multiple class="file-loading">
        </div>

        <div class="col-lg-12">
            <!-- grid -->
            <div class="grid">
                <div class="grid-sizer"></div>
                <c:forEach var="PhotoVO" items="${list}">
                    <div class="grid-item">
                        <p>${PhotoVO.pho_title}</p>
                        <a data-fancybox="images"
                           href="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
                            <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
                        </a>
                        <p>
                        <!-- delete -->
                        <form action="<%=request.getContextPath()%>/PhotoServlet.do" method="post">
                            <input type="hidden" name="pho_no" value="${PhotoVO.pho_no}">
                            <input type="hidden" name="requestURL" value="<%= request.getServletPath()%>">
                            <input type="hidden" name="action" value="delete">
                            <button class="btn btn-danger btn-large"><i class="icon-remove"></i> Del</button>
                        </form>
                        <!-- delete -->
                        </p>
                    </div>
                </c:forEach>
            </div>
            <!-- grid -->
        </div>
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
    });
</script>

<%------------Masonry------------------%>

<!-- fileinput -->
<script>

    $(document).on('ready', function () {
        $("#input-44").fileinput({
            uploadUrl: '<%=request.getContextPath()%>/frontend/PhotoUpload',
            maxFilePreviewSize: 10240,
            language: "zh-TW",
            allowedFileExtensions: ["jpg", "png", "gif", "jpeg"]
        });
    });

    <%-- $("#input-ru").fileinput({
        language: "zh-TW",
        uploadUrl: "<%=request.getContextPath()%>/frontend/PhotoUpload",
        allowedFileExtensions: ["jpg", "png", "gif",'jpeg']
    }); --%>
</script>
<!-- fileinput -->
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.PhotoService" %>
<%@ page import="com.diary.model.PhotoVO" %>
<%@ page import="java.util.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache");        //HTTP 1.0
    response.setDateHeader("Expires", 0);
%>

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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/babeq.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Add fancyBox -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.css">
    <script src="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.min.js"></script>

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
            background-color: #2ef3ff;
            /*border: dashed 2px gray;*/
        }
    </style>
</head>
<body>
<%! int count = 0; %>
<%= ++count %>
<div class="container">
    <div class="row">

        <!-- grid -->
        <div class="grid">
            <div class="grid-sizer"></div>
            <c:forEach var="PhotoVO" items="${list}">
                <div class="grid-item">
                    <a data-fancybox="images"
                       href="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
                        <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
                    </a>
                    <p>
                    <!-- delete -->
                    <form action="<%=request.getContextPath()%>/PhotoServlet.do" method="post">
                            ${PhotoVO.pho_title}
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

</html>
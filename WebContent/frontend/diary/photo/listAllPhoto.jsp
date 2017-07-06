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


<html>
<head>

    <title>�Ҧ��ۤ���� - listAllPhoto.jsp</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/babeq.css">
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../../assets/js/fancybox/jquery.fancybox.css" rel="stylesheet"/>

    <script src="//cdn.ckeditor.com/4.7.0/standard/ckeditor.js"></script>
    <style>
        #dropDIV {
            text-align: center;
            width: 100%;
            height: 100%;
            margin: auto;
            /*background-color: #f0f;*/
            /*border: dashed 2px gray;*/
        }

        .card {
            width: 100%;
            height: 100%;
        }

    </style>
</head>
<body>
<%--header--%>
<header>
    <nav class="navbar navbar-default navbar-fixed-top " role="navigation">
        <div class="container">
            <!-- ������ÿ��� -->
            <div><a class="navbar-brand" href="#"><img src="../../pic/babeq3.png" width="230"></a></div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- �k��� -->

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="navbarIcon">
                        <img class="headerIcon" src="../../pic/list_01.png" width="80"></a>
                    </li>
                    <li><a href="#" class="navbarIcon">
                        <img class="headerIcon" src="../../pic/ring_01.png" width="80"></a>
                    </li>
                    <li><a href="#" class="navbarIcon">
                        <img class="headerIcon" src="../../pic/login_01.png" width="80"></a>
                    </li>
                </ul>
            </div>
            <img src="../../pic/pencilline.png" class="navbarLine" style="margin-top: 0px">
            <!-- ������ÿ��ϵ��� -->
        </div>
    </nav>
</header>
<%--header--%>

<%-- <%@ include file="pages/header.file" %> --%>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
        <td>
            <h3>�Ҧ����u��� - ListAllEmp.jsp</h3>
            <a href="<%=request.getContextPath()%>/select_page.jsp">
                <img src="images/back1.gif" width="100" height="32" border="0">�^����
            </a>
        </td>
    </tr>
</table>

<div class="container">
	<div class="row">
	
	    <%-- ���~��C --%>
	    <c:if test="${not empty errorMsgs}">
	        <font color='red'>�Эץ��H�U���~:
	            <ul>
	                <c:forEach var="message" items="${errorMsgs}">
	                    <li>${message}</li>
	                </c:forEach>
	            </ul>
	        </font>
	    </c:if>
	
	    <div class="col-md-8 col-md-offset-2">
	        <style type="text/css">
	            .photo {
	                width: 200px;
	            }
	        </style>
	        <table border='1' bordercolor='#CCCCFF' width='800'>
	            <tr>
	                <th>pho_title</th>
	                <th>baby_no</th>
	                <th>img</th>
	                <th>�R��</th>
	            </tr>
	            <%@ include file="pages/page1.file" %>
	            <c:forEach var="PhotoVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	                <tr align='center' valign='middle'><!--�N�ק諸���@���[�J����Ӥw-->
	                    <td>${PhotoVO.pho_title}</td>
	                    <td>${PhotoVO.baby_no}</td>
	                    <td><img class="photo"
	                             src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
	                    </td>
	
	                    <td>
	                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
	                            <input type="submit" value="�R��">
	                            <input type="hidden" name="pho_no" value="${PhotoVO.pho_no}">
	                            <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
	                            <!--�e�X�����������|��Controller-->
	                            <input type="hidden" name="whichPage" value="<%=whichPage%>">
	                            <!--�e�X��e�O�ĴX����Controller-->
	                            <input type="hidden" name="action" value="utility.delete">
	                        </FORM>
	                    </td>
	                </tr>
	            </c:forEach>
	        </table>
	        <%@ include file="pages/page3.file" %>
	
	
	        <br>�����������|:<br><b>
	        <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
	        <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%>
	    </b>
	    </div>
	</div>
	<div class="row">
	    <div class="col-sm-8 offset-sm-2">
	        <div id="dropDIV" ondragover="dragoverHandler(event)" ondrop="dropHandler(event)">
	            �즲�Ϥ��즹�B�W��
	            <div id="up_progress"></div>
	
	
	            <div id="imgDIV">
	            
	                <c:forEach var="PhotoVO" items="${list}">
	                    <%--SHOW PHOTO--%>
	                    <div class="grid-item">
	                        <img src="<%=request.getContextPath()%>/frontend/PhotoReader/PhotoReader.do?pho_no=${PhotoVO.pho_no}">
	                    </div>
	                    <%--SHOW PHOTO--%>
	                </c:forEach>
	            </div>
	            <%--end card-columns imgDIV--%>
	        </div>
	        <%--end dropDIV--%>
	    </div>
	    <%-- end col-sm-8 offset-sm-2--%>
	</div>
	
</div>
<%-- <%@ include file="pages/footer.file" %> --%>

<footer>
    <div class="container">
        <img src="../../pic/pencilline.png" class="navbarLine">
        <div class="footerBottom navbar-right navbar-nav">
            <a href="#" class="navbarIcon"><img class="headerIcon" src="../../pic/chat_01.png" width="80"></a>
        </div>
    </div>
</footer>
</body>

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%--Masonry �r���y--%>
<script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/3.0.4/jquery.imagesloaded.js"></script>

<style>
    * {
        box-sizing: border-box;
    }

    /* force scrollbar */
    html {
        overflow-y: scroll;
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
        width: 25%;
    }

    .grid-item {
        float: left;
    }

    .grid-item img {
        display: block;
        max-width: 100%;
    }

</style>
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

<!-- FancyBox -->
<script type="text/javascript">
    $(function () {
        //    fancybox
        jQuery(".fancybox").fancybox();
    });

</script>
<!-- FancyBox -->

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

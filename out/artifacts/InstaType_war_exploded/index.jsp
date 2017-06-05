<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhouxiaoyuan
  Date: 03/05/2017
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>Welcome</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="jqueryui/style.css">
<style>
    .ui-autocomplete {
        max-height: 150px;
        overflow-y: auto;
        /* 防止水平滚动条 */
        overflow-x: hidden;
    }

    * html .ui-autocomplete {
        max-height: 100px;
    }
    #imgShow .imgDiv{
        width:150px;
        height: 200px;
        margin:10px;
        display: inline-block;
        text-align: center;
    }
    #imgShow a{
        display: inline-block;
        width: 150px;
        height: 150px;
    }
    #imgShow i{
        display: block;
        margin-bottom: 2px;
    }

</style>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>


<body>
<h1>
    <center>Welcome!</center>
</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12 column">
            <div class="carousel slide" id="carousel-367737">
                <ol class="carousel-indicators">
                    <li class="active" data-slide-to="0" data-target="#carousel-367737">
                    </li>
                    <li data-slide-to="1" data-target="#carousel-367737">
                    </li>
                    <li data-slide-to="2" data-target="#carousel-367737">
                    </li>
                </ol>
                <div class="carousel-inner">
                    <div class="item active">
                        <img alt=""
                             src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/1/default.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                First Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                                gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt=""
                             src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/2/default.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                Second Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                                gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                    <div class="item">
                        <img alt=""
                             src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/3/default.jpg"/>
                        <div class="carousel-caption">
                            <h4>
                                Third Thumbnail label
                            </h4>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta
                                gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.
                            </p>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel-367737" data-slide="prev"><span
                        class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control"
                                                                                href="#carousel-367737"
                                                                                data-slide="next"><span
                    class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<div class="container">
    <div class="row">
        <form action="search.action">
            <div class="col-md-8 column col-center-block">
                <div class="input-group input-group-lg">
                    <span class="input-group-addon" id="sizing-addon1">@</span>
                    <input name="userName" type="text" class="form-control" placeholder="Username"
                           aria-describedby="sizing-addon1">
                    <span class="input-group-btn">
                        <input type="submit" class="btn btn-secondary" value="Search">
                    </span>
                </div>
                <i id="urlName" hidden><% out.println(request.getAttribute("imgUrl"));%></i>
                <i id="urlId" hidden><% out.println(request.getAttribute("imgId"));%></i>
                <i id="usrId" hidden><% out.println(request.getAttribute("usrId"));%></i>
            </div>
        </form>
        <div id="imgShow" class="col-md-11 column col-center-block"></div>
    </div>
</div>

<script>
    $(function(){
        var urlName=$("#urlName").html();
        urlName=urlName.substring(1,urlName.length-2);
        urlName=urlName.split(",");

        var urlId=$("#urlId").html();
        urlId=urlId.substring(1,urlId.length-2);
        urlId=urlId.split(", ");


        var usrId=$("#usrId").html();
        usrId=usrId.substring(1,usrId.length-2);
        usrId=usrId.split(",");



        for(var i=0;i<urlName.length;i++){
            oImg=document.createElement("img");
            oImg.src=urlName[i];
            oA=document.createElement("a");
            oA.appendChild(oImg);
            oI=document.createElement("i");
            oI.innerHTML=usrId[i];
            oDiv=document.createElement("div");
            oDiv.appendChild(oI);
            oDiv.appendChild(oA);
            oDiv.className="imgDiv";
            $("#imgShow").append(oDiv);
        }

        aA=$("#imgShow a");
        aImg=$("#imgShow a img");



        for (var j=0;j<aA.length;j++){
            aA[j].index=j;
            aA[j].href="result.jsp";
            aA[j].name=urlId[j];
            aA[j].onclick=function(){
                $.ajax({
                    type:"POST",
                    url:"click.action",
                    data:{
                        imgId:this.name,
                        imgUrl:aImg[this.index].src,
                    },
                    dataType:"json"
                });

            }
        }
    })
</script>
</body>

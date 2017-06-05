<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>result</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<%--<script src="js/jquery-1.11.3.min.js"></script>--%>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>

<body>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-10 column col-center-block" >
            <img id="image" src="<% out.println(request.getAttribute("image"));%>"/>

        <i id="userId">userId:<% out.println(request.getAttribute("Id"));%></i>
        </div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <div class = "row">
        <div class="col-md-4 column col-center-block " >
            <table border="1">
                <thead>
                <tr>
                    <th>Category</th>
                    <th>correlation coefficient</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ResultLists" items="${ResultList}">
                    <tr>
                        <td>${ResultLists.result1}</td>
                        <td>${ResultLists.result2}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
    </div>
</div>
</body>



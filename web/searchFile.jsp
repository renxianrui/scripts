<%--
  Created by IntelliJ IDEA.
  User: Ren
  Date: 2018/10/27
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <title>查找文件</title>

</head>
<body>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#reportlets" data-toggle="tab">
                查找模板文件
            </a>
        </li>
        <li><a href="#hhdi" data-toggle="tab">整合平台内容查找</a></li>

    </ul>
    <div id="myTabContent" class="tab-content">

        <div class="tab-pane fade in active" id="reportlets">
            <form class="form-inline" role="form" action="searchFile" method="post">
                <input type="text" class="form-control" id="name" placeholder="请输入查找内容"  name="searchText">
                <button type="submit" class="btn btn-primary">查找</button><span class="text-danger">&nbsp;&nbsp;&nbsp;&nbsp;注:空为查找未挂出模板</span>
            </form>
            <span class="text-primary">当前查找内容为:${searchText}</span>
            <table  class="table table-striped table-bordered table-hover  table-condensed">
                <thead>
                <th>模板</th>
                <th>挂载目录</th>
                </thead>
                <c:forEach items="${reports}" var="report" varStatus="st">
                <tr>
                    <td>${report.serverPath}</td>
                    <td>${report.systemPath}</td>
                </tr>
                </c:forEach>
            </table>
        </div>


        <div class="tab-pane fade" id="hhdi">
            <p></p>
        </div>

    </div>

    <div style="height:200px"></div>

</body>
</html>

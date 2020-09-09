<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 08/31/2020
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="fa/css/all.css">
    <script src="fa/js/all.js"></script>
</head>
<body>

<!--đây là menu-->
<div class="container-fluid menu-navbar">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <button type="button" class="btn btn-link">
                    <a href="${pageContext.request.contextPath}/product?action=create">Thêm sản phẩm</a>
                </button>
                <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/product?action=search" method="post">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="regex">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </div>
</div>
<!--đây là menu-->

<!--đây là section-->
<div class="container">
    <div class="row">
        <table style="border: solid 1px" class="table table-hover">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
                <td>Amount</td>
                <td>Color</td>
                <td>Category</td>
                <td>Action</td>
            </tr>
            <jsp:useBean id="listAllProduct" scope="request" type="java.util.List"/>
            <c:forEach items="${listAllProduct}" var="products">
                <tr>
                    <td>${products.getId()}</td>
                    <td>${products.getName()}</td>
                    <td>${products.getPrice()}</td>
                    <td>${products.getAmount()}</td>
                    <td>${products.getColor()}</td>
                    <td>${products.getCategory()}</td>
                    <td><a href="${pageContext.request.contextPath}/product?action=edit&id=${products.getId()}">Edit&ensp;|&ensp;</a>
                        <a href="${pageContext.request.contextPath}/product?action=delete&id=${products.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<!--đây là section-->

<%--dialog box for delete--%>
<c:if test='${requestScope["product"] != null}'>
    <div id="deleteModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Thông báo</h4>
                </div>
                <div class="modal-body">
                    <p>Bạn muốn xóa sản phẩm?</p>
                </div>
                <div class="modal-footer">
                    <a href="${pageContext.request.contextPath}/control?action=deleteProduct&id=${requestScope["product"].getId()}">Xác nhận</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test='${requestScope["controller"] != null}'>
    <script type="text/javascript">
        $(window).on('load',function(){
            $('#${requestScope["controller"]}').modal('show');
        });
    </script>
</c:if>
<%--dialog box for delete--%>
</body>
</html>
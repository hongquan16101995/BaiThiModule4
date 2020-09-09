<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 09/09/2020
  Time: 9:58 AM
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
</head>
<body>
<c:if test='${requestScope["product"] != null}'>
    <div class="row" style="clear: both">
        <div class="col-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <p class="card-title" style="margin-bottom: 20px; float: left">SỬA SẢN PHẨM</p>
                    <form action="${pageContext.request.contextPath}/control?action=editProduct"
                          method="post">
                        <div class="form-group" hidden>
                            <label>Id:&ensp;</label>
                            <input type="text" class="form-control" name="product-id"
                                   value="${requestScope["product"].getId()}">
                        </div>
                        <div class="form-group">
                            <label>Name Product:&ensp;</label>
                            <input type="text" class="form-control" name="product-name"
                                   value="${requestScope["product"].getName()}">
                        </div>
                        <div class="form-group">
                            <label>Price Product:&ensp;</label>
                            <input type="text" class="form-control" name="product-price"
                                   value="${requestScope["product"].getPrice()}">
                        </div>
                        <div class="form-group">
                            <label>Amount Product:&ensp;</label>
                            <input type="text" class="form-control" name="product-amount"
                                   value="${requestScope["product"].getAmount()}">
                        </div>
                        <div class="form-group">
                            <label>Color Product:&ensp;</label>
                            <input type="text" class="form-control" name="product-color"
                                   value="${requestScope["product"].getColor()}">
                        </div>
                        <div class="form-group">
                            <label>Description Product:&ensp;</label>
                            <input type="text" class="form-control" name="product-description"
                                   value="${requestScope["product"].getDescription()}">
                        </div>
                        <div class="form-group">
                            <label for="category">Category Product: </label>
                            <select class="form-control" id="category" name="product-category">
                                <c:forEach items="${categoryList}" var="category">
                                    <option>${requestScope["product"].getCategory()}</option>
                                    <option>${category.getCategory()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-link">Edit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</c:if>
</body>
</html>

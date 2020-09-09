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
<div class="row" style="clear: both">
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <p class="card-title" style="margin-bottom: 20px; float: left">THÊM MỚI SẢN PHẨM</p>
                <form action="${pageContext.request.contextPath}/control?action=createProduct"
                      method="post">
                    <div class="form-group">
                        <label>Name Product:&ensp;</label>
                        <input type="text" class="form-control" name="product-name">
                    </div>
                    <div class="form-group">
                        <label>Price Product:&ensp;</label>
                        <input type="text" class="form-control" name="product-price">
                    </div>
                    <div class="form-group">
                        <label>Amount Product:&ensp;</label>
                        <input type="text" class="form-control" name="product-amount">
                    </div>
                    <div class="form-group">
                        <label>Color Product:&ensp;</label>
                        <input type="text" class="form-control" name="product-color">
                    </div>
                    <div class="form-group">
                        <label>Description Product:&ensp;</label>
                        <input type="text" class="form-control" name="product-description">
                    </div>
                    <div class="form-group">
                        <label for="category">Category Product: </label>
                        <select class="form-control" id="category" name="product-category">
                            <c:forEach items="${categoryList}" var="category">
                                <option></option>
                                <option>${category.getCategory()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                    <button type="button" class="btn btn-link">
                        <a href="${pageContext.request.contextPath}/product">Back</a>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

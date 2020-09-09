package controller;

import model.Category;
import model.ConnectionDBOfProduct;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ConnectionDBOfProduct connectionDBOfProduct = new ConnectionDBOfProduct();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if ("search".equals(action)) {
            listSearch(request, response);
        } else {
            listAllProduct(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                deleleProductById(request,response);
                break;
            case "edit":
                editProductById(request,response);
                break;
            case "create":
                createProduct(request,response);
                break;
            default:
                listAllProduct(request, response);
                break;
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = connectionDBOfProduct.selectAllCategory();
        request.setAttribute("categoryList", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home_create.jsp");
        dispatcher.forward(request, response);
    }

    private void listSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regex = request.getParameter("regex");
        List<Product> products = connectionDBOfProduct.selectProductByName(regex);
        request.setAttribute("listAllProduct", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);
    }

    private void deleleProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String controller = "deleteModal";
        request.setAttribute("controller", controller);
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = connectionDBOfProduct.selectProductById(id);
        request.setAttribute("product", product);
        List<Product> products = connectionDBOfProduct.selectAllProduct();
        request.setAttribute("listAllProduct", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);
    }

    private void editProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = connectionDBOfProduct.selectProductById(id);
        request.setAttribute("product", product);
        List<Category> categories = connectionDBOfProduct.selectAllCategory();
        request.setAttribute("categoryList", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home_edit.jsp");
        dispatcher.forward(request, response);
    }

    private void listAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = connectionDBOfProduct.selectAllProduct();
        request.setAttribute("listAllProduct", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
        dispatcher.forward(request, response);
    }
}

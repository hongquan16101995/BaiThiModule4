package controller;

import model.ConnectionDBOfProduct;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControlServlet", urlPatterns = "/control")
public class ControlServlet extends HttpServlet {
    ConnectionDBOfProduct connectionDBOfProduct = new ConnectionDBOfProduct();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editProduct":
                editProduct(request, response);
                break;
            case "createProduct":
                createProduct(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        if ("deleteProduct".equals(action)) {
            deleteProduct(request, response);
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("product-id"));
        String name = request.getParameter("product-name");
        long price = Long.parseLong(request.getParameter("product-price"));
        int amount = Integer.parseInt(request.getParameter("product-amount"));
        String color = request.getParameter("product-color");
        String description = request.getParameter("product-description");
        String category = request.getParameter("product-category");
        Product product = new Product(id, name, price, amount, color, description, category);
        if (connectionDBOfProduct.updateProduct(product)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product");
            dispatcher.forward(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (connectionDBOfProduct.deleteProduct(id)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/product");
            dispatcher.forward(request, response);
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("product-name");
        long price = Long.parseLong(request.getParameter("product-price"));
        int amount = Integer.parseInt(request.getParameter("product-amount"));
        String color = request.getParameter("product-color");
        String description = request.getParameter("product-description");
        String category = request.getParameter("product-category");
        Product product = new Product(name, price, amount, color, description, category);
        connectionDBOfProduct.insertProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product?action=create");
        dispatcher.forward(request, response);
    }
}

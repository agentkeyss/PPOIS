package com.example.tictactoe.Servlets;

import com.example.tictactoe.Constants;
import com.example.tictactoe.Models.Field;
import com.example.tictactoe.Models.Sign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InitServlet", value = Constants.START_URL)
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession(true);

        Field field = new Field();
        Map<Integer, Sign> fieldData = field.getField();
        List<Sign> data = field.getFieldData();

        currentSession.setAttribute(Constants.FIELD_ATTRIBUTE, field);
        currentSession.setAttribute(Constants.DATA_ATTRIBUTE, data);

        getServletContext().getRequestDispatcher(Constants.INDEX_URL).forward(req, resp);
    }
}

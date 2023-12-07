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
import jakarta.servlet.RequestDispatcher;

import java.util.List;
import java.io.IOException;

@WebServlet(name = "LogicServlet", value = Constants.LOGIC_URL)
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        Field field = extractField(currentSession);

        int index = getSelectedIndex(req);
        Sign currentSign = field.getField().get(index);

        if (Sign.EMPTY != currentSign) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constants.INDEX_URL);
            dispatcher.forward(req, resp);
            return;
        }

        field.getField().put(index, Sign.CROSS);

        if (checkWin(resp, currentSession, field)) {
            return;
        }

        int emptyFieldIndex = field.getEmptyFieldIndex();

        if (emptyFieldIndex >= 0) {
            field.getField().put(emptyFieldIndex, Sign.NOUGHT);
            if (checkWin(resp, currentSession, field)) {
                return;
            }
        } else {
            currentSession.setAttribute(Constants.DRAW_ATTRIBUTE, true);
            List<Sign> data = field.getFieldData();
            currentSession.setAttribute(Constants.DATA_ATTRIBUTE, data);
            resp.sendRedirect(Constants.INDEX_URL);
            return;
        }

        List<Sign> data = field.getFieldData();

        currentSession.setAttribute(Constants.DATA_ATTRIBUTE, data);
        currentSession.setAttribute(Constants.FIELD_ATTRIBUTE, field);

        resp.sendRedirect(Constants.INDEX_URL);
    }

    private int getSelectedIndex(HttpServletRequest request) {
        String click = request.getParameter(Constants.CLICK_PARAMETER);
        boolean isNumeric = click.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(click) : 0;
    }

    private Field extractField(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute(Constants.FIELD_ATTRIBUTE);
        if (Field.class != fieldAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Сессия поломалась(");
        }
        return (Field) fieldAttribute;
    }

    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if (Sign.CROSS == winner || Sign.NOUGHT == winner) {
            currentSession.setAttribute(Constants.WINNER_ATTRIBUTE, winner);
            List<Sign> data = field.getFieldData();
            currentSession.setAttribute(Constants.DATA_ATTRIBUTE, data);
            response.sendRedirect(Constants.INDEX_URL);
            return true;
        }
        return false;
    }
}

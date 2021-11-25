package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.Dao;
import com.epam.mentoring.poll.dao.impl.PollDaoImpl;
import com.epam.mentoring.poll.model.Poll;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/poll")
public class PollServlet extends HttpServlet {

    private Dao<Poll> pollDao;

    public PollServlet(){
        System.out.println("com.epam.mentoring.poll.servlet.PollServlet is loaded");
        pollDao = new PollDaoImpl();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Poll> pollList = pollDao.findAll();
        req.setAttribute("pollList",pollList);
        req.getRequestDispatcher("/WEB-INF/view/poll.jsp").forward(req,resp);
    }
}

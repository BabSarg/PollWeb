package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.AnswerDao;
import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.dao.ResultDao;
import com.epam.mentoring.poll.dao.impl.AnswerDaoImpl;
import com.epam.mentoring.poll.dao.impl.QuestionDaoImpl;
import com.epam.mentoring.poll.dao.impl.ResultDaoImpl;
import com.epam.mentoring.poll.model.Answer;
import com.epam.mentoring.poll.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/poll/result")
public class PollResultServlet extends HttpServlet {

    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private ResultDao resultDao;

    public PollResultServlet(){
        questionDao = new QuestionDaoImpl();
        answerDao = new AnswerDaoImpl();
        resultDao = new ResultDaoImpl();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> attributeNames = req.getAttributeNames();
        int weight = 0;
        while (attributeNames.hasMoreElements()){
            String attrName = attributeNames.nextElement();
            if(attrName.endsWith("_question")){
                String answerId = req.getParameter(attrName);
                Answer answer = answerDao.findById(Long.parseLong(answerId));
                weight += answer.getWeight();
            }
        }
        Result result = resultDao.findByScoreBetween(weight);
        if(result == null){
            resp.sendRedirect("/poll");
        }else {
            req.setAttribute("result",result);
            req.getRequestDispatcher("/WEB-INF/view/poll_result.jsp").forward(req,resp);
        }

    }
}

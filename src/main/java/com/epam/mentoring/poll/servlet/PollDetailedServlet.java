package com.epam.mentoring.poll.servlet;

import com.epam.mentoring.poll.dao.AnswerDao;
import com.epam.mentoring.poll.dao.QuestionDao;
import com.epam.mentoring.poll.dao.ResultDao;
import com.epam.mentoring.poll.dao.impl.AnswerDaoImpl;
import com.epam.mentoring.poll.dao.impl.QuestionDaoImpl;
import com.epam.mentoring.poll.model.Answer;
import com.epam.mentoring.poll.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/poll/beginpoll")
public class PollDetailedServlet extends HttpServlet {

    private QuestionDao questionDao;
    private AnswerDao answerDao;

    public PollDetailedServlet(){
        questionDao = new QuestionDaoImpl();
        answerDao = new AnswerDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pollId = req.getParameter("pollId");
        if(pollId == null || pollId.trim().isEmpty()){
            resp.sendRedirect("/poll");
        }else {
            List<Question> questionList = questionDao.findByPollId(Long.parseLong(pollId));
            questionList = questionList
                    .stream()
                    .peek(question -> {
                        List<Answer> answerList = answerDao.findByQuestionId(question.getId());
                        question.setAnswers(answerList);
                    }).collect(Collectors.toList());
            req.setAttribute("questionList",questionList);
            req.getRequestDispatcher("/WEB-INF/view/poll_detailed.jsp").forward(req,resp);
        }
    }
}

package com.giffgaff.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.giffgaff.entity.Question;
import com.giffgaff.entity.Survey;



@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();
	private static List<Question> questions = new ArrayList<Question>();
	static {
		Question question1 = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question2 = new Question("Question2",
				"Most Populus Country in the World", "China", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question3 = new Question("Question3",
				"Highest GDP in the World", "United States", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question4 = new Question("Question4",
				"Second largest english speaking country", "India", Arrays
						.asList("India", "Russia", "United States", "China"));

		 questions = new ArrayList<>(Arrays.asList(question1,
				question2, question3, question4));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
				"Description of the Survey", questions);

		surveys.add(survey);
	}

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	/**
	 * @param surveyId
	 * @return
	 */
	public Survey retrieveSurvey(String surveyId) {
		for (Survey survey : surveys) {
			if (survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;
	}

	/**
	 * @param surveyId
	 * @return
	 */
	public List<Question> retrieveQuestions(String surveyId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		return survey.getQuestions();
	}

	/**
	 * @param surveyId
	 * @param questionId
	 * @return
	 */
	public Question retrieveQuestion(String surveyId, String questionId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		for (Question question : survey.getQuestions()) {
			if (question.getId().equals(questionId)) {
				return question;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	/**
	 * @param surveyId
	 * @param question
	 * @return
	 */
	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(randomId);

		survey.getQuestions().add(question);

		return question;
	}

	/**
	 * @param id
	 */
	   public void deleteQuestion(String id) {
	        Iterator<Question> iterator = questions.iterator();
	        while (iterator.hasNext()) {
	        	Question ques = iterator.next();
	            if (ques.getId().equals(id)) {
	                iterator.remove();
	            }
	        }
	    }

	/**
	 * @param question
	 */
	   public void updateQuestion(Question question){
   		questions.remove(question);
   		questions.add(question);
   }
}

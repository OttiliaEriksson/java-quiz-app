package com.github.quizapp.support;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Deque;

import org.junit.jupiter.api.Test;

import com.github.quizapp.models.Question;
import com.github.quizapp.models.Quiz;

/**
 * Tests for {@link QuizParser}. Relies on the test-only quiz.xml in
 * src/test/resources, which contains the quizzes "Geography", "Science"
 * and "Empty".
 * @author Ottilia Eriksson
 */
public class QuizParserTest {

    // ---------- getAllQuizNames ----------
    
   @Test
    void getAllQuizNames_returnsAllNamesInDocumentOrder() {
        String[] names = QuizParser.getAllQuizNames();
 
        assertArrayEquals(new String[]{"Geography", "Science", "Empty"}, names);
    }

    // ---------- parseQuiz: lookup ----------

    @Test
    void parseQuiz_unknownName_returnsNull() {
        assertNull(QuizParser.parseQuiz("Does not exist"));
    }

    @Test
    void parseQuiz_nameIsCaseSensitive() {
        assertNull(QuizParser.parseQuiz("geography"));
    }

    @Test
    void parseQuiz_nullName_returnsNull() {
        assertNull(QuizParser.parseQuiz(null));
    }

    // ---------- parseQuiz: question filtering and order ----------

    @Test
    void parseQuiz_skipsQuestionsWithEmptyTextOrWrongAnswerCount() {
        Quiz quiz = QuizParser.parseQuiz("Geography");
 
        // 4 questions in XML, but 1 has empty text and 1 has too few answers
        assertEquals(2, quiz.questions().size());
    }

    @Test
    void parseQuiz_storedQuestionCountMatchesDequeSize() {
        Quiz quiz = QuizParser.parseQuiz("Geography");
 
        assertEquals(quiz.questions().size(), quiz.nrOfQuestions());
    }

    @Test
    void parseQuiz_keepsQuestionsInDocumentOrder() {
        Quiz quiz = QuizParser.parseQuiz("Geography");
 
        assertNotNull(quiz);
        Deque<Question> questions = quiz.questions();
        assertEquals("What is the capital of Sweden?", questions.pollFirst().questionText());
        assertEquals("Which is the largest ocean?", questions.pollFirst().questionText());
        assertTrue(questions.isEmpty());
    }

    
}

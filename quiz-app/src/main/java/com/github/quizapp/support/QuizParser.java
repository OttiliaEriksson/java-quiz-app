package com.github.quizapp.support;

import com.github.quizapp.models.AnswerOption;
import com.github.quizapp.models.Question;
import com.github.quizapp.models.Quiz;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Utility class responsible for parsing quiz data from an XML file.
 * Provides methods to load a specific quiz by name and to retrieve all available quiz names.
 * @author Ottilia Eriksson
 */
public final class QuizParser {

    /**
     * Parses the quiz with the specified name from the XML resource.
     * Extracts questions and their answer options, assembling them into a Quiz record.
     * @param quizName the name of the quiz to parse
     * @return a Quiz object representing the parsed quiz data, or null if no matching quiz is found
     * @throws RuntimeException if there is an error reading or parsing the XML file
     */
    public static Quiz parseQuiz(final String quizName) {
        try {
            final NodeList quizList = getQuizList();

            for (int i = 0; i < quizList.getLength(); i++) {
                final Node quizNode = quizList.item(i);
                if (quizNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element quizElement = (Element) quizNode;

                    final String currentQuizName = quizElement
                            .getElementsByTagName("quiz-name")
                            .item(0).getTextContent();

                    if (!currentQuizName.equals(quizName)) {
                        continue;
                    }

                    final NodeList questionNodes = quizElement
                            .getElementsByTagName("question");

                    Deque<Question> questions = new ArrayDeque<>();

                    for (int j = 0; j < questionNodes.getLength(); j++) {
                        final Node questionNode = questionNodes.item(j);
                        if (questionNode.getNodeType() == Node.ELEMENT_NODE) {
                            final Element questionElement = (Element) questionNode;

                            final String questionText = questionElement.getAttribute("text");
                            if (questionText.isEmpty()) {
                                continue; // skip questions without text
                            }

                            final NodeList answerNodes = questionElement.getElementsByTagName("answer");
                            if (answerNodes.getLength() != AppConfig.NUM_ANSWER_OPTIONS) {
                                continue; // skip questions without exact number of answers
                            }

                            AnswerOption[] answers = new AnswerOption[answerNodes.getLength()];

                            for (int k = 0; k < answerNodes.getLength(); k++) {
                                final Element answerElement = (Element) answerNodes.item(k);

                                String answerText = answerElement.getAttribute("text");
                                boolean isCorrect = Boolean.parseBoolean(answerElement.getAttribute("right-answer"));

                                answers[k] = new AnswerOption(answerText, isCorrect);
                            }

                            questions.addLast(new Question(questionText, answers));
                        }
                    }

                    return new Quiz(currentQuizName, questions.size(), questions);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Reads the quiz XML file and returns a NodeList containing all <quiz> elements.
     * @return a NodeList of all quiz elements in the XML document
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created
     * @throws IOException if the XML resource file cannot be read
     * @throws SAXException if parsing the XML fails
     * @throws RuntimeException if the quiz XML file cannot be found in the classpath
     */
    private static NodeList getQuizList() throws ParserConfigurationException,
            IOException, SAXException {
        final InputStream inputStream = QuizParser.class
                .getClassLoader().getResourceAsStream("quiz.xml");
        if (inputStream == null) {
            throw new RuntimeException("Could not find file");
        }

        final DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                .newInstance();

        final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        final Document doc = dBuilder.parse(inputStream);
        doc.getDocumentElement().normalize();

        return doc.getElementsByTagName("quiz");
    }

    /**
     * Retrieves the names of all quizzes available in the quiz XML file.
     * @return an array of quiz names as Strings
     * @throws RuntimeException if there is an error reading or parsing the XML file
     */
    public static String[] getAllQuizNames() {
        try {
            NodeList quizList = getQuizList();
            List<String> quizNames = new ArrayList<>();

            for (int i = 0; i < quizList.getLength(); i++) {
                Node quizNode = quizList.item(i);
                if (quizNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element quizElement = (Element) quizNode;
                    String quizName = quizElement
                            .getElementsByTagName("quiz-name")
                            .item(0)
                            .getTextContent();
                    quizNames.add(quizName);
                }
            }
            return quizNames.toArray(new String[0]);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the number of valid questions in a quiz with the given name.
     *
     * @param quizName the name of the quiz
     * @return the number of valid questions in the specified quiz, or 0 if not found
     * @throws RuntimeException if there is an error reading or parsing the XML file
     */
    public static int getQuestionCount(final String quizName) {
        try {
            final NodeList quizList = getQuizList();

            for (int i = 0; i < quizList.getLength(); i++) {
                final Node quizNode = quizList.item(i);
                if (quizNode.getNodeType() == Node.ELEMENT_NODE) {
                    final Element quizElement = (Element) quizNode;
                    final String currentQuizName = quizElement
                            .getElementsByTagName("quiz-name")
                            .item(0).getTextContent();

                    if (!currentQuizName.equals(quizName)) {
                        continue;
                    }

                    return getValidQuestionCount(quizElement);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    /**
     * Counts and returns the number of valid questions in the provided quiz element.
     * A question is valid if it has non-empty text and exactly the required number of answer options.
     * @param quizElement the XML element representing the quiz
     * @return the number of valid questions
     */
    private static int getValidQuestionCount(Element quizElement) {
        final NodeList questionNodes = quizElement
                .getElementsByTagName("question");

        int validQuestionCount = 0;

        for (int j = 0; j < questionNodes.getLength(); j++) {
            final Element questionElement = (Element) questionNodes.item(j);
            final String questionText = questionElement.getAttribute("text");

            final NodeList answerNodes = questionElement
                    .getElementsByTagName("answer");

            if (!questionText.isEmpty()
                    && answerNodes.getLength() == AppConfig.NUM_ANSWER_OPTIONS) {
                validQuestionCount++;
            }
        }
        return validQuestionCount;
    }
}

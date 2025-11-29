package com.comp3607project;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class JeopardyGameTest {
        //parsing, gameplay, scoring, reporting, and logging.

        //TESTS FOR PARSING
        @Test
        public void testCSVHandlerValidFile() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_CSV.csv");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);

                assertEquals(25, contents.size());
                assertEquals("Variables & Data Types", contents.getFirst().getCategory());
                assertEquals(100, contents.getFirst().getValue());
                assertEquals("Which of the following declares an integer variable in C++?", contents.getFirst().getQuestion());
                assertEquals("int num;", contents.getFirst().getA());
                assertEquals("float num;", contents.getFirst().getB());
                assertEquals("num int;", contents.getFirst().getC());
                assertEquals("integer num;", contents.getFirst().getD());
                assertEquals('A', contents.getFirst().getAnswer());
                

                System.out.println("CSV Valid File test:\nExpected number of questions: 25\nActual size of contents list: " + contents.size());
                System.out.println("Expected category: Variables & Data Types\nActual category: " + contents.getFirst().getCategory());
                System.out.println("Expected value: 100\nActual value: " + contents.getFirst().getValue());
                System.out.println("Expected question: Which of the following declares an integer variable in C++?:\nActual question" + contents.getFirst().getQuestion());
                System.out.println("Expected option A: int num\nActual option A: " + contents.getFirst().getA());
                System.out.println("Expected option B: float num\nActual option B: " + contents.getFirst().getB());
                System.out.println("Expected option C: num int\nActual option C: " + contents.getFirst().getC());
                System.out.println("Expected option D: integer num\nActual option D: " + contents.getFirst().getD() + "\n");
        
        }       

        @Test
        public void testJSONHandlerValidFile() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_JSON.json");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);

                assertEquals(25, contents.size());
                assertEquals("Variables & Data Types", contents.getFirst().getCategory());
                assertEquals(100, contents.getFirst().getValue());
                assertEquals("Which of the following declares an integer variable in C++?", contents.getFirst().getQuestion());
                assertEquals("int num;", contents.getFirst().getA());
                assertEquals("float num;", contents.getFirst().getB());
                assertEquals("num int;", contents.getFirst().getC());
                assertEquals("integer num;", contents.getFirst().getD());
                assertEquals('A', contents.getFirst().getAnswer());

                System.out.println("JSON Valid File Test:\nExpected number of questions: 25\nActual size of contents list: " + contents.size());
                System.out.println("Expected category: Variables & Data Types\nActual category: " + contents.getFirst().getCategory());
                System.out.println("Expected value: 100\nActual value: " + contents.getFirst().getValue());
                System.out.println("Expected question: Which of the following declares an integer variable in C++?:\nActual question" + contents.getFirst().getQuestion());
                System.out.println("Expected option A: int num\nActual option A: " + contents.getFirst().getA());
                System.out.println("Expected option B: float num\nActual option B: " + contents.getFirst().getB());
                System.out.println("Expected option C: num int\nActual option C: " + contents.getFirst().getC());
                System.out.println("Expected option D: integer num\nActual option D: " + contents.getFirst().getD() + "\n");
        }

        @Test
        public void testXMLHandlerValidFile() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);

                assertEquals(25, contents.size());
                assertEquals("Variables & Data Types", contents.getFirst().getCategory());
                assertEquals(100, contents.getFirst().getValue());
                assertEquals("Which of the following declares an integer variable in C++?", contents.getFirst().getQuestion());
                assertEquals("int num;", contents.getFirst().getA());
                assertEquals("float num;", contents.getFirst().getB());
                assertEquals("num int;", contents.getFirst().getC());
                assertEquals("integer num;", contents.getFirst().getD());
                assertEquals('A', contents.getFirst().getAnswer());

                System.out.println("XML Valid File Test:\nExpected number of questions: 25\nActual size of contents list: " + contents.size());
                System.out.println("Expected category: Variables & Data Types\nActual category: " + contents.getFirst().getCategory());
                System.out.println("Expected value: 100\nActual value: " + contents.getFirst().getValue());
                System.out.println("Expected question: Which of the following declares an integer variable in C++?:\nActual question" + contents.getFirst().getQuestion());
                System.out.println("Expected option A: int num\nActual option A: " + contents.getFirst().getA());
                System.out.println("Expected option B: float num\nActual option B: " + contents.getFirst().getB());
                System.out.println("Expected option C: num int\nActual option C: " + contents.getFirst().getC());
                System.out.println("Expected option D: integer num\nActual option D: " + contents.getFirst().getD() + "\n");
        }

        //TESTS FOR GAMEPLAY
        @Test
        public void testLoadGameContent() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);

                GameManager manager = new GameManager(contents);
                assertFalse(manager.checkIfEmpty());
                System.out.println("Test to load game content:\nExpected results for checkIfEmpty: False\nActual results for checkIfEmpty: " + manager.checkIfEmpty()+"\n");

        }
        
        @Test
        public void testValidCategory() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                Set <String> categories = manager.getCategories();
                String category = "Variables & Data Types";

                assertTrue(categories.contains(category));
                System.out.println("Test for valid category (Variables & Data Types):\nExpected Results: true\nActual Results: " + categories.contains(category) + "\n" );

        }

        @Test
        public void testValidValue() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                Set <Integer> values = manager.getValues("Arrays");
                int value = 100;

                assertTrue(values.contains(value));
                System.out.println("Test for valid value (100):\nExpected Results: true\nActual value: " + values.contains(value) + "\n" );
        }

        @Test
        public void testInvalidCategory() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                String category = "Game";
                Set <String> categories = manager.getCategories();
                assertFalse(categories.contains(category));

                System.out.println("Test for invalid category (Game):\nExpexted Results: false \nActual Results: " + categories.contains(category) + "\n" );

        }

        @Test
        public void testInvalidValue() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                Set <Integer> values = manager.getValues("Arrays");
                int value = 800;

                assertFalse(values.contains(value));
                System.out.println("Test for invalid value (800):\nExpected Results: false\nActual value: " + values.contains(value) + "\n" );
        }

        @Test 
        public void testVaildQuestion() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                String categoey = "Variables & Data Types";
                int value = 100;

                GameContent question = manager.getQuestionInfo(categoey, value);

                assertTrue(question.getQuestion().equals(contents.getFirst().getQuestion()));
                assertTrue(question.getQuestion().equals("Which of the following declares an integer variable in C++?"));

                System.out.println("Test a vaild question is returned when a valid category and value is given(Variables & Data Types, 100:\nExpecteted question: Which of the following declares an integer variable in C++?\nActual question: " + question.getQuestion() + "\n");
        }

        @Test 
        public void testInvaildQuestion() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                String categoey = "Game";
                int value = 100;

                GameContent question = manager.getQuestionInfo(categoey, value);

                assertNull(question);

                System.out.println("Test that null is returned when an invalid category given is given to retrieve a question(Game, 100:\nExpecteted results: null \nActual results: " + question + "\n");
               

        }

        @Test
        public void testMarkAsAnswered() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                GameContent question = manager.getQuestionInfo("Arrays", 200);

                manager.markAsAnswered(question, "Arrays");
                List<GameContent> answeredList = manager.getAnsweredQuestions(); 
                
                assertTrue(answeredList.contains(question));
                assertNull(manager.getQuestionInfo("Arrays", 200));
                
                System.out.println ("Test if question is removed from map when markAsAnswered is called and inserted into a new list:\nResults expected for map: null\nActual results for map: " + manager.getQuestionInfo("Arrays", 200) + "\nResults expected for list: true\n" + //
                "Actual results for list: " + answeredList.contains(question) + " \n");
        }

        @Test
        public void testCheckIfEmpty() throws URISyntaxException{

                URL resourceUrl = getClass().getClassLoader().getResource("sample_game_XML.xml");
                File file = new File(resourceUrl.toURI());

                InputHandlerFactory input = new InputHandlerFactory();
                InputHandler inputHandler = input.getInputHandler(file);

                List <GameContent> contents = inputHandler.parse(file);
                GameManager manager = new GameManager(contents);

                for (GameContent g : contents){

                        manager.markAsAnswered(g, g.getCategory());
                }

                assertTrue(manager.checkIfEmpty());
                System.out.println("Test that the game map is empty when all questions are marked as answered:\nExpected results: true\nActual results: " + manager.checkIfEmpty() + "\n");

        }

        @Test
        public void testAddScore(){

        
                Player p1 = new Player("shem");
                p1.addPoints(100);

                assertTrue(100 == p1.getScore());
                System.out.println("Test that a value is correctly added to players score:\nExpected results: 100\nActual results: " + p1.getScore() +"\n");
        }

        @Test
        public void testRemoveScore(){

        
                Player p1 = new Player("shem");
                p1.addPoints(400);
                p1.removePoints(100);

                assertTrue(300 == p1.getScore());
                System.out.println("Test that a value is correctly removed to players score:\nExpected results: 300\nActual results: " + p1.getScore() +"\n");
        }

        //TESTS FOR LOGGING
        @Test
        public void testLogging(){

                File file = new File("test.csv");
                Game game = new Game(file);
                GameLogger logger = new GameLogger();

                game.addObserver(logger);

                LogData log = new LogData("Test001", "testPlayerID", "Testing",LocalDateTime.now(), "Test",0,"","",0);
                game.notify(log, 0, "");

                logger.generateEventLog("TestLogGenerated001");

                assertTrue(logger.fileCreated());

                System.out.println("Test that logs are created and stored in CSV file:\n Expected reults: true\nActual results: " + logger.fileCreated() + "\n");


        }
}

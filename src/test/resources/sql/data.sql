INSERT INTO Student(id, name, surname, student_number, gender, status, created_date, updated_date)
VALUES (1, 'John', 'Wick', '12345', 'MALE', true, now(), now());

INSERT INTO Student (id, name, surname, student_number, gender, status, created_date, updated_date)
VALUES (2, 'John', 'Doe', '12346', 'MALE', true, now(), now());

INSERT INTO Student (id, name, surname, student_number, gender, status, created_date, updated_date)
VALUES (3, 'John', 'Test', '12347', 'MALE', true, now(), now());

INSERT INTO Test (id, name, status, created_date, updated_date)
VALUES (1, 'test1', true, now(), now());

INSERT INTO Test (id, name, status, created_date, updated_date)
VALUES (2, 'test2', true, now(), now());

INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (1, 1, 'question-text', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION1', true, now(),
        now());
INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (2, 1, 'question-text2', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION2', true, now(),
        now());
INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (3, 1, 'question-text3', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION3', true, now(),
        now());

INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (4, 2, 'question-text4', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION1', true, now(),
        now());
INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (5, 2, 'question-text5', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION2', true, now(),
        now());
INSERT INTO Question (id, test_id, text, option1, option2, option3, option4, answer, status, created_date, updated_date)
VALUES (6, 2, 'question-text6', 'OPTION1-text', 'OPTION2-text', 'OPTION3-text', 'OPTION4-text', 'OPTION3', true, now(),
        now());

INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (1, 1, 1, true, 'OPTION1', true, now(), now());
INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (2, 1, 2, true, 'OPTION2', true, now(), now());
INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (3, 1, 3, true, 'OPTION3', true, now(), now());

INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (4, 2, 4, true, 'OPTION1', true, now(), now());
INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (5, 2, 5, false, 'OPTION4', true, now(), now());
INSERT INTO Answer (id, student_id, question_id, answer_is_correct, question_answer, status, created_date, updated_date)
VALUES (6, 2, 6, false, 'OPTION3', true, now(), now());

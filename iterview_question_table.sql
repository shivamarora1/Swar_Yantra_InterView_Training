-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2018 at 05:30 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swar_yantra`
--

-- --------------------------------------------------------

--
-- Table structure for table `iterview_question_table`
--

CREATE TABLE `iterview_question_table` (
  `id` int(10) NOT NULL,
  `question` varchar(100) NOT NULL,
  `hint` varchar(1000) NOT NULL,
  `audio_url` varchar(1000) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iterview_question_table`
--

INSERT INTO `iterview_question_table` (`id`, `question`, `hint`, `audio_url`, `created_at`, `updated_at`) VALUES
(0, 'What motivates you to do good job?', 'My aim for supporting my family financially and to see a proud smile on my parents\' face motivates me to do a good job. And another reason is that I want to prove myself and become self-dependent in terms of money and to get good fame.', '1516094936.mp3', '2018-01-16 09:28:56', '2018-01-16 09:28:56'),
(1, 'What are yours goals?', 'My goal is doing hard work and become to be a collector because of thought and done are goal nothing is impossible but Cross any difficulty by myself.', '1515742217.mp3', '2018-01-21 09:27:09', '2018-01-12 07:30:16'),
(2, 'Why should I  hire you?', 'Sir I have one year experience theoretical and practical knowledge related to my branch and l promis', 'audio2.mp3', '2018-01-09 13:01:57', '2018-01-09 13:01:57'),
(3, 'What are your strengths and weaknesses?', 'Sir my strengths are my patience and my positive attitude towards each and every adverse situation. ', 'audio3.mp3', '2018-01-09 13:01:58', '2018-01-09 13:01:58'),
(4, 'Why do you want to work at our company?', 'Well, Why not your company? I am a fresher with great ability to learn and take up new challenges. A', 'audio4.mp3', '2018-01-09 13:01:58', '2018-01-09 13:01:58'),
(5, 'What is the difference between confidence and over confidence?\r\n', 'Well, according to me in short confidence means I can do this work\' and overconfidence means ONLY I ', 'audio5.mp3', '2018-01-09 13:01:58', '2018-01-09 13:01:58'),
(6, 'What is the difference between hard work and smart work', 'Hard work is putting in as many efforts as you can to get the desired results whereas smart work is ', 'audio6.mp3', '2018-01-09 13:01:58', '2018-01-09 13:01:58'),
(7, 'Can you work under pressure?', 'I love to work under pressure and I have experienced it during my college cultural.', 'audio7.mp3', '2018-01-09 13:01:58', '2018-01-09 13:01:58'),
(8, 'Are you willing to relocate or travel?', '\r\nIndeed, Without any hesitation.\r\nAs an international cabin crew, I traveled from one destination t', 'audio8.mp3', '2018-01-09 13:20:35', '2018-01-09 13:20:35'),
(9, 'How do you feel about working nights and weekends?', '\r\nI have worked in the company internationally and globally where I had flown during days and nights', 'audio9.mp3', '2018-01-09 13:20:35', '2018-01-09 13:20:35'),
(10, 'What makes you angry?', 'Nothing can make me angry as I am skilled in the training of emotional intelligence as cabin crew career. I deal every situation with soft-spoken but firm words. And I put each and every sentence professionally. I don\'t let the person cross the line to angry me as simple as that.', '1515742267.mp3', '2018-01-12 07:31:06', '2018-01-12 07:31:06'),
(11, 'Give me example of your creativity?', 'Well, I think creative things means to make something different with your skills.  I would like to share my little creative thing that I have made when I was 12. I have made one boxing heavy bag at home with some non usable cloths and some other stuff (I will tell the process if they ask.', '1515761344.mp3', '2018-01-12 12:49:04', '2018-01-12 12:49:04'),
(12, 'What are your outside interest ?', 'I enjoy reading fiction novels & learning how to cook new recipes. And sometimes, just sitting silent. Besides these, I am open to trying anything I have not done earlier.', '1515823661.mp3', '2018-01-13 06:07:40', '2018-01-13 06:07:40'),
(13, 'Who has inspired you in your life and why?', 'I would like to say every parent inspires his/her child, but Some of the hardest journey that an individual undergo is to be inspired by real-time heroes whose struggles motivates other\'s to follow that path like Dr. APJ Abdul Kalam who has the ability to take every situation in a very positive also I would like to add we can be inspired by our mistakes and can learn from them!', '1515823725.mp3', '2018-01-13 06:08:45', '2018-01-13 06:08:45'),
(14, 'Tell me something about our company?', 'As per my knowledge your company is fast growing, well reputed company in market and employee oriented also and also your company provides lots of opportunity in future and well defined adaptable atmosphere.', '1516013757.mp3', '2018-01-15 10:55:57', '2018-01-15 10:55:57'),
(15, 'Where do u see your self in next 5 years..', '5 years down the line I see myself as a mid-tier associate having a relevant work experience in my field, helping out the newcomers, cooperating with my colleagues and thus developing on an individual as well as on organizational level.', '1516014081.mp3', '2018-01-15 11:01:20', '2018-01-15 11:01:20'),
(16, 'What was the toughest decision u ever had?', 'Studying engineering is the toughest decision for me.', '1516015717.mp3', '2018-01-15 11:28:37', '2018-01-15 11:28:37'),
(17, 'Have you considered starting your own business?', 'Presently I figure I don\'t have an arrangement to begin business since I am a fresher I don\'t have much commonsense and market information. Initially, I need to learn. In the wake of picking up encounter I will think about that.', '1516016696.mp3', '2018-01-15 11:44:56', '2018-01-15 11:44:56'),
(18, 'Would you lie for the company?', 'Sorry Sir, I will Never lie. Because Lie cannot remain hidden for a long time. It may be caught.', '1516017768.mp3', '2018-01-15 12:02:48', '2018-01-15 12:02:48'),
(19, 'What is your salary expectation?', 'This is one of the most troublesome questions for many interviewees. For some people, however, it causes no bother at all. It will depend on your personality as to how you feel talking about salary expectations. That said, there are some tips to help you deal with the question. Firstly, it is okay to talk about pay in terms of ranges and not to be specific about a particular number. It is also okay to include other benefits, like healthcare, pensions and time off within the context of salary. Make sure you have looked at other, similar jobs being advertised in other organisations so that you have an idea of the pay rate in the market.', 'audio10.mp3', '2018-01-22 06:08:12', '2018-01-22 06:08:12'),
(20, 'Is there anything that you would like to ask me?', 'Always have at least one question prepared in advance. This is your chance to drill down into an area of the business that might not have been covered in the interview. Alternatively, you may simply like to ask for feedback on how you have done in the interview.  A good tip is to pick up on something that has been mentioned in passing by the interviewer about the job. Ask him or her to expand on this. Not only does it make you appear interested, but it shows that you have been listening attentively to what has been said. It should leave the interviewer with a good final impression of you.  These ten questions are certainly not the only ones that can be posed, but they are the most common ones. Remember that you donâ€™t need to answer all questions at an interview if you feel they are too personal or you are not comfortable with them. Getting yourself prepared for common questions is necessary prep work before attending an interview.', 'audio17.mp3', '2018-01-22 06:00:56', '2018-01-22 06:00:56'),
(21, 'What makes a good team player?', 'Many people say in their CV that they are good at working cooperatively or are team players, but few say what this actually means. Think about examples from your past that demonstrate your ability to build bridges, form networks or simply get on with people. This neednâ€™t be from your professional life. You could cite any examples from clubs or organisations to which you belong.', 'audio12.mp3', '2018-01-22 06:03:26', '2018-01-22 06:03:26'),
(22, 'What do you mean by platform independence?', 'Platform independence means that we can write and compile the java code in one platform (eg Windows) and can execute the class in any other supported platform eg (Linux,Solaris,etc).', 'audio13.mp3', '2018-01-22 06:22:45', '2018-01-22 06:22:45'),
(23, 'What is a JVM?', 'JVM is Java Virtual Machine which is a run time environment for the compiled java class files.', 'audio14.mp3', '2018-01-22 06:43:55', '2018-01-22 06:43:55'),
(24, 'Are JVM\'s platform independent?', 'JVM\'s are not platform independent. JVM\'s are platform specific run time implementation provided by the vendor.', 'audio15.mp3', '2018-01-22 06:45:00', '2018-01-22 06:45:00'),
(25, 'What is the difference between a JDK and a JVM?', 'JDK is Java Development Kit which is for development purpose and it includes execution environment also. But JVM is purely a run time environment and hence you will not be able to compile your source files using a JVM.', '1516603804.mp3', '2018-01-22 06:50:03', '2018-01-22 06:50:03'),
(26, 'Differences between C and Java?', ' 1.JAVA is Object-Oriented while C is procedural.  2.Java is an Interpreted language while C is a compiled language.  3.C is a low-level language while JAVA is a high-level language.  4.C uses the top-down approach while JAVA uses the bottom-up approach.  5.Pointer go backstage in JAVA while C requires explicit handling of pointers.  6.The Behind-the-scenes Memory Management with JAVA & The User-Based Memory Management in C.  7.JAVA supports Method Overloading while C does not support overloading at all.  8.Unlike C, JAVA does not support Preprocessors, & does not really them.  9.The standard Input & Output Functions--C uses the printf & scanf functions as its standard input & output while JAVA uses the System.out.print & System.in.read functions.  10.Exception Handling in JAVA And the errors & crashes in C.', '1516613170.mp3', '2018-01-22 09:26:09', '2018-01-22 09:26:09'),
(27, 'What is inner class in java?\r\n', 'We can define a class inside a class and they are called nested classes. Any non-static nested class is known as inner class. Inner classes are associated with the object of the class and they can access all the variables and methods of the outer class. Since inner classes are associated with instance, we can’t have any static variables in them.', '1516614989.mp3', '2018-01-22 09:56:28', '2018-01-22 09:56:28'),
(28, 'How to sort a collection of custom Objects in Java?', 'We need to implement Comparable interface to support sorting of custom objects in a collection. Comparable interface has compareTo(T obj) method which is used by sorting methods and by providing this method implementation, we can provide default way to sort custom objects collection.\r\n\r\nHowever, if you want to sort based on different criteria, such as sorting an Employees collection based on salary or age, then we can create Comparator instances and pass it as sorting methodology. For more details read Java Comparable and Comparator', '1516615358.mp3', '2018-01-22 10:02:37', '2018-01-22 10:02:37'),
(29, 'What is the benefit of Composition over Inheritance?\r\n', 'One of the best practices of java programming is to “favor composition over inheritance”. Some of the possible reasons are:\r\n\r\nAny change in the superclass might affect subclass even though we might not be using the superclass methods. For example, if we have a method test() in subclass and suddenly somebody introduces a method test() in superclass, we will get compilation errors in subclass. Composition will never face this issue because we are using only what methods we need.', '1516617689.mp3', '2018-01-22 10:41:29', '2018-01-22 10:41:29'),
(30, 'What is composition in java?', 'omposition is the design technique to implement has-a relationship in classes. We can use Object composition for code reuse.\r\n\r\nJava composition is achieved by using instance variables that refers to other objects. Benefit of using composition is that we can control the visibility of other object to client classes and reuse only what we need. Read more with example at Java Composition example.', '1516617952.mp3', '2018-01-22 10:45:52', '2018-01-22 10:45:52');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `iterview_question_table`
--
ALTER TABLE `iterview_question_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `iterview_question_table`
--
ALTER TABLE `iterview_question_table`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

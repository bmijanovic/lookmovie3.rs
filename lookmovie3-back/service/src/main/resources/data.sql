INSERT INTO USERS(ID, EMAIL, NAME, PASSWORD, EMAIL_VERIFIED, LOGIN_ATTEMPTS, DELETED, LIKED_GENRES) VALUES
    ('e3661c31-d1a4-47ab-94b6-1c6500dccf25', 'ognjen@gmail.com', 'Ognjen Radovanovic', '$2y$10$uaQTp4iF5tJoC.R4kCzWOuWB0zNveArf/oKXDvnSs81Z563fPAmZ6', true, 0, false, '{}'::smallint[]),
    ('e3661c31-d1a4-47ab-94b6-1c6500dccf24', 'bojan@gmail.com', 'Bojan Mijanovic', '$2y$10$C836IyKmo1L08tOzYm8r9.CCW6CjnClul764C1E9DP9aqnO8LOl5G', true, 0, false, '{}'::smallint[]);



INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id) VALUES ('e3661c31-d1a4-47ab-94b6-1c6500dccf25', 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES ('e3661c31-d1a4-47ab-94b6-1c6500dccf24', 1);

-- Insert Directors
INSERT INTO director (id, name, surname) VALUES
('e3661c31-d1a4-47ab-94b6-1c6500dccf26', 'Quentin', 'Tarantino'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf27', 'Christopher', 'Nolan'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'Martin', 'Scorsese'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf29', 'Stanley', 'Kubrick'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf30', 'Alfred', 'Hitchcock'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf31', 'Steven', 'Spielberg'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf32', 'James', 'Cameron'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf33', 'Tim', 'Burton'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf34', 'Peter', 'Jackson'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf35', 'Ridley', 'Scott'),
('e3661c31-d1a4-47ab-94b6-1c6500dccd36', 'Damien', 'Chazelle'),
('e3661c31-d1a4-47ab-94b6-1c6500dccd37', 'Lana', 'Wachowski'),
('e3661c31-d1a4-47ab-94b6-1c6500dccd38', 'David', 'Fincher'),
('e3661c31-d1a4-47ab-94b6-1c6500dccd39', 'Wes', 'Anderson');


-- Insert Actors
INSERT INTO actor (id, Name, Surname) VALUES
('e3661c31-d1a4-47ab-94b6-1c6500dccf36', 'Tom', 'Hanks'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf37', 'Meryl', 'Streep'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf38', 'Brad', 'Pitt'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf39', 'Emma', 'Stone'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'Robert', 'De Niro'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf41', 'Jennifer', 'Lawrence'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'Leonardo', 'DiCaprio'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf43', 'Charlize', 'Theron'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf44', 'Johnny', 'Depp'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf45', 'Angelina', 'Jolie');


-- Insert Films
INSERT INTO film (id, name, genre, duration, year, main_actor_id, director_id, description) VALUES
('e3661c31-d1a4-47ab-94b6-1c6500dccf46', 'Inglourious Basterds', 14, 153, 2009, 'e3661c31-d1a4-47ab-94b6-1c6500dccf41', 'e3661c31-d1a4-47ab-94b6-1c6500dccf26', 'In Nazi-occupied France during World WAR II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner''s vengeful plans for the same.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf47', 'The Dark Knight', 0, 152, 2008, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf27', 'When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf48', 'The Godfather', 11, 175, 1972, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf49', 'Forrest Gump', 2, 142, 1994, 'e3661c31-d1a4-47ab-94b6-1c6500dccf36', 'e3661c31-d1a4-47ab-94b6-1c6500dccf31', 'The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf50', 'Titanic', 3, 195, 1997, 'e3661c31-d1a4-47ab-94b6-1c6500dccf45', 'e3661c31-d1a4-47ab-94b6-1c6500dccf32', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf51', 'Jurassic Park', 10, 127, 1993, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf31', 'During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf52', 'La La Land', 17, 128, 2016, 'e3661c31-d1a4-47ab-94b6-1c6500dccf39', 'e3661c31-d1a4-47ab-94b6-1c6500dccd36', 'While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf53', 'The Shawshank Redemption', 2, 142, 1994, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf54', 'The Lord of the Rings: The Fellowship of the Ring', 5, 178, 2001, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf34', 'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf55', 'The Matrix', 0, 136, 1999, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccd37', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the WAR against its controllers.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf56', 'The Silence of the Lambs', 11, 118, 1991, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf57', 'Gladiator', 0, 155, 2000, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf35', 'A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf58', 'Avatar', 10, 162, 2009, 'e3661c31-d1a4-47ab-94b6-1c6500dccf43', 'e3661c31-d1a4-47ab-94b6-1c6500dccf32', 'A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf59', 'Interstellar', 10, 169, 2014, 'e3661c31-d1a4-47ab-94b6-1c6500dccf41', 'e3661c31-d1a4-47ab-94b6-1c6500dccf27', 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf60', 'The Departed', 11, 151, 2006, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf61', 'Fight Club', 2, 139, 1999, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf35', 'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf62', 'The Avengers', 0, 143, 2012, 'e3661c31-d1a4-47ab-94b6-1c6500dccf36', 'e3661c31-d1a4-47ab-94b6-1c6500dccf35', 'Earth\''s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf63', 'The Prestige', 2, 130, 2006, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf27', 'After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion while sacrificing everything they have to outwit each other.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf64', 'Black Panther', 0, 134, 2018, 'e3661c31-d1a4-47ab-94b6-1c6500dccf43', 'e3661c31-d1a4-47ab-94b6-1c6500dccf31', 'T\''Challa, heir to the hidden but advanced kingdom of Wakanda, must step forWARd to lead his people into a new future and must confront a challenger from his country\''s past.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf65', 'The Social Network', 20, 120, 2010, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccd38', 'As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea, and by the co-founder who was later squeezed out of the business.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf66', 'The Grand Budapest Hotel', 10, 99, 2014, 'e3661c31-d1a4-47ab-94b6-1c6500dccf39', 'e3661c31-d1a4-47ab-94b6-1c6500dccd39', 'A writer encounters the owner of an aging high-class hotel, who tells him of his early years serving as a lobby boy in the hotel\''s glorious years under an exceptional concierge.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf68', 'Inception', 6, 148, 2010, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccd37', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf69', 'The Green Mile', 11, 189, 1999, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'The lives of guards on Death Row are affected by one of their charges: a black man accused of child murder and rape, yet who has a mysterious gift.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf70', 'The Departed', 11, 151, 2006, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf71', 'The Shawshank Redemption', 2, 142, 1994, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf72', 'The Lord of the Rings: The Return of the King', 5, 201, 2003, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf34', 'Gandalf and Aragorn lead the World of Men against Sauron\''s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf73', 'The Godfather: Part II', 11, 202, 1974, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf74', 'The Dark Knight Rises', 0, 164, 2012, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccd37', 'Eight years after the Joker''s reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City from the brutal guerr'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf75', 'Fight Club', 2, 139, 1999, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf35', 'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much ||s survival.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf76', 'The Departed', 11, 151, 2006, 'e3661c31-d1a4-47ab-94b6-1c6500dccf40', 'e3661c31-d1a4-47ab-94b6-1c6500dccf28', 'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.'),
('e3661c31-d1a4-47ab-94b6-1c6500dccf77', 'The Lord of the Rings: The Two Towers', 5, 179, 2002, 'e3661c31-d1a4-47ab-94b6-1c6500dccf42', 'e3661c31-d1a4-47ab-94b6-1c6500dccf34', 'While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron\''s new ally, Saruman, and his hordes of Isengard.');

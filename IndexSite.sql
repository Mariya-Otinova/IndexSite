CREATE DATABASE base_indexsite CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE TABLE base_indexsite.userlist (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT, /*не обновляется при update и не переприсваивается при delete*/
  created datetime NOT NULL, /*не обновляется автоматически*/
  upcreated timestamp, /*обновляется автоматически при insert и update*/
  login varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci UNIQUE NOT NULL,
  password text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
  );
CREATE TABLE base_indexsite.sitelist (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT, /*не обновляется при update и не переприсваивается при delete*/
  user_id int NOT NULL REFERENCES base_indexsite.userlist (id), /*идентификатор юзера, который внёс изменения*/
  created datetime NOT NULL, /*не обновляется автоматически*/
  upcreated timestamp, /*обновляется автоматически при insert и update*/
  url text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  deletflag enum("0","1") DEFAULT "0" /*Флаг удаления (реального удаления не происходит)*/
  );
CREATE TABLE base_indexsite.dynamic_index (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT, /*не обновляется при update и не переприсваивается при delete*/
  site_id int NOT NULL REFERENCES base_indexsite.sitelist (site_id),
  user_id int NOT NULL REFERENCES base_indexsite.userlist (id), /*идентификатор юзера, который внёс изменения*/
  created timestamp, /*обновляется автоматически при insert*/
  uniqwords int, /*количество уникальных слов*/
  words int, /*количество слов*/
  latin_words int, /*количество слов латинскими буквами*/
  cyrillic_words int, /*количество слов на кириллице*/
  freq_wordlist text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci, /*строка слово:частота через запятую*/
  freq_letter text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci /*строка слово:частота через запятую*/
  );









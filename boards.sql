SELECT * FROM boards;
USE board;

DESC boards;

CREATE TABLE boards (
    id INT AUTO_INCREMENT PRIMARY KEY, -- 게시글 ID (기본 키, 자동 증가)
    title VARCHAR(255) NOT NULL,       -- 게시글 제목
    content TEXT NOT NULL            -- 게시글 상세 내용
) ;

DROP TABLE IF EXISTS boards;
SHOW TABLES;
DESCRIBE boards;
SHOW TABLES LIKE 'boards';

GRANT ALL PRIVILEGES ON board.* TO 'root'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'root'@'localhost';

DESCRIBE boards;

SHOW CREATE TABLE boards;

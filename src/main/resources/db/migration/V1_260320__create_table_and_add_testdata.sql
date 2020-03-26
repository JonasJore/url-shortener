CREATE TABLE shortened_url (
    id varchar(10) NOT NULL,
    url varchar(450),
    shortened varchar(150),
    PRIMARY KEY (id)
);

INSERT INTO shortened_url VALUES ("WjHlQ2LOoY", "https://www.reddit.com", "snoo");
INSERT INTO shortened_url VALUES ("aSdRtUlKJh", "https://www.finn.no", "finn");
CREATE TABLE shortened_url (
    uuid VARCHAR(36) NOT NULL,
    shortened_url_id VARCHAR(10) NOT NULL,
    original_url VARCHAR(450),
    shortened_url_name VARCHAR(150),
    PRIMARY KEY (uuid, shortened_url_id)
);

INSERT INTO shortened_url VALUES ("a09f3193-3547-4370-a5fc-efb728ed6928", "WjHlQ2LOoY", "https://www.reddit.com", "snoo");
INSERT INTO shortened_url VALUES ("ca311fcb-d466-4fe5-9e29-397b41adc711", "aSdRtUlKJh", "https://www.finn.no", "finn");
INSERT INTO shortened_url VALUES ("aa89f584-f6a6-4d8f-8ae3-a986b7b21569", "dfgDSq2NfJ","https://www.twitch.tv", "twitch");
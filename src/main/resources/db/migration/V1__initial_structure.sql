CREATE TABLE IF NOT EXISTS numerology (
    id                  bigint NOT NULL AUTO_INCREMENT,
    `number`            int(10) not null,
    description         TEXT,
    type                varchar(255) NOT NULL,
    PRIMARY KEY  (`id`),
    INDEX idx_number_type (`number`, type)
)  ENGINE InnoDB;
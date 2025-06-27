CREATE TABLE unit (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(1024),
    parent_unit_id UUID,
    CONSTRAINT fk_parent_unit FOREIGN KEY (parent_unit_id)
      REFERENCES unit(id)
);
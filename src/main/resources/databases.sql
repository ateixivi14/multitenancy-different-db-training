CREATE USER owner;

CREATE DATABASE tenant_1;
GRANT ALL PRIVILEGES ON DATABASE tenant_1 TO owner;

CREATE DATABASE tenant_2;
GRANT ALL PRIVILEGES ON DATABASE tenant_2 TO owner;
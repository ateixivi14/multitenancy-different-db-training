DROP TABLE tenant;

CREATE TABLE tenant
(
    tenant_id VARCHAR(255) NOT NULL,
    username  VARCHAR(255),
    db        VARCHAR(255),
    password  VARCHAR(255),
    url       VARCHAR(255),
    CONSTRAINT pk_tenant PRIMARY KEY (tenant_id)
);

INSERT INTO tenant (tenant_id, username, db, password, url)
VALUES ('tenant_1', 'postgres', 'tenant_1', 'postgres', 'jdbc:postgresql://localhost:5432/tenant_1');

INSERT INTO tenant (tenant_id, username, db, password, url)
VALUES ('tenant_2', 'postgres', 'tenant_2', 'postgres', 'jdbc:postgresql://localhost:5432/tenant_2');
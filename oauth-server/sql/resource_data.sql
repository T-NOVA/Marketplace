INSERT INTO authority (id, authority) VALUES(0,'ROLE_OAUTH_ADMIN');
INSERT INTO authority (id, authority) VALUES(1,'ROLE_ADMIN');
INSERT INTO authority (id, authority) VALUES(2,'ROLE_USER');
INSERT INTO credentials (id, enabled, name, password, version)  VALUES(1,1,'oauth_admin','admin',0);
INSERT INTO credentials (id, enabled, name, password, version) VALUES(2,1,'resource_admin','admin',0);
INSERT INTO credentials (id, enabled, name, password, version) VALUES(3,1,'user','user',0);
INSERT INTO credentials_authorities (credentials_id, authorities_id) VALUES(1,1);
INSERT INTO credentials_authorities (credentials_id, authorities_id) VALUES(2,2);
INSERT INTO credentials_authorities (credentials_id, authorities_id) VALUES(3,3);

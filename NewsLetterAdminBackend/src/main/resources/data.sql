INSERT INTO user_details (user_id, first_name, last_name, user_address, user_state, user_zipcode, user_contact, user_image) values
('kishan.kumar282@gmail.com', 'Kishan', 'Kumar', 'Samashpur', 'Bihar', '851213', '8839045054', ''),
('suraj282@gmail.com', 'Suraj', 'Kumar', 'Darbhanga', 'Bihar', '850012', '9763564789', '');

INSERT INTO user_credentials (user_id, user_password, user_role) values
('kishan.kumar282@gmail.com', '$2a$12$oUdTAa8hi22V5NCOuQY0h.gd/86KSLpXh2Y.O384Yuk8iE7JqkurK', 'ADMIN'),
('suraj282@gmail.com', '$2a$12$AAUI83DwwopVDhAA9wMVf.MFbBS1QGmZk3OXlO.66/q0lIHPs6bzC', 'EDITOR');
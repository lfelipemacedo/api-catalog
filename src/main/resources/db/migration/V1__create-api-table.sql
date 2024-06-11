CREATE TABLE T_APIS (
    id bigint AUTO_INCREMENT,
    name varchar(50),
    description varchar(255),
    created_at timestamp not null,
    updated_at timestamp not null,
    deleted_at timestamp,

    primary key(id)
)
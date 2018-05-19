# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table credential (
  id                            integer auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  constraint uq_credential_username unique (username),
  constraint pk_credential primary key (id)
);

create table customer (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint uq_customer_name unique (name),
  constraint pk_customer primary key (id)
);

create table customer_representative (
  id                            integer auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  customer_class                varchar(255),
  credit_limit                  double not null,
  credit_used                   double not null,
  company_id                    integer,
  constraint pk_customer_representative primary key (id)
);

create table department (
  id                            integer auto_increment not null,
  name                          varchar(255),
  constraint uq_department_name unique (name),
  constraint pk_department primary key (id)
);

create table employee (
  id                            integer auto_increment not null,
  first_name                    varchar(255),
  last_name                     varchar(255),
  credential_id                 integer,
  department_id                 integer,
  constraint uq_employee_credential_id unique (credential_id),
  constraint pk_employee primary key (id)
);

create table employee_customer_representative (
  employee_id                   integer not null,
  customer_representative_id    integer not null,
  constraint pk_employee_customer_representative primary key (employee_id,customer_representative_id)
);

create table order_item (
  id                            integer auto_increment not null,
  product_id                    integer,
  product_order_id              integer,
  quantity                      integer,
  constraint pk_order_item primary key (id)
);

create table product (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  price                         double,
  image_url                     varchar(255),
  product_category_id           integer,
  applications                  mediumtext,
  features                      mediumtext,
  technical_data                mediumtext,
  constraint pk_product primary key (id)
);

create table product_category (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  image_url                     varchar(255),
  is_master                     tinyint(1) default 0 not null,
  parent_category_id            integer,
  constraint pk_product_category primary key (id)
);

create table product_competitor (
  id                            integer auto_increment not null,
  name                          varchar(255),
  description                   varchar(255),
  image_url                     varchar(255),
  technical_data                mediumtext,
  features                      mediumtext,
  related_product_id            integer,
  price                         double,
  constraint pk_product_competitor primary key (id)
);

create table product_order (
  id                            integer auto_increment not null,
  buyer_id                      integer,
  handler_id                    integer,
  total_price                   double,
  date_ordered                  varchar(255),
  date_paid                     varchar(255),
  is_paid                       tinyint(1) default 0,
  constraint pk_product_order primary key (id)
);

alter table customer_representative add constraint fk_customer_representative_company_id foreign key (company_id) references customer (id) on delete restrict on update restrict;
create index ix_customer_representative_company_id on customer_representative (company_id);

alter table employee add constraint fk_employee_credential_id foreign key (credential_id) references credential (id) on delete restrict on update restrict;

alter table employee add constraint fk_employee_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_employee_department_id on employee (department_id);

alter table employee_customer_representative add constraint fk_employee_customer_representative_employee foreign key (employee_id) references employee (id) on delete restrict on update restrict;
create index ix_employee_customer_representative_employee on employee_customer_representative (employee_id);

alter table employee_customer_representative add constraint fk_employee_customer_representative_customer_representative foreign key (customer_representative_id) references customer_representative (id) on delete restrict on update restrict;
create index ix_employee_customer_representative_customer_representative on employee_customer_representative (customer_representative_id);

alter table order_item add constraint fk_order_item_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_order_item_product_id on order_item (product_id);

alter table order_item add constraint fk_order_item_product_order_id foreign key (product_order_id) references product_order (id) on delete restrict on update restrict;
create index ix_order_item_product_order_id on order_item (product_order_id);

alter table product add constraint fk_product_product_category_id foreign key (product_category_id) references product_category (id) on delete restrict on update restrict;
create index ix_product_product_category_id on product (product_category_id);

alter table product_category add constraint fk_product_category_parent_category_id foreign key (parent_category_id) references product_category (id) on delete restrict on update restrict;
create index ix_product_category_parent_category_id on product_category (parent_category_id);

alter table product_competitor add constraint fk_product_competitor_related_product_id foreign key (related_product_id) references product (id) on delete restrict on update restrict;
create index ix_product_competitor_related_product_id on product_competitor (related_product_id);

alter table product_order add constraint fk_product_order_buyer_id foreign key (buyer_id) references customer_representative (id) on delete restrict on update restrict;
create index ix_product_order_buyer_id on product_order (buyer_id);

alter table product_order add constraint fk_product_order_handler_id foreign key (handler_id) references employee (id) on delete restrict on update restrict;
create index ix_product_order_handler_id on product_order (handler_id);


# --- !Downs

alter table customer_representative drop foreign key fk_customer_representative_company_id;
drop index ix_customer_representative_company_id on customer_representative;

alter table employee drop foreign key fk_employee_credential_id;

alter table employee drop foreign key fk_employee_department_id;
drop index ix_employee_department_id on employee;

alter table employee_customer_representative drop foreign key fk_employee_customer_representative_employee;
drop index ix_employee_customer_representative_employee on employee_customer_representative;

alter table employee_customer_representative drop foreign key fk_employee_customer_representative_customer_representative;
drop index ix_employee_customer_representative_customer_representative on employee_customer_representative;

alter table order_item drop foreign key fk_order_item_product_id;
drop index ix_order_item_product_id on order_item;

alter table order_item drop foreign key fk_order_item_product_order_id;
drop index ix_order_item_product_order_id on order_item;

alter table product drop foreign key fk_product_product_category_id;
drop index ix_product_product_category_id on product;

alter table product_category drop foreign key fk_product_category_parent_category_id;
drop index ix_product_category_parent_category_id on product_category;

alter table product_competitor drop foreign key fk_product_competitor_related_product_id;
drop index ix_product_competitor_related_product_id on product_competitor;

alter table product_order drop foreign key fk_product_order_buyer_id;
drop index ix_product_order_buyer_id on product_order;

alter table product_order drop foreign key fk_product_order_handler_id;
drop index ix_product_order_handler_id on product_order;

drop table if exists credential;

drop table if exists customer;

drop table if exists customer_representative;

drop table if exists department;

drop table if exists employee;

drop table if exists employee_customer_representative;

drop table if exists order_item;

drop table if exists product;

drop table if exists product_category;

drop table if exists product_competitor;

drop table if exists product_order;


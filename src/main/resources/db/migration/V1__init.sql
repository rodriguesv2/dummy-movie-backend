-- V1: baseline de schema mínimo para validar Flyway
create table if not exists movies (
  id bigserial primary key,
  title varchar(200) not null,
  year int not null check (year between 1888 and 2100),
  genres varchar(200),
  created_at timestamp not null default now()
);

create unique index if not exists ux_movies_title_year on movies (title, year);

-- seed opcional só para teste visual rápido
insert into movies (title, year, genres)
values
  ('Inception', 2010, 'Sci-Fi, Action'),
  ('The Dark Knight', 2008, 'Action, Crime')
on conflict do nothing;
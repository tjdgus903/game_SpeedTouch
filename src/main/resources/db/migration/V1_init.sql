-- (PostgreSQL 기준, H2에서도 동작하도록 확장 생성은 제외)
create table if not exists users (
  id uuid primary key,
  nickname varchar(24) not null,
  provider varchar(10) not null,
  created_at timestamptz not null
);

create table if not exists sessions (
  id uuid primary key,
  user_id uuid not null references users(id) on delete cascade,
  start_at timestamptz not null,
  end_at timestamptz,
  final_score int,
  device_info text,
  created_at timestamptz not null
);

create table if not exists scores (
  id bigserial primary key,
  user_id uuid not null references users(id) on delete cascade,
  session_id uuid not null references sessions(id) on delete cascade,
  score int not null,
  occurred_at timestamptz not null,
  unique (session_id)
);

create index if not exists idx_scores_score_time
  on scores(score desc, occurred_at desc);

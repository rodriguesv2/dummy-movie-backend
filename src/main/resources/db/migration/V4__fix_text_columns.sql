DO $$
BEGIN
  IF EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = 'public'
      AND table_name = 'movies'
      AND column_name = 'title'
      AND data_type = 'bytea'
  ) THEN
    ALTER TABLE public.movies
      ALTER COLUMN title TYPE varchar(200)
      USING convert_from(title, 'UTF8');
  END IF;
END
$$;

DO $$
BEGIN
  IF EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = 'public'
      AND table_name = 'movies'
      AND column_name = 'genres'
      AND data_type = 'bytea'
  ) THEN
    ALTER TABLE public.movies
      ALTER COLUMN genres TYPE varchar(200)
      USING convert_from(genres, 'UTF8');
  END IF;
END
$$;

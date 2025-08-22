ALTER TABLE public.movies
ADD COLUMN IF NOT EXISTS duration_min integer;

DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint c
    WHERE c.conname = 'chk_movies_duration_min'
      AND c.conrelid = 'public.movies'::regclass
  ) THEN
    ALTER TABLE public.movies
      ADD CONSTRAINT chk_movies_duration_min
      CHECK (duration_min IS NULL OR duration_min > 0);
  END IF;
END
$$;
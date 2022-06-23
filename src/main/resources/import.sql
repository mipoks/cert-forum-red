INSERT INTO public.category (id, created, updated, description, name, parent_id, version, visible) VALUES (1, '2022-05-31 20:00:10.000000', '2022-05-31 20:00:11.000000', 'fdv', 'dfg', null, null, true);
INSERT INTO public.category (id, created, updated, description, name, parent_id, version, visible) VALUES (2, '2022-05-31 20:00:10.000000', '2022-05-31 20:00:11.000000', 'ete', 'ete', null, null, true);
INSERT INTO public.category (id, created, updated, description, name, parent_id, version, visible) VALUES (3, '2022-05-31 20:00:10.000000', '2022-05-31 20:00:11.000000', 'qwe', 'qwe', null, null, true);
INSERT INTO public.category (id, created, updated, description, name, parent_id, version, visible) VALUES (4, '2022-05-31 20:00:10.000000', '2022-05-31 20:00:11.000000', 'qwerty', 'qwerty', null, null, true);
INSERT INTO public.category (id, created, updated, description, name, parent_id, version, visible) VALUES (5, '2022-05-31 20:00:10.000000', '2022-05-31 20:00:11.000000', 'qwerty007', 'qwerty007', null, null, true);

ALTER TABLE category
    ALTER COLUMN id
        RESTART WITH 25;
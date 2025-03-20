INSERT INTO public.roles(name) VALUES('ROLE_USER');
INSERT INTO public.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO public.genders(name) VALUES('Мужской');
INSERT INTO public.genders(name) VALUES('Женский');

INSERT INTO public.blood_types(name) VALUES('O(I)+');
INSERT INTO public.blood_types(name) VALUES('A(II)+');
INSERT INTO public.blood_types(name) VALUES('B(III)+');
INSERT INTO public.blood_types(name) VALUES('AB(IV)+');
INSERT INTO public.blood_types(name) VALUES('O(I)-');
INSERT INTO public.blood_types(name) VALUES('A(II)-');
INSERT INTO public.blood_types(name) VALUES('B(III)-');
INSERT INTO public.blood_types(name) VALUES('AB(IV)-');

INSERT INTO public.statuses (name,points,queues) values ('Новый', 100, 0);
INSERT INTO public.statuses (name,points,queues) values ('Новичок', 100, 1);
INSERT INTO public.statuses (name,points,queues) values ('Спасатель', 200, 2);
INSERT INTO public.statuses (name,points,queues) values ('Герой', 300, 3);
INSERT INTO public.statuses (name,points,queues) values ('Супер герой', 500, 4);
INSERT INTO public.statuses (name,points,queues) values ('Идеальная кровь', 600, 5);
INSERT INTO public.statuses (name,points,queues) values ('Снова в строю', 700, 6);
INSERT INTO public.statuses (name,points,queues) values ('Путешественник', 800, 7);
INSERT INTO public.statuses (name,points,queues) values ('Активный', 1000, 8);

INSERT INTO public.regions (name) values ('Бишкек');
INSERT INTO public.regions (name) values ('Чуй');
INSERT INTO public.regions (name) values ('Ысык-Көл');
INSERT INTO public.regions (name) values ('Нарын');
INSERT INTO public.regions (name) values ('Талас');
INSERT INTO public.regions (name) values ('Жалал-Абад');
INSERT INTO public.regions (name) values ('Ош');
INSERT INTO public.regions (name) values ('Баткен');

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Бишкек, ул.Исанова №2', 'BeDonor', '2023-01-29 19:26:25.000000', 1);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Бишкек, ул.Панфилова №3', 'BeDonor', '2023-01-29 19:26:25.000000', 1);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Бишкек, ул.Токтогула №21', 'BeDonor', '2023-01-29 19:26:25.000000', 1);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Бишкек, ул.Юнусалиева №67', 'BeDonor', '2023-01-29 19:26:25.000000', 1);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Бишкек, ул.Загорская №18', 'BeDonor', '2023-01-29 19:26:25.000000', 1);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Чолпон-Ата, ул.Осмонова №10', 'BeDonor', '2023-01-29 19:26:25.000000', 3);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Каракол, ул.Айтматова №7', 'BeDonor', '2023-01-29 19:26:25.000000', 3);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Каракол, ул.Боконбаева №10', 'BeDonor', '2023-01-29 19:26:25.000000', 3);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Нарын, ул.Советская №19', 'BeDonor', '2023-01-29 19:26:25.000000', 4);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Нарын, ул.Московская №20', 'BeDonor', '2023-01-29 19:26:25.000000', 4);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Нарын, ул.Тоголок Молдо №31', 'BeDonor', '2023-01-29 19:26:25.000000', 4);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Талас, ул.Атабекова №61', 'BeDonor', '2023-01-29 19:26:25.000000', 5);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Талас, ул.Манаса №23/Б', 'BeDonor', '2023-01-29 19:26:25.000000', 5);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Талас, ул.Сартбаева №31', 'BeDonor', '2023-01-29 19:26:25.000000', 5);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Талас, ул.Ленина №45', 'BeDonor', '2023-01-29 19:26:25.000000', 5);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Талас, ул.Московская №43', 'BeDonor', '2023-01-29 19:26:25.000000', 5);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Жалал-Абад, ул.Ленина №30', 'BeDonor', '2023-01-29 19:26:25.000000', 6);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Жалал-Абад, ул.Ленина №59', 'BeDonor', '2023-01-29 19:26:25.000000', 6);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Жалал-Абад, ул.Осмонова №10', 'BeDonor', '2023-01-29 19:26:25.000000', 6);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Жалал-Абад, ул.Сатылганова №27', 'BeDonor', '2023-01-29 19:26:25.000000', 6);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Жалал-Абад, ул.Рыскулова №27', 'BeDonor', '2023-01-29 19:26:25.000000', 6);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Ош, ул.Боконбаева №20', 'BeDonor', '2023-01-29 19:26:25.000000', 7);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Ош, ул.Ондош-Ата №11', 'BeDonor', '2023-01-29 19:26:25.000000', 7);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Ош, ул.Каралаева №34', 'BeDonor', '2023-01-29 19:26:25.000000', 7);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Ош, ул.Сартбаева №15', 'BeDonor', '2023-01-29 19:26:25.000000', 7);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Ош, ул.Жайыл-Баатыр №20', 'BeDonor', '2023-01-29 19:26:25.000000', 7);

INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Баткен, ул.Чортекова №3', 'BeDonor', '2023-01-29 19:26:25.000000', 8);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Баткен, ул.Чортекова №3', 'BeDonor', '2023-01-29 19:26:25.000000', 8);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Кызыл-Кыя, ул.Сталина №9', 'BeDonor', '2023-01-29 19:26:25.000000', 8);
INSERT INTO public.medical_centers (created_at, location, name, updated_at, region_id) VALUES ('2023-01-29 19:25:26.000000', 'г.Кызыл-Кыя, ул.Ленина №20', 'BeDonor', '2023-01-29 19:26:25.000000', 8);
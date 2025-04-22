INSERT INTO public.roles(name) VALUES('ROLE_USER');
INSERT INTO public.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO public.genders(name) VALUES('Мужской');
INSERT INTO public.genders(name) VALUES('Женский');
INSERT INTO public.genders(name) VALUES('Предпочитаю не указывать');

INSERT INTO public.blood_types(name) VALUES('O(I)+');
INSERT INTO public.blood_types(name) VALUES('A(II)+');
INSERT INTO public.blood_types(name) VALUES('B(III)+');
INSERT INTO public.blood_types(name) VALUES('AB(IV)+');
INSERT INTO public.blood_types(name) VALUES('O(I)-');
INSERT INTO public.blood_types(name) VALUES('A(II)-');
INSERT INTO public.blood_types(name) VALUES('B(III)-');
INSERT INTO public.blood_types(name) VALUES('AB(IV)-');

INSERT INTO public.statuses (name,points,queues) values ('Новичок',100,1);
INSERT INTO public.statuses (name,points,queues) values ('Спасатель',200,2);
INSERT INTO public.statuses (name,points,queues) values ('Герой',300,3);
INSERT INTO public.statuses (name,points,queues) values ('Супер герой',500,4);
INSERT INTO public.statuses (name,points,queues) values ('Идеальная кровь',600,5);
INSERT INTO public.statuses (name,points,queues) values ('Снова в строю',700,6);
INSERT INTO public.statuses (name,points,queues) values ('Путешественник',800,7);
INSERT INTO public.statuses (name,points,queues) values ('Активный',1000,8);

INSERT INTO public.regions (name) values ('Бишкек');
INSERT INTO public.regions (name) values ('Чуй');
INSERT INTO public.regions (name) values ('Ысык-Көл');
INSERT INTO public.regions (name) values ('Нарын');
INSERT INTO public.regions (name) values ('Талас');
INSERT INTO public.regions (name) values ('Жала-Абад');
INSERT INTO public.regions (name) values ('Ош');
INSERT INTO public.regions (name) values ('Баткен');
INSERT INTO public.regions (name) values ('Неизвестно');




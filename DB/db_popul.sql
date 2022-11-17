INSERT INTO ROLES(name) VALUES 
('Адміністратор'),
('Повар'),
('Клієнт');

INSERT INTO User_Status(name) VALUES 
('Вільний'),
('Зайнятий'),
('Зупинений'),
('Заблокований');

INSERT INTO Users(first_name,last_name,login, password, email, role_id, status_id) VALUES 
('Олександр','Довженко','admin','$2a$10$nn.vkbmoPlU0HhPNaBwZauWfbvnBtbHtIGUK2szmk3h06f0veB1ma','zuckonit1@gmail.com',1,1),
('Марія','Іванова','cook1','cook1','zuckonit1@gmail.com',2,1),
('Іван','Карпенко','cook2','cook2','zuckonit1@gmail.com',2,1),
('Марина','Степанюк','cook3','cook3','zuckonit1@gmail.com',2,1),
('Юлія','Гапонюк','customer1','customer1','zuckonit1@gmail.com',3,1);

INSERT INTO Pizza(name,description,price,massa,cooking_time,avatar_url) VALUES
('Веганська піца з грибами','Піца на рослинних вершках з грибами в поєднанні з маслинами, оливками і свіжими томатами. Подається зі свіжою руколою, яку пакуєио окремо.',133,400,20,'https://vilki-palki.od.ua/storage/img-cache/500_500_1622465704%D0%92%D0%B5%D0%B3%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%D0%BF%D0%B8%D1%86%D1%86%D0%B0%D1%81%D0%B3%D1%80%D0%B8%D0%B1%D0%B0%D0%BC%D0%B8.png.webp'),
('Парма',' Піца на томатній основі з прошутто і свіжими помідорами чері. Подається з міксом салатів у медово-гірчичному соусі в окремому боксі.',211,610,30,'https://vilki-palki.od.ua/storage/img-cache/500_500_1622465787%D0%9F%D0%B0%D1%80%D0%BC%D0%B0.png.webp'),
('Прошутто',' Піца на фірмовій томатній основі з вяленим мясом "Прошуто" в поєднанні зі свіжими томатами і печерицями, приправлена ​​орегано.',165,690,25,'https://vilki-palki.od.ua/storage/img-cache/500_500_1622465811%D0%9F%D1%80%D0%BE%D1%88%D1%83%D1%82%D1%82%D0%BE.png.webp'),
('Cheese Boom (4 Сири)',' Забагато сиру не буває: моцарела, делікатний дор блю, пармезан і гауда на вершковій основі, приправлені орегано. ',175,560,20,'https://vilki-palki.od.ua/storage/img-cache/500_500_1622465500CheeseBoom(4%D1%81%D1%8B%D1%80%D0%B0).png.webp'),
('Мясний бум','Піца на томатній основі з 4-ма видами мяса, ніжною моцарелою, приправлена ​​орегано - справжній бестселер у нашому онлайн-ресторані. ',177,660,35,'https://vilki-palki.od.ua/storage/img-cache/500_500_1623938311%D0%9C%D1%8F%D1%81%D0%BD%D0%BE%D0%B9%D0%91%D1%83%D0%BC.png.webp'),
('Classic',' Нічого зайвого для любителів класики: піца на томатній основі з шинкою, грибами, сиром і свіжою петрушкою. ',145,600,20,'https://vilki-palki.od.ua/storage/img-cache/500_500_1623942967Classic.png.webp'),
('Кантрі',' Піца на томатній основі, яка припала до смаку багатьом нашим клієнтам, що не дивно, адже вона поєднує 4 види мяса і 2 види сиру, доповнена маринованими огірками та свіжою петрушкою.',177,650,30,'https://vilki-palki.od.ua/storage/img-cache/500_500_1623943103%D0%9A%D0%B0%D0%BD%D1%82%D1%80%D0%B8.png.webp'),
('Курчело-Бекончело',' Піца на вершковій основі, назву якої придумали наші підписники в Інстаграм, стала по-справжньому знаменитою. Поєднує у собі курку су-від, бекон, свіжі томати і цибулю, заправлена ​​соусом карі.',169,650,35,'https://vilki-palki.od.ua/storage/img-cache/500_500_1626954707%D0%9A%D1%83%D1%80%D1%87%D0%B5%D0%BB%D0%BB%D0%BE-%D0%91%D0%B5%D0%BA%D0%BE%D0%BD%D1%87%D0%B5%D0%BB%D0%BB%D0%BE.png.webp');


INSERT INTO Order_Status(name) VALUES 
('Нове'),
('Відкрите'),
('Прийняте'),
('Завершене');

INSERT INTO Pizza_Order_Status(name) VALUES 
('У черзі'),
('Готується'),
('Готова');
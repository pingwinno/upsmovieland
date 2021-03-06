INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Дарлин Эдвардс'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Побег из Шоушенка'),
        'Гениальное кино! Смотришь и думаешь «Так не бывает!», но позже понимаешь, что только так и должно быть. Начинаешь заново осмысливать значение фразы, которую постоянно используешь в своей жизни, «Надежда умирает последней». Ведь если ты не надеешься, то все в твоей жизни гаснет, не остается смысла. Фильм наполнен бесконечным числом правильных афоризмов. Я уверена, что буду пересматривать его сотни раз.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Габриэль Джексон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Побег из Шоушенка'),
        'Кино это является, безусловно, «со знаком качества». Что же до первого места в рейтинге, то, думаю, здесь имело место быть выставление «десяточек» от большинства зрителей вкупе с раздутыми восторженными откликами кинокритиков. Фильм атмосферный. Он драматичный. И, конечно, заслуживает того, чтобы находиться довольно высоко в мировом кинематографе.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Рональд Рейнольдс'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Зеленая миля'),
        'Перестал удивляться тому, что этот фильм занимает сплошь первые места во всевозможных кино рейтингах. Особенно я люблю когда при экранизации литературного произведение из него в силу специфики кинематографа исчезает ирония и появляется некий сверхреализм, обязанный удерживать зрителя у экрана каждую отдельно взятую секунду.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Нил Паркер'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Форрест Гамп'),
        'Много еще можно сказать об этом шедевре. И то, что он учит верить, и то, чтобы никогда не сдаваться… Но самый главный девиз я увидел вот в этой фразе: «Занимайся жизнью, или занимайся смертью».');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Трэвис Райт'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Список Шиндлера'),
        'Очень хороший фильм, необычный сюжет, я бы даже сказала непредсказуемый. Такие фильмы уже стали редкостью.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Джесси Паттерсон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE '1+1'),
        'У меня не найдётся слов, чтобы описать этот фильм. Не хочется быть банальной и говорить какой он отличный, непредсказуемый и т. д., но от этого никуда не деться к сожалению — ведь он ШЕДЕВРАЛЬНЫЙ!');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Амелия Кэннеди'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE '1+1'),
        'Скажу сразу — фильм выглядел многообещающе, даже если не брать в расчет что он находился в топе-250 лучших фильмов. Известные голливудские актеры на главных ролях. Но нет в этом фильме должной атмосферы. Нет такого чувства что вот сейчас случится что-то страшное. Что герои попали в ситуацию из которой не смогут выбраться. В общем, не понравилось.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Габриэль Джексон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Жизнь прекрасна'),
        '«Все должно быть супер! Супер! Су-пер!» И это именно супер, ну слов других не подберешь.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Деннис Крейг'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Бойцовский клуб'),
        'Фильм очень красивый. Не во всем, конечно, но яркие персонажи и костюмы — это уже кое-что.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Габриэль Джексон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Как приручить дракона'),
        'Этот фильм из разряда тех, что могут обеспечить хороший отдых и приподнятое настроение за счёт своей лёгкости, совсем непошлого юмора, умеренной дозы напряжения, динамики нужных скоростей.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Нил Паркер'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Как приручить дракона'),
        'Назначается Киношедевром среди развлекательных фильмов.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Амелия Кэннеди'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Гладиатор'),
        'Данный кинофильм — нестареющая классика мирового кинематографа, который можно пересматривать до бесконечности и, что удивительно, он не может надоесть.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Дэрил Брайант'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Темный рыцарь'),
        'Рекомендую смотреть всем и не обращать внимания на надоевшее уже спасение целого мира одним человеком.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Дэрил Брайант'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Большой куш'),
        'Удивлен. Никто не отозвался плохо? Неужели было создано произведение искусства, которое нравится всем, и которое совершенно? Нет. Может, я один такой? Фильм не вызывает во мне никаких эмоций. Неплохая сказочка. Замечательная наивная атмосфера. Местами есть забавные шутки. И, как мне показалось, этот фильм — своего рода стёб над другими боевиками. При этом превосходящий многие боевики.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Деннис Крейг'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Унесённые призраками'),
        'Необыкновенно позитивный фильм. Его можно пересматривать много раз для поднятия настроения, находя много смешных, незаметных на первый взгляд моментов.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Амелия Кэннеди'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Звёздные войны: Эпизод 4 – Новая надежда'),
        'Легендарный. Культовый. Бессмертный. Три слова. Всего лишь три. А сколько же они выражают неподдельных эмоций и радостных впечатлений по отношению к очередному любимому и уважаемому фильму из минувшего в лету детства? Много. Слишком много. И описать эти сердечные и гарцующие в здравом рассудке чувства обыкновенными строчными предложениями иногда не представляется возможным.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Трэвис Райт'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Большой куш'),
        'Приятного просмотра для всех, кто не видел ещё этого шедевра больше впечатлений для тех, кто пересматривает в надцатый раз. =)');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Нил Паркер'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Большой куш'),
        'Это один из любимых моих фильмов с самого детства. Я видела его столько раз, что знаю практически наизусть. И могу сказать с уверенностью, что посмотрю еще не один раз.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Габриэль Джексон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Молчание ягнят'),
        'Фильм, безусловно, посмотрела уже большая часть населения, которая хоть каким-то образом имеет отношение к кинематографу. Я считаю, что фильм можно пересмотреть еще не один раз.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Айда Дэвис'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Как приручить дракона'),
        'Фильм продуман до мельчайших деталей. Идеальный фильм для улучшения настроения, единственный в своем роде. Обязателен к просмотру!');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Нил Паркер'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Блеф'),
        'Фильм потрясающий, в нем хватает абсолютно всего: и драк, и музыки, и юмора, и любви.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Рональд Рейнольдс'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Гран Торино'),
        'У фильма есть свои мелкие недостатки  и неточности, но многочисленные достоинства в несколько раз перевешивают. Много вдохновляющего креатива!');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Дарлин Эдвардс'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Молчание ягнят'),
        'Хоть и не по возрасту мне заводить скрипучую пластинку с мелодией «Раньше и деревья были выше, и трава зеленее…», а хочется. Выражать свою любовь к настолько близкому произведению крайне сложно.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Айда Дэвис'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Блеф'),
        'Вердикт: прекрасная, нестареющая классика, которая рекомендована мною для всех.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Дэрил Брайант'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Хороший, плохой, злой'),
        'Для воскресного вечернего просмотра подходит по всем критериям.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Нил Паркер'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Укрощение строптивого'),
        'Хороший и по-настоящему интересный фильм, с хорошим сюжетом и неплохим музыкальным сопровождением. Всем советую к просмотру.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Трэвис Райт'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Джанго освобожденный'),
        'Полагаю, этот фильм должен быть в коллекции каждого уважающего себя киномана.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Амелия Кэннеди'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Танцующий с волками'),
        'Нетленный шедевр мирового кинематографа, который можно пересматривать десятки раз и получать все такой — же, извините за выражение, кайф от просмотра. Минусы у фильма, конечно, есть, но черт возьми. Их просто не хочется замечать! Ты настолько поглощен просмотром фильма, что просто не хочется придираться к разным мелочам.');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Айда Дэвис'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Джанго освобожденный'),
        'Фильм только выигрывает от частого просмотра и всегда поднимает мне настроение (наряду с драмой, тут еще и тонкий юмор).');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Джесси Паттерсон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Укрощение строптивого'),
        'Конечно, бесспорно культовый фильм, не реалистичный, наивный, где то глупый, но такой же увлекательный и удивительный, как и много лет назад');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Джесси Паттерсон'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Титаник'),
        'В итоге мы имеем отличный представитель своего жанра, который прошёл проверку временем и до сих пор отлично смотрится, несмотря на классический сюжет');
INSERT INTO REVIEWS (USER_NAME, MOVIE_ID, COMMENT)
VALUES ((SELECT USER_NAME FROM USERS WHERE FULL_NAME = 'Деннис Крейг'),
        (SELECT ID FROM MOVIES WHERE NAME_RUSSIAN LIKE 'Пролетая над гнездом кукушки'),
        'Скажу только одно — как я жалею, что не посмотрела его раньше!');

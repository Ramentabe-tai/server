INSERT INTO Category (category_name) VALUES
                                                      ('食べ物'),
                                                      ('ショッピング'),
                                                      ('服'),
                                                      ('趣味'),
                                                      ('公共料金'),
                                                      ('その他');


INSERT INTO PaymentTransaction (amount, account_id, category_id, member_id, transaction_date, message, transaction_type)
VALUES
    (3000, 1, NULL, 1, '2024-01-01 12:00:00', 'Initial Deposit', 'DEPOSIT'),
    (12000, 1, NULL, 1, '2024-01-05 14:00:00', 'test Deposit', 'DEPOSIT'),
    (2000, 1, 1, 1, '2024-01-07 13:00:00', 'Lunch', 'WITHDRAWAL'),
    (5000, 1, 2, 1, '2024-01-10 10:00:00', 'New Year Shopping', 'WITHDRAWAL'),    -- ショッピング
    (8000, 1, 1, 1, '2024-01-15 15:00:00', 'Groceries', 'WITHDRAWAL'),           -- 食べ物
    (13000, 1, 6, 1, '2024-02-20 20:00:00', 'Car Rent', 'WITHDRAWAL'),            -- No specific category
    (1500, 1, 4, 1, '2024-02-25 18:00:00', 'Utilities', 'WITHDRAWAL'),           -- 趣味
    (7500, 1, 3, 1, '2024-03-05 12:00:00', 'Travel', 'WITHDRAWAL'),              -- 服
    (6750, 1, 1, 1, '2024-03-20 20:00:00', 'Dining Out', 'WITHDRAWAL'),          -- 食べ物
    (9000, 1, 3, 1, '2024-04-01 09:00:00', 'Car Maintenance', 'WITHDRAWAL'),    -- 服
    (10000, 1, 5, 1, '2024-04-15 14:00:00', 'Insurance', 'WITHDRAWAL'),      -- No specific category
    (9500, 1, 4, 1, '2024-05-10 13:00:00', 'Health', 'WITHDRAWAL'),             -- 趣味
    (11500, 1, 2, 1, '2024-05-25 11:00:00', 'Entertainment', 'WITHDRAWAL'),     -- ショッピング
    (6200, 1, 1, 1, '2024-06-05 17:00:00', 'Education', 'WITHDRAWAL'),          -- 食べ物
    (5920, 1, 2, 1, '2024-06-20 21:00:00', 'Miscellaneous', 'WITHDRAWAL'),
    (5000, 1, NULL, 1, '2024-01-15 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (7000, 1, NULL, 1, '2024-01-25 10:00:00', 'Gift', 'DEPOSIT'),
    (8000, 1, NULL, 1, '2024-02-10 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (9500, 1, NULL, 1, '2024-02-25 10:00:00', 'Freelance Payment', 'DEPOSIT'),
    (12000, 1, NULL, 1, '2024-03-10 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (15000, 1, NULL, 1, '2024-03-25 10:00:00', 'Bonus', 'DEPOSIT'),
    (11000, 1, NULL, 1, '2024-04-10 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (13000, 1, NULL, 1, '2024-04-25 10:00:00', 'Project Payment', 'DEPOSIT'),
    (9000, 1, NULL, 1, '2024-05-10 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (10000, 1, NULL, 1, '2024-05-25 10:00:00', 'Refund', 'DEPOSIT'),
    (8000, 1, NULL, 1, '2024-06-10 10:00:00', 'Monthly Savings', 'DEPOSIT'),
    (8500, 1, NULL, 1, '2024-06-25 10:00:00', 'Lottery Win', 'DEPOSIT');

INSERT INTO Mission (mission_title, mission_description, exp_point)
VALUES
    ('水筒を持ってこよう', '水筒に水を入れる。約200円節約！', 100),
    ('冷房の設定温度を1度上げる', '約500円節約！', 200),
    ('晩ご飯はカレーを作りましょう', '3回分の食事が節約！', 300),
    ('買い物はできるだけサンディで済ませる', 'あらゆる物が安いサンディで買い出しを済ませて出費を抑える', 200),
    ('コンビニ食品禁止チャレンジ', '自炊を行える時間が無い時は納豆や豆腐といった手間がかからない料理で済ませる', 300),
    ('自炊チャレンジ', 'できるだけ安いスーパーで食材を買って自炊を行い、食費を抑える', 300);

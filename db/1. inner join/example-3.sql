select pp.name, p.seria, p.number
from people as pp join passport as p on pp.passport_id = p.id;

select pp.name as Имя, p.seria as Серия, p.number as Номер
from people as pp join passport as p on pp.passport_id = p.id;

select pp.name as "Имя владельца", p.seria Серия, p.number Номер
from people pp join passport p on pp.passport_id = p.id;
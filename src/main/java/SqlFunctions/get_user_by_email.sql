create or replace function get_user_by_email(in_email varchar)
returns json
as
$$
declare
	result json;
	userObj public.user;
begin
	select * into userObj
	from public.user u
	where u.email = in_email;

	if userObj.id is not null then
		result := json_build_object('status', 'Success', 'data', array_to_json(array_agg(row_to_json(userObj))));
	else
		result := json_build_object('status', 'Error', 'data', null);
	end if;


	return result;
end;
$$
language plpgsql;
-- select * from public.get_user_by_email('mina@scashcall.com');